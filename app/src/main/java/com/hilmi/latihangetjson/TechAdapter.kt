package com.hilmi.latihangetjson

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hilmi.latihangetjson.databinding.PostRowItemBinding
import com.squareup.picasso.Picasso

class TechAdapter : RecyclerView.Adapter<TechAdapter.MyViewHolder>() {

    private var techList = ArrayList<Tech>()

    fun setData(list: List<Tech>?) {
        if (list == null) return
        techList.clear()
        techList.addAll(list)
    }

    class MyViewHolder(val binding: PostRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        PostRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = techList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            tvItem.text = techList[position].title
            Picasso.get().load(techList[position].thumb).into(imgItem)
            root.setOnClickListener {
                val intent = Intent(root.context, DetailActivity::class.java)
                    .putExtra(DetailActivity.NEWS_DATA, techList[position])
                root.context.startActivity(intent)
            }
        }
    }
}