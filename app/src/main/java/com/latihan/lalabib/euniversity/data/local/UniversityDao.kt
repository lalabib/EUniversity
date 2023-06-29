package com.latihan.lalabib.euniversity.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UniversityDao {
    @Query("Select * from matakuliah")
    fun getMatakuliah(): LiveData<List<MataKuliahEntities>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMataKuliah(matakuliah: MataKuliahEntities)

    @Query("Select * from matakuliah where id = :id")
    fun getMatkulById(id: Int): LiveData<MataKuliahEntities>
}