package github.paulmburu.gads_leaderboard_app.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.paulmburu.gads_leaderboard_app.data.api.LeadersApi
import github.paulmburu.gads_leaderboard_app.ui.viewState.LearningLeadersState
import github.paulmburu.gads_leaderboard_app.ui.viewState.SkillLeadersState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class LeadersViewModel : ViewModel() {
    private val _learningLeadersState: MutableStateFlow<LearningLeadersState> = MutableStateFlow(LearningLeadersState.Idle)
    val learningLeadersState: StateFlow<LearningLeadersState>
        get() = _learningLeadersState

    private val _skillLeadersState: MutableStateFlow<SkillLeadersState> = MutableStateFlow(SkillLeadersState.Idle)
    val skillLeadersState: StateFlow<SkillLeadersState>
        get() = _skillLeadersState


    fun fetchLearningLeaders(){
        _learningLeadersState.value = LearningLeadersState.Loading
        viewModelScope.launch {
            _learningLeadersState.value = try {
                LearningLeadersState.Success(LeadersApi.retrofitService.getLearningLeaders())
            }catch (e: Exception){
                LearningLeadersState.Error(e.localizedMessage)
            }
        }


    }

    fun fetchSkillLeaders(){
        _skillLeadersState.value = SkillLeadersState.Loading
        viewModelScope.launch {
            _skillLeadersState.value = try {
                SkillLeadersState.Success(LeadersApi.retrofitService.getSkillLeaders())
            }catch (e: Exception){
                SkillLeadersState.Error(e.localizedMessage)
            }
        }
    }



}