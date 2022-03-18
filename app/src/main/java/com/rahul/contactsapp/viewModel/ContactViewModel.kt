package com.rahul.contactsapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rahul.contactsapp.model.Contacts
import com.rahul.contactsapp.repository.ContactRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel( val repo:ContactRepository):ViewModel() {
    fun addContacts(contacts:Contacts){
        CoroutineScope(Dispatchers.IO).launch {
            repo.addContactsInDB(contacts)
        }
    }
    fun getContactList(): LiveData<List<Contacts>> {
        return repo.getContactList()
    }
}