package github.paulmburu.gads_leaderboard_app.utils


import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setTextData")
fun bindTextView(textView: TextView, title: String?){
    title.let { textView.setText(it) }
}

@BindingAdapter("setHoursText")
fun bindhoursTextView(textView: TextView, title: Int?){
    title.let { textView.setText("${it} learning hours, ") }
}

@BindingAdapter("setSkillsText")
fun bindSkillsTextView(textView: TextView, title: Int?){
    title.let { textView.setText("${it} skill IQ Score, ") }
}


 