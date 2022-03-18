package com.rahul.contactsapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var contactDb:ContactDatabase
    lateinit var contactDao:ContactsDAO
    lateinit var viewModel:ContactViewModel
    lateinit var repository:ContactRepository
    lateinit var adapter:ContactAdapter

    private var contactList = mutableListOf<Contacts>()
    var tempList = mutableListOf<Contacts>()

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
          contactList.clear()
            contactList.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }
    private fun setAdapter(){
        adapter =  ContactAdapter(contactList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var item:MenuItem = menu!!.findItem(R.id.app_bar_search)
        var searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isNotEmpty()){
                    tempList.clear()
                    var search = newText.toLowerCase(Locale.getDefault())

                    for (contacts in contactList){
                        if (contacts.name.toLowerCase(Locale.getDefault()).contains(search)){
                            tempList.add(contacts)
                        }
                        binding.recyclerView.adapter!!.notifyDataSetChanged()
                    }
                }
                else{
                    tempList.clear()
                    tempList.addAll(contactList)
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }*/
}