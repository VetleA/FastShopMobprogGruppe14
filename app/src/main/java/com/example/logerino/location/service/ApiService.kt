package service


import model.PhysicalStore
import model.ProduktInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/*@GET utfører en HTTP GET-forespørsel med bruk av retrofit.
* @Query er en retrofit annotasjon som legger ved forspørselparameter
* @Header legger til HTTP-header i forspørselen. Håndterer autentisering */
interface ApiService {
    @GET("products")
    suspend fun searchProducts(
        @Query("search") searchQuery: String,
        @Header("Authorization") bearerToken: String
    ): Response<ProduktInfo>

    @GET("physical-stores")
    suspend fun getPhysicalStores(
        @Query("lat") latitude: Float,
        @Query("lng") longitude: Float,
        @Query("size") size: String,
        @Header("Authorization") bearerToken: String
    ): Response<PhysicalStore>
}