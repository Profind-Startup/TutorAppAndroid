package pe.com.retrofind.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prototype_reservation.view.*
import kotlinx.android.synthetic.main.prototype_subject.view.*
import kotlinx.android.synthetic.main.prototype_subject.view.txtPostBody
import kotlinx.android.synthetic.main.prototype_subject.view.txtPostTitle
import pe.com.retrofind.R
import pe.com.retrofind.activities.DetailReservationActivity
import pe.com.retrofind.models.Reservation
import pe.com.retrofind.models.Subject
import java.io.Serializable


class ReservationAdapter(val postList: List<Reservation>) :
    RecyclerView.Adapter<ReservationAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemReservation = itemView.itemReservation
        fun bindTo(reservation: Reservation) {

            itemReservation.setOnClickListener {
                this@ReservationAdapter.selectedIndex = adapterPosition
                val intent = Intent(it.context, DetailReservationActivity::class.java)
                intent.putExtra("reservation", reservation as Serializable)
                it.context.startActivity(intent)
            }
        }
    }

    var selectedIndex = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.prototype_reservation,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return postList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.txtPostTitle.text ="Día de Reserva: " +  postList.get(position).reservation_date
        holder.itemView.txtPostBody.text = "Tema Reservado" + postList.get(position).subject_id
        holder.bindTo(postList.get(position))
    }
}

