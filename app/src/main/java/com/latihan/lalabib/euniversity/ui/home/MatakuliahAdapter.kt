package com.latihan.lalabib.euniversity.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.latihan.lalabib.euniversity.data.local.MataKuliahEntities
import com.latihan.lalabib.euniversity.databinding.RvMatkulBinding

class MatakuliahAdapter :
    ListAdapter<MataKuliahEntities, MatakuliahAdapter.MatkulViewHolder>(DIFFUTIL) {

    private object DIFFUTIL : DiffUtil.ItemCallback<MataKuliahEntities>() {
        override fun areItemsTheSame(
            oldItem: MataKuliahEntities,
            newItem: MataKuliahEntities
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MataKuliahEntities,
            newItem: MataKuliahEntities
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatkulViewHolder {
        val binding = RvMatkulBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatkulViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatkulViewHolder, position: Int) {
        val matkul = getItem(position)
        holder.bind(matkul)
    }

    class MatkulViewHolder(private val binding: RvMatkulBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(matkul: MataKuliahEntities) {
            binding.tvName.text = matkul.nama
            binding.tvDosen.text = matkul.dosen.nama
        }

    }
}