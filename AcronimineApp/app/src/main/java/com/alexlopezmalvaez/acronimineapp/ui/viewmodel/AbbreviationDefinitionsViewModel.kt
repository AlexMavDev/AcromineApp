package com.alexlopezmalvaez.acronimineapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexlopezmalvaez.acronimineapp.data.model.AbbreDefsResponse
import com.alexlopezmalvaez.acronimineapp.data.repository.AcromineRepository
import com.alexlopezmalvaez.acronimineapp.util.Coroutines
import kotlinx.coroutines.Job

class AbbreviationDefinitionsViewModel (
        private val repository: AcromineRepository
): ViewModel() {
    private lateinit var job: Job
    private val _abbreDefsList = MutableLiveData<AbbreDefsResponse>()

    val abbreDefsList: LiveData<AbbreDefsResponse>
        get() = _abbreDefsList

    fun getAbbreviationDefinitionsList(abbreviation: String){
        job = Coroutines.ioThenMain(
                { repository.getAbbreviationDefinitions(abbreviation)},
                {
                    _abbreDefsList.value = it
                }
        )
    }

    override fun onCleared() {
        super.onCleared()

        if(::job.isInitialized)
            job.cancel()
    }
}