package com.example.toiletshalost

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.PropertyName
import com.google.firebase.database.ValueEventListener
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(ResponseObject::class.java) // Разобраться с десериализацией



                value?.current?.let{
                    myRef.child("current").setValue(null)
                    println(it)
                    play(it)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(this.toString(), "Failed to read value.", error.toException())
            }
        })

    }

    private fun play(id: String) {
        val logsTextView = findViewById<TextView>(R.id.logsTextView)
        if (id.isNotBlank()){
            try {
                mp.reset()
                mp.setDataSource(this, Uri.parse("android.resource://" + this.packageName + "/" + TRACKS_MAP[id]))
                mp.prepare()
                mp.start()
            } catch (e : Exception) {
                Toast.makeText(this, "Что-то пошло не так, смотри логи", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
                logsTextView.text = "$id ${e.stackTrace}"
            }
        }
    }

    companion object {
        private val firebase = FirebaseDatabase.getInstance()
        val myRef = firebase.reference
        val mp = MediaPlayer()
        val TRACKS_MAP = mapOf(
            Pair("0", R.raw.t0),
            Pair("1", R.raw.t1),
            Pair("2", R.raw.t2),
            Pair("3", R.raw.t3),
            Pair("4", R.raw.t4),
            Pair("5", R.raw.t5),
            Pair("6", R.raw.t6),
            Pair("7", R.raw.t7),
            Pair("8", R.raw.t8),
            Pair("9", R.raw.t9),
            Pair("10", R.raw.t10),
            Pair("11", R.raw.t11),
            Pair("12", R.raw.t12),
            Pair("13", R.raw.t13),
            Pair("14", R.raw.t14),
            Pair("15", R.raw.t15),
            Pair("16", R.raw.t16),
            Pair("17", R.raw.t17),
            Pair("18", R.raw.t18),
            Pair("19", R.raw.t19),
            Pair("20", R.raw.t20),
            Pair("21", R.raw.t21),
            Pair("22", R.raw.t22),
            Pair("23", R.raw.t23),
            Pair("24", R.raw.t24),
            Pair("25", R.raw.t25),
            Pair("26", R.raw.t26),
            Pair("27", R.raw.t27),
            Pair("28", R.raw.t28),
            Pair("29", R.raw.t29),
            Pair("30", R.raw.t30),
            Pair("31", R.raw.t31),
            Pair("32", R.raw.t32),
            Pair("33", R.raw.t33),
            Pair("34", R.raw.t34),
            Pair("35", R.raw.t35),
            Pair("36", R.raw.t36),
            Pair("37", R.raw.t37),
            Pair("38", R.raw.t38),
            Pair("39", R.raw.t39),
            Pair("40", R.raw.t40),
            Pair("41", R.raw.t41),
            Pair("42", R.raw.t42),
            Pair("43", R.raw.t43),
            Pair("44", R.raw.t44),
            Pair("45", R.raw.t45),
            Pair("46", R.raw.t46),
            Pair("47", R.raw.t47),
            Pair("48", R.raw.t48),
            Pair("49", R.raw.t49),
            Pair("50", R.raw.t50),
            Pair("51", R.raw.t51),
            Pair("52", R.raw.t52),
            Pair("53", R.raw.t53),
            Pair("54", R.raw.t54),
            Pair("55", R.raw.t55),
            Pair("56", R.raw.t56),
            Pair("57", R.raw.t57),
            Pair("58", R.raw.t58),
            Pair("59", R.raw.t59)
        )
    }

    data class ResponseObject(
        @PropertyName("current")val current: String? = "",
        @PropertyName("sounds")val sounds: List<Sound>? = listOf()
    )

    data class Sound(
        @PropertyName("id")val id: Int? = 0,
        @PropertyName("name")val name: String? = ""
        )
}