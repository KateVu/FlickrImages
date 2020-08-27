package com.katevu.flickimages

import android.os.Bundle
import android.util.Log

class SearchActivity : BaseActivity() {
    private val TAG = "SearchActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        setContentView(R.layout.activity_search)

        activateToolbar(true)
        Log.d(TAG, "onCreate ends")
    }
}