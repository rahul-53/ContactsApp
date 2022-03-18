package com.rahul.contactsapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Contacts::class], version = 1)
abstract class ContactDatabase:RoomDatabase() {

    abstract fun getContactsDao():ContactsDAO

    companion object{
        private var INSTANCE: ContactDatabase? = null
        fun getContactDatabase(context: Context): ContactDatabase{
            if (INSTANCE!=null){
                return INSTANCE!!
            }else{
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "contacts"
                )
                INSTANCE = builder.build()
            }
            return INSTANCE!!
        }
    }
}