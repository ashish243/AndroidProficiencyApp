package com.androidproficiencyapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidproficiencyapp.R
import com.androidproficiencyapp.data.model.Rows
import com.bumptech.glide.Glide
import com.androidproficiencyapp.ui.main.adapter.MainAdapter.DataViewHolder
import kotlinx.android.synthetic.main.canada_list_row.view.imageViewAvatar
import kotlinx.android.synthetic.main.canada_list_row.view.textViewTittle
import kotlinx.android.synthetic.main.canada_list_row.view.textViewDescription

class MainAdapter(private val users: ArrayList<Rows>) : RecyclerView.Adapter<DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(row: Rows) {
            itemView.apply {
                textViewTittle.text = row.title?: "Null"
                textViewDescription.text = row.description?: "Null"
                Glide.with(imageViewAvatar.context)
                    .load(row.imageHref)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.mipmap.ic_launcher_round)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.canada_list_row, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(rows: List<Rows>) {
        this.users.apply {
            clear()
            addAll(rows)
        }

    }
}