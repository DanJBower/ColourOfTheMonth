package com.danjbower.colourofthemonth.viewmodel

import androidx.lifecycle.ViewModel
import com.danjbower.colourofthemonth.data.ColourInfo
import com.danjbower.colourofthemonth.data.getColourInfo
import com.danjbower.colourofthemonth.model.ColourOfTheMonthModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ColourOfTheMonthViewModel : ViewModel
{
    private val _model: ColourOfTheMonthModel
    private val _viewState: MutableStateFlow<ViewState>
    val viewState: StateFlow<ViewState>

    constructor(model: ColourOfTheMonthModel)
    {
        _model = model
        _viewState = MutableStateFlow(ViewState(
            currentDate = _model.currentChange.date,
            currentColourInfo = getColourInfo(_model.currentChange.colour),
            showExtraDetail = false,
            showChangelog = false,
            changeLog = _model.changelog.map { history ->
                HistoryViewModel(history)
            },
        ))
        viewState = _viewState.asStateFlow()
    }

    data class ViewState
    (
        val currentDate: String,
        val currentColourInfo: ColourInfo,
        val showExtraDetail: Boolean,
        val showChangelog: Boolean,
        val changeLog: List<HistoryViewModel>,
    )

    fun toggleInfoDetail() {
        val state = _viewState.value;
        if (!state.showChangelog)
        {
            _viewState.value = state.copy(
                showExtraDetail = !state.showExtraDetail
            )
        }
    }

    fun toggleChangelog() {
        val state = _viewState.value;
        _viewState.value = state.copy(
            showChangelog = !state.showChangelog
        )
    }
}
