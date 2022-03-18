package com.rahul.contactsapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rahul.contactsapp.repository.ContactRepository

class ContactViewModelFactory(val repository:ContactRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactViewModel(this.repository) as T
    }

}