package kz.afr.efreight.view.stock_arrivale

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kz.afr.efreight.R
import kz.afr.efreight.network.model.Procedure

class RecyclerAdapter (val list: ArrayList<Procedure>, val context: Context, val delegate: ProcedureDelegate) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewholder {
        val holder = LayoutInflater.from(context).inflate(R.layout.rv_stock_arrival_item, parent, false)
        return RecyclerViewholder(holder, delegate)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerViewholder, position: Int) {
        holder.bind(list.get(position))
    }


    class RecyclerViewholder constructor(view: View, val delegate: ProcedureDelegate) : RecyclerView.ViewHolder(view){
        val procedure_id: TextView = view.findViewById(R.id.proc_id)
        val name: TextView = view.findViewById(R.id.flight_name)
        val date: TextView = view.findViewById(R.id.flight_date)
        val holder: View = view.findViewById(R.id.holder)

        fun bind(procedure: Procedure){
            procedure_id.text = "Номер полета:" + (procedure.procedure_id.toString())
            name.text = "Название рейса: " + procedure.flight_number
            date.text = "Дата вылета: "+procedure.flight_date
            holder.setOnClickListener {
                date.text = " CLICKED"
                delegate.itemClicked(procedure)
            }
        }
    }

    interface ProcedureDelegate{
        fun itemClicked(item: Procedure)
    }
}