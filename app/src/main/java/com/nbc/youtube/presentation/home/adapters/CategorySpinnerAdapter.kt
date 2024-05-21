package com.nbc.youtube.presentation.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.nbc.youtube.databinding.ItemCustomSpinnerBinding
import com.nbc.youtube.presentation.model.CategoryInfo

class CategorySpinnerAdapter(context: Context) : ArrayAdapter<CategoryInfo>(
    context,
    android.R.layout.simple_spinner_item
) {

    private val categoryList: MutableList<CategoryInfo> = mutableListOf()

    fun setCategoryList(categories: List<CategoryInfo>) {
        categoryList.clear()
        categoryList.addAll(categories)
        notifyDataSetChanged()
    }

    override fun getCount(): Int { //필수. 없으면 인식을 하지 못함.
        return categoryList.size
    }

    override fun getItem(position: Int): CategoryInfo {
        return categoryList[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createSpinnerView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createSpinnerView(position, convertView, parent)
    }

    private fun createSpinnerView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemCustomSpinnerBinding = if (convertView == null) {
            val inflater = LayoutInflater.from(parent.context)
            ItemCustomSpinnerBinding.inflate(inflater, parent, false)
        } else {
            ItemCustomSpinnerBinding.bind(convertView)
        }

        binding.tvCategoryItem.text = categoryList[position].name

        return binding.root
    }
}
