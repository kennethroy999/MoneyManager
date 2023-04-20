package com.kinetx.moneymanager.recyclerview

import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kinetx.moneymanager.R
import com.kinetx.moneymanager.database.TransactionDatabase
import com.kinetx.moneymanager.dataclass.TransactionListClass

class TransactionListAdapter(val listener: OnTransactionListListener) : RecyclerView.Adapter<TransactionListAdapter.MyViewHolder>()
{

    private var _transactionList = emptyList<TransactionListClass>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item,parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val currentItem = _transactionList[position]
        holder.itemCategoryTwo.text = currentItem.categoryTwo
        holder.itemCategoryOne.text = currentItem.categoryOne
        holder.itemComment.text     = currentItem.comments

        val myCalendar: Calendar = Calendar.getInstance()
        myCalendar.timeInMillis = currentItem.date

        holder.itemDate.text        = "${myCalendar.get(Calendar.DAY_OF_MONTH)}-${myCalendar.get(Calendar.MONTH)+1}-${myCalendar.get(Calendar.YEAR)}"
        holder.itemAmount.text      = currentItem.amount.toString()

    }

    override fun getItemCount(): Int
    {
        return _transactionList.size
    }

    fun setData(it: List<TransactionListClass>) {
        this._transactionList = it
        notifyDataSetChanged()
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnLongClickListener
    {
        val itemCategoryOne : TextView  = itemView.findViewById(R.id.transaction_list_category_one)
        val itemCategoryTwo : TextView  = itemView.findViewById(R.id.transaction_list_category_two)
        val itemComment : TextView      = itemView.findViewById(R.id.transaction_list_comment)
        val itemDate : TextView         = itemView.findViewById(R.id.transaction_list_date)
        val itemAmount : TextView       = itemView.findViewById(R.id.transaction_list_amount)

        init
        {
            itemView.setOnLongClickListener(this)
        }

        override fun onLongClick(v: View?): Boolean
        {
            val position = adapterPosition
            if (position!=RecyclerView.NO_POSITION)
            {
                listener.onTransactionListLonClick(position)
                return true
            }
            return false
        }

    }

    interface OnTransactionListListener
    {
        fun onTransactionListLonClick(position: Int)
    }

}