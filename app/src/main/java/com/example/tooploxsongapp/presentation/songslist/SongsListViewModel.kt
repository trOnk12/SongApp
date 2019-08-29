package com.example.tooploxsongapp.presentation.songslist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.domain.usecases.GetCombinedSongsUseCase
import com.example.tooploxsongapp.domain.usecases.GetLocalSongsUseCase
import com.example.tooploxsongapp.domain.usecases.GetRemoteSongsUseCase
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SongsListViewModel
@Inject constructor(
    private val getCombinedSongsUseCase: GetCombinedSongsUseCase,
    private val getLocalSongsUseCase: GetLocalSongsUseCase,
    private val getRemoteSongsUseCase: GetRemoteSongsUseCase
) : BaseViewModel() {

    enum class UIState {
        LOADING,
        NO_LOADING,
        NO_SEARCH_RESULTS,
        NO_ARTIST_NAME,
        NO_SOURCE,
        SHOW_INFO_SCREEN,
        HIDE_INFO_SCREEN
    }

    var songItemViewModelList: MutableLiveData<List<SongItemViewModel>> = MutableLiveData()
    val artistName: MutableLiveData<String> = MutableLiveData()
    val releaseYear: MutableLiveData<String> = MutableLiveData()
    val uiState: MutableLiveData<UIState> = MutableLiveData()

    fun fetchSongList(fetchLocal: Boolean, fetchRemote: Boolean) {
        if (artistName.value != null && artistName.value != "") {

            uiState.value = UIState.SHOW_INFO_SCREEN
            uiState.value = UIState.LOADING

            determineFetchSource(fetchLocal, fetchRemote)
        } else {
            if (artistName.value == null || artistName.value == "") {
                uiState.value = UIState.SHOW_INFO_SCREEN
                uiState.value = UIState.NO_ARTIST_NAME
            }
        }
    }

    private fun determineFetchSource(fetchLocal: Boolean, fetchRemote: Boolean) {
        if (!fetchLocal && !fetchRemote) {
            uiState.value = UIState.NO_SOURCE
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

    private fun fetchCombinedSongs() {
        disposables.add(getCombinedSongsUseCase.getSongs(artistName.value!!, releaseYear.value)
            .debounce(250, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ results ->
                showResults(results)
            }, { t: Throwable? -> Log.d("TEST", "error" + t.toString()) })
        )
    }

    private fun fetchLocalSongs() {
        disposables.add(
            getLocalSongsUseCase.getLocalSongs(artistName.value!!, releaseYear.value)
                .debounce(250, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ results ->
                    showResults(results)
                }, { t: Throwable? -> Log.d("TEST", "error" + t.toString()) })
        )
    }

    private fun fetchRemoteSongs() {
        disposables.add(getRemoteSongsUseCase.getRemoteSongs(artistName.value!!, releaseYear.value)
            .debounce(250, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ results ->
                showResults(results)
            }, { t: Throwable? -> Log.d("TEST", "error" + t.toString()) })
        )
    }

    private fun showResults(results: MutableList<SongItemViewModel>?) {
        uiState.value = UIState.NO_LOADING

        if (results == null || results.size == 0) {
            uiState.value = UIState.NO_SEARCH_RESULTS
        } else {
            uiState.value = UIState.HIDE_INFO_SCREEN
        }

        songItemViewModelList.value = results
    }

}