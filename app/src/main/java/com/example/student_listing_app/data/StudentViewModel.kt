package com.example.student_listing_app.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application): AndroidViewModel(application) {

    val getAllData: LiveData<List<Student>>
    private val repository: StudentRepository

    init {
        val studentDao = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)   //initializing a repository
        getAllData = repository.getAllData
    }

    fun addStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addStudent(student)
        }

    }
}