package team.cesea.myguide.UI.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyadapt.view.*
import team.cesea.myguide.R
import android.view.ViewTreeObserver
import android.view.MotionEvent





class recyada(activity: FragmentActivity) : RecyclerView.Adapter<recyada.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyada.ViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyadapt, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: recyada.ViewHolder, position: Int) {
        holder.text.text = "raj"


    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.textView7
    }
}