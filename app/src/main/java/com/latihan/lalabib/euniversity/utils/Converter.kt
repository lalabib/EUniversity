package com.latihan.lalabib.euniversity.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.latihan.lalabib.euniversity.data.local.Dosen
import com.latihan.lalabib.euniversity.data.local.Mahasiswa

class Converter {
    @TypeConverter
    fun fromMahasiswaList(mahasiswaList: List<Mahasiswa>): String {
        return Gson().toJson(mahasiswaList)
    }

    @TypeConverter
    fun toMahasiswaList(json: String): List<Mahasiswa> {
        val listType = object : TypeToken<List<Mahasiswa>>() {}.type
        return Gson().fromJson(json, listType)
    }

    @TypeConverter
    fun fromDosen(dosen: Dosen): String {
        return Gson().toJson(dosen)
    }

    @TypeConverter
    fun toDosen(json: String): Dosen {
        return Gson().fromJson(json, Dosen::class.java)
    }
}
