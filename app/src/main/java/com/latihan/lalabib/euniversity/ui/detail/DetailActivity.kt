package com.latihan.lalabib.euniversity.ui.detail

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.lalabib.euniversity.R
import com.latihan.lalabib.euniversity.data.local.Mahasiswa
import com.latihan.lalabib.euniversity.databinding.ActivityDetailBinding
import com.latihan.lalabib.euniversity.utils.ViewModelFactory
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var mahasiswaAdapter: MahasiswaAdapter
    private var mahasiswaList = listOf<Mahasiswa>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupData()
        search()
    }

    private fun setupView() {
        supportActionBar?.apply {
            title = getString(R.string.detail_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
    }

    private fun setupData() {
        val matkulId = intent.getIntExtra(extra_id, 0)
        mahasiswaAdapter = MahasiswaAdapter()

        detailViewModel.setId(matkulId)
        detailViewModel.matkul.observe(this) { matkul ->
            binding.apply {
                tvMatkul.text = matkul.nama
                tvDosen.text = matkul.dosen.nama
                tvNid.text = matkul.dosen.nid

                mahasiswaList = matkul.mahasiswa
                mahasiswaAdapter.submitList(matkul.mahasiswa)
                rvMahasiswa.layoutManager = LinearLayoutManager(this@DetailActivity)
                rvMahasiswa.adapter = mahasiswaAdapter
            }
        }
    }

    private fun search() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val search = binding.searchView
        search.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }

        })
    }

    private fun filter(e: String) {
        val filteredItem = ArrayList<Mahasiswa>()
        for (item in mahasiswaList) {
            if (item.nama.lowercase(Locale.getDefault()).contains(e.lowercase(Locale.getDefault())) ||
                item.nim.lowercase(Locale.getDefault()).contains(e.lowercase(Locale.getDefault()))
            ) {
                filteredItem.add(item)
            }
        }

        if (e.isEmpty()) {
            mahasiswaAdapter.submitList(filteredItem)
            binding.apply {
                ivIcon.visibility = View.VISIBLE
                tvMatkul.visibility = View.VISIBLE
                tvDosenTitle.visibility = View.VISIBLE
                tvDosen.visibility = View.VISIBLE
                tvNidTitle.visibility = View.VISIBLE
                tvNid.visibility = View.VISIBLE
                return
            }
        }

        mahasiswaAdapter.submitList(filteredItem)
        binding.apply {
            ivIcon.visibility = View.GONE
            tvMatkul.visibility = View.GONE
            tvDosenTitle.visibility = View.GONE
            tvDosen.visibility = View.GONE
            tvNidTitle.visibility = View.GONE
            tvNid.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val extra_id = "id"
    }
}