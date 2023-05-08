package com.vishal.app.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vishal.app.Util.ApiState
import com.vishal.app.ViewModel.MainViewModel
import com.vishal.app.adapter.JokesAdapter
import com.vishal.app.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var jokeAdapter: JokesAdapter
     private val mainViewModel:MainViewModel by viewModel()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainViewModel.getJokeDataEveryMinut()
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.reverseLayout = true
        mLayoutManager.stackFromEnd = true


        lifecycleScope.launchWhenStarted {
            mainViewModel._postStateFlow.collect {it->
                when(it){
                    is ApiState.Loading->{
                        binding.recyclerview.isVisible=false
                        binding.progressBar.isVisible=true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = false
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success->{
                        binding.recyclerview.isVisible = true
                        binding.progressBar.isVisible = false
                        jokeAdapter = JokesAdapter()
                        binding.recyclerview.adapter = jokeAdapter
                        binding.recyclerview.layoutManager = mLayoutManager
                        jokeAdapter.setItems(it.data)

                    }
                    is ApiState.Empty->{

                    }
                }
            }
        }

    }


}