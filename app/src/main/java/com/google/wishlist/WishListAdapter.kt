package com.google.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishListAdapter(var items: List<WishListItem>): RecyclerView.Adapter<WishListAdapter.WishListViewHolder>() {

    inner class WishListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // inflate the custom layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return WishListViewHolder(view)
    }

    // get the size of the items list
    override fun getItemCount(): Int {
        return items.size
    }

    // populate data into view holder
    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.textItemName).text = items[position].itemName
            findViewById<TextView>(R.id.textPrice).text = items[position].itemPrice.toString()
            findViewById<TextView>(R.id.textUrl).text = items[position].itemUrl
        }
    }
}