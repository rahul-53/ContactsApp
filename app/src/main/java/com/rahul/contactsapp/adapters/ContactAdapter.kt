package com.rahul.contactsapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rahul.contactsapp.MainActivity
import com.rahul.contactsapp.databinding.ItemLayoutBinding
import com.rahul.contactsapp.model.Contacts
import com.rahul.contactsapp.repository.ContactRepository
import com.rahul.contactsapp.viewModel.ContactViewModel
import com.rahul.contactsapp.viewModel.ContactViewModelFactory

class ContactAdapter(private val context: Context, private val contactList:List<Contacts>)
    :RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(itemLayoutBinding)
    }

    private var differCallback = object:DiffUtil.ItemCallback<Contacts>(){
        override fun areItemsTheSame(oldItem: Contacts, newItem: Contacts): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Contacts, newItem: Contacts): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contacts = differ.currentList[position]
        holder.itemViewBinding.apply {
            tvName.text=contacts.name
            tvNumber.text = contacts.number.toString()
        }
        holder.itemViewBinding.shareMe.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, contacts.toString())
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}
 class ContactViewHolder( val itemViewBinding: ItemLayoutBinding)
    :RecyclerView.ViewHolder(itemViewBinding.root){

    }
