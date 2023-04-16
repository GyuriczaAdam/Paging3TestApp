package hu.gyadam.paging3test.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.gyadam.paging3test.data.local.BeerEntity
import hu.gyadam.paging3test.data.mappers.toBeer
import javax.inject.Inject
import kotlinx.coroutines.flow.map

@HiltViewModel
class BeerViewModel @Inject constructor(
    pager: Pager<Int, BeerEntity>,
) : ViewModel() {

    val beerPagingFlow = pager
        .flow
        .map { pagingData->
            pagingData.map { it.toBeer() }
        }
        .cachedIn(viewModelScope)
}