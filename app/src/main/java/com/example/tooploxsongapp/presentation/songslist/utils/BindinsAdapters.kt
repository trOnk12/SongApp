package com.example.tooploxsongapp.presentation.songslist.utils

import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.presentation.songslist.adapter.SongsListAdapter


@BindingAdapter("localSongsData")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<MenuView.ItemView>) {
    if (recyclerView.adapter is SongsListAdapter) {
        (recyclerView.adapter as SongsListAdapter).setSongList(items as ArrayList<SongItemViewModel>)
    }


@BindingAdapter("infoContainerVisibility")
fun setInfoBoxViibility(linearLayout: LinearLayout,boolean: Boolean ){
    if(boolean){
        linearLayout.visibility = View.VISIBLE
    }
    else{
        linearLayout.visibility = View.GONE
    }
}

    @BindingAdapter("textViewVisibility")
    fun setTextBoxVisibility(textView: TextView,boolean: Boolean ){
        if(boolean){
            textView.visibility = View.VISIBLE
        }
        else{
            textView.visibility = View.GONE
        }
    }

    @BindingAdapter("isLoading")
    fun setProgressBarVisibility(progressBar: ProgressBar,boolean: Boolean ){
        if(boolean){
            progressBar.visibility = View.VISIBLE
        }
        else{
            progressBar.visibility = View.GONE
        }
    }
}

