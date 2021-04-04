package com.a.androidmovieapp.ui.search

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.a.androidmovieapp.*
import com.a.androidmovieapp.data.common.MoviesResult
import com.a.androidmovieapp.data.common.onError
import com.a.androidmovieapp.data.common.onLoading
import com.a.androidmovieapp.data.common.onSuccess
import com.a.androidmovieapp.databinding.ActivitySearchCharactersBinding
import com.a.androidmovieapp.di.viewmodel.DaggerViewModelFactory
import com.a.androidmovieapp.domain.models.CharacterModel
import com.a.androidmovieapp.ui.details.DetailsCharactersActivity
import com.a.androidmovieapp.utils.CHARACTER_EXTRA
import com.a.androidmovieapp.utils.ConnectionLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

class SearchCharactersActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory
    private val viewModel by lazy { viewModelProvider(viewModelFactory) as SearchCharactersViewModel}

    private lateinit var binding: ActivitySearchCharactersBinding
    private val linearLayoutManager by lazy { LinearLayoutManager(this)}
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MoviesApplication.appComponent.inject(this)
        checkInternetAvailability()
        setEditTextListener()


    }

    private fun checkInternetAvailability() {
        ConnectionLiveData(this).observe(this){
            if(!it) toast(getString(R.string.network_connection_error))
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    @SuppressLint("ClickableViewAccessibility")
    private fun setEditTextListener(){
        lifecycleScope.launch{
            binding.etSearch.getTextChangeStateFlow()
                .debounce(300)
                .filter{ query->
                    if (query.isEmpty())
                    {
                        runOnUiThread {setError(null)}
                        return@filter false
                    }else{
                        return@filter true
                    }
                }.distinctUntilChanged()
                .flatMapLatest { query->
                    viewModel.searchCharacters(query)
                }
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    processResult(result)
                }
        }
    }

    private fun processResult(result: MoviesResult<List<CharacterModel>>) {
        result.onSuccess { list ->
            setListAdapter(list)
        }.onError { error ->
            when (error.messageResource) {
                is Int -> setError(getString(error.messageResource))
                is ResponseBody? -> setError(error.messageResource?.string())
            }
        }.onLoading {
            binding.groupError.hide()
            binding.progressCircular.show()
        }
    }

    private fun setListAdapter(list: List<CharacterModel>) {
        binding.progressCircular.hide()
        if(list.isEmpty()){
            binding.groupError.show()
            binding.rvCharacters.hide()
        }else{
            binding.rvCharacters.show()
            binding.groupError.hide()
            binding.rvCharacters.show()
            if(!::charactersAdapter.isInitialized){
                with(binding.rvCharacters){
                    layoutManager = linearLayoutManager
                    charactersAdapter = CharactersAdapter { setOnCharacterClicked(it) }
                    adapter = charactersAdapter
                    charactersAdapter.submitList(list)
                }
            }else{
                charactersAdapter.submitList(list)
            }
        }
    }

    private fun setOnCharacterClicked(character: CharacterModel) {
        Intent(this, DetailsCharactersActivity::class.java).apply {
            putExtra(CHARACTER_EXTRA, character)
            startActivity(this)
        }
    }

    private fun setError(error: String?) {
        binding.rvCharacters.hide()
        binding.progressCircular.hide()
        binding.groupError.show()
        binding.tvError.text = error
    }
}