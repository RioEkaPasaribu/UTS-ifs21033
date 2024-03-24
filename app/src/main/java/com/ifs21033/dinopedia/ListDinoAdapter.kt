package com.ifs21033.dinopedia

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21033.dinopedia.databinding.ItemRowFamilyBinding

class ListDinoAdapter(private val listDino: List<Dino>) :
    RecyclerView.Adapter<ListDinoAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowFamilyBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup, false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        try {
            val dino = listDino[position]
            holder.binding.ivItemFruit.setImageResource(dino.icon)
            holder.binding.tvItemFruit.text = dino.name
            holder.binding.textView2.text = dino.familyName

            holder.itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(dino)
            }
        } catch (e: Exception) {
            // Menampilkan pesan kesalahan
            Log.e("ListDinoAdapter", "Error in onBindViewHolder: ${e.message}")
        }
    }


    override fun getItemCount(): Int = listDino.size

    class ListViewHolder(var binding: ItemRowFamilyBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Dino)
    }
}
