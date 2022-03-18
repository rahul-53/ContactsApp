package com.rahul.contactsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
//import com.rahul.contactsapp.adapters.ContactAdapter
import com.rahul.contactsapp.databinding.ActivityMainBinding
import com.rahul.contactsapp.model.ContactDatabase
import com.rahul.contactsapp.model.Contacts
import com.rahul.contactsapp.model.ContactsDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var contactDb:ContactDatabase
    lateinit var contactDao:ContactsDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        contactDb= ContactDatabase.getContactDatabase(this)
        contactDao= contactDb.getContactsDao()
        addContact()


    }
     private fun addContact(){
      binding.button.setOnClickListener {
          val contactData = Contacts(
              binding.etName.text.toString(),
              binding.etNumber.text.toString().toInt()
          )
          CoroutineScope(Dispatchers.IO).launch {
              contactDao.addContacts(contactData)

          }
      }

    }
}