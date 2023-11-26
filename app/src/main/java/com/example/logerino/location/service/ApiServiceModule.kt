package service.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.ApiService

/*Retrofit er en HTTP klient for android.
* Hjelper med å koble seg til et eksternt API*/
object ApiServiceModule {
    private const val BASE_URL = "https://kassal.app/api/v1/"

    fun createApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }
}
