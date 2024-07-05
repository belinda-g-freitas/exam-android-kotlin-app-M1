package belinda.freitas.examen_final.ui.student_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.Student
import model.students

class StudentDetailsViewModel(student: Student) : ViewModel() {

    private val _text = MutableLiveData<Student>().apply {
        value = student
    }
    val text: LiveData<Student> = _text
}