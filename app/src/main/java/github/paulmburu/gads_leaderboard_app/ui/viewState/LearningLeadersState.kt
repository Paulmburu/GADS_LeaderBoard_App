package github.paulmburu.gads_leaderboard_app.ui.viewState

import github.paulmburu.gads_leaderboard_app.data.dto.LearningLeadersItem

/*  
 *  Created by Paul Mburu on 9/11/20.
 *
 *  https://github.com/Paulmburu
 *  Email: paulmburu53@gmail.com
 *         kotdroidsicario@gmail.com
 *  PhoneNumber: +254704002748
 */
sealed class LearningLeadersState {
    object Idle: LearningLeadersState()
    object Loading: LearningLeadersState()
    data class Success(val data: List<LearningLeadersItem>): LearningLeadersState()
    data class Error(val error: String): LearningLeadersState()
}