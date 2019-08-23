package com.example.tooploxsongapp.presentation.songslist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tooploxsongapp.data.entities.CombinedSongs
import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.usecases.GetCombinedSongsUseCase
import com.example.tooploxsongapp.domain.usecases.GetLocalSongsUseCase
import com.example.tooploxsongapp.domain.usecases.GetRemoteSongsUseCase
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SongsListViewModel
@Inject constructor(
    private val getCombinedSongsUseCase: GetCombinedSongsUseCase,
    private val getLocalSongsUseCase: GetLocalSongsUseCase,
    private val getRemoteSongsUseCase: GetRemoteSongsUseCase
) : BaseViewModel() {

    private val autoCompletePublishSubject = PublishRelay.create<String>()

    private val artistName: String = ""
    private val releaseYear: String = ""

    var localSongs: MutableLiveData<List<LocalSong>> = MutableLiveData()
    var remoteSongs: MutableLiveData<List<RemoteSong>> = MutableLiveData()

    fun onArtistNameTextChanged(fetchLocal: Boolean, fetchRemote: Boolean) {
        determineFetchSource(fetchLocal, fetchRemote)
    }

    fun refreshSongList(fetchLocal: Boolean, fetchRemote: Boolean) {
        determineFetchSource(fetchLocal, fetchRemote)
    }

    private fun determineFetchSource(fetchLocal: Boolean, fetchRemote: Boolean) {

        if (!fetchLocal && !fetchRemote) {
            nothingToFetch()
            return
        }

        if (fetchLocal && fetchRemote) {
            fetchCombinedSongs()
            return
        }

        if (fetchLocal && !fetchRemote) {
            fetchLocalSongs()
            return
        }

        if (!fetchLocal && fetchRemote) {
            fetchRemoteSongs()
            return
        }
    }

    private fun nothingToFetch() {
        Log.d("TEST", "NOTHING TO FETCH")
    }

    private fun fetchCombinedSongs() {
        disposables.add(autoCompletePublishSubject
            .debounce(250, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { getCombinedSongsUseCase.getSongs(artistName, releaseYear).toObservable() }
            .subscribeOn(Schedulers.io())
            .subscribe({ results ->
                showResultsCombined(results)
            }, { t: Throwable? -> Log.d("TEST", "error" + t.toString()) })
        )
    }

    private fun showResultsCombined(results: CombinedSongs) {
        localSongs.value = results.localSong
        remoteSongs.value = results.remoteSong
    }

    private fun fetchLocalSongs() {
        disposables.add(autoCompletePublishSubject
            .debounce(250, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { getLocalSongsUseCase.getLocalSongs(artistName, releaseYear).toObservable() }
            .subscribeOn(Schedulers.io())
            .subscribe({ results ->
                showLocalResults(results)
            }, { t: Throwable? -> Log.d("TEST", "error" + t.toString()) })
        )
    }

    private fun showLocalResults(results: List<LocalSong>?) {
        localSongs.value = results
        remoteSongs.value = null
    }

    private fun fetchRemoteSongs() {
        disposables.add(autoCompletePublishSubject
            .debounce(250, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { getRemoteSongsUseCase.getRemoteSongs(artistName, releaseYear).toObservable() }
            .subscribeOn(Schedulers.io())
            .subscribe({ results ->
                showRemoteResults(results)
            }, { t: Throwable? -> Log.d("TEST", "error" + t.toString()) })
        )
    }

    private fun showRemoteResults(results: List<RemoteSong>?) {
        remoteSongs.value = results
        localSongs.value = null
    }

}