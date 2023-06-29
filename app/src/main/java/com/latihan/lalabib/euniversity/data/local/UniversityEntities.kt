package com.latihan.lalabib.euniversity.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matakuliah")
data class MataKuliahEntities(
    @PrimaryKey
    val id: Int,
    val nama: String,
    val mahasiswa: List<Mahasiswa>,
    val dosen: Dosen,
)

data class Mahasiswa(
    val nim: String,
    val nama: String,
)

data class Dosen(
    val nid: String,
    val nama: String,
)