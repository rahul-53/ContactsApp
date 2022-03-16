package com.rahul.contactsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahul.contactsapp.databinding.ItemLayoutBinding
import com.rahul.contactsapp.model.Contacts

class ContactAdapter(private val contactList:List<Contacts>)
    :RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contacts = contactList[position]
        holder.itemViewBinding.apply {
            tvName.text=contacts.name
            tvNumber.text = contacts.number
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class ContactViewHolder( val itemViewBinding: ItemLayoutBinding)
        :RecyclerView.ViewHolder(itemViewBinding.root){}
}