package kz.afr.efreight.network.client

import android.content.Context
import android.text.TextUtils
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.paperdb.Paper
import kz.afr.efreight.App
import kz.afr.efreight.R
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


object ApiClient {
    private var retrofit: Retrofit? = null
    private val REQUEST_TIMEOUT = 10
    private var okHttpClient: OkHttpClient? = null
    private val base_url = App.instance.getString(R.string.base_url)

    const val USERNAME_PREFS = "username"
    const val PASSWORD_PREFS = "password"

    fun getClient(context: Context): Retrofit {

        if (okHttpClient == null)
            initOkHttp(context)

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient!!)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    private fun initOkHttp(context: Context) {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(interceptor)

        val username: String = Paper.book().read(USERNAME_PREFS)
        val password: String = Paper.book().read(PASSWORD_PREFS)

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("HTTP_X_ASCCPE_USERNAME", username)
                .addHeader("HTTP_X_ASCCPE_PASSWORD", password)

            // Adding Authorization token (API Key)
            // Requests will be denied without API key
            if (!TextUtils.isEmpty(PrefUtils.getApiKey(context))) {
                PrefUtils.getApiKey(context)?.let {
                    requestBuilder.addHeader("Authorization", it)
                }
            }

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        okHttpClient = httpClient.build()
    }
}