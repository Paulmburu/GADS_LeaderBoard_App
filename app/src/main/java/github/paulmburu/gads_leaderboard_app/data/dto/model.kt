package github.paulmburu.gads_leaderboard_app.data.dto

import com.squareup.moshi.JsonClass

/*  
 *  Created by Paul Mburu on 9/11/20.
 *
 *  https://github.com/Paulmburu
 *  Email: paulmburu53@gmail.com
 *         kotdroidsicario@gmail.com
 *  PhoneNumber: +254704002748
 */

@JsonClass(generateAdapter = true)
data class LearningLeadersItem(
    val badgeUrl: String,
    val country: String,
    val hours: Int,
    val name: String
)

@JsonClass(generateAdapter = true)
data class SkillLeadersItem(
    val badgeUrl: String,
    val country: String,
    val name: String,
    val score: Int
)