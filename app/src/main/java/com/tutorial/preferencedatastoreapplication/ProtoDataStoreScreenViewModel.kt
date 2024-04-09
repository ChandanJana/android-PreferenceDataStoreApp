package com.tutorial.preferencedatastoreapplication

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Created by Chandan Jana on 30-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
class ProtoDataStoreScreenViewModel(
    private val dataStore: DataStore<ProtoPreferences>
) : ViewModel() {
    private val articles = listOf("Article 1", "Article 2", "Article 3")

    init {
        for (article in articles) {
            saveBookmarkedArticle(article, bookmarked = false)
        }
    }

    fun saveBookmarkedArticle(articleId: String, bookmarked: Boolean) {
        viewModelScope.launch {
            dataStore.updateData { protoPreferences ->
                protoPreferences.toBuilder()
                    .putBookmarkedArticleIds(articleId, bookmarked)
                    .build()
            }
        }
    }

    val bookmarkedArticlesState = dataStore.data.map { protoPreferences ->
        protoPreferences.bookmarkedArticleIdsMap
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null)
}