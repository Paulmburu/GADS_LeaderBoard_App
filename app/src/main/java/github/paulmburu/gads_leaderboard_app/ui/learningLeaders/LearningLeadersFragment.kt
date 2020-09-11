package github.paulmburu.gads_leaderboard_app.ui.learningLeaders

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import github.paulmburu.gads_leaderboard_app.R
import github.paulmburu.gads_leaderboard_app.data.dto.LearningLeadersItem
import github.paulmburu.gads_leaderboard_app.ui.viewModel.LeadersViewModel
import github.paulmburu.gads_leaderboard_app.ui.viewState.LearningLeadersState
import github.paulmburu.gads_leaderboard_app.utils.isVisible
import kotlinx.android.synthetic.main.learning_leaders_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LearningLeadersFragment : Fragment() {

    private var learnersAdapter: LearnersAdapter? = null
    private lateinit var viewModel: LeadersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.learning_leaders_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LeadersViewModel::class.java)
        viewModel.fetchLearningLeaders()
        setupUI()
        setupObservers()
    }

    private fun setupUI(){
        learnersAdapter = LearnersAdapter()

        learningLeadersRecyclerview.apply {
            adapter = learnersAdapter
        }
    }

    private fun setupObservers(){
        lifecycleScope.launch {
            viewModel.learningLeadersState.collect {
                when(it){
                    is LearningLeadersState.Idle -> {
                        learningProgressBar.isVisible(true)
                    }
                    is LearningLeadersState.Loading -> {
                        Log.d("TAG", "setupObservers: LOADING")
                        learningProgressBar.isVisible(true)
                    }
                    is LearningLeadersState.Success -> {
                        learningProgressBar.isVisible(false)
                        learnersAdapter!!.learnerLeadersList = it.data
                        Log.d("TAG", "setupObservers: data ${it.data}")
                    }
                    is LearningLeadersState.Error -> {
                        Log.d("TAG", "setupObservers: Error ${it.error}")
                    }
                }
            }
        }
    }
}
