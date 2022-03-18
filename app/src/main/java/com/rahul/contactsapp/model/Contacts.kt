package com.rahul.contactsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "contacts_table")
data class Contacts(

    @ColumnInfo(name = "name")var name:String,
    @ColumnInfo(name = "number")var number:Int
    ){
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id") var id:Int = 0
}
