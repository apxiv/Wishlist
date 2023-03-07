package com.google.wishlist

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = mutableListOf(
            WishListItem("Android Book", 74.62, "https://a.co/d/iI8vRl3"),
            WishListItem("Google Home", 72.0, "https://tinyurl.com/2w7rthzf"),
            WishListItem("Febreze Car", 7.19, "https://tinyurl.com/yveepssa"),
            WishListItem("Sony Headphones", 149.99, "https://tinyurl.com/2p9xa8v7"),
            WishListItem("Data-Intensive Apps", 34.38, "https://a.co/d/9STcgHq")
        )

        val rvWishList = findViewById<RecyclerView>(R.id.rvWishList)
        val adapter = WishListAdapter(items)
        rvWishList.adapter = adapter
        rvWishList.layoutManager = LinearLayoutManager(this)


        val editName = findViewById<EditText>(R.id.editItemName)
        val editPrice = findViewById<EditText>(R.id.editPrice)
        val editUrl = findViewById<EditText>(R.id.editUrl)
        val buttonAdd = findViewById<Button>(R.id.addButton)
        buttonAdd.isEnabled = false

        // button is disabled until all fields are filled
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val value1 = editName.text.toString()
                val value2 = editPrice.text.toString()
                val value3 = editUrl.text.toString()
                buttonAdd.isEnabled = value1.isNotEmpty() && value2.isNotEmpty() && value3.isNotEmpty()
            }
        }

        editName.addTextChangedListener(textWatcher)
        editPrice.addTextChangedListener(textWatcher)
        editUrl.addTextChangedListener(textWatcher)

        buttonAdd.setOnClickListener {
            hideKeyboard()
            val itemName = editName.text.toString()
            val price = editPrice.text.toString().toDouble()
            val url = editUrl.text.toString()
            editName.text.clear()
            editPrice.text.clear()
            editUrl.text.clear()
            val item = WishListItem(itemName, price, url)
            items.add(item)
            adapter.notifyItemInserted(items.size - 1)
        }
    }
    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}