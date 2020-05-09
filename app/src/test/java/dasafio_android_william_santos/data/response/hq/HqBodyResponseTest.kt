package dasafio_android_william_santos.data.response.hq

import com.example.desafio_android_william_santos.data.MarvelServices
import com.example.desafio_android_william_santos.data.response.hq.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
class HqBodyResponseTest {

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

        val hqAction = apiService.getComics(1,"1","1","1")
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

        val hqAction = apiService.getComics(1,"1","1","1")
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

        val hqAction = apiService.getComics(1,"1","1","1")
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

        val hqAction = apiService.getComics(1,"1","1","1")
        val status = hqAction.execute().code()

        Assert.assertEquals( hqAction.isExecuted,true)
        Assert.assertEquals( status,500)
    }



    @After
    fun teardown() {
        server?.shutdown()
    }

    @Test
    fun testInstance(){
        val thumbnail =  ThumbnailResponse("photo","png")
        val prices = listOf(
            PriceResponse(100),
            PriceResponse(1000),
            PriceResponse(99999)
        )
        val hqResultResponse = listOf(
            HqResultResponse(
                "teste",
                "coverage 100%",
                thumbnail,
                prices
            )
        )

        val hqdata =   HqDataResponse(hqResultResponse)

        val hqBodyResponse = HqBodyResponse(
            hqdata,
            200
        )

        Assert.assertEquals(hqBodyResponse.data,hqdata)
        Assert.assertEquals(hqBodyResponse.code,200)
    }
}