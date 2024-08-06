package com.example.superheroes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<RecyclerView>(R.id.listView)

        val viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.getData()
        viewModel.uiState.observe(this) {uiState->
            when(uiState){
                is MyViewModel.UIState.Empty -> Unit
                is MyViewModel.UIState.Result -> {
                    listView.adapter = RecyclerViewAdapter(uiState.superheroes)
                }
                is MyViewModel.UIState.Error -> Toast.makeText(this, uiState.description, Toast.LENGTH_LONG).show()
            }
        listView.layoutManager = LinearLayoutManager(this)
        listView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
        }
    }
}

data class Superhero(val id: Long,
                     val images: Images,
                     val name: String,
                     val biography: Biography,
                     val appearance: Appearance)

data class Appearance(val gender: String, val race: String)

data class Biography(val fullName: String)

data class Images(val sm: String)