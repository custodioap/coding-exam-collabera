package ac.apps.coding_challenge_collabera

import ac.apps.coding_challenge_collabera.data.model.Person
import ac.apps.coding_challenge_collabera.databinding.ActivityPersonDetailsBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Suppress("DEPRECATION")
class PersonDetails : AppCompatActivity() {

    private lateinit var binding: ActivityPersonDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get the person object from the intent
        val person = intent.getParcelableExtra<Person>("person")

        // bind the data to the UI
        person?.let {
            binding.person = it
        }

    }
}