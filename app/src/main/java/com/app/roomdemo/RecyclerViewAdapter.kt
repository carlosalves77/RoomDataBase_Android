package com.app.roomdemo


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.roomdemo.db.UserEntity
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerViewAdapter(val listner: RowClickListner): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {


   var items = ArrayList<UserEntity>()

    fun setListDAta(data: ArrayList<UserEntity>) {
        this.items = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {

        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(inflater, listner)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener{
            listner.onItemClickListener(items[position])
        }
        holder.bind(items[position])

    }

    override fun getItemCount(): Int {

        return items.size
    }

    class MyViewHolder(view:View, val listner: RowClickListner): RecyclerView.ViewHolder(view) {

        val tvName = view.tvName
        val tvEmail = view.tvEmail
        val deleteUserId = view.deleteUserID

        fun bind(data: UserEntity) {

            tvName.text = data.name
            tvEmail.text = data.email

            deleteUserId.setOnClickListener{
                listner.onDeleteUserClickListener(data)
            }

        }
    }

    interface RowClickListner {
        fun  onDeleteUserClickListener(user: UserEntity)
        fun onItemClickListener(user: UserEntity)
    }

}