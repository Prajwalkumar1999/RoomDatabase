package com.example.roomdbapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.roomdbapplication.databinding.ActivityInserDataBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InserDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInserDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inser_data)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_inser_data)
        val db = NoteDatabase.getInstance(this@InserDataActivity)

        binding.button.setOnClickListener {

            GlobalScope.launch(Dispatchers.IO) {
                db.noteDao()
                    .insert(
                        Note(
                            binding.edittext1.text.toString(),
                            binding.editext2.text.toString(),
                            0
                        )
                    )

                val intent = Intent(this@InserDataActivity, MainActivity::class.java)
                startActivity(intent)

                finish()
            }
        }
    }
}