package team.cesea.myguide.UI.Landing

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_fragment.*
import team.cesea.myguide.R
import team.cesea.myguide.utilities.SessionMaintainence
import java.util.*

class home : Fragment() {

    companion object {
        fun newInstance() = home()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val instance = SessionMaintainence.instance!!
        name.text = "Hi," + instance.fullname
        date.text = current_date()
        Glide.with(context!!)
            .load(instance.profilepic)
            .placeholder(R.drawable.imjhk)
            .into(userimages)
    }

    @SuppressLint("SimpleDateFormat")
    fun current_date(): String? {
        val sdf = java.text.SimpleDateFormat("MMMM dd,yyyy")
        val currentDate = sdf.format(Date())
        return currentDate
    }


    fun current_day(): String? {
        val calendar = Calendar.getInstance()
        val date = calendar.time
        // full name form of the day
        val day = java.text.SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)
        return day
    }
}
