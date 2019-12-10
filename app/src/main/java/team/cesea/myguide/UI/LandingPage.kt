package team.cesea.myguide.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.landing_pag_fragment.*
import org.jetbrains.anko.toast
import team.cesea.myguide.R

class LandingPage : Fragment() {
//    val navOptions = NavOptions.Builder().setPopUpTo(R.id.landingPage, true).build()

    companion object {
        fun newInstance() = LandingPage()
    }

    private lateinit var viewModel: LandingPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.landing_pag_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LandingPageViewModel::class.java)
//        Navigation.findNavController(requireActivity(), R.id.fragment)
//            .navigate(
//                R.id.action_landingPage_to_home2, null,
//                navOptions
//            )
//        top_navigation_constraint.setNavigationChangeListener { view, position ->
//            when (position) {
//                0 -> {
//                    Navigation.findNavController(requireActivity(), R.id.fragment)
//                        .navigate(
//                            R.id.action_landingPage_to_home2, null,
//                            navOptions
//                        )
//                }
//                1 -> {
//                    Navigation.findNavController(requireActivity(), R.id.fragment)
//                        .navigate(
//                            R.id.action_landingPage_to_friendFinding, null,
//                            navOptions
//                        )
//                }
//                2 -> {
//                    Navigation.findNavController(requireActivity(), R.id.fragment)
//                        .navigate(
//                            R.id.action_landingPage_to_group, null,
//                            navOptions
//                        )
//                }
//            }
//            activity!!.toast(position.toString())
//        }
//
//        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
//            Navigation.findNavController(requireActivity(), R.id.fragment)
//                .navigate(
//                    R.id.action_landingPage_to_login, null,
//                    navOptions
//                )
//        }
//        callback.isEnabled = true
    }

}
