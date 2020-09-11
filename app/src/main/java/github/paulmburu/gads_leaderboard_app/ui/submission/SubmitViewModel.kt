package github.paulmburu.gads_leaderboard_app.ui.submission

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.paulmburu.gads_leaderboard_app.data.api.SubmitApi
import github.paulmburu.gads_leaderboard_app.ui.viewState.LearningLeadersState
import github.paulmburu.gads_leaderboard_app.ui.viewState.SubmitState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

/*  
 *  Created by Paul Mburu on 9/11/20.
 *
 *  https://github.com/Paulmburu
 *  Email: paulmburu53@gmail.com
 *         kotdroidsicario@gmail.com
 *  PhoneNumber: +254704002748
 */
class SubmitViewModel: ViewModel() {
    private val _submitState: MutableStateFlow<SubmitState> = MutableStateFlow(
        SubmitState.Idle)
    val submitState: StateFlow<SubmitState>
        get() = _submitState

    fun submit(firstName: String, secondName: String, email:String, github: String){
        Log.d("submit", "submit: ${firstName} ${secondName} ${email} ${github}")
        _submitState.value = SubmitState.Loading

        viewModelScope.launch {
            _submitState.value = try {
                SubmitApi.retrofitService.submitData(firstName,secondName,email, github)
                SubmitState.Success
            }catch (e:Exception){
                SubmitState.Error
            }
        }
    }
}