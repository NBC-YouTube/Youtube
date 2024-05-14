package com.nbc.youtube.presentation.home.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CategorySpinnerAdapter(context: Context) : ArrayAdapter<String>(context,
    android.R.layout.simple_spinner_item) {

    private val categoryList: MutableList<String> = mutableListOf()

    fun setCategoryList(categories: List<String>) {
        categoryList.clear()
        categoryList.addAll(categories)
        notifyDataSetChanged()
        Log.d("test", "setCategoryList: work ${categoryList}")
    }

    override fun getCount(): Int { //필수. 없으면 인식을 하지 못함.
        return categoryList.size
    }

    override fun getItem(position: Int): String {
        return categoryList[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createSpinnerView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createSpinnerView(position, convertView, parent)
    }

    private fun createSpinnerView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_item, parent, false)

        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = categoryList[position]

        return view
    }
}
