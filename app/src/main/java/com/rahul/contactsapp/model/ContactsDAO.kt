package com.rahul.contactsapp.model


import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addContacts(contacts: Contacts)

    @Query("SELECT * FROM contacts_table")
    fun getContacts():LiveData<List<Contacts>>

    @Query("SELECT * FROM contacts_table WHERE name LIKE:searchName")
     fun searchInDb(searchName: String) : LiveData<List<Contacts>>

    @Delete
    fun deleteContact(contacts: Contacts)

}