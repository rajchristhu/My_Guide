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
import kotlinx.android.synthetic.main.login_fragment.*
import team.cesea.myguide.R

class Login : Fragment() {
    val navOptions = NavOptions.Builder().setPopUpTo(R.id.login, true).build()

    companion object {
        fun newInstance() = Login()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        button2.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragment)
                .navigate(
                    R.id.action_login_to_landingPage, null,
                    navOptions
                )
        }


        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            Navigation.findNavController(requireActivity(), R.id.fragment)
                .navigate(
                    R.id.action_login_to_getStart2, null,
                    navOptions
                )
        }
        callback.isEnabled=true
    }


}
