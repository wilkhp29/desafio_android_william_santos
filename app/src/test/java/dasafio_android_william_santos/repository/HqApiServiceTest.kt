package dasafio_android_william_santos.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.desafio_android_william_santos.R
import com.example.desafio_android_william_santos.data.MarvelServices
import com.example.desafio_android_william_santos.data.model.Hq
import com.example.desafio_android_william_santos.presentation.Hq.HQViewModel
import com.example.desafio_android_william_santos.repository.hq.HqApiDataSource
import com.nhaarman.mockitokotlin2.verify
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


@RunWith(MockitoJUnitRunner::class)
class HqApiServiceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var viewModal: HQViewModel
    private var server: MockWebServer =  MockWebServer()
    private lateinit var apiService: MarvelServices
    @Mock
    private lateinit var hqLiveDataObserver: Observer<Hq>
    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int,Int?>>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        server.start()

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val moshi:Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())

            .build()

        val baseUrl = server.url("/")

        apiService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
            .create(MarvelServices::class.java)

        viewModal = HQViewModel(HqApiDataSource(apiService))
    }


    @Test
    fun `when view getHq get success the set hqLiveData`(){
        var hq = Hq("alguma.coisa","William",10.0,"test mock")
        

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("{data:{results:[{title:'William',description:'test mock',prices:[{price:10}],thumbnail:{ path:'alguma', extension:'coisa'}}]},code:200}"))

        viewModal.hqLiveData.observeForever(hqLiveDataObserver)
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getHqHighestValue(1)

        TimeUnit.MILLISECONDS.sleep(100);
        verify(hqLiveDataObserver).onChanged(hq)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1,null))

    }

    @Test
    fun `when view getHq get api erro 401 the set hqLiveData`(){
        server.enqueue(
            MockResponse()
                .setResponseCode(401)
              )

        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getHqHighestValue(1)
        TimeUnit.MILLISECONDS.sleep(100);
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_401))

    }

    @Test
    fun `when view getHq get api erro 400 the set hqLiveData`(){

        server.enqueue(
            MockResponse()
                .setResponseCode(400)
              )
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getHqHighestValue(1)
        TimeUnit.MILLISECONDS.sleep(100);
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_400))

    }

    @Test
    fun `when view getHq get erro serve the set hqLiveData`(){
        server.enqueue(
            MockResponse()
                .setResponseCode(500)
              )
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getHqHighestValue(1)
        TimeUnit.MILLISECONDS.sleep(100);
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_400))

    }

    @After
    fun teardown() {
        server.shutdown()
    }
}
