package com.rahul.contactsapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.contactsapp.adapters.ContactAdapter
import com.rahul.contactsapp.adapters.ContactViewHolder
import com.rahul.contactsapp.databinding.ActivityMainBinding
import com.rahul.contactsapp.model.ContactDatabase
import com.rahul.contactsapp.model.Contacts
import com.rahul.contactsapp.model.ContactsDAO
import com.rahul.contactsapp.repository.ContactRepository
import com.rahul.contactsapp.viewModel.ContactViewModel
import com.rahul.contactsapp.viewModel.ContactViewModelFactory


class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {
    lateinit var binding:ActivityMainBinding
    lateinit var contactDb:ContactDatabase
    lateinit var contactDao:ContactsDAO
    lateinit var viewModel:ContactViewModel
    lateinit var repository:ContactRepository
    lateinit var adapter:ContactAdapter

    private var contactList = mutableListOf<Contacts>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        contactDb= ContactDatabase.getContactDatabase(this)
        contactDao= contactDb.getContactsDao()
        repository= ContactRepository(contactDao)

        setAdapter()

        viewModel = ViewModelProvider(this,ContactViewModelFactory(repository))
            .get(ContactViewModel::class.java)

        val name = binding.etName.text
        val number = binding.etNumber.text

        binding.button.setOnClickListener {
            //Toast.makeText(this,"$name and $number",Toast.LENGTH_SHORT).show()
            val entity = Contacts( name.toString(), number.toString())
            viewModel.addContacts(entity)
        }

        viewModel.getContactList().observe(this, Observer {
          contact ->adapter.differ.submitList(contact)
        })

    }
    private fun setAdapter(){
        adapter =  ContactAdapter(this,contactList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)

        val search = menu?.findItem(R.id.app_bar_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled= true
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            searchContact(newText)
        }
        return true
    }


    private fun searchContact(query:String){
        val searchQuery = "%$query%"
        viewModel.searchContacts(searchQuery).observe(this) { list ->
            adapter.differ.submitList(list)
        }
    }
}