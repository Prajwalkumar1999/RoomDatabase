package com.example.roomdbapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.roomdbapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val db = NoteDatabase.getInstance(this@MainActivity)
        var allNotes: List<Note>

        GlobalScope.launch(Dispatchers.IO) {
           // db.noteDao()
             //   .insert(Note("Title", "This is description", 0))

            //TODO Get the data
            allNotes = db.noteDao().getAllNotes()
            Log.d("AllNotes Value", "Note: $allNotes")
            Log.d("AllNotes Value", "Note: " + allNotes.size.toString())
            binding.recyclerView.adapter=FeedListAdapter(this@MainActivity,allNotes)
        }
        binding.floatingButton.setOnClickListener{

            val intent= Intent(this,InserDataActivity::class.java)
            startActivity(intent)
        }
    }
}