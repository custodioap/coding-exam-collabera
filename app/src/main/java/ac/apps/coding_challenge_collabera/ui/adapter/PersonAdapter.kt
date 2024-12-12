package ac.apps.coding_challenge_collabera.ui.adapter

import ac.apps.coding_challenge_collabera.data.model.Person
import ac.apps.coding_challenge_collabera.databinding.ListPersonLayoutBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter (private var personList: List<Person>,
                     private val onPersonClick: (Person) -> Unit ):
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    // viewholder with databinding
    inner class PersonViewHolder(private val binding: ListPersonLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(person: Person){
            binding.person = person
            binding.executePendingBindings()

            // add listener on card
            binding.root.setOnClickListener {
                onPersonClick(person)
            }
        }
    }

    // create view for list item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        // inflate layout for binding
        val binding = ListPersonLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(personList[position])
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    fun updateData(newPersonList: List<Person>) {
        personList = newPersonList
        notifyDataSetChanged()
    }
}