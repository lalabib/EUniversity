package com.latihan.lalabib.euniversity.data

import android.content.Context
import androidx.lifecycle.LiveData
import com.latihan.lalabib.euniversity.data.local.Mahasiswa
import com.latihan.lalabib.euniversity.data.local.MataKuliahEntities
import com.latihan.lalabib.euniversity.data.local.UniversityDao
import com.latihan.lalabib.euniversity.data.local.UniversityDatabase

class UniversityRepository(private val universityDao: UniversityDao) {

    fun getMatakuliah(): LiveData<List<MataKuliahEntities>> = universityDao.getMatakuliah()

    fun getMatkulById(id: Int): LiveData<MataKuliahEntities> = universityDao.getMatkulById(id)

    companion object {
        @Volatile
        private var instance: UniversityRepository? = null

        fun getInstance(context: Context): UniversityRepository = instance ?: synchronized(this) {
            if (instance == null) {
                val database = UniversityDatabase.getInstance(context)
                instance = UniversityRepository(database.UniversityDao())
            }
            return instance as UniversityRepository
        }
    }
}