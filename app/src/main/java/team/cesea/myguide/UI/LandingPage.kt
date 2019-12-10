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
import team.cesea.myguide.R

class LandingPage : Fragment() {
    val navOptions = NavOptions.Builder().setPopUpTo(R.id.landingPage, true).build()

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
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            Navigation.findNavController(requireActivity(), R.id.fragment)
                .navigate(
                    R.id.action_landingPage_to_login, null,
                    navOptions
                )
        }
        callback.isEnabled = true
    }

}
