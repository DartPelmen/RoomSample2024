package edu.festu.ivankuznetsov.roomsample.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.festu.ivankuznetsov.roomsample.database.CourseDB
import edu.festu.ivankuznetsov.roomsample.database.entity.Course
import java.util.concurrent.Executors

class CourseViewModel : ViewModel() {
    private val executorService = Executors.newSingleThreadExecutor()

    var courses = MutableLiveData<MutableList<Course>>()
    init {
        courses.postValue(mutableListOf())
    }

    fun addData(context: Context, course: Course) {
        executorService.execute {
            CourseDB.getInstance(context)
                .courseDao().add(course)
            //неэффективно
            getAll(context)
        }
    }

    fun getAll(context: Context) {
        executorService.execute {
            courses.postValue(
                CourseDB.getInstance(context)
                    .courseDao().select().toMutableList()
            )
        }
    }


}