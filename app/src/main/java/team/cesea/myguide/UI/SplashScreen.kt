package team.cesea.myguide.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import kotlinx.coroutines.*
import team.cesea.myguide.R
import team.cesea.myguide.utilities.SessionMaintainence
import kotlin.coroutines.CoroutineContext

class SplashScreen : Fragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()//To change initializer of created properties use File | Settings | File Templates.
    val navOptions = NavOptions.Builder().setPopUpTo(R.id.splashScreen, true).build()

    companion object {
        fun newInstance() = SplashScreen()
    }

    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)
        launch {
            delay(3000)
            withContext(Dispatchers.Main) {
                val instance = SessionMaintainence.instance!!
                if (instance.Uid == "") {
                    Navigation.findNavController(requireActivity(), R.id.fragment)
                        .navigate(
                            R.id.action_splashScreen_to_login, null,
                            navOptions
                        )
                } else {
                    Navigation.findNavController(requireActivity(), R.id.fragment)
                        .navigate(
                            R.id.action_splashScreen_to_land2, null,
                            navOptions
                        )
                }
            }
        }
    }


}
