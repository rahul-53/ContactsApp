package com.rahul.contactsapp.repository

import androidx.lifecycle.LiveData
import com.rahul.contactsapp.model.Contacts
import com.rahul.contactsapp.model.ContactsDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ContactRepository( val contactDao:ContactsDAO) {
    fun addContactsInDB(contacts:Contacts){
        CoroutineScope(Dispatchers.IO).launch {
            contactDao.addContacts(contacts)
        }
    }
    fun getContactList():LiveData<List<Contacts>>{
        return contactDao.getContacts()
    }
    fun searchContact(searchName:String): LiveData<List<Contacts>> {
        return contactDao.searchInDb(searchName)
    }

}