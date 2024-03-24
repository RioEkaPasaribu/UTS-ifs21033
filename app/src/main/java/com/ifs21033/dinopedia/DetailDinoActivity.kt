package com.ifs21033.dinopedia

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21033.dinopedia.databinding.ActivityDetailDinoBinding // Ganti dengan import yang sesuai

class DetailDinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDinoBinding // Ganti dengan binding yang sesuai
    private var dino: Dino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDinoBinding.inflate(layoutInflater) // Ganti dengan binding yang sesuai
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mengambil data Family dari intent
        dino = intent.getParcelableExtra(EXTRA_DINO)

        if (dino != null) {
            supportActionBar?.title = "${dino!!.name}"
            loadData(dino!!)
        } else {
            finish()
        }
    }

    private fun loadData(dino: Dino) {
        binding.ivDetailIcon.setImageResource(dino.icon)
        binding.tvDetailName.text = dino.name
        binding.tvDetailDescription.text = dino.descs
        binding.tvDetailChar.text = dino.char
        binding.tvDetailKel.text = dino.kel
        binding.tvDetailHabitat.text = dino.habitat
        binding.tvDetailPerilaku.text = dino.makanan
        binding.tvDetailClassi.text = dino.length
        binding.tvtall.text = dino.tall
        binding.tvWeight.text = dino.weight
        binding.tvWeak.text = dino.weak

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_button -> {
                shareAnimalDetails()
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareAnimalDetails() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        val shareMessage = "Check out this animal: ${dino?.name}\nDescription: ${dino?.descs}"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, "Share Animal Details"))
    }

    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}
