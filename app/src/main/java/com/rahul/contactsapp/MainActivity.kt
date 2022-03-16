package com.rahul.contactsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.contactsapp.adapters.ContactAdapter
import com.rahul.contactsapp.databinding.ActivityMainBinding
import com.rahul.contactsapp.model.Contacts

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setAdapter()
    }

    private fun setAdapter(){
        val contacts = mutableListOf<Contacts>()
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        contacts.add(Contacts("rahul","12345"))
        binding.recyclerView.adapter= ContactAdapter(contacts)
        binding.recyclerView.layoutManager= LinearLayoutManager(this)
    }

}