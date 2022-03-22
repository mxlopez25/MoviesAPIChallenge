package com.mxlopez.moviesapichallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mxlopez.moviesapichallenge.databinding.ActivityMainBinding
import com.mxlopez.moviesapichallenge.repository.MovieDbApiRepository
import com.mxlopez.moviesapichallenge.util.Constants
import com.mxlopez.moviesapichallenge.viewmodels.SharedViewModel
import com.mxlopez.moviesapichallenge.viewmodels.SharedViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = MovieDbApiRepository()
        val factory = SharedViewModelFactory(repository)
        sharedViewModel = ViewModelProvider(this, factory).get(SharedViewModel::class.java)

        sharedViewModel.fetchMovies()

        sharedViewModel.movies.observe(this) {
            Constants.movieList = it
            Log.d("MainActivity", Constants.movieList.toString())
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavMain)
        val navController = findNavController(R.id.fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.searchFragment, R.id.favoritesFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNav.setupWithNavController(navController)
        supportActionBar!!.title = "Home"
    }
}