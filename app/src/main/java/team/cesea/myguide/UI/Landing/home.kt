package team.cesea.myguide.UI.Landing

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.home_fragment.*
import team.cesea.myguide.R
import team.cesea.myguide.UI.adapter.recyada
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

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val instance = SessionMaintainence.instance!!
        val RecyclerViewLayoutManager = LinearLayoutManager(context)
        dss.layoutManager = RecyclerViewLayoutManager
        val jobpostHorizontalAdapter =
            recyada(activity!!)
        dss.adapter = jobpostHorizontalAdapter
        dss.isNestedScrollingEnabled = false;
        name.text = "Hi," + instance.fullname
        date.text = current_date()
        Glide.with(context!!)
            .load(instance.profilepic)
            .placeholder(R.drawable.imjhk)
            .into(userimages)
        addmone.setOnClickListener {
            showDiag()
        }
        val onScrollChangedListener = ViewTreeObserver.OnScrollChangedListener {
            val scrollY = re.scrollY;
            val scrollx = re.scrollX;
            Log.e("x", scrollY.toString())
            if (scrollY >= 480) {
                textView10.visibility = View.VISIBLE

            } else {

                textView10.visibility = View.GONE

            }
//            487
            Log.e("Y", scrollx.toString())
        }
        re.setOnTouchListener(object : View.OnTouchListener {
            private var observer: ViewTreeObserver? = null

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                if (observer == null) {
                    observer = re.viewTreeObserver
                    observer!!.addOnScrollChangedListener(onScrollChangedListener)

                } else if (!observer!!.isAlive) {


                    observer!!.removeOnScrollChangedListener(onScrollChangedListener)
                    observer = re.viewTreeObserver
                    observer!!.addOnScrollChangedListener(onScrollChangedListener)
                }

                return false
            }
        })
        bac.setOnClickListener {
            showDiag()
        }
        addmo.setOnClickListener {
            addmone.visibility = View.VISIBLE
            addmo.visibility = View.GONE
            add.visibility = View.GONE
            addtwo.visibility = View.GONE
            val imm =
                activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        }

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


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(Build.VERSION_CODES.FROYO)
    private fun showDiag() {

        val dialogView = View.inflate(activity, R.layout.dialog, null)

        val dialog = Dialog(activity!!, R.style.MyAlertDialogStyle)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogView)

        val imageView = dialog.findViewById(R.id.closeDialogImg) as Button
        imageView.setOnClickListener { revealShow(dialogView, false, dialog) }

        dialog.setOnShowListener { revealShow(dialogView, true, null) }

        dialog.setOnKeyListener(DialogInterface.OnKeyListener { dialogInterface, i, keyEvent ->
            if (i == KeyEvent.KEYCODE_BACK) {

                revealShow(dialogView, false, dialog)
                return@OnKeyListener true
            }

            false
        })



        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun revealShow(dialogView: View, b: Boolean, dialog: Dialog?) {

        val view = dialogView.dialogs

        val w = view.width
        val h = view.height

        val endRadius = Math.hypot(w.toDouble(), h.toDouble()).toInt()

        val cx = (addmone.x + addmone.width / 2).toInt()
        val cy = addmone.y.toInt() + addmone.height + 56


        if (b) {
            val revealAnimator =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, endRadius.toFloat())

            view.visibility = View.VISIBLE
            revealAnimator.duration = 700
            revealAnimator.start()

        } else {

            val anim =
                ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius.toFloat(), 0f)

            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    dialog!!.dismiss()
                    view.visibility = View.INVISIBLE

                }
            })
            anim.duration = 700
            anim.start()
        }

    }
}
