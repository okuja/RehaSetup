package com.okujajoshua.reha.network.reha


import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.okujajoshua.reha.R
import com.okujajoshua.reha.RehaApplication
import com.okujajoshua.reha.network.reha.card.CardActivationBody
import com.okujajoshua.reha.network.reha.card.CreateCardIdResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.*
import javax.net.ssl.*
import kotlin.collections.ArrayList


private const val BASE_URL =
    "https://sandbox.api.visa.com/"

interface RehaApiService {

    @POST("dcas/cardservices/v1/cards")
    suspend fun createCardId(@Body body: CardActivationBody): CreateCardIdResponse


    //here we shall implement the calls to the reha api
//    @POST("/card/{cardId}/cardactivation")
//    fun cardActivation(@Path("cardId") cardId: String, @Body user: User?): Call<User?>?

}


object RehaApi {

    fun createApi(application: Application): RehaApiService {
        val okHttpClient = getSSLClient(application.applicationContext)

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val rehaApiService = retrofit.create(RehaApiService::class.java)
        return rehaApiService

    }

}

@Throws(
    NoSuchAlgorithmException::class,
    KeyStoreException::class,
    KeyManagementException::class,
    CertificateException::class,
    IOException::class
)
fun getSSLClient(context: Context): OkHttpClient? {
    val client: OkHttpClient
    val sslSocketFactory: SSLSocketFactory
    val trustManagers: Array<TrustManager>
    val trustManager: X509TrustManager


    var keyStore = readKeyStore(context)

    //Initialize trust manager factory
    val trustManagerFactory: TrustManagerFactory =
        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
    trustManagerFactory.init(keyStore)
    trustManagers = trustManagerFactory.trustManagers
    check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
        "Unexpected default trust managers:" + Arrays.toString(
            trustManagers
        )
    }
    trustManager = trustManagers[0] as X509TrustManager



    //Initialize key manager factory
    val keyManagerAlgorithm = KeyManagerFactory.getDefaultAlgorithm()
    val keyManagerFactory = KeyManagerFactory.getInstance(keyManagerAlgorithm)
    keyManagerFactory.init(keyStore,"Bumblebee".toCharArray())

    val sslContext: SSLContext = SSLContext.getInstance("TLS")
    sslContext.init(keyManagerFactory.keyManagers, trustManagers, null)
    sslSocketFactory = sslContext.socketFactory

    client = OkHttpClient.Builder()
        .sslSocketFactory(sslSocketFactory, trustManager)
        .addInterceptor(SupportInterceptor())
        .addInterceptor(
            AuthenticationInterceptor(
                "6RJFA882DAP8EVRXX0LF21SDimfoRH_QTNLI3vXZ6Hv4eLkKY",
                "A5V626fGhA64Yr6EC1n8iMGJOH"
            )
        )
        .build()

    return client
}

/**
 * Get keys store. Key file should be encrypted with pkcs12 standard. It    can be done with standalone encrypting java applications like "keytool". File password is also required.
 *
 * @param context Activity or some other context.
 * @return Keys store.
 * @throws KeyStoreException
 * @throws CertificateException
 * @throws NoSuchAlgorithmException
 * @throws IOException
 */
@Throws(
    KeyStoreException::class,
    CertificateException::class,
    NoSuchAlgorithmException::class,
    IOException::class
)

private fun readKeyStore(context: Context): KeyStore? {

    val privateKeyClientCertificateBundleInputStream: InputStream =
        BufferedInputStream(context.resources.openRawResource(R.raw.rehacertkeybundle1))

    val keyStore: KeyStore = KeyStore.getInstance("pkcs12")

    try {
        keyStore.load(privateKeyClientCertificateBundleInputStream,"Bumblebee".toCharArray())
    } catch (e: Exception) {
        var x = e
        e.printStackTrace()
    } finally {
        if (privateKeyClientCertificateBundleInputStream != null) {
            privateKeyClientCertificateBundleInputStream.close()
        }
    }

    return keyStore
}


class SupportInterceptor : Interceptor {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
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











