package belinda.freitas.examen_final.students_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import model.Student

class StudentsListViewModel : ViewModel() {
    private val _users = arrayListOf<Student>()/*listOf(
        Student("Jeanne","Doe", "jeanne.doe@yahoo.uk", "AL 1"),
        Student("Bélinda","Freitas", "belinda.freitas@gmail.com", "AL 4"),
        Student("John","Doe", "john.doe@yahoo.fr", "SRS 1"),
        Student("Xavier","Adédjé", "francois.xavier@outlook.fr", "MP 1"),
        Student("Kristina","Tonteri-Young", "kty@gmail.com", "AL 3"),
        Student("Gloria","Ferrari", "gloria.ferr@yahoo.fr", "AD 4"),
        Student("Antoine","Doe", "tony.doe@gmail.com", "AL 2"),
        Student("Alba","Baptista", "alba.baptista@info.pt", "IABD 5"),
        Student("Honoré","Adjé", "hono.adje@gmail.com", "AL 1"),
        Student("Marc","Adédjé", "marek@yahoo.fr", "SRS 3"),
        Student("Esther","Exposito", "esther_exp@yahoo.uk", "TL 2"),
        Student("Innocent","Gafa", "innogafa@outlook.fr", "CCA 1"),
        )*/

        private val _texts = MutableLiveData<List<Student>>().apply {
        value = _users
    }

    val students: LiveData<List<Student>> = _texts
}