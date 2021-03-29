package com.dsoft.liverpooldirectory.di

import android.content.Context
import androidx.room.Room
import com.dsoft.liverpooldirectory.data.LFCDatabase
import com.dsoft.liverpooldirectory.api.VKAPIRequest
import com.dsoft.liverpooldirectory.other.Constants.DATABASE_NAME
import com.dsoft.liverpooldirectory.other.Constants.VK_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Singleton
    @Provides
    fun provideVkApi(): VKAPIRequest {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(VK_API_BASE_URL)
            .build()
            .create(VKAPIRequest::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context.applicationContext,
        LFCDatabase::class.java,
        DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideCommentsDao(db: LFCDatabase) = db.commentsDao()

    @Singleton
    @Provides
    fun provideNewsDao(db: LFCDatabase) = db.newsDao()

    @Singleton
    @Provides
    fun provideTableDao(db: LFCDatabase) = db.tableDao()

}