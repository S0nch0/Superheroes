package com.example.superheroes

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

val disposable = CompositeDisposable()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<RecyclerView>(R.id.listView)
        val request = ApiClient.client.create(ApiInterface::class.java)
            .getSuperheroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({

                listView.adapter  = RecyclerViewAdapter(it)

                

            },{ error ->
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            })
        disposable.add(request)
        listView.layoutManager = LinearLayoutManager(this)
        listView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL))
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}



data class HeroesResponse(val data: Data)

data class Data (val heroes:List<Superhero>)

data class Superhero(val id: Long,
                     val images: Images,
                     val name: String,
                     val biography: Biography,
                     val appearance: Appearance)

data class Appearance(val gender: String, val race: String)

data class Biography(val fullName: String)

data class Images(val sm: String)