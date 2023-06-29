package com.latihan.lalabib.euniversity.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.latihan.lalabib.euniversity.data.Converter
import com.latihan.lalabib.euniversity.data.StartingDatabase

@Database(entities = [MataKuliahEntities::class], version = 2, exportSchema = false)
@TypeConverters(Converter::class)
abstract class UniversityDatabase : RoomDatabase() {

    abstract fun UniversityDao(): UniversityDao

    companion object {
        @Volatile
        private var instance: UniversityDatabase? = null

        fun getInstance(context: Context): UniversityDatabase = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                UniversityDatabase::class.java,
                "university_db"
            )
                .addCallback(StartingDatabase(context))
                .fallbackToDestructiveMigration()
                .build()
                .also { instance = it }
        }
    }
}