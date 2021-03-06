package com.footzone.footzone.repository.main

import com.footzone.footzone.model.AddStadiumRequest
import com.footzone.footzone.model.FavouriteStadiumRequest
import com.footzone.footzone.model.Location
import com.footzone.footzone.model.*
import com.footzone.footzone.networking.service.ApiService
import okhttp3.MultipartBody
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getUserData(userId: String) = apiService.getUserData(userId)

    suspend fun getUserPlayHistory() = apiService.getUserPlayHistory()

    suspend fun updateUserProfilePhoto(userId: String, file: MultipartBody.Part) =
        apiService.updateUserProfilePhoto(userId, file)

    suspend fun getNearByStadiums(location: Location) =
        apiService.getNearByStadiums(location)

    suspend fun addToFavouriteStadiums(favouriteStadiumRequest: FavouriteStadiumRequest) =
        apiService.addToFavouriteStadiums(favouriteStadiumRequest)

    suspend fun getFavouriteStadiums(userId: String) = apiService.getFavouriteStadiums(userId)

    suspend fun getFavouriteStadiumsList(userId: String) =
        apiService.getFavouriteStadiumsList(userId)

    suspend fun getSingleStadiumData(stadiumId: String) = apiService.getSingleStadiumData(stadiumId)

    suspend fun detectIsNotificationAvailable() = apiService.detectIsNotificationAvailable()

    suspend fun getHolderStadiums(userId: String) = apiService.getHolderStadiums(userId)

    suspend fun getPitchData(stadiumId: String) = apiService.getPitchData(stadiumId)

    suspend fun postHolderStadium(
        stadium: AddStadiumRequest,
        files: ArrayList<MultipartBody.Part>
    ) =
        apiService.postHolderStadium(stadium, files)

    suspend fun getHolderStadium(stadiumId: String) = apiService.getHolderStadium(stadiumId)

    suspend fun getAllStadiums() =
        apiService.getAllStadiums()

    suspend fun getCommentAllByStadiumId(stadiumId: String) =
        apiService.getCommentAllByStadiumId(stadiumId)

    suspend fun getSearchedStadiums(search: String) =
        apiService.getSearchedStadiums(search)

    suspend fun editUser(userId: String, body: EditNameRequest) =
        apiService.editUser(userId, body)

    suspend fun editHolderStadium(
        stadiumId: String,
        stadium: AddStadiumRequest
    ) =
        apiService.editHolderStadium(stadiumId, stadium)


    suspend fun acceptOrDeclineBookingRequest(acceptDeclineRequest: AcceptDeclineRequest) =
        apiService.acceptOrDeclineBookingRequest(acceptDeclineRequest)

    suspend fun getSessionsForSpecificDay(stadiumId: String, date: String) =
        apiService.getSessionsForSpecificDay(stadiumId, date)

    suspend fun sendBookingRequest(bookingRequest: BookingRequest) =
        apiService.sendBookingRequest(bookingRequest)

    suspend fun getSentBookingRequests(status: String) = apiService.getSentBookingRequests(status)

    suspend fun getPlayedHistory(userId: String) = apiService.getPlayedHistory(userId)

    suspend fun getPlayingSoonStadium() = apiService.getPlayingSoonStadium()

    suspend fun deleteStadiumPhoto(stadiumId: String, photoId: String) =
        apiService.deleteStadiumPhoto(stadiumId, photoId)

    suspend fun addPhotoToStadium(stadiumId: String, file: MultipartBody.Part) =
        apiService.addPhotoToStadium(stadiumId, file)
}