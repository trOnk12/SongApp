package com.example.tooploxsongapp.presentation.songslist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import com.example.tooploxsongapp.R
import com.example.tooploxsongapp.databinding.ActivityMainBinding
import javax.inject.Inject

class SongsListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var songsListViewModel: SongsListViewModel
    private lateinit var binding: ActivityMainBinding

    private var fetchLocal = false
    private var fetchRemote = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        songsListViewModel.refreshSongList(fetchLocal, fetchRemote)

        return super.onOptionsItemSelected(item)
    }

    private fun initViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        songsListViewModel = ViewModelProviders.of(this,viewModelFactory)[SongsListViewModel::class.java]
        binding.songListViewModel = songsListViewModel
    }

    private fun initArtistNameSearchInputListener() {
        artistNameSearchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                songsListViewModel.onArtistNameTextChanged(fetchLocal, fetchRemote)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

}
