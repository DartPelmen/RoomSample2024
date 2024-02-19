package edu.festu.ivankuznetsov.roomsample.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import edu.festu.ivankuznetsov.roomsample.database.entity.Course

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(course: Course)
    @Update
    fun update(course: Course)
    @Delete
    fun delete(course: Course)
    @Query("SELECT * FROM COURSES")
    fun select():List<Course>
    @Query("SELECT * FROM COURSES WHERE courseId = :id")
    fun getById(id:Int):Course?
}