package com.brijesh.simform_practical.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brijesh.simform_practical.databinding.UserItemListBinding
import com.brijesh.simform_practical.model.UserData

import com.bumptech.glide.Glide

class MainAdapter(private val onItemClicked: ( position: Int,userdata: UserData) -> Unit) :
    RecyclerView.Adapter<MainViewHolder>() {

    var userdataList = mutableListOf<UserData>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUserDataList(movies: List<UserData>) {
        this.userdataList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = UserItemListBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = userdataList[position]
        holder.binding.tvName.text =
            item.name?.title + ". " + item.name?.first + " " + item.name?.last
        holder.binding.tvEmail.text = item.email
        Glide.with(holder.itemView.context).load(item.picture?.medium!!)
            .into(holder.binding.imageViewAirlinesLogo)
        // Calling the clickListener sent by the constructor
        holder.binding.llMain.setOnClickListener { onItemClicked(position,item) }
    }

    override fun getItemCount(): Int {
        return userdataList.size
    }
}

class MainViewHolder(val binding: UserItemListBinding) : RecyclerView.ViewHolder(binding.root) {

}