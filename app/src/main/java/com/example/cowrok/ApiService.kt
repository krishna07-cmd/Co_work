package com.example.cowrok

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
interface ApiService {
    //login
    @POST("digitalflake/api/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    //register
    @POST("digitalflake/api/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    //confirm booking
    @FormUrlEncoded
    @POST("digitalflake/api/confirm_booking")
    fun confirmBooking(
        @Field("date") date: String,
        @Field("slot_id") slotId: Int,
        @Field("workspace_id") workspaceId: Int,
        @Field("type") type: Int
    ): Call<Any>
}



data class LoginRequest(val email: String, val password: String)

data class LoginResponse(val success: Boolean, val message: String)






data class RegisterRequest(
    val fullName: String,
    val mobileNumber: String,
    val email: String
)
data class RegisterResponse(
    val success: Boolean,
    val message: String
)
