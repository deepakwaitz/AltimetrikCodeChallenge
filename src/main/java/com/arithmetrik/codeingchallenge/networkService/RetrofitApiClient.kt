
import com.arithmetrik.codeingchallenge.BuildConfig
import com.bacardi.metypesdk.service.TLSSocketFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApiClient {
    companion object {
        private var retrofit: Retrofit? = null
        /**
         * interceptor and builder is used to print the log
         */
        private var httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        var builder: OkHttpClient.Builder = OkHttpClient().newBuilder()
            .sslSocketFactory(TLSSocketFactory())
            .hostnameVerifier { hostname, session -> true }
            .connectTimeout(30, TimeUnit.SECONDS)

        fun getRetrofitApiClient(baseUrl: String?): Retrofit {
            if (baseUrl != null) {
                //logs for debug builds
                if (BuildConfig.DEBUG) {
                    builder.addInterceptor(
                        httpLoggingInterceptor
                    )
                }
                retrofit = Retrofit
                    .Builder()
                    .baseUrl(baseUrl)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }

            return retrofit as Retrofit
        }
    }
}