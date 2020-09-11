package github.paulmburu.gads_leaderboard_app.ui.learningLeaders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import github.paulmburu.gads_leaderboard_app.R
import github.paulmburu.gads_leaderboard_app.data.dto.LearningLeadersItem
import github.paulmburu.gads_leaderboard_app.databinding.ItemLearningLeadersBinding

class LearnersAdapter() :  RecyclerView.Adapter<HomeViewHolder>() {


    var learnerLeadersList: List<LearningLeadersItem> = emptyList()
        set(value) {
            field = value
            // For an extra challenge, update this to use the paging library.

            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val withDataBinding: ItemLearningLeadersBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            HomeViewHolder.LAYOUT,
            parent,
            false)
        return HomeViewHolder(withDataBinding)
    }

    override fun getItemCount() = learnerLeadersList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        holder.viewDataBinding.also {
            it.learningLeadersItem = learnerLeadersList.get(position)

        }
    }
}

class HomeViewHolder(val viewDataBinding: ItemLearningLeadersBinding): RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.item_learning_leaders
    }
}