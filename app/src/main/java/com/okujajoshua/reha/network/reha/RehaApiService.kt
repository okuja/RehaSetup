package com.okujajoshua.reha.network.reha

import com.okujajoshua.reha.domain.User
import com.okujajoshua.reha.network.reha.card.CardActivationBody
import com.okujajoshua.reha.network.reha.card.CreateCardIdResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate

private const val BASE_URL =
    "https://sandbox.api.visa.com"

interface RehaApiService {

    @POST("/dcas/cardservices/v1/cards")
    suspend fun createCardId(@Body body: CardActivationBody): CreateCardIdResponse


    //here we shall implement the calls to the reha api
//    @POST("/card/{cardId}/cardactivation")
//    fun cardActivation(@Path("cardId") cardId: String, @Body user: User?): Call<User?>?

}

//val cf: CertificateFactory = CertificateFactory.getInstance("X.509")
//val caInput: InputStream = BufferedInputStream(FileInputStream("cert.pem"))
//val ca: X509Certificate = caInput.use {
//    cf.generateCertificate(it) as X509Certificate
//}
//
//val peerCertificate = CertificatePinner.pin(ca)


val certificatePinner = CertificatePinner
    .Builder()
    .add("sandbox.api.visa.com", "sha256/OAR3GMlRJTTXus2cwgBASDRg6xwxzO/WSZ/+6vnFhJA=")
    .add("sandbox.api.visa.com", "sha256/zUIraRNo+4JoAYA7ROeWjARtIoN4rIEbCpfCRQT6N6A=")
    .add("sandbox.api.visa.com", "sha256/r/mIkG3eEpVdm+u/ko/cwxzOMo1bk4TyHIlByibiA5E=")
    .build()


val okHttpClient = OkHttpClient().newBuilder()
    .certificatePinner(certificatePinner)
    .addInterceptor(SupportInterceptor())
    .addInterceptor(
        AuthenticationInterceptor(
            "6RJFA882DAP8EVRXX0LF21SDimfoRH_QTNLI3vXZ6Hv4eLkKY",
            "A5V626fGhA64Yr6EC1n8iMGJOH"
        )
    )
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object RehaApi {
    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val rehaApiService = retrofit.create(RehaApiService::class.java)
}


class SupportInterceptor : Interceptor {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(request)
    }
}

class AuthenticationInterceptor(user: String, password: String) : Interceptor {

    private val credentials: String = Credentials.basic(user, password)

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .header("Authorization", credentials).build()
        return chain.proceed(authenticatedRequest)
    }
}