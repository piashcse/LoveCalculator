package com.piashcse.lovecalculator

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.piashcse.lovecalculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.result.setOnClickListener {
            val name1 = binding.nameOne.text.toString()
            val name2 = binding.nameTwo.text.toString()
            val n1: Long
            val n2: Long
            val value1: Long
            val value2: Long
            val result: Float
            if (name1 != "" && name2 != "") {
                n1 = sumOfString(name1)
                n2 = sumOfString(name2)
                value2 = oneDigit(n1)
                value1 = oneDigit(n2)
                result = if (value1 > value2) {
                    value2.toFloat() / value1.toFloat() * 100
                } else {
                    value1.toFloat() / value2.toFloat() * 100
                }
                binding.resultTextView.text = result.toInt().toString().plus("%")
                binding.imageViewHeart.visibility = View.VISIBLE
                val inputManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(
                    currentFocus?.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            } else {
                Toast.makeText(applicationContext, getString(R.string.please_give_two_names), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun sumOfString(name: String): Long {
        var total: Long = 0
        for (i in name.indices) {
            if (name[i] in 'a'..'z' || name[i] in 'A'..'Z') {
                when (name[i]) {
                    'a', 'A' -> total++
                    'b', 'B' -> total += 2
                    'c', 'C' -> total += 3
                    'd', 'D' -> total += 4
                    'e', 'E' -> total += 5
                    'f', 'F' -> total += 6
                    'g', 'G' -> total += 7
                    'h', 'H' -> total += 8
                    'i', 'I' -> total += 9
                    'j', 'J' -> total += 10
                    'k', 'K' -> total += 11
                    'l', 'L' -> total += 12
                    'm', 'M' -> total += 13
                    'n', 'N' -> total += 14
                    'o', 'O' -> total += 15
                    'p', 'P' -> total += 16
                    'q', 'Q' -> total += 17
                    'r', 'R' -> total += 18
                    's', 'S' -> total += 19
                    't', 'T' -> total += 20
                    'u', 'U' -> total += 21
                    'v', 'V' -> total += 22
                    'w', 'W' -> total += 23
                    'x', 'X' -> total += 24
                    'y', 'Y' -> total += 25
                    'z', 'Z' -> total += 26
                    else -> {}
                }
            }
        }
        return total
    }

    private fun oneDigit(number: Long): Long {
        var numberForCalculation = number
        var mod: Long
        var digit: Long
        while (numberForCalculation > 9) {
            digit = 0
            while (numberForCalculation != 0L) {
                mod = numberForCalculation % 10
                numberForCalculation /= 10
                digit += mod
            }
            numberForCalculation = digit
        }
        return numberForCalculation
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, getString(R.string.please_click_again_to_exit), Toast.LENGTH_SHORT)
            .show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            val alert = AlertDialog.Builder(this)
            alert.setTitle(getString(R.string.about_me))
            alert.setIcon(R.drawable.lovea)
            val name = """
                I'm Mehedi Hassan Piash, 
                working as a Senior Software Engineer. 
                Contact info : 01812353930
                Email : piash599@gmail.com
                """.trimIndent()
            alert.setMessage(name)
            alert.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}