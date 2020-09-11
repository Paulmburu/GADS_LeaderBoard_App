package github.paulmburu.gads_leaderboard_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import github.paulmburu.gads_leaderboard_app.ui.learningLeaders.LearningLeadersFragment
import github.paulmburu.gads_leaderboard_app.ui.skillIQLeaders.SkillLeadersFragment
import github.paulmburu.gads_leaderboard_app.ui.submission.SubmitActivity
import kotlinx.android.synthetic.main.activity_main.*

private const val NUM_PAGES = 2
class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.my_toolbar))
        submit.setOnClickListener {
            startActivity(Intent(this,SubmitActivity::class.java))
        }

        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.pager)
        initViewPager()

//        supportActionBar?.let {
//            it.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
//            it.setCustomView(R.layout.main_custom_toolbar)
//        }
    }

    private fun initViewPager(){

        val pagerAdapter = LeadersSlidePagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = pagerAdapter


        TabLayoutMediator(tabLayout, viewPager){
                tab, position ->
            if (position == 0) {
                tab.setText("LEARNING LEADERS")
            } else if (position == 1) {
                tab.setText("SKILL  IQ LEADERS")
            }
        }.attach()
    }

    private inner class LeadersSlidePagerAdapter(fa: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fa,lifecycle) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    LearningLeadersFragment()
                }
                1 -> {
                    SkillLeadersFragment()
                }
                else -> LearningLeadersFragment()
            }
        }

    }
}