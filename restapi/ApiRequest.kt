package com.app.pam.restapi

import android.accounts.NetworkErrorException
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatDialog
import com.app.pam.R
import com.app.pam.custom.Toast
import com.app.pam.custom.Utils.dismissDialog
import com.app.pam.custom.getProgressDialog
import com.app.pam.views.activity.auth.LoginActivity
import com.app.pam.views.activity.common.ActivityBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.text.ParseException
import java.util.concurrent.TimeoutException

@SuppressLint("ParcelCreator")
class ApiRequest<T>(
    private val activity: Activity,
    objectType: T,
    private val TYPE: Int,
    private val isShowProgressDialog: Boolean,
    private val apiResponseInterface: ApiResponseInterface
) : Callback<T>, Parcelable {

    private val TAG = javaClass.simpleName
    private var mProgressDialog: AppCompatDialog? = null
    private var retryCount = 0
    private var call: Call<T>? = null

    init {
        showProgress()
        call = objectType as Call<T>
        call!!.enqueue(this)
    }

    private fun showProgress() {
        if (isShowProgressDialog) {
            if (mProgressDialog == null)
                mProgressDialog = getProgressDialog(ActivityBase.globalActivity)
        }
    }

    private fun dismissProgress() {
        if (isShowProgressDialog) {
            dismissDialog(activity, mProgressDialog!!)
        }
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        dismissProgress()
        Log.e("REQUEST", "REQUEST_URL = " + call.request().url().toString())
        Log.e("REQUEST", "RESPONSE_CODE = " + response.code().toString())

        if (response.isSuccessful) {
            Log.e("REQUEST", "RESPONSE = " + response.body())
            apiResponseInterface.getApiResponse(ApiResponseManager(response.body(), TYPE))
        } else {
            Toast(response.message(), false, activity)

            try{
                val error = ErrorUtils.parseError(response)

                Toast(error.message(), false, activity)

                if (error.status() == 401) {
                    val intent = Intent(activity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    activity.startActivity(intent)
                    activity.finishAffinity()
                }

            } catch (e: Exception) {
                Log.e("REQUEST", "RESPONSE EXCEPTION ${e.printStackTrace()}")
            }
        }
    }

    override fun onFailure(call: Call<T>, error: Throwable) {

        Log.e("REQUEST", "REQUEST_URL = " + call.request().url().toString())
        Log.e("REQUEST", "ERROR" + error.printStackTrace())

        if (retryCount++ < TOTAL_RETRIES) {
            Log.e("REQUEST", "RETRYING $retryCount OUT OF $TOTAL_RETRIES)")
            retry()
            return
        }
        dismissProgress()
        when (error) {
            is NetworkErrorException -> Toast(
                activity.resources.getString(R.string.toast_time_out),
                false,
                activity
            )
            is TimeoutException -> Toast(
                activity.resources.getString(R.string.toast_time_out),
                false,
                activity
            )
            is SocketTimeoutException -> Toast(
                activity.resources.getString(R.string.toast_try_after_sometimes),
                false,
                activity
            )
            is ParseException -> Toast(
                activity.resources.getString(R.string.toast_something_wrong),
                false,
                activity
            )
        }
    }

    private fun retry() {
        call!!.clone().enqueue(this)
    }

    companion object {
        private val TAG = "ApiRequest"
        private val TOTAL_RETRIES = 5
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(TYPE)
        parcel.writeByte(if (isShowProgressDialog) 1 else 0)
        parcel.writeInt(retryCount)
    }

    override fun describeContents(): Int {
        return 0
    }
}