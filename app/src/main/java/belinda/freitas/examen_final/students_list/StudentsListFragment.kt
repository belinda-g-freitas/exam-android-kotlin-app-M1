package belinda.freitas.examen_final.students_list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import belinda.freitas.examen_final.R
import belinda.freitas.examen_final.databinding.FragmentStudentsListBinding
import belinda.freitas.examen_final.databinding.ItemStudentsListBinding
import model.Student
import model.students

class StudentsListFragment : Fragment() {

    private var _binding: FragmentStudentsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStudentsListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerviewTransform
        val studentAdapter = StudentsListAdapter(students, container)
        recyclerView.adapter = studentAdapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    class StudentsListAdapter(private val students: List<Student>,private val viewGroup: ViewGroup?): RecyclerView.Adapter<StudentsListAdapter.StudentListViewHolder>() {

        private val drawables = listOf(
            R.drawable.avatar_1,
            R.drawable.avatar_2,
            R.drawable.avatar_3,
            R.drawable.avatar_4,
            R.drawable.avatar_5,
            R.drawable.avatar_6,
            R.drawable.avatar_7,
            R.drawable.avatar_8,
            R.drawable.avatar_9,
            R.drawable.avatar_10,
            R.drawable.avatar_11,
            R.drawable.avatar_12,
            R.drawable.avatar_13,
            R.drawable.avatar_14,
            R.drawable.avatar_15,
            R.drawable.avatar_16,
        )

        inner class StudentListViewHolder(itemView: ItemStudentsListBinding): RecyclerView.ViewHolder(itemView.root){
            val imageView: androidx.constraintlayout.utils.widget.ImageFilterView= itemView.imageViewStudent!!
            val nameTxtV: TextView = itemView.name
            val emailTxtV: TextView = itemView.email
            val gradeTxtV: TextView = itemView.grade
            val detailsBtn: ImageButton = itemView.phoneBtn
            val item = itemView.toDetails
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListViewHolder {
            val binding = ItemStudentsListBinding.inflate(LayoutInflater.from(parent.context))
            return StudentListViewHolder(binding)
        }

        override fun onBindViewHolder(holder: StudentListViewHolder, position: Int) {
            var currentUser = students[position]

            holder.imageView.setImageDrawable(ResourcesCompat.getDrawable(holder.imageView.resources, drawables[position], null))
            holder.nameTxtV.text = "${currentUser.firstname} ${currentUser.lastname}"
            holder.emailTxtV.text = currentUser.email
            holder.gradeTxtV.text = currentUser.grade
            // call number
            holder.detailsBtn.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel:${currentUser.phone}"))
                startActivity(viewGroup!!.context, intent, null)
            }
            // on tile click
            holder.item.setOnClickListener {
                var navController: NavController?
                navController = findNavController(it)
                var bundle: Bundle
                bundle = Bundle()
                bundle.putString("email", currentUser.email)
                bundle.putString("grade", currentUser.grade)
                bundle.putString("firstname", currentUser.firstname)
                bundle.putString("lastname", currentUser.lastname)
                bundle.putInt("photo", drawables[position])
                navController?.navigate(R.id.action_nav_students_list, bundle)
            }
        }

        override fun getItemCount(): Int {
            return  students.size
        }
    }
}