package com.latihan.lalabib.euniversity.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan.lalabib.euniversity.R
import com.latihan.lalabib.euniversity.databinding.ActivityDetailBinding
import com.latihan.lalabib.euniversity.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupData()
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
        val mahasiswaAdapter = MahasiswaAdapter()

        detailViewModel.setId(matkulId)
        detailViewModel.matkul.observe(this) { matkul ->
            binding.apply {
                tvMatkul.text = matkul.nama
                tvDosen.text = matkul.dosen.nama
                tvNid.text = matkul.dosen.nid

                mahasiswaAdapter.submitList(matkul.mahasiswa)
                rvMahasiswa.layoutManager = LinearLayoutManager(this@DetailActivity)
                rvMahasiswa.adapter = mahasiswaAdapter
            }
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