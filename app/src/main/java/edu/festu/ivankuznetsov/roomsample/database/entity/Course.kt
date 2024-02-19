package edu.festu.ivankuznetsov.roomsample.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("courses")
data class Course(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "courseId") val id: Long = 0,
    @ColumnInfo("author") val author: String,
    @ColumnInfo("title") val courseName: String
)