package belinda.freitas.examen_final.ui.student_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import belinda.freitas.examen_final.databinding.FragmentStudentDetailsBinding

class StudentDetailsFragment : Fragment() {

    private var _binding: FragmentStudentDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val email = arguments?.getString("email")
        val grd = arguments?.getString("grade")
        val f_name= arguments?.getString("firstname")
        val lastname = arguments?.getString("lastname")
        val photo = arguments?.getInt("photo")

        _binding = FragmentStudentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val last: TextView = binding.lastname
        last.text = lastname
        val first: TextView = binding.firstname
        first.text = f_name
        val mail: TextView = binding.email
        mail.text = email
        val grade: TextView = binding.grade
        grade.text = grd
        val img: androidx.constraintlayout.utils.widget.ImageFilterView = binding.photo
        img.setImageDrawable(ResourcesCompat.getDrawable(resources, photo!!, null))

        activity?.title = "$f_name $lastname"
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.title = "Liste des utilisateurs"
        _binding = null
    }

}