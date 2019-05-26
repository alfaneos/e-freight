package kz.afr.efreight.view.stock_arrivale

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.afr.efreight.R
import kz.afr.efreight.network.model.Procedure

class RecyclerAdapter (val list: ArrayList<Procedure>, val context: Context) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewholder {
        val holder = LayoutInflater.from(context).inflate(R.layout.rv_stock_arrival_item, parent, false)
        return RecyclerViewholder(holder)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerViewholder, position: Int) {
        holder.bind(list.get(position))
    }


    class RecyclerViewholder constructor(view: View) : RecyclerView.ViewHolder(view){
        val procedure_id: TextView = view.findViewById(R.id.proc_id)
        val name: TextView = view.findViewById(R.id.flight_name)
        val date: TextView = view.findViewById(R.id.flight_date)

        init {
        }
        fun bind(procedure: Procedure){
            procedure_id.setText(procedure.procedure_id)
            name.text = procedure.flight_number
            date.text = procedure.flight_date
        }
    }
}