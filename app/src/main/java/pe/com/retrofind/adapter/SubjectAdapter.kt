package pe.com.retrofind.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prototype_subject.view.*
import pe.com.retrofind.R
import pe.com.retrofind.models.Subject

/*class SubjectAdapter(var subjects: List<Subject>) : RecyclerView.Adapter<SubjectAdapter.SubjectPrototype>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectPrototype {
       return SubjectPrototype(LayoutInflater.from(parent.context).inflate(R.layout.prototype_subject, parent, false    ))
    }

    override fun getItemCount(): Int {
    return subjects.size
    }

    override fun onBindViewHolder(holder: SubjectPrototype, position: Int) {
    holder.txtSubject.text = subjects.get(position).name
    }


    class SubjectPrototype(itemView: View) : RecyclerView.ViewHolder(itemView){
    var txtSubject =itemView.nameSubject
    }


}*/
class SubjectAdapter(val postList: List<Subject>, val context: Context) :
    RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.prototype_subject,
            parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.txtPostTitle.text = postList.get(position).name
        holder.itemView.txtPostBody.text = postList.get(position).area

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}