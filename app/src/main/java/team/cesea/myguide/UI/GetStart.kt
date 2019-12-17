package team.cesea.myguide.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.get_start_fragment.*
import team.cesea.myguide.R

class GetStart : Fragment() {
    val navOptions = NavOptions.Builder().setPopUpTo(R.id.getStart, true).build()

    companion object {
        fun newInstance() = GetStart()
    }

    private lateinit var viewModel: GetStartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.get_start_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GetStartViewModel::class.java)

    }

}
