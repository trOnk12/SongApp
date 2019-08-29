package com.example.tooploxsongapp.presentation.songslist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import com.example.tooploxsongapp.R
import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.databinding.ActivityMainBinding
import com.example.tooploxsongapp.presentation.songslist.adapter.SongsListAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject

import android.widget.CheckBox
import android.content.Context
import android.view.Menu

import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.song_item.*


class SongsListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var songsListViewModel: SongsListViewModel
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: SongsListAdapter

    private var fetchLocal = true
    private var fetchRemote = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initRecyclerView()
        initListeners()
    }

    private fun initViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        songsListViewModel = ViewModelProviders.of(this, viewModelFactory)[SongsListViewModel::class.java]
        binding.songListViewModel = songsListViewModel

        songsListViewModel.songItemViewModelList.observe(this, Observer {
            adapter.setSongList(it as ArrayList<SongItemViewModel>)
            adapter.notifyDataSetChanged()
        })

        songsListViewModel.uiState.observe(this, Observer { uiEvent ->
            determineEvent(uiEvent)
        })
    }

    private fun initRecyclerView() {
        adapter = SongsListAdapter()

        songs_list_rv.layoutManager = LinearLayoutManager(this)
        songs_list_rv.adapter = adapter
    }

    private fun initListeners() {
        val textChangeListener = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                songsListViewModel.fetchSongList(fetchLocal, fetchRemote)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        }

        artistNameSearchInput.addTextChangedListener(textChangeListener)
        releaseYearInput.addTextChangedListener(textChangeListener)

        source_dialog.setOnClickListener { p0 ->
            createSourceOptionsDialog(p0.context)
        }
    }

    private fun determineEvent(uiState: SongsListViewModel.UIState?) {
        if (uiState == SongsListViewModel.UIState.HIDE_INFO_SCREEN) infoScreenVisibility(false)
        if (uiState == SongsListViewModel.UIState.SHOW_INFO_SCREEN) infoScreenVisibility(true)
        if (uiState == SongsListViewModel.UIState.LOADING) isLoading(true)
        if (uiState == SongsListViewModel.UIState.NO_LOADING) isLoading(false)
        if (uiState == SongsListViewModel.UIState.NO_SEARCH_RESULTS) showInformation("Ooops... no search results !!")
        if (uiState == SongsListViewModel.UIState.NO_ARTIST_NAME) showInformation("Enter an artist name.")
        if (uiState == SongsListViewModel.UIState.NO_SOURCE) showInformation("Choose source.")
    }

    private fun infoScreenVisibility(visible: Boolean) {
        if (visible) {
            songs_list_rv.visibility = View.GONE
            information_container.visibility = View.VISIBLE
        } else {
            songs_list_rv.visibility = View.VISIBLE
            information_container.visibility = View.GONE
        }
    }

    private fun showInformation(information: String) {
        information_text.text = information
    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            information_text.visibility = View.GONE
            progress_bar.visibility = View.VISIBLE
        } else {
            information_text.visibility = View.VISIBLE
            progress_bar.visibility = View.GONE
        }
    }

    private fun createSourceOptionsDialog(context: Context) {
        val checkBoxView = View.inflate(context, R.layout.source_options, null)

        val localCheckBox = checkBoxView.findViewById<View>(R.id.local_checkbox) as CheckBox
        val remoteCheckBox = checkBoxView.findViewById<View>(R.id.remote_checkbox) as CheckBox

        localCheckBox.isChecked = fetchLocal
        remoteCheckBox.isChecked = fetchRemote

        val builder = AlertDialog.Builder(context)

        localCheckBox.setOnCheckedChangeListener { _, p1 -> fetchLocal = p1 }
        remoteCheckBox.setOnCheckedChangeListener { _, p1 -> fetchRemote = p1 }

        builder.setTitle("The source")
        builder.setMessage("Choose the source")
            .setView(checkBoxView)
            .setCancelable(false)
            .setPositiveButton("Done") { dialog, _ ->
                songsListViewModel.fetchSongList(fetchLocal, fetchRemote)
                dialog.dismiss()
            }.show()
    }

}
