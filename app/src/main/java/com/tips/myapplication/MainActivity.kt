package com.tips.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.TransitionManager
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button = findViewById<Button>(R.id.btn1)

        val layout = findViewById<ConstraintLayout>(R.id.main)

        button.setOnClickListener {
            val constraintSet = ConstraintSet()
            constraintSet.clone(layout)

            constraintSet.clear(R.id.btn1)

            // Restore size after clearing
            constraintSet.constrainWidth(R.id.btn1, ConstraintSet.WRAP_CONTENT)
            constraintSet.constrainHeight(R.id.btn1, ConstraintSet.WRAP_CONTENT)

            constraintSet.connect(R.id.btn1, ConstraintSet.START,  ConstraintSet.PARENT_ID, ConstraintSet.START)
            constraintSet.connect(R.id.btn1, ConstraintSet.END,    ConstraintSet.PARENT_ID, ConstraintSet.END)
            constraintSet.connect(R.id.btn1, ConstraintSet.TOP,    ConstraintSet.PARENT_ID, ConstraintSet.TOP)
            constraintSet.connect(R.id.btn1, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)

            constraintSet.setHorizontalBias(R.id.btn1, Random.nextFloat())
            constraintSet.setVerticalBias(R.id.btn1, Random.nextFloat())

            TransitionManager.beginDelayedTransition(layout)
            constraintSet.applyTo(layout)
        }
    }
}