package com.a.androidmovieapp.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.a.androidmovieapp.*
import com.a.androidmovieapp.data.common.DataSourceException
import com.a.androidmovieapp.data.common.onError
import com.a.androidmovieapp.data.common.onLoading
import com.a.androidmovieapp.data.common.onSuccess
import com.a.androidmovieapp.databinding.ActivityDetailsCharactersBinding
import com.a.androidmovieapp.domain.models.CharacterModel
import com.a.androidmovieapp.utils.CHARACTER_EXTRA
import com.a.androidmovieapp.utils.UNDEFINED
import okhttp3.ResponseBody
import javax.inject.Inject

class DetailsCharactersActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { viewModelProvider(viewModelFactory) as DetailsCharactersViewModel }

    private lateinit var binding: ActivityDetailsCharactersBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MoviesApplication.appComponent.inject(this)
        initObservers()
        initViews()
    }

    private fun initObservers() {
        viewModel.resultMovie.observe(this) {
            it.onSuccess { movies ->
                binding.progressCircular.hide()
                binding.cardMovies.show()
                with(binding.rvMovies) {
                    layoutManager = LinearLayoutManager(this@DetailsCharactersActivity)
                    adapter = MoviesAdapter(movies)
                }

            }.onError { error->
                showError(error)
                binding.progressCircular.show()

            }.onLoading {
                binding.progressCircular.show()
            }
        }

        viewModel.resultPlanet.observe(this) {
            it.onSuccess { planet->
                binding.cardPopulation.show()
                binding.tvDetailsPopulation.text =
                    getString(
                        R.string.population_value,
                        getExtraCharacter()?.name,
                        planet.population
                    )
            }
                .onError { error ->
                    showError(error)
                    binding.progressCircular.show()
                }
                .onLoading {
                    binding.progressCircular.show()
                }
        }

        viewModel.resultSpecie.observe(this){
            it.onSuccess {  species ->
                binding.progressCircular.hide()
                binding.cardSpecies.show()
                with(binding.rvSpecies){
                    layoutManager = LinearLayoutManager(this@DetailsCharactersActivity)
                    adapter = SpeciesAdapter(species)
                }

            }
                .onError { error->
                    showError(error)
                    binding.progressCircular.show()

                }
                .onLoading {
                    binding.progressCircular.show()
                }
        }
    }

    private fun showError(error: DataSourceException) {
        when(error.messageResource){
            is Int -> toast(getString(error.messageResource))
            is ResponseBody? -> toast(error.messageResource!!.string())

        }
    }

    private fun initViews() {
        getExtraCharacter()?.apply {
            with(binding) {
                tvDetailsNameValue.text = name
                tvDetailsBirthYear.text = birthYear
                if (height.hasValue()) {
                    tvDetailsHeight.text =
                        getString(R.string.height_in_cm_and_feet, height, height.convertCmToFeet())
                } else {
                    tvDetailsHeight.text = UNDEFINED
                }
            }
            getPlanet(getPlanetUrl())
            getMovies(this)
            getSpecies(this)
        }
    }

    private fun getSpecies(character: CharacterModel) {
        if (character.species.isNotEmpty()) {
            viewModel.getSpecies(character.getSpeciesUrl())
        }
    }

    private fun getMovies(character: CharacterModel) {
        if (character.films.isNotEmpty()) {
            viewModel.getMovies(character.getMoviesUrl())
        }

    }

    private fun getPlanet(planetUrl: String) {
        if (planetUrl.hasValue()) viewModel.getPlanet((planetUrl))
    }


    private fun getExtraCharacter() =
        intent?.extras?.getParcelable(CHARACTER_EXTRA) as CharacterModel?


}