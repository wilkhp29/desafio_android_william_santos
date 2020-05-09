package dasafio_android_william_santos.data.presetation.hq

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.desafio_android_william_santos.R
import com.example.desafio_android_william_santos.data.model.Hq
import com.example.desafio_android_william_santos.data.results.HqResult
import com.example.desafio_android_william_santos.presentation.Hq.HQViewModel
import com.example.desafio_android_william_santos.repository.hq.HqReposiroty
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HqViewModalTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var viewModal: HQViewModel

    @Mock
    private lateinit var hqLiveDataObserver: Observer<Hq>
    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int,Int?>>

    @Test
    fun `when view getCharacter get success the set hqLiveData`(){
        var hq = Hq("","William",3000.0,"alguma coisa")
        val resultSucess = MockRepository(HqResult.Sucess(hq))
        viewModal = HQViewModel(resultSucess)
        viewModal.hqLiveData.observeForever(hqLiveDataObserver)
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getHqHighestValue(1)

        verify(hqLiveDataObserver).onChanged(hq)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1,null))

    }

    @Test
    fun `when view getCharacter get api erro 401 the set hqLiveData`(){
        val resultSucess = MockRepository(HqResult.ApiError(401))
        viewModal =HQViewModel(resultSucess)
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getHqHighestValue(1)

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_401))

    }

    @Test
    fun `when view getCharacter get api erro 400 the set hqLiveData`(){
        val resultSucess = MockRepository(HqResult.ApiError(400))
        viewModal = HQViewModel(resultSucess)
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getHqHighestValue(1)

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_400))

    }

    @Test
    fun `when view getCharacter get erro serve the set hqLiveData`(){
        val resultSucess = MockRepository(HqResult.ServerError)
        viewModal = HQViewModel(resultSucess)
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getHqHighestValue(1)

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_servidor))

    }
}

class MockRepository(private val result: HqResult) : HqReposiroty{
    override fun getHq(hqId: Int, HqResultCallback: (result: HqResult) -> Unit) {
        HqResultCallback(result)
    }


}