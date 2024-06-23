package com.danjbower.colourofthemonth.viewmodel

import androidx.lifecycle.ViewModel
import com.danjbower.colourofthemonth.data.ColourInfo
import com.danjbower.colourofthemonth.data.History
import com.danjbower.colourofthemonth.data.getColourInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HistoryViewModel : ViewModel
{
    private val _history: History
    private val _viewState: MutableStateFlow<ViewState>
    val viewState: StateFlow<ViewState>

    constructor(history: History)
    {
        _history = history
        _viewState = MutableStateFlow(ViewState(
            currentDate = _history.date,
            currentColourInfo = getColourInfo(_history.colour),
            showExtraDetail = false,
        ))
        viewState = _viewState.asStateFlow()
    }

    data class ViewState
    (
        val currentDate: String,
        val currentColourInfo: ColourInfo,
        val showExtraDetail: Boolean,
    )

    fun toggleInfoDetail() {
        val state = _viewState.value
        _viewState.value = state.copy(
            showExtraDetail = !state.showExtraDetail
        )
    }
}
