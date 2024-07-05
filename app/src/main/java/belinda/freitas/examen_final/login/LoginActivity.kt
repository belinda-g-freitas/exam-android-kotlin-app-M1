package belinda.freitas.examen_final.ui.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import belinda.freitas.examen_final.MainActivity
import belinda.freitas.examen_final.R
import belinda.freitas.examen_final.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

	private lateinit var loginViewModel: LoginViewModel
	private lateinit var binding: ActivityLoginBinding
	lateinit var sharedPreferences: SharedPreferences
	lateinit var checkbox: CheckBox

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityLoginBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val email = binding.email
		val password = binding.password
		val login = binding.login
		val loading = binding.loading
		checkbox = binding.checkbox

		sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE)
		if(sharedPreferences.getString("EMAIL", "")!!.isNotEmpty()) {
			var intent = Intent(this, MainActivity::class.java)
			startActivity(intent)
		}

		//sharedPreferences = getSharedPreferences("USER", Context.MODE_PRIVATE)

		loginViewModel = ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)

		loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
			val loginState = it ?: return@Observer

			// disable login button unless both username / password is valid
			login.isEnabled = loginState.isDataValid

			if (loginState.usernameError != null) {
				email.error = getString(loginState.usernameError)
			}
			if (loginState.passwordError != null) {
				password.error = getString(loginState.passwordError)
			}
			if (!checkbox.isChecked){
				Toast.makeText(applicationContext,"Veuillez accepter les conditions d'utilisation.",Toast.LENGTH_SHORT).show()
			}
		})

		loginViewModel.loginResult.observe(this@LoginActivity, Observer {
			val loginResult = it ?: return@Observer

			loading.visibility = View.GONE
			if (loginResult.error != null) {
				showLoginFailed(loginResult.error)
			}
			if (loginResult.success != null) {
				var editor = sharedPreferences.edit()
				editor.putString("EMAIL", email.text.toString().trim())
				editor.apply()
				updateUiWithUser(loginResult.success)
				setResult(AppCompatActivity.RESULT_OK)

				val intent = Intent(this, MainActivity::class.java)
				intent.putExtra("EMAIL", email.text.toString().trim())
				startActivity(intent)
				//Complete and destroy login activity once successful
				finish()
			}
		})

		email.afterTextChanged {
			loginViewModel.loginDataChanged(
				email.text.toString(),
				password.text.toString()
			)
		}

		password.apply {
			afterTextChanged {
				loginViewModel.loginDataChanged(
					email.text.toString(),
					password.text.toString()
				)
			}

			setOnEditorActionListener { _, actionId, _ ->
				when (actionId) {
					EditorInfo.IME_ACTION_DONE ->
						loginViewModel.login(
							email.text.toString(),
							password.text.toString()
						)
				}
				false
			}

			login.setOnClickListener {
				loading.visibility = View.VISIBLE
				loginViewModel.login(email.text.toString(), password.text.toString())
			}
		}
	}

	private fun updateUiWithUser(model: LoggedInUserView) {
		val welcome = getString(R.string.welcome)
		val displayName = model.displayName
		// TODO : initiate successful logged in experience
		Toast.makeText(
			applicationContext,
			"$welcome ${sharedPreferences.getString("EMAIL","")}",
			Toast.LENGTH_LONG
		).show()
	}

	private fun showLoginFailed(@StringRes errorString: Int) {
		Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
	}
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
	this.addTextChangedListener(object : TextWatcher {
		override fun afterTextChanged(editable: Editable?) {
			afterTextChanged.invoke(editable.toString())
		}

		override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

		override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
	})
}