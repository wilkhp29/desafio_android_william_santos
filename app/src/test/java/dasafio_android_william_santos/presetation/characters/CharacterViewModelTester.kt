package dasafio_android_william_santos.presetation.characters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.dasafio_android_william_santos.data.model.Character
import com.example.desafio_android_william_santos.R
import com.example.desafio_android_william_santos.data.results.CharacterResult
import com.example.desafio_android_william_santos.presentation.characters.CharactersViewModel
import com.example.desafio_android_william_santos.repository.characters.CharactersReposiroty
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CharacterViewModelTester {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var viewModal:CharactersViewModel

    @Mock
    private lateinit var characterLiveDataObserver: Observer<List<Character>>
    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int,Int?>>

    @Test
    fun `when view getCharacter get success the set characterLiveData`(){
        var character = listOf(
            Character(1,"William","test mock","alguma coisa")
        )

        val resultSucess = MockRepository(CharacterResult.Sucess(character))
        viewModal = CharactersViewModel(resultSucess)
        viewModal.characersLiveData.observeForever(characterLiveDataObserver)
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getCharacter()

        verify(characterLiveDataObserver).onChanged(character)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1,null))

    }

    @Test
    fun `when view getCharacter get api erro 401 the set characterLiveData`(){
        val resultSucess = MockRepository(CharacterResult.ApiError(401))
        viewModal = CharactersViewModel(resultSucess)
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getCharacter()

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_401))

    }

    @Test
    fun `when view getCharacter get api erro 400 the set characterLiveData`(){
        val resultSucess = MockRepository(CharacterResult.ApiError(400))
        viewModal = CharactersViewModel(resultSucess)
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getCharacter()

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_400))

    }

    @Test
    fun `when view getCharacter get erro serve the set characterLiveData`(){
        val resultSucess = MockRepository(CharacterResult.ServerError)
        viewModal = CharactersViewModel(resultSucess)
        viewModal.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        viewModal.getCharacter()

        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.error_servidor))

    }
}

class MockRepository(private val result: CharacterResult) : CharactersReposiroty{
    override fun getCharacters(
        offset: Int,
        limit: Int,
        charactersResultCallback: (result: CharacterResult) -> Unit
    ) {
       charactersResultCallback(result)
    }

}