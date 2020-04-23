package adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidproficiencyapp.R

class CanadaListAdapor : RecyclerView.Adapter<CanadaListAdapor.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.canada_list_row, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewTitle.text = "Value"
        holder.textViewDesc.text = "Value"
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return 10
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val textViewTitle = itemView.findViewById(R.id.textViewTittle) as TextView
            val textViewDesc = itemView.findViewById(R.id.textView2) as TextView


    }


}