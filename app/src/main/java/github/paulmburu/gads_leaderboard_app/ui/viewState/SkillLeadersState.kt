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
sealed class SkillLeadersState {
    object Idle: SkillLeadersState()
    object Loading: SkillLeadersState()
    data class Success(val data: List<SkillLeadersItem>):SkillLeadersState()
    data class Error(val error: String):SkillLeadersState()
}