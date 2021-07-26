package id.slavant.dbtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)

        button.setOnClickListener {
//            writeBookingToDb(Random.nextInt(until = 100).toString())
            val order = Order(
                location = "Torrevieja",
                time = "14:00",
                date = "25.09.21")
            writeBookingToDb(order)
        }

    }

    private fun writeBookingToDb( value: Order){
        val database = Firebase.database
        val myRef = database.getReference("bdtest")
        val key = database.getReference("bdtest").push().key
        if (key != null) {
            myRef
                .child(key)
                .setValue(value)
                .addOnCompleteListener { Toast.makeText(this, "completed", Toast.LENGTH_SHORT).show() }
                .addOnSuccessListener { Toast.makeText(this, "successfully saved", Toast.LENGTH_SHORT).show() }
                .addOnFailureListener { Toast.makeText(this, "error", Toast.LENGTH_SHORT).show() }
        }

    }
}