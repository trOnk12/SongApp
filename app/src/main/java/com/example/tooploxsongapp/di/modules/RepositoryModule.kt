package com.example.tooploxsongapp.di.modules

import com.example.tooploxsongapp.data.api.RemoteSongsApi
import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.data.local.LocalSongsReader
import com.example.tooploxsongapp.data.repository.SongRepositoryImpl
import com.example.tooploxsongapp.data.repository.SongsDataStore
import com.example.tooploxsongapp.data.repository.SongsLocalImpl
import com.example.tooploxsongapp.data.repository.SongsRemoteImpl
import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.repository.SongRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideSongRepository(songsLocalImpl: SongsLocalImpl, remoteSongsImpl: SongsRemoteImpl): SongRepository {
        return SongRepositoryImpl(remoteSongsImpl, songsLocalImpl)
    }

    @Provides
    fun provideSongsLocal(localSongsReader: LocalSongsReader): SongsDataStore<LocalSong> {
        return SongsLocalImpl(localSongsReader)
    }

    @Provides
    fun provideSongsRemote(remoteSongsApi: RemoteSongsApi): SongsDataStore<RemoteSong> {
        return SongsRemoteImpl(remoteSongsApi)
    }

}