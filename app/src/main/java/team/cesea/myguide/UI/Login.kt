package team.cesea.myguide.UI

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.login_fragment.*
import team.cesea.myguide.R
import team.cesea.myguide.UI.adapter.SliderAdapter
import team.cesea.myguide.utilities.SessionMaintainence

class Login : Fragment() {
    val navOptions = NavOptions.Builder().setPopUpTo(R.id.login, true).build()
    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    var slogan: ArrayList<String>? = null

    lateinit var mGoogleSignInOptions: GoogleSignInOptions

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
        firebaseAuth = FirebaseAuth.getInstance()
        configureGoogleSignIn()
        val sliderImage = ArrayList<Int>()
        sliderImage.add(R.drawable.ic_android_black_24dp)
        sliderImage.add(R.drawable.ic_android_black_24dp)
        sliderImage.add(R.drawable.ic_android_black_24dp)

        slogan = arrayListOf<String>("Confident Coding!!", "Explore Many!!", "Make Peaceful Life!!")
        viewPager.adapter = SliderAdapter(activity!!, sliderImage, slogan!!)
        indicator.setupWithViewPager(viewPager, true)
        button2.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragment)
                .navigate(
                    R.id.action_login_to_land2, null,
                    navOptions
                )
        }

        google_button.setOnClickListener {
            signIn()
        }
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            activity!!.finishAffinity()
        }
        callback.isEnabled = true
    }

    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(activity!!, mGoogleSignInOptions)
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = firebaseAuth.currentUser
                val instance = SessionMaintainence.instance!!
                instance.Uid = user!!.uid
                instance.profilepic = user.photoUrl.toString()
                instance.fullname = user.displayName
                Navigation.findNavController(requireActivity(), R.id.fragment)
                    .navigate(
                        R.id.action_login_to_land2, null,
                        navOptions
                    )
            } else {
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            Navigation.findNavController(requireActivity(), R.id.fragment)
                .navigate(
                    R.id.action_login_to_land2, null,
                    navOptions
                )
        }
    }
}
