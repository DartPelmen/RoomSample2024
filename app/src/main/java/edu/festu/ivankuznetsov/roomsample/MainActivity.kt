package edu.festu.ivankuznetsov.roomsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import edu.festu.ivankuznetsov.roomsample.database.CourseDB
import edu.festu.ivankuznetsov.roomsample.database.CourseDatabase
import edu.festu.ivankuznetsov.roomsample.database.entity.Course
import edu.festu.ivankuznetsov.roomsample.databinding.ActivityMainBinding
import edu.festu.ivankuznetsov.roomsample.ui.CourseAdapter
import edu.festu.ivankuznetsov.roomsample.util.CoursesDiffUtil
import edu.festu.ivankuznetsov.roomsample.viewmodel.CourseViewModel
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: CourseDatabase
    private lateinit var adapter: CourseAdapter
    private val itemModel: CourseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        database = CourseDB.getInstance(applicationContext)
        setContentView(binding.root)

        adapter = CourseAdapter()
        binding.coursesList.layoutManager = LinearLayoutManager(this)
        binding.coursesList.adapter = adapter
        binding.coursesList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


        itemModel.courses.observe(this){
            val productDiffUtilCallback =
                CoursesDiffUtil(adapter.getCourses(), it)
            val productDiffResult =
                DiffUtil.calculateDiff(productDiffUtilCallback)
            adapter.setCourses(it)
            productDiffResult.dispatchUpdatesTo(adapter)
        }
        itemModel.getAll(this)

        binding.addButton.setOnClickListener {
            if(binding.authorTextField.text.isNullOrBlank()){
                binding.authorTextLayout.error = "Добавьте автора"
            } else if(binding.courseTextField.text.isNullOrBlank()){
                binding.authorTextLayout.error = ""
                binding.courseTextLayout.error = "Добавьте название курса"
            } else {
                binding.courseTextLayout.error = ""
                itemModel.addData(this,Course(author = binding.authorTextField.text.toString(),
                    courseName = binding.courseTextField.text.toString()))
            }
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}