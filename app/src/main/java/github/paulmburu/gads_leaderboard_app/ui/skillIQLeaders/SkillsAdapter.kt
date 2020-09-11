package github.paulmburu.gads_leaderboard_app.ui.skillIQLeaders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import github.paulmburu.gads_leaderboard_app.R
import github.paulmburu.gads_leaderboard_app.data.dto.SkillLeadersItem
import github.paulmburu.gads_leaderboard_app.databinding.ItemSkillLeadersBinding

class SkillsAdapter() :  RecyclerView.Adapter<HomeViewHolder>() {


    var skillLeadersList: List<SkillLeadersItem> = emptyList()
        set(value) {
            field = value
            // For an extra challenge, update this to use the paging library.

            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val withDataBinding: ItemSkillLeadersBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            HomeViewHolder.LAYOUT,
            parent,
            false)
        return HomeViewHolder(withDataBinding)
    }

    override fun getItemCount() = skillLeadersList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        holder.viewDataBinding.also {
            it.skillLeadersItem = skillLeadersList.get(position)
            
        }
    }
}

class HomeViewHolder(val viewDataBinding: ItemSkillLeadersBinding): RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.item_skill_leaders
    }
}