package team.cesea.myguide.UI.Landing

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import team.cesea.myguide.R

class FriendFinding : Fragment() {

    companion object {
        fun newInstance() = FriendFinding()
    }

    private lateinit var viewModel: FriendFindingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.friend_finding_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FriendFindingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
