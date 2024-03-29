package edu.festu.ivankuznetsov.roomsample.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.festu.ivankuznetsov.roomsample.database.CourseDB
import edu.festu.ivankuznetsov.roomsample.database.entity.Course
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

class CourseViewModel : ViewModel() {
    private val executorService = Executors.newSingleThreadExecutor()

    var courses = MutableLiveData<MutableList<Course>>()
    init {
        courses.postValue(mutableListOf())
    }

    fun addData(context: Context, course: Course) {
        executorService.execute {
            val id = CourseDB.getInstance(context)
                .courseDao().add(course)
                courses.postValue(courses.value.apply {
                    this?.add(Course(id, course.author,course.courseName))
                })
            //неэффективно
           // getAll(context)
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

    fun dropCourse(context: Context, course: Course) {
        CompletableFuture.runAsync({
            Log.d(TAG, "DELETING")
            CourseDB.getInstance(context)
                .courseDao().delete(course)
        },executorService).thenApply {
            Log.d(TAG, "RE-FETCHING DATA")
            getAll(context)
        }
    }
    companion object{
        private val TAG = CourseViewModel::class.java.simpleName
    }
}