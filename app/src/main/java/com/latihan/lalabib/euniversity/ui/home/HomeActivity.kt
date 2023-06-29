package com.latihan.lalabib.euniversity.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.lalabib.euniversity.R
import com.latihan.lalabib.euniversity.databinding.ActivityHomeBinding
import com.latihan.lalabib.euniversity.ui.detail.DetailActivity
import com.latihan.lalabib.euniversity.ui.detail.DetailActivity.Companion.extra_id
import com.latihan.lalabib.euniversity.utils.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupData()
    }

    private fun setupView() {
        supportActionBar?.title = getString(R.string.home_title)
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    private fun setupData() {
        val matkulAdapter = MatakuliahAdapter {
            val intent = Intent(this@HomeActivity, DetailActivity::class.java)
            intent.putExtra(extra_id, it.id)
            startActivity(intent)
        }

        homeViewModel.getMatakuliah().observe(this) {
            matkulAdapter.submitList(it)
        }

        //setup recyclerview to adapter and layout manager to handle matakuliah data
        binding.apply {
            rvMatkul.layoutManager = LinearLayoutManager(this@HomeActivity)
            rvMatkul.adapter = matkulAdapter
        }
    }
}