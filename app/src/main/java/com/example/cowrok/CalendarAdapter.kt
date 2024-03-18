package com.example.cowrok

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.cowrok.databinding.DateItemBinding

class CalendarAdapter(
    private val calendarInterface: CalendarInterface,
    private val list: ArrayList<CalendarData>
): RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    var pos: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    inner class ViewHolder(private val binding: DateItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(calandarDataModel: CalendarData, position: Int) {
            val calendarDay = binding.tvCalenderDay
            val calendarDate = binding.tvCalenderDate
            val cardView = binding.root

            if (pos == position) {
                calandarDataModel.isSelected = true
            }

            if (calandarDataModel.isSelected) {
                pos = -1
                calendarDay.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                calendarDate.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                cardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.black))
            } else {
                calendarDay.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
                cardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
            }

            calendarDay.text = calandarDataModel.calendarDay
            calendarDate.text = calandarDataModel.calendarDate

            cardView.setOnClickListener {
                calendarInterface.onSelect(calandarDataModel, adapterPosition, calendarDate)
            }
        }
    }

    interface CalendarInterface {
        fun onSelect(calendarData: CalendarData, position: Int, day: TextView)
    }

    fun setPosition(pos: Int) {
        this.pos = pos
    }

    fun updateList(calendarList: ArrayList<CalendarData>) {
        list.clear()
        list.addAll(calendarList)
        notifyDataSetChanged()
    }
}
