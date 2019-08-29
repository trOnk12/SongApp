package com.example.tooploxsongapp.presentation.songslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.databinding.SongItemBinding
import kotlinx.android.synthetic.main.song_item.view.*

class SongsListAdapter : RecyclerView.Adapter<SongsListAdapter.SongHolder>() {

    private var songsList: ArrayList<SongItemViewModel> = ArrayList()

    fun clearLocalSongsData() {
        songsList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SongItemBinding.inflate(inflater)
        return SongHolder(binding)

    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bind(songsList[position])
    }

    override fun getItemCount(): Int {
        return songsList.size
    }

    fun setSongList(songItemViewModelList: ArrayList<SongItemViewModel>) {
        this.songsList = songItemViewModelList
    }

    class SongHolder(private val binding: SongItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SongItemViewModel) {
            binding.item = item
            binding.executePendingBindings()
        }
    }
}