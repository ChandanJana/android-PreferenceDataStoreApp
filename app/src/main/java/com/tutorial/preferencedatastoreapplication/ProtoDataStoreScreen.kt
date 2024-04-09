package com.tutorial.preferencedatastoreapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Created by Chandan Jana on 30-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
@Composable
fun ProtoDataStoreScreen(doneCallback: ()->Unit) {

    val viewModel: ProtoDataStoreScreenViewModel = viewModel(
        factory = ViewModelFactory(LocalContext.current.protoDataStore)
    )

    val articles by viewModel.bookmarkedArticlesState.collectAsStateWithLifecycle()
    val list = articles?.toList() ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LazyColumn(
            //modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(list) { (articleId, bookmarked) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(articleId)
                    Checkbox(
                        checked = bookmarked,
                        onCheckedChange = { value ->
                            viewModel.saveBookmarkedArticle(articleId, value)
                        }
                    )
                }
            }
        }

        Button(
            onClick = doneCallback,
            modifier = Modifier.padding(16.dp)
        ) {
            Text("DONE")
        }
    }
}