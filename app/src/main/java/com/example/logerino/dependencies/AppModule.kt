package com.example.logerino.dependencies

import com.example.logerino.repository.AuthRepository
import com.example.logerino.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
// @InstallIn forteller oss at Appmodule som singleton lever så lenge som applikasjonen
@InstallIn(SingletonComponent::class)
object AppModule {

    //Gir oss en instance av Firebase authentication
    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth):AuthRepository{
        return AuthRepositoryImpl(firebaseAuth)
    }
}