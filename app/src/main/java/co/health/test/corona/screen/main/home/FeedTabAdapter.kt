package co.health.test.corona.screen.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import co.health.test.corona.screen.main.home.learn.LearnFragment
import co.health.test.corona.screen.main.home.question.QuestionFragment

class FeedTabAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return if (position == 0)
            LearnFragment.newInstance(1)
        else QuestionFragment.newInstance(1)

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0)
            "آموزش" else "سوالات"
    }


}