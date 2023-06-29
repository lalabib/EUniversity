package com.latihan.lalabib.euniversity.data

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.latihan.lalabib.euniversity.R
import com.latihan.lalabib.euniversity.data.local.Dosen
import com.latihan.lalabib.euniversity.data.local.Mahasiswa
import com.latihan.lalabib.euniversity.data.local.MataKuliahEntities
import com.latihan.lalabib.euniversity.data.local.UniversityDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class StartingDatabase(private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingDatabase(context)
        }
    }

    //Filling database with the data from JSON
    private fun fillWithStartingDatabase(context: Context) {
        //obtaining instance of data access object
        val dao = UniversityDatabase.getInstance(context).UniversityDao()
        val jsonArray = loadJsonArray(context)
        // using try catch to load the necessary data
        try {
            if (jsonArray != null) {
                for (i in 0 until jsonArray.length()) {
                    //variable to obtain the JSON object
                    val item = jsonArray.getJSONObject(i)

                    // Extracting the "dosen" object
                    val dosenObject = item.getJSONObject("dosen")

                    // Extracting the "mahasiswa" array
                    val mahasiswaArray = item.getJSONArray("mahasiswa")
                    val mahasiswaList = mutableListOf<Mahasiswa>()

                    // Looping through the "mahasiswa" array
                    for (j in 0 until mahasiswaArray.length()) {
                        val mahasiswaObject = mahasiswaArray.getJSONObject(j)
                        val nim = mahasiswaObject.getString("nim")
                        val nama = mahasiswaObject.getString("nama")

                        val mahasiswa = Mahasiswa(nim, nama)
                        mahasiswaList.add(mahasiswa)
                    }

                    // Creating the MataKuliahEntities object and inserting it into the database
                    dao.insertAllMataKuliah(
                        MataKuliahEntities(
                            item.getInt("id"),
                            item.getString("nama"),
                            mahasiswaList,
                            Dosen(
                                dosenObject.getString("nid"),
                                dosenObject.getString("nama")
                            )
                        )
                    )
                }
            }
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
    }

    //read json array and load it to matakuliah
    private fun loadJsonArray(context: Context): JSONArray? {
        val builder = StringBuilder()
        val `in` = context.resources.openRawResource(R.raw.matakuliah)
        //using Buffered reader to read the input stream byte
        val reader = BufferedReader(InputStreamReader(`in`))
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                builder.append(line)
            }
            val json = JSONObject(builder.toString())
            return json.getJSONArray("matakuliah")
        } catch (exception: IOException) {
            exception.printStackTrace()
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
        return null
    }
}