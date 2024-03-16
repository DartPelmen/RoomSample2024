package edu.festu.ivankuznetsov.roomsample.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.festu.ivankuznetsov.roomsample.database.entity.Course
import edu.festu.ivankuznetsov.roomsample.databinding.CourseItemBinding

/**
 * Адаптер для RecyclerView.
 * Связывает данные (массив) и UI (ячейки списка)
 * */
class CourseAdapter(private val clicker: (String) ->Unit = {}): RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    /**
     * данные
     * */
    private var courses: MutableList<Course> = mutableListOf()

        fun getCourses() = courses
        fun setCourses(value: List<Course>){
            courses = value.toMutableList()
        }

    /**
     * что делать при создании "обслуживателя" ячейки
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CourseItemBinding
            .inflate(LayoutInflater
                .from(parent.context)))
    }
    /**
     * количество данных
     * */
    override fun getItemCount(): Int = courses.size
    /**
     * что делать в случае привязки "обслуживателя" ячейки
     * */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "REDRAW WITH ${courses[position]}")
        holder.itemBinding.authorLabel.text = courses[position].author
        holder.itemBinding.courseLabel.text = courses[position].courseName

        holder.itemView.setOnClickListener {
            clicker(courses[position].author)
        }
    }
    /**
     * "обслуживатель"
     * */
    inner class ViewHolder(val itemBinding: CourseItemBinding):
        RecyclerView.ViewHolder(itemBinding.root)

    companion object {
        private val TAG = CourseAdapter::class.java.simpleName
    }
}
