package com.example.newsapplication.entities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.BR

interface BaseListener

abstract class BaseAdapter<T>(private var item:MutableList<T>, private val listener: BaseListener):
    RecyclerView.Adapter<BaseAdapter.BaseViewHolder>(){
    abstract val layoutId :Int
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),layoutId,parent,false))
    }
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = item[position]
        when(holder){
            is ItemViewHolder -> {
                holder.binding.setVariable(BR.news,currentItem)
                holder.binding.setVariable(BR.itemclick,listener)
            }
        }
    }
    fun setItem(items:MutableList<T>){
        item = items
        notifyDataSetChanged()
    }
    fun getItem()=item
    override fun getItemCount() =item.size
    class ItemViewHolder (val binding: ViewDataBinding):BaseViewHolder (binding)
    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}