package pe.com.retrofind.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prototype_subject.view.*
import pe.com.retrofind.R
import pe.com.retrofind.models.Reservation
import pe.com.retrofind.models.Subject


class ReservationAdapter(val postList: List<Reservation>, val context: Context) :
    RecyclerView.Adapter<ReservationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.prototype_reservation,
                parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.txtPostTitle.text = postList.get(position).reservation_date
        holder.itemView.txtPostBody.text = postList.get(position).subject_id

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}