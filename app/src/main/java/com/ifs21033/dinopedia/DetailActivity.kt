package com.ifs21033.dinopedia

import android.content.Intent
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mengambil data Family dari intent
        family = intent.getParcelableExtra(EXTRA_FAMILY)

        if (family != null) {
            supportActionBar?.title = "Family ${family!!.name}"
            loadData(family!!)
        } else {
            finish()
        }

        binding.button33.setOnClickListener{
            val intentWithData = Intent(this@DetailActivity, DinoActivity::class.java)
            intentWithData.putExtra(DinoActivity.EXTRA_FAMILY, family!!)
            startActivity(intentWithData)
        }
    }

    private fun loadData(family: Family) {
        binding.ivDetailIcon.setImageResource(family.icon)
        binding.tvDetailName.text = family.name
        binding.tvDetailDescription.text = family.descrip
        binding.tvDetailKel.text = family.period
        binding.tvDetailChar.text = family.char
        binding.tvDetailHabitat.text = family.habitat
        binding.tvDetailPerilaku.text = family.perilaku
        binding.tvDetailClassi.text = family.classi
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_button -> {
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

    companion object {
        const val EXTRA_FAMILY = "extra_family"
    }
}
