package edu.festu.ivankuznetsov.roomsample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.festu.ivankuznetsov.roomsample.database.dao.CourseDao
import edu.festu.ivankuznetsov.roomsample.database.entity.Course

@Database(entities = [Course::class], version = 1)
abstract class CourseDatabase: RoomDatabase() {
    abstract fun courseDao(): CourseDao
}

//class DBSingleton(){
//    companion object{
//        private lateinit var instance: CourseDatabase
//        private fun getInstance(context: Context):CourseDatabase{
//            if(!::instance.isInitialized){
//                synchronized(DBSingleton::class.java){
//                    if(!::instance.isInitialized){
//                        instance = Room.databaseBuilder(context,
//                            CourseDatabase::class.java,
//                            "course-db").build()
//                    }
//                }
//            }
//            return instance;
//        }
//    }
//}


object CourseDB{
    private lateinit var instanse: CourseDatabase
    fun getInstance(context: Context): CourseDatabase{
        if(!::instanse.isInitialized){
            instanse = Room.databaseBuilder(context,
                CourseDatabase::class.java,
                "course-db").build()
        }
        return instanse;
    }
}