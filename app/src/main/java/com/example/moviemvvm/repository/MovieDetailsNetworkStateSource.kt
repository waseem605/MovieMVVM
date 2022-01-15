package com.example.moviemvvm.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviemvvm.api.TheMovieInterface
import com.example.moviemvvm.vlo.MainMovieModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsNetworkStateSource(private val apiServices:TheMovieInterface,private val compositeDisposable: CompositeDisposable) {
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState:LiveData<NetworkState>
    get() = _networkState

    private val _downloadDetails= MutableLiveData<MainMovieModel>()
    val downloadMovieResponse:LiveData<MainMovieModel>
    get() = _downloadDetails

    fun fetchMovieDetails(movieId:Int){
        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiServices.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        _downloadDetails.postValue(it)
                    },{
                        _networkState.postValue(NetworkState.ERROR)
                        Log.d("TAG", "fetchMovieDetails: "+it.message)

                    }
                    )
            )

        }catch (e:Exception){

        }

    }

}