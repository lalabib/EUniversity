package com.latihan.lalabib.euniversity.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.latihan.lalabib.euniversity.data.UniversityRepository
import com.latihan.lalabib.euniversity.data.local.Mahasiswa
import com.latihan.lalabib.euniversity.data.local.MataKuliahEntities

class DetailViewModel(private val repository: UniversityRepository): ViewModel() {

    private val _matkulId = MutableLiveData<Int>()

    private val _id = _matkulId.switchMap {
        repository.getMatkulById(it)
    }

    val matkul: LiveData<MataKuliahEntities> = _id

    fun setId(id: Int) {
        if (id == _matkulId.value) {
            return
        }
        _matkulId.value = id
    }
}