package com.example.mathapp.di

import com.example.mathapp.data.auth.AuthInterceptor
import com.example.mathapp.data.auth.AuthRepository
import com.example.mathapp.data.auth.AuthSessionRepository
import com.example.mathapp.data.auth.InMemoryAuthSessionRepository
import com.example.mathapp.data.auth.MockAuthRepository
import com.example.mathapp.data.auth.SessionManager
import com.example.mathapp.data.journal.JournalRepository
import com.example.mathapp.data.journal.MockJournalRepository
import com.example.mathapp.data.network.ApiService
import com.example.mathapp.data.notification.MockNotificationRepository
import com.example.mathapp.data.notification.NotificationRepository
import com.example.mathapp.data.profile.ApiProfileRepository
import com.example.mathapp.data.profile.ProfileRepository
import com.example.mathapp.data.timetable.MockTimetableRepository
import com.example.mathapp.data.timetable.TimetableRepository
import com.example.mathapp.domain.auth.AuthenticateUserUseCase
import com.example.mathapp.presentation.common.RootViewModel
import com.example.mathapp.presentation.auth.AuthViewModel
import com.example.mathapp.presentation.journal.JournalViewModel
import com.example.mathapp.presentation.notification.NotificationViewModel
import com.example.mathapp.presentation.profile.ChildProfileViewModel
import com.example.mathapp.presentation.profile.ProfileViewModel
import com.example.mathapp.presentation.timetable.TimetableViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

val appModule = module {

    single<OkHttpClient> {
        // TODO: remove trust-all when server SSL certificate is properly configured
        val trustAllCerts = object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) = Unit
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) = Unit
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        }
        val sslContext = SSLContext.getInstance("TLS").apply {
            init(null, arrayOf(trustAllCerts), SecureRandom())
        }
        val koin = getKoin()
        OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustAllCerts)
            .hostnameVerifier { _, _ -> true } // TODO: remove when SSL is fixed
            .addInterceptor(AuthInterceptor { koin.get<SessionManager>().accessToken })
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    single<ApiService> {
        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .baseUrl("https://xn----btbeiralcm3b5ag4hub.xn--p1ai/")
            .client(get())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ApiService::class.java)
    }

    single<AuthRepository> { MockAuthRepository() }
    single<AuthSessionRepository> { InMemoryAuthSessionRepository() }
    single { AuthenticateUserUseCase(get(), get()) }
    single { SessionManager(get()) }

    single<NotificationRepository> { MockNotificationRepository() }
    single<TimetableRepository> { MockTimetableRepository() }
    single<JournalRepository> { MockJournalRepository() }
    single<ProfileRepository> { ApiProfileRepository(get(), get()) }

    viewModel { RootViewModel(get()) }
    viewModel { AuthViewModel(get()) }
    viewModel { TimetableViewModel(get()) }
    viewModel { JournalViewModel(get()) }
    viewModel { NotificationViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { ChildProfileViewModel(get()) }
}
