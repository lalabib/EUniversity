package com.latihan.lalabib.euniversity.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.latihan.lalabib.euniversity.data.local.Mahasiswa
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.latihan.lalabib.euniversity.databinding.RvMahasiswaBinding

class MahasiswaAdapter : ListAdapter<Mahasiswa, MahasiswaAdapter.MahasiswaViewHolder>(DIFFUTIL) {

    private object DIFFUTIL : DiffUtil.ItemCallback<Mahasiswa>() {
        override fun areItemsTheSame(oldItem: Mahasiswa, newItem: Mahasiswa): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Mahasiswa, newItem: Mahasiswa): Boolean {
            return oldItem.nim == newItem.nim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val binding = RvMahasiswaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MahasiswaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        val mahasiswa = getItem(position)
        holder.bind(mahasiswa)
    }

    class MahasiswaViewHolder(private val binding: RvMahasiswaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mahasiswa: Mahasiswa) {
            binding.apply {
                tvName.text = mahasiswa.nama
                tvNim.text = mahasiswa.nim
            }
        }
    }
}