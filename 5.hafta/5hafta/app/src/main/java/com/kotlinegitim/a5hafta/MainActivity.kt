package com.kotlinegitim.a5hafta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kotlinegitim.a5hafta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var movieList: ArrayList<Movie>
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)

        addData()
    }

    private fun addData(){
        val movie = Movie(1, "Barbie", "Barbie, Mattelin aynı adlı oyun serisine dayanan 21 Temmuz 2023 çıkışlı bir Amerikan romantik komedi filmi.", "Greta Gerwig", "barbie" )
        val movie2 = Movie(2, "Oppenheimer", "Oppenheimer, Christopher Nolan tarafından yazılan ve yönetilen 2023 yapımı bir epik biyografik gerilim filmidir", "Christopher Nolan", "oppenheimer" )
        val movie3 = Movie(3, "Prometheus", "Bir uzay aracı dünyaya gelir, insansı bir yaratık elinde bulunan sıvıyı içerek parçalanmaya başlar ve suya karışır.", "Ridley Scott", "prometheus" )

        movieList = ArrayList<Movie>()
        movieList.add(movie)
        movieList.add(movie2)
        movieList.add(movie3)

        movieAdapter = MovieAdapter(this, movieList)
        binding.rv.adapter = movieAdapter
    }

}