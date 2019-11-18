package com.app.pam.restapi

import com.app.copd_hub.pojo.HospitalAddmissionModel
import com.app.pam.pojo.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("user-login")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_token") device_token: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("forgot-password")
    fun forgotPassword(
        @Field("email") email: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("change-email")
    fun changeEmail(
        @Header("Authorization") token: String,
        @Field("email") email: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("change-password")
    fun changePassword(
        @Header("Authorization") token: String,
        @Field("current-password") current_password: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @Headers("accept:application/json")
    @GET("list-contect")
    fun contactList(
        @Header("Authorization") token: String
    ): Call<ContactList>

    @Headers("accept:application/jsoncharset=UTF-8")
    @POST("add-contect")
    fun addContact(
        @Header("Authorization") token: String,
        @Body contact: Contact
    ): Call<ResponseBody>

    @FormUrlEncoded
    @Headers("accept:application/jsoncharset=UTF-8")
    @POST("delete-contect")
    fun deleteContact(
        @Header("Authorization") token: String,
        @Field("id") id: Int
    ): Call<ResponseBody>

    @GET("logout/{id}")
    fun logout(
        @Path("id") id: String
    ): Call<ContactList>

    @FormUrlEncoded
    @Headers("accept:application/jsoncharset=UTF-8")
    @POST("addmission-hospital")
    fun addHospital(
        @Header("Authorization") token: String,
        @Field("add_hospital") add_hospital: String
    ): Call<ContactList>

    @FormUrlEncoded
    @Headers("accept:application/jsoncharset=UTF-8")
    @POST("change-name")
    fun resetUserName(
        @Header("Authorization") token: String,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @Headers("accept:application/jsoncharset=UTF-8")
    @POST("list-appointment")
    fun listAppoinment(
        @Header("Authorization") token: String,
        @Field("month") month: Int,
        @Field("year") year: Int
    ): Call<ListAppoinment>

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("add-appointment")
    fun addAppoinment(
        @Header("Authorization") token: String,
        @Field("appoiment") appoiment: String,
        @Field("date") year: String,
        @Field("time") time: String,
        @Field("who") who: String
    ): Call<ResponseBody>


    @GET("education-home")
    fun getEducationContent(@Header("Authorization") token: String): Call<EducationContent>

    @GET("patient-data-wheeze/{id}")
    fun getWheezes(@Header("Authorization") token: String, @Path("id") userId: Int): Call<WheezesModel_1>

    @GET("patient-data/{id}")
    @Headers("accept:application/json")
    fun getPatientData(
        @Header("Authorization") inToken: String,
        @Path("id") inId: String
    ): Call<MyMedacationPatientDataModel>

    @GET("patient-data-spacer/{id}")
    fun getPatientDataSpacer(
        @Header("Authorization") inToken: String,
        @Path("id") inId: String
    ): Call<PatientDataSpacerModel>

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("feeling-state-store")
    fun saveFeelingState(@Header("Authorization") token: String, @FieldMap fields: HashMap<String, Any>): Call<ResponseBody>

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("remove-appointment")
    fun deleteAppoinment(
        @Header("Authorization") token: String,
        @Field("id") id: Int
    ): Call<ResponseBody>

    @GET("therapy-data")
    fun getTherapy(@Header("Authorization") token: String): Call<MyProfileModel>

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("education-search")
    fun getEducationVideoList(@Header("Authorization") token: String, @Field("name") name: String): Call<Education_List>

    @GET("trigger-list")
    @Headers("accept:application/json")
    fun getTriggerList(@Header("Authorization") inToken: String): Call<Trigger>

    @GET("education-therapy/{id}")
    @Headers("accept:application/json")
    fun getEducationTherapyList(@Header("Authorization") inToken: String, @Path("id") inId: String): Call<EducationTherapyModel>

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("store-result")
    fun storeResult(@Header("Authorization") token: String, @Field("arr") id: String): Call<ResponseBody>

    @GET("education-detail/{id}")
    @Headers("accept:application/json")
    fun getCategoryVideos(
        @Header("Authorization") inToken: String,
        @Path("id") inId: Int
    ): Call<EducationDetails>

    @GET("surgery-list")
    @Headers("accept:application/json")
    fun getRegisterSurgeryList(): Call<RegisterSurgeryList>

    @GET("surgery-history")
    @Headers("accept:application/json")
    fun getsurgeryHistory(@Header("Authorization") inToken: String): Call<GpSurgeryModel>

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("patient-register")
    fun getpatientRegister(
        @Field("txtFirstName") txtFirstName: String,
        @Field("txtLastName") txtLastName: String,
        @Field("txtEmail") txtEmail: String,
        @Field("txtPhoneNumber") txtPhoneNumber: String,
        @Field("txtDOB") txtDOB: String,
        @Field("txtHeight") txtHeight: String,
        @Field("txtWeight") txtWeight: String,
        @Field("surgery_id") surgery_id: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("surgery-change")
    fun surgeryChange(@Header("Authorization") token: String, @Field("surgery_id") id: String): Call<ResponseBody>


    @GET("with-data")
    @Headers("accept:application/json")
    fun getWithData(@Header("Authorization") inToken: String): Call<WithListDataModel>


    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("patient-data-store")
    fun patientDataStore(
        @Header("Authorization") inToken: String,
        @Field("surgery_id") surgery_id: String,
        @Field("patient_id") patient_id: String,
        @Field("visit_date") visit_date: String,
        @Field("reliever_therapy") reliever_therapy: String,
        @Field("reliever_therapy_checked") reliever_therapy_checked: String,
        @Field("reliever_therapy_instruction") reliever_therapy_instruction: String,
        @Field("preventer_therapy") preventer_therapy: String,
        @Field("preventer_therapy_checked") preventer_therapy_checked: String,
        @Field("preventer_therapy_instruction") preventer_therapy_instruction: String,
        @Field("rescue_therapy") rescue_therapy: String,
        @Field("rescue_therapy_checked") rescue_therapy_checked: String,
        @Field("home_rescue_instruction") home_rescue_instruction: String,
        @Field("always_use") always_use: String,
        @Field("spacer") spacer: String,
        @Field("unwell") unwell: String,
        @Field("trrigger") trrigger: String,
        @Field("wheeze") wheeze: String,
        @Field("peakFlow") peakFlow: String,
        @Field("forced") forced: String,
        @Field("fractional") fractional: String,
        @Field("bloodTest") bloodTest: String
    ): Call<ResponseBody>

    @GET("patient-profile/{id}")
    @Headers("accept:application/json")
    fun getMyChildProfile(@Header("authorization") inToken: String, @Path("id") inId: String): Call<MyChildProfile>

    @Headers("accept:application/json")
    @GET("forecasts/v1/daily/5day/{location_key}")
    fun get5dayWeatherData(
        @Path("location_key") location_key: String,
        @Query(value = "apikey", encoded = true) apikey: String,
        @Query(value = "language", encoded = true) language: String,
        @Query(value = "details", encoded = true) details: Boolean,
        @Query(value = "metric", encoded = true) metric: Boolean
    ): Call<Weather>

    @Headers("accept:application/json")
    @GET("/locations/v1/cities/geoposition/search")
    fun getLocationKeyForWeather(
        @Query(
            value = "apikey",
            encoded = true
        ) apikey: String, @Query(value = "q", encoded = true) q: String
    ): Call<ResponseBody>

    @Headers("accept:application/json")
    @GET("currentconditions/v1/{location_key}")
    fun getTodayWeatherData(
        @Path("location_key") location_key: String,
        @Query(
            value = "apikey",
            encoded = true
        ) apikey: String, @Query(value = "details", encoded = true) details: Boolean
    ): Call<ResponseBody>

    @GET("chart-data/{filter}")
    @Headers("accept:application/json")
    fun getGraphData(
        @Header("Authorization") inToken: String,
        @Path("filter") filter: String
    ): Call<GraphPOJO>


    @Headers("accept:application/json")
    @GET("patient-report/{id}")
    fun getMyPlanPDF(
        @Header("Authorization") inToken: String,
        @Path("id") id: String
    ): Call<CommonResponse>


    @GET("addmission-hospital-get")
    @Headers("accept:application/json")
    fun getAddmissionHospital(@Header("Authorization") token: String): Call<HospitalAddmissionModel>


}