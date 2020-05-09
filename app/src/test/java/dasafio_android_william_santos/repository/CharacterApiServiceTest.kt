package dasafio_android_william_santos.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.dasafio_android_william_santos.data.model.Character
import com.example.desafio_android_william_santos.R
import com.example.desafio_android_william_santos.data.MarvelServices
import com.example.desafio_android_william_santos.presentation.characters.CharactersViewModel
import com.example.desafio_android_william_santos.repository.characters.CharactersApiDataSource
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
class CharacterApiServiceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var viewModal: CharactersViewModel
    private var server: MockWebServer =  MockWebServer()
    private lateinit var apiService: MarvelServices
    @Mock
    private lateinit var characterLiveDataObserver: Observer<List<Character>>
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

        viewModal = CharactersViewModel(CharactersApiDataSource(apiService))
    }


    @Test
    fun `when view getCharacter get success the set characterLiveData`(){
        var character = listOf(
            Character(1,"William","test mock","alguma.coisa")
        )

        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody("{data:{results:[{id:1,name:'William',description:'test mock',thumbnail:{ path:'alguma', extension:'coisa'}}]},code:200}"))

        viewModal.characersLiveData.observeForever(characterLiveDataObserver)
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getCharacter()

        TimeUnit.MILLISECONDS.sleep(100);
        verify(characterLiveDataObserver).onChanged(character)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1,null))

    }

    @Test
    fun `when view getCharacter get api erro 401 the set characterLiveData`(){
        server.enqueue(
            MockResponse()
                .setResponseCode(401)
                .setBody("{data:{results:[]},code:200}"))

        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getCharacter()
        TimeUnit.MILLISECONDS.sleep(100);
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_401))

    }

    @Test
    fun `when view getCharacter get api erro 400 the set characterLiveData`(){

        server.enqueue(
            MockResponse()
                .setResponseCode(400)
                .setBody("{data:{results:[]},code:200}"))
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getCharacter()
        TimeUnit.MILLISECONDS.sleep(100);
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_400))

    }

    @Test
    fun `when view getCharacter get erro serve the set characterLiveData`(){
        server.enqueue(
            MockResponse()
                .setResponseCode(500)
                .setBody("{data:{results:[],code:200}"))
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getCharacter()
        TimeUnit.MILLISECONDS.sleep(100);
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_servidor))

    }

    @After
    fun teardown() {
        server.shutdown()
    }
}