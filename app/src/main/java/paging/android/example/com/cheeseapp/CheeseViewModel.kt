/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package paging.android.example.com.cheeseapp

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * A simple [AndroidViewModel] that provides a [Flow]<[PagingData]> of delicious cheeses.
 */
class CheeseViewModel(private val dao: CheeseDao) : ViewModel() {

    val allCheeses: Flow<PagingData<CheeseListItem>> = Pager(
        config = PagingConfig(
            pageSize = 60,
            enablePlaceholders = true,
            maxSize = 200
        )
    ) {
        dao.allCheesesByName()
    }.flow
        .map { pagingData ->
            pagingData
                .map { cheese -> CheeseListItem.Item(cheese) }
                .insertSeparators { before: CheeseListItem?, after: CheeseListItem? ->
                    if (before == null && after == null) {
                        // List is empty after fully loaded; return null to skip adding separator.
                        null
                    } else if (after == null) {
                        // Footer; return null here to skip adding a footer.
                        null
                    } else if (before == null) {
                        // Header
                        CheeseListItem.Separator(after.name.first())
                    } else if (before.name.first() != after.name.first()) {
                        // Between two items that start with different letters.
                        CheeseListItem.Separator(after.name.first())
                    } else {
                        // Between two items that start with the same letter.
                        null
                    }
                }
        }
        .cachedIn(viewModelScope)

    fun insert(text: CharSequence) = ioThread {
        dao.insert(Cheese(id = 0, name = text.toString()))
    }

    fun remove(cheese: Cheese) = ioThread {
        dao.delete(cheese)
    }
}
