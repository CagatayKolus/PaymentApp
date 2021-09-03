package com.cagataykolus.paymentapp.ui.home

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataykolus.paymentapp.data.repository.ApplicableRepository
import com.cagataykolus.paymentapp.model.Applicable
import com.cagataykolus.paymentapp.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Çağatay Kölüş on 27.08.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val applicableRepository: ApplicableRepository) :
    ViewModel() {
    private val _applicablesLiveData = MutableLiveData<State<List<Applicable>>>()
    val applicablesLiveData: LiveData<State<List<Applicable>>> = _applicablesLiveData

    fun getApplicables() {
        viewModelScope.launch {
            applicableRepository.getAllApplicables()
                .onStart { _applicablesLiveData.value = State.loading() }
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _applicablesLiveData.value = state }
        }
    }

//    fun getApplicables(query: String) {
//        viewModelScope.launch {
//            applicableRepository.deleteAllApplicables()
//                .onStart {}
//                .map {}
//                .collect {}
//        }
//
//        val timer = object : CountDownTimer(1000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {}
//            override fun onFinish() {
//                viewModelScope.launch {
//                    applicableRepository.getAllApplicables(query = query)
//                        .onStart { _applicablesLiveData.value = State.loading() }
//                        .map { resource -> State.fromResource(resource) }
//                        .collect { state -> _applicablesLiveData.value = state }
//                }
//            }
//        }
//        timer.start()
//    }
}