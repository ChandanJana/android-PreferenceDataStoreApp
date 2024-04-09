package com.tutorial.preferencedatastoreapplication

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Chandan Jana on 30-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
class ViewModelFactory<K>(
    private val dataStore: DataStore<K>,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(PrefsDataStoreScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PrefsDataStoreScreenViewModel(
                dataStore as DataStore<Preferences>
            ) as T
        }
        else if (modelClass.isAssignableFrom(ProtoDataStoreScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProtoDataStoreScreenViewModel(
                dataStore as DataStore<ProtoPreferences>
            ) as T
        }


        throw IllegalArgumentException("Unknown ViewModel class")
    }
}