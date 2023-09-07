package com.example.giphysearch

import android.os.Bundle
import android.view.ViewTreeObserver
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var searchEditText: EditText

    private val viewModel: GifViewModel by lazy {
        GifViewModelFactory(Repository(), applicationContext)
            .create(GifViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.search_bar_edit_text)
        val itemsPerPage = 30

        adapter = Adapter(this, mutableListOf())
        recyclerView.adapter = adapter

        val itemWidth = resources.getDimensionPixelSize(R.dimen.item_gif_width)
        val screenWidth = resources.displayMetrics.widthPixels
        val spanCount = screenWidth / itemWidth
        recyclerView.layoutManager = GridLayoutManager(this, spanCount.toInt())

        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val query = searchEditText.text.toString()
                viewModel.searchGifs(query, offset = 0, limit = itemsPerPage)
                searchEditText.text.clear()
                hideKeyboard()
                true
            } else {
                false
            }
        }

        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val itemWidth = resources.getDimensionPixelSize(R.dimen.item_gif_width)
                val screenWidth = recyclerView.width
                val spanCount = screenWidth / itemWidth
                (recyclerView.layoutManager as GridLayoutManager).spanCount = spanCount.toInt()
            }
        })

        viewModel.gifs.observe(this) { gifDataList ->
            adapter.updateData(gifDataList)
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}