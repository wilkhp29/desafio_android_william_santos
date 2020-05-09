package dasafio_android_william_santos.data.response.character

import com.example.dasafio_android_william_santos.data.model.Character
import com.example.desafio_android_william_santos.data.MarvelServices
import com.example.desafio_android_william_santos.data.response.characters.CharacterDataResponse
import com.example.desafio_android_william_santos.data.response.characters.CharacterResultResponse
import com.example.desafio_android_william_santos.data.response.characters.CharactersBodyResponse
import com.example.desafio_android_william_santos.data.response.characters.ThumbnailResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.assertEquals
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(MockitoJUnitRunner::class)
class ResponsesTests {

    private var server: MockWebServer? = null
    private lateinit var apiService: MarvelServices
    @Before
    @Throws(Exception::class)
    fun setUp() {
        server = MockWebServer()
        server!!.start()

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)


        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        apiService = Retrofit.Builder()
            .baseUrl(server!!.url("/"))
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
            .create(MarvelServices::class.java)
    }



    @Test
    fun `test api response sucess`(){
        server!!.enqueue(
            MockResponse()
            .setResponseCode(200)
            .setBody("{data:{results:[]},code:200}"))

        val hqAction = apiService.getCharacters(1,1,"1","1","1")
        val status = hqAction.execute().code()

        Assert.assertEquals( hqAction.isExecuted,true)
        Assert.assertEquals( status,200)
    }


    @Test
    fun `test api response 400`(){
        server!!.enqueue(
            MockResponse()
            .setResponseCode(400)
            .setBody("{data:{results:[]},code:200}"))

        val hqAction = apiService.getCharacters(1,1,"1","1","1")
        val status = hqAction.execute().code()

        Assert.assertEquals( hqAction.isExecuted,true)
        Assert.assertEquals( status,400)
    }


    @Test
    fun `test api response 401`(){
        server!!.enqueue(
            MockResponse()
            .setResponseCode(401)
            .setBody("{data:{results:[]},code:200}"))

        val hqAction = apiService.getCharacters(1,1,"1","1","1")
        val status = hqAction.execute().code()

        Assert.assertEquals( hqAction.isExecuted,true)
        Assert.assertEquals( status,401)
    }


    @Test
    fun `test api response 500`(){
        server!!.enqueue(
            MockResponse()
            .setResponseCode(500)
            .setBody("{data:{results:[]},code:200}"))

        val hqAction = apiService.getCharacters(1,1,"1","1","1")
        val status = hqAction.execute().code()

        Assert.assertEquals( hqAction.isExecuted,true)
        Assert.assertEquals( status,500)
    }



    @After
    fun teardown() {
        server?.shutdown()
    }

    @Test
    fun `test Body response instance`(){
        val thumbnail = ThumbnailResponse("img","png")
        val result = CharacterResultResponse(1,"william","coverage",thumbnail)
        val list = listOf(result)
        val data = CharacterDataResponse(list)
        val body = CharactersBodyResponse(data,200,null)

        assertEquals(body.code,200)
        assertEquals(body.data,data)
    }

    @Test
    fun `test data response instance`(){
        val thumbnail = ThumbnailResponse("img","png")
        val result = CharacterResultResponse(1,"william","coverage",thumbnail)
        val list = listOf(result)
        val data = CharacterDataResponse(list)

        assertEquals(data.characterList,list)
    }

    @Test
    fun `test result response instance`(){
        val thumbnail = ThumbnailResponse("img","png")
        val result = CharacterResultResponse(1,"william","coverage",thumbnail)
        val model = Character(1,"william","coverage","img.png")

        assertEquals(result.id,1)
        assertEquals(result.thumbnail,thumbnail)
        assertEquals(result.description,"coverage")
        assertEquals(result.name,"william")
        assertEquals(result.getCharacterModel(),model)

        assertEquals(model.id,1)
        assertEquals(model.img,"img.png")
        assertEquals(model.description,"coverage")
        assertEquals(model.name,"william")
    }

    @Test
    fun `test thumbnail response instance`(){
        val thumbnail = ThumbnailResponse("img","png")

        assertEquals(thumbnail.extension,"png")
        assertEquals(thumbnail.path,"img")
        assertEquals(thumbnail.getPhoto(),"img.png")

    }
}