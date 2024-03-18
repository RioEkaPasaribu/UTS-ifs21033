package com.ifs21033.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21033.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var family: Family? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        family = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILY,
                Family::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILY)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (family != null) {
            supportActionBar?.title = " Family ${family!!.name}"
            loadData(family!!)
        } else {
            finish()
        }
    }
    private fun loadData(family: Family) {
        binding.ivDetailIcon.setImageResource(family.icon)
        binding.tvDetailName.text = family.name
        binding.tvDetailDescription.text = family.descrip
        binding.tvDetailPeriod.text = family.period
        binding.tvDetailChar.text = family.char
        binding.tvDetailHabitat.text = family.habitat
        binding.tvDetailPerilaku.text = family.perilaku
        binding.tvDetailClassi.text = family.classi
    }
    companion object {
        const val EXTRA_FAMILY = "extra_family"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareAnimalDetails() // Panggil method untuk membagikan detail hewan
                true
            }
            android.R.id.home -> {
                finish() // Handle tombol kembali di action bar
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareAnimalDetails() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val shareMessage = "Check out this animal: ${family?.name}\nDescription: ${family?.descrip}" // Isi pesan yang akan dibagikan
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, "Share Animal Details"))
    }
}