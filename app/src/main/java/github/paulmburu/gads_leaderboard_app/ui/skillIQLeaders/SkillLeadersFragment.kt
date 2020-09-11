package github.paulmburu.gads_leaderboard_app.ui.skillIQLeaders

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import github.paulmburu.gads_leaderboard_app.R
import github.paulmburu.gads_leaderboard_app.ui.learningLeaders.LearnersAdapter
import github.paulmburu.gads_leaderboard_app.ui.viewModel.LeadersViewModel
import github.paulmburu.gads_leaderboard_app.ui.viewState.LearningLeadersState
import github.paulmburu.gads_leaderboard_app.ui.viewState.SkillLeadersState
import github.paulmburu.gads_leaderboard_app.utils.isVisible
import kotlinx.android.synthetic.main.learning_leaders_fragment.*
import kotlinx.android.synthetic.main.skill_leaders_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SkillLeadersFragment : Fragment() {

    private var skillsAdapter: SkillsAdapter? = null
    private lateinit var viewModel: LeadersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.skill_leaders_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LeadersViewModel::class.java)
        viewModel.fetchSkillLeaders()
        setupUI()
        setupObservers()
    }

    private fun setupUI(){
        skillsAdapter = SkillsAdapter()
        skillLeadersRecyclerview.apply {
            adapter = skillsAdapter
        }
    }

    private fun setupObservers(){
        lifecycleScope.launch {
            viewModel.skillLeadersState.collect {
                when(it){
                    is SkillLeadersState.Idle -> {
                        skillProgressBar.isVisible(true)
                    }
                    is SkillLeadersState.Loading -> {
                        Log.d("TAG", "setupObservers: LOADING")
                        skillProgressBar.isVisible(true)
                    }
                    is SkillLeadersState.Success -> {
                        skillProgressBar.isVisible(false)
                        skillsAdapter!!.skillLeadersList = it.data
                        Log.d("TAG", "setupObservers: data ${it.data}")
                    }
                    is SkillLeadersState.Error -> {
                        Log.d("TAG", "setupObservers: Error ${it.error}")
                    }
                }
            }
        }
    }

}