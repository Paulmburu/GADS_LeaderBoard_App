package github.paulmburu.gads_leaderboard_app.ui.submission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import github.paulmburu.gads_leaderboard_app.R
import github.paulmburu.gads_leaderboard_app.databinding.DialogSubmissionErrorBinding
import github.paulmburu.gads_leaderboard_app.databinding.DialogSubmissionSuccessfulBinding
import github.paulmburu.gads_leaderboard_app.databinding.DialogSubmitProjectBinding
import github.paulmburu.gads_leaderboard_app.ui.viewModel.LeadersViewModel
import github.paulmburu.gads_leaderboard_app.ui.viewState.LearningLeadersState
import github.paulmburu.gads_leaderboard_app.ui.viewState.SubmitState
import github.paulmburu.gads_leaderboard_app.utils.isVisible
import kotlinx.android.synthetic.main.activity_submit.*
import kotlinx.android.synthetic.main.learning_leaders_fragment.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SubmitActivity : AppCompatActivity() {

    private lateinit var viewModel: SubmitViewModel
    private lateinit var submitDialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        viewModel = ViewModelProviders.of(this).get(SubmitViewModel::class.java)
        setupObservers()

        findViewById<Button>(R.id.submitButton).setOnClickListener {
           displaySubmissionDialog()
        }

    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.submitState.collect {
                when (it) {
                    is SubmitState.Idle -> {
                    }
                    is SubmitState.Loading -> {
                        Log.d("TAG", "setupObservers: LOADING")
                        learningProgressBar.isVisible(true)
                    }
                    is SubmitState.Success -> {
                        learningProgressBar.isVisible(false)
                        Log.d("TAG", "setupObservers: SUCCESS}")
                        showSuccessDialog()
                    }
                    is SubmitState.Error -> {
                        Log.d("TAG", "setupObservers: Error")
                        showFailureDialog()
                    }
                }
            }
        }
    }

    private fun displaySubmissionDialog() {
        submitDialog = this.let {
            val builder = AlertDialog.Builder(it)
            val layoutInflater = this.layoutInflater

            val submissionDialog = DialogSubmitProjectBinding.inflate(layoutInflater)
            submissionDialog.textLabelSubmitQuestionMark.text = "?"
            submissionDialog.imageCancelSubmission.setOnClickListener {
                submitDialog.dismiss()
                displayInputFields()
            }
            submissionDialog.buttonSubmitProject.setOnClickListener {
                viewModel.submit(
                    findViewById<TextInputEditText>(R.id.firstName).text.toString().trim(),
                    findViewById<TextInputEditText>(R.id.secondName).text.toString().trim(),
                    findViewById<TextInputEditText>(R.id.emailAddress).text.toString().trim(),
                    findViewById<TextInputEditText>(R.id.projectOnGithub).text.toString().trim()
                )
                submitDialog.dismiss()
                displayInputFields()
            }

            builder.setView(submissionDialog.root)
            builder.create()
        }
        submitDialog.setOnDismissListener {
            displayInputFields()
        }

        hideInputFields()
        submitDialog.show()
    }

    private fun showSuccessDialog() {

        val alertDialog: AlertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            val layoutInflater = this.layoutInflater

            val failureDialog = DialogSubmissionSuccessfulBinding.inflate(layoutInflater)
            builder.setView(failureDialog.root)
            builder.create()
        }
        alertDialog.setOnDismissListener {
            displayInputFields()
        }
        hideInputFields()
        alertDialog.show()

    }

    private fun showFailureDialog() {
        val alertDialog: AlertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            val layoutInflater = this.layoutInflater

            val failureDialog = DialogSubmissionErrorBinding.inflate(layoutInflater)
            builder.setView(failureDialog.root)
            builder.create()
        }
        alertDialog.setOnDismissListener {
            displayInputFields()
        }
        hideInputFields()
        alertDialog.show()
    }

    private fun displayInputFields() {
        firstNameInput.isVisible(false)
        secondNameInput.isVisible(false)
        emailInput.isVisible(false)
        githubInput.isVisible(false)
        submitButton.isVisible(false)
    }

    private fun hideInputFields() {
        firstNameInput.isVisible(true)
        secondNameInput.isVisible(true)
        emailInput.isVisible(true)
        githubInput.isVisible(true)
        submitButton.isVisible(true)
    }
}