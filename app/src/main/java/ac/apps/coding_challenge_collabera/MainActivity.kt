package ac.apps.coding_challenge_collabera

import ac.apps.coding_challenge_collabera.databinding.MainActivityBinding
import ac.apps.coding_challenge_collabera.ui.adapter.PersonAdapter
import android.os.Bundle
import androidx.activity.ComponentActivity
import ac.apps.coding_challenge_collabera.ui.viewmodel.PersonViewModel
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.google.android.material.dialog.MaterialDialogs

class MainActivity : AppCompatActivity() {


    private lateinit var binding: MainActivityBinding
    private lateinit var adapter: PersonAdapter
    private val viewModel: PersonViewModel by lazy { ViewModelProvider(this).get(PersonViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize the toolbar
        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Person List"

        // setup adapter for recyclerview
        adapter = PersonAdapter(emptyList()) { person ->
            val intent = Intent(this, PersonDetails::class.java).apply{
                putExtra("person", person)
            }

            startActivity(intent)
        }

        binding.rvPerson.layoutManager = LinearLayoutManager(this)
        binding.rvPerson.adapter = adapter

        // observe the data from view model
        viewModel.personData.observe(this) { personList ->
            personList?.let {
                adapter.updateData(it)
                binding.pullToRefresh.isRefreshing = false //stop the refreshing
            }
        }

        //set up pull to refresh
        binding.pullToRefresh.setOnRefreshListener {
            viewModel.fetchPersonData(10)
        }

        // Observe errors
        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                println("Error: $it")
            }
        }

        viewModel.fetchPersonData(10) //load 10 person data initially

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                // Handle add action
                showMaterialDialog() // show dialog
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showMaterialDialog(){
        val dialog = MaterialDialog(this).show {
            title(text = "Search Person")
            input(hint = "Enter a number", prefill = "", inputType = android.text.InputType.TYPE_CLASS_NUMBER) { dialog, text ->
                // This is the input listener; 'text' will contain the entered text.
                val number = text.toString().toIntOrNull()

                if (number != null) {
                    // If it's a valid number, show a toast or use the number
                    viewModel.fetchPersonData(number)
                } else {
                    // If the input is not a valid number
                    Toast.makeText(this@MainActivity, "Please enter a valid number", Toast.LENGTH_SHORT).show()
                }
            }

            positiveButton(text = "OK") { dialog ->
                // Action when OK button is clicked
                dialog.dismiss()
            }
            negativeButton(text = "Cancel") { dialog ->
                // Action when Cancel is clicked
                dialog.dismiss()

            }
        }
    }
}