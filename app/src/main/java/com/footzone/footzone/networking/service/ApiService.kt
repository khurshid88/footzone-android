package com.footzone.footzone.networking.service

import com.footzone.footzone.model.*
import com.footzone.footzone.model.profile.UserData
import com.footzone.footzone.model.sessionsday.SessionsDayResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    @GET("sms/send/forRegister/{phoneNumber}")
    suspend fun singUp(@Path("phoneNumber") phoneNumber: String): SignInCodeResponse

    @GET("sms/send/forLogin/{phoneNumber}")
    suspend fun singIn(@Path("phoneNumber") phoneNumber: String): SignInCodeResponse

    @POST("sms/validate/forRegister")
    suspend fun checkValidation(@Body smsVerification: SmsVerification): SmsVerificationResponse

    @POST("auth/login")
    suspend fun signInVerification(@Body signInVerification: SignInVerification): LogInResponse

    @POST("auth/register")
    suspend fun registerUser(@Body user: User): RegisterResponse

    @POST("stadium/viewNearStadiums")
    suspend fun getNearByStadiums(@Body location: Location): ShortStadiumDetailResponse

    @GET("favorites/{userId}")
    suspend fun getFavouriteStadiums(@Path("userId") userId: String): ShortStadiumDetailResponse

    @POST("favorites")
    suspend fun addToFavouriteStadiums(@Body favouriteStadiumRequest: FavouriteStadiumRequest): Response

    @GET("favorites/list/{userId}")
    suspend fun getFavouriteStadiumsList(@Path("userId") userId: String): FavouriteStadiumResponse

    @GET("stadium/all/brief/{stadiumId}")
    suspend fun getSingleStadiumData(@Path("stadiumId") stadiumId: String): SingleStadiumResponse

    @GET("user/{userId}")
    suspend fun getUserData(@Path("userId") userId: String): UserData

    @GET("stadium/history")
    suspend fun getUserPlayHistory(): ShortStadiumDetailResponse

    @Multipart
    @POST("user/changeProfilePicture/{userId}")
    suspend fun updateUserProfilePhoto(
        @Path("userId") userId: String,
        @Part file: MultipartBody.Part,
    ): Response


    //not yet fully connected
    @GET("stadium/{stadiumId}")
    suspend fun getPitchData(@Path("stadiumId") stadiumId: String): FullStadiumDetailResponse

    @GET("stadium/holder/{userId}")
    suspend fun getHolderStadiums(@Path("userId") userId: String): ShortStadiumDetailResponse

    //the stadium owner adds the stadium
    @Multipart
    @POST("stadium")
    suspend fun postHolderStadium(
        @Part("stadium") stadium: AddStadiumRequest,
        @Part files: List<MultipartBody.Part>,
    ): Unit

    @GET("stadium/{stadiumId}")
    suspend fun getHolderStadium(@Path("stadiumId") stadiumId: String): FullStadiumDetailResponse

    @GET("stadium/all")
    suspend fun getAllStadiums(): AllStadiumResponse

    @GET("stadium/search?")
    suspend fun getSearchedStadiums(
        @Query("search") search: String,
    ): ShortStadiumDetailResponse

    @PUT("stadium/edit/content/{stadiumId}")
    suspend fun editHolderStadium(
        @Path("stadiumId") stadiumId: String,
        @Body stadium: AddStadiumRequest,
    ): Response

    @PUT("user/edit/{userId}")
    suspend fun editUser(
        @Path("userId") userId: String,
        @Body body: EditNameRequest,
    ): Response

    @GET("comment/{stadiumId}")
    suspend fun getCommentAllByStadiumId(@Path("stadiumId") stadiumId: String): CommentsData

    //user booking pitch
    @POST("session")
    suspend fun sendBookingRequest(@Body bookingRequest: BookingRequest): Response

    @PUT("session/{sessionId}")
    suspend fun editSession(@Path("sessionId") sessionId: String)

    //stadium owner response to request
    @POST("session/acceptOrDecline")
    suspend fun acceptOrDeclineBookingRequest(@Body acceptDeclineRequest: AcceptDeclineRequest): Response

    //requests sent to stadium owner PENDING PLAYED NOTIFICATIONS
    @GET("session/requests/{status}")
    suspend fun getSentBookingRequests(@Path("status") status: String): StadiumBookSentResponse

    @GET("session/day/{stadiumId}/{date}")
    suspend fun getSessionsForSpecificDay(
        @Path("stadiumId") stadiumId: String,
        @Path("date") date: String,
    ): SessionsDayResponse

    @GET("session/history/{userId}")
    suspend fun getPlayedHistory(@Path("userId") userId: String): PlayedHistoryResponse

    @GET("session/playing/soon")
    suspend fun getPlayingSoonStadium(): PlayingSoonHistoryResponse

    @DELETE("stadium/edit/photo/delete/{stadiumId}/{photoId}")
    suspend fun deleteStadiumPhoto(
        @Path("stadiumId") stadiumId: String,
        @Path("photoId") photoId: String,
    ): Response

    @Multipart
    @POST("stadium/edit/photo/add/{stadiumId}")
    suspend fun addPhotoToStadium(
        @Path("stadiumId") stadiumId: String,
        @Part file: MultipartBody.Part,
    ): Response

    @GET("notification")
    suspend fun detectIsNotificationAvailable(): NotificationAvailabilityResponse
}