package com.footzone.footzone.ui.fragments.bookBottomSheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.footzone.footzone.model.AcceptDeclineRequest
import com.footzone.footzone.model.AllStadiumResponse
import com.footzone.footzone.model.BookingRequest
import com.footzone.footzone.model.Response
import com.footzone.footzone.repository.main.MainRepository
import com.footzone.footzone.utils.UiStateObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class BookDialogViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    private val _bookRequest =
        MutableStateFlow<UiStateObject<Response>>(UiStateObject.EMPTY)
    val bookRequest = _bookRequest

    fun sendBookingRequest(bookingRequest: BookingRequest) =
        viewModelScope.launch {
            _bookRequest.value = UiStateObject.LOADING

            try {
                val response = mainRepository.sendBookingRequest(bookingRequest)
                _bookRequest.value = UiStateObject.SUCCESS(response)

            } catch (e: Exception) {
                _bookRequest.value =
                    UiStateObject.ERROR(e.localizedMessage ?: "No connection", false)
            }
        }
}