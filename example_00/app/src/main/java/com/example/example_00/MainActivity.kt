package com.example.example_00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ContactAdapter({ contact ->
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NAME,contact.name)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NUMBER,contact.number)
            intent.putExtra(AddActivity.EXTRA_CONTACT_ID,contact.id)
            startActivity(intent)
        },{ contact ->
            deleteDialog(contact)
        })

        val lm = LinearLayoutManager(this)
        val main_recycleView = findViewById<RecyclerView>(R.id.main_recycleview)
        main_recycleView.adapter = adapter
        main_recycleView.layoutManager = lm
        main_recycleView.setHasFixedSize(true)

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(this, Observer<List<Contact>>{
            contacts -> adapter.setContacts(contacts!!)
        })

        findViewById<Button>(R.id.main_button).setOnClickListener {
            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteDialog(contact: Contact){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("No"){ _, _ -> }
            .setPositiveButton("Yes"){ _, _ -> contactViewModel.delete(contact) }
        builder.show()
    }

}