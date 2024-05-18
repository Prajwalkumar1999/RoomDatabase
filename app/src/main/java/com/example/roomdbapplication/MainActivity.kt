package com.example.roomdbapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = NoteDatabase.getInstance(this@MainActivity)
        var allNotes: List<Note>

        GlobalScope.launch(Dispatchers.IO) {
            db.noteDao()
                .insert(Note("Title", "This is description", 0))

            //TODO Get the data
            allNotes = db.noteDao().getAllNotes()
            Log.d("AllNotes Value", "Note: $allNotes")
            Log.d("AllNotes Value", "Note: " + allNotes.size.toString())
        }
    }
}