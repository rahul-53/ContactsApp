package com.rahul.contactsapp.model


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addContacts(contacts: Contacts)

    @Query("SELECT * FROM contacts_table")
    fun getContacts():LiveData<List<Contacts>>

    @Delete
    fun deleteContact(contacts: Contacts)

}