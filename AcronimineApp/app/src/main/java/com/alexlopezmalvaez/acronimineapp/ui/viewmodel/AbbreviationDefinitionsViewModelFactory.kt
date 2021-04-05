package com.alexlopezmalvaez.acronimineapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexlopezmalvaez.acronimineapp.data.repository.AcromineRepository

@Suppress("UNCHECKED_CAST")
class AbbreviationDefinitionsViewModelFactory(
        private val repository: AcromineRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AbbreviationDefinitionsViewModel(repository) as T
    }
}