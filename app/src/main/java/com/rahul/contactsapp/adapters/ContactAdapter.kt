package com.rahul.contactsapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.rahul.contactsapp.databinding.ItemLayoutBinding
import com.rahul.contactsapp.model.Contacts
import com.rahul.contactsapp.repository.ContactRepository
import com.rahul.contactsapp.viewModel.ContactViewModel

class ContactAdapter(private val contactList:List<Contacts>)
    :RecyclerView.Adapter<ContactViewHolder>() {

    private var oldData = emptyList<Contacts>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contacts = contactList[position]
        holder.itemViewBinding.apply {
            tvName.text=contacts.name
            tvNumber.text = contacts.number.toString()
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun setData(newData: List<Contacts>){
        oldData = newData
        notifyDataSetChanged()
    }

}
 class ContactViewHolder( val itemViewBinding: ItemLayoutBinding)
    :RecyclerView.ViewHolder(itemViewBinding.root){

    }
