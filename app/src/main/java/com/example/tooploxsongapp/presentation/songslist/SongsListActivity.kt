package com.example.tooploxsongapp.presentation.songslist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders
import com.example.tooploxsongapp.R
import kotlinx.android.synthetic.main.activity_main.*

class SongsListActivity : AppCompatActivity() {

    private lateinit var songsListViewModel: SongsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val songListViewModel = ViewModelProviders.of(this).get(SongsListViewModel::class.java)

        artistNameSearchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                songListViewModel.onArtistNameTextChanged(artistNameSearchInput.text.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })

    }

}
