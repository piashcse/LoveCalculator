package com.piashcse.lovecalculator

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
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.result.setOnClickListener {
            val name1 = binding.nameOne.text.toString()
            val name2 = binding.nameTwo.text.toString()

            if (name1.isNotBlank() && name2.isNotBlank()) {
                val result = calculateCompatibility(name1, name2)
                displayResult(result)
                hideKeyboard()
            } else {
                showToast(getString(R.string.please_give_two_names))
            }
        }
    }

    private fun calculateCompatibility(name1: String, name2: String): Int {
        val value1 = singleDigitValue(sumOfChars(name1))
        val value2 = singleDigitValue(sumOfChars(name2))
        return (minOf(value1, value2).toFloat() / maxOf(value1, value2) * 100).toInt()
    }

    private fun displayResult(result: Int) {
        binding.resultTextView.text = String.format(Locale.getDefault(), "%d%%", result.toInt())
        binding.imageViewHeart.visibility = View.VISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun hideKeyboard() {
        val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.windowToken?.let {
            inputManager.hideSoftInputFromWindow(it, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    private fun sumOfChars(name: String): Long {
        return name.uppercase().sumOf { char -> (char - 'A' + 1).takeIf { it in 1..26 }?.toLong() ?: 0 }
    }

    private fun singleDigitValue(number: Long): Long {
        var result = number
        while (result > 9) {
            result = result.toString().sumOf { it.toString().toLong() }
        }
        return result
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        showToast(getString(R.string.please_click_again_to_exit))
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            showAboutDialog()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun showAboutDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.about_me))
            .setIcon(R.drawable.lovea)
            .setMessage(getString(R.string.about_info))
            .show()
    }
}