package com.example.tooploxsongapp.presentation.songslist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tooploxsongapp.data.repository.SongRepositoryImpl
import com.example.tooploxsongapp.domain.model.Song
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import io.reactivex.disposables.CompositeDisposable

class SongsListViewModel(val songsRepositoryImpl: SongRepositoryImpl) : BaseViewModel() {

    private val autoCompletePublishSubject = PublishRelay.create<String>()

    var localSongs: MutableLiveData<List<Song>> = MutableLiveData()
    var remoteSongs: MutableLiveData<List<Song>> = MutableLiveData()

    fun onArtistNameTextChanged(newArtistName: String) {

        disposables.add(autoCompletePublishSubject
            .debounce(250, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { songsRepositoryImpl.getSongByArtist(newArtistName).toObservable() }
            .subscribeOn(Schedulers.io())
            .subscribe({ results ->
                showResults(results)
            }, { t: Throwable? -> Log.d("TEST", "error" + t.toString()) })
        )

    }

    private fun showResults(results: List<Song>?) {
        remoteSongs.value = results
    }

}