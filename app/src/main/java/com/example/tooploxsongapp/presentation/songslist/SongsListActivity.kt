package com.example.tooploxsongapp.presentation.songslist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders
import com.example.tooploxsongapp.R
import kotlinx.android.synthetic.main.activity_main.*

class SongsListActivity : AppCompatActivity() {

    private lateinit var songsListViewModel: SongsListViewModel

    private var fetchLocal = false
    private var fetchRemote = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
    }

    private fun initialize() {
        initToolBar()
        initViewModel()
        initArtistNameSearchInputListener()
    }

    private fun initToolBar() {
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_songs, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.local -> fetchLocal = !fetchLocal
            R.id.remote -> fetchRemote = !fetchRemote
        }

        songsListViewModel.refreshSongList(fetchLocal,fetchRemote)

        return super.onOptionsItemSelected(item)
    }

    private fun initViewModel() {
        songsListViewModel = ViewModelProviders.of(this).get(SongsListViewModel::class.java)
    }

    private fun initArtistNameSearchInputListener() {
        artistNameSearchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                    songsListViewModel.onArtistNameTextChanged(fetchLocal,fetchRemote)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

}
