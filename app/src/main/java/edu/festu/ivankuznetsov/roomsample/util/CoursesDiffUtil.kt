package edu.festu.ivankuznetsov.roomsample.util

import androidx.recyclerview.widget.DiffUtil
import edu.festu.ivankuznetsov.roomsample.database.entity.Course

class CoursesDiffUtil(private val oldList: List<Course>,
                      private val newList: List<Course>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}