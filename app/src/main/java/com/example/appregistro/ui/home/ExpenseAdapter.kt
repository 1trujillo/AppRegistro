package com.example.appregistro.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appregistro.R
import com.example.appregistro.data.model.Expense

class ExpenseAdapter(
    private val items: MutableList<Expense>,
    private val onItemClick: (Expense) -> Unit
) : RecyclerView.Adapter<ExpenseAdapter.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val tvAmount: TextView = view.findViewById(R.id.tvAmount)
        val tvCategory: TextView = view.findViewById(R.id.tvCategory)
        val tvDesc = view.findViewById<TextView>(R.id.tvTitle)

        init {
            view.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    onItemClick(items[pos])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val e = items[position]
        holder.tvAmount.text = String.format("$ %.2f", e.amount)
        holder.tvCategory.text = e.category
        holder.tvDesc.text = e.description
    }

    override fun getItemCount() = items.size

    fun add(expense: Expense) {
        items.add(0, expense)
        notifyItemInserted(0)
    }
}
