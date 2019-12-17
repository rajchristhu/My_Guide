package team.cesea.myguide.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import kotlinx.android.synthetic.main.activity_land.*
import team.cesea.myguide.R
import team.cesea.myguide.UI.Landing.FriendFinding
import team.cesea.myguide.UI.Landing.Group
import team.cesea.myguide.UI.Landing.home


class Land : AppCompatActivity() {
    val navOptions = NavOptions.Builder().setPopUpTo(R.id.land2, true).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_land)
        changesFragment(home(), "home")
        top_navigation_constraint.setNavigationChangeListener { view, position ->
            when (position) {
                0 -> {
                    changesFragment(home(), "home")
                }
                1 -> {
                    changesFragment(Group(), "group")
                }
                2 -> {
                    changesFragment(FriendFinding(), "frd")
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragmentManager = supportFragmentManager
        val currentFragment = fragmentManager.findFragmentById(R.id.fragment3)
//        when (currentFragment!!.tag) {
//            "home" -> top_navigation_constraint.setCurrentActiveItem(0)
//            "group" -> top_navigation_constraint.setCurrentActiveItem(1)
//            "frd" -> top_navigation_constraint.setCurrentActiveItem(2)
//            else ->
//                finishAffinity()
//
//        }


    }

    //change fragment
    private fun changesFragment(targetFragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment3, targetFragment, tag)
            .addToBackStack(null)
            .commit()
    }
}
