package github.paulmburu.gads_leaderboard_app.ui.viewState

import github.paulmburu.gads_leaderboard_app.data.dto.SkillLeadersItem

/*  
 *  Created by Paul Mburu on 9/11/20.
 *
 *  https://github.com/Paulmburu
 *  Email: paulmburu53@gmail.com
 *         kotdroidsicario@gmail.com
 *  PhoneNumber: +254704002748
 */
sealed class SubmitState {
    object Idle: SubmitState()
    object Loading: SubmitState()
    object Success : SubmitState()
    object Error:SubmitState()
}