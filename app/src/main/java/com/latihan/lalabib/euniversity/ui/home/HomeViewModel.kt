package com.latihan.lalabib.euniversity.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.latihan.lalabib.euniversity.data.UniversityRepository
import com.latihan.lalabib.euniversity.data.local.MataKuliahEntities

class HomeViewModel(private val repository: UniversityRepository): ViewModel() {

    fun getMatakuliah(): LiveData<List<MataKuliahEntities>> = repository.getMatakuliah()
}