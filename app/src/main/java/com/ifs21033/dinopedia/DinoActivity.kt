package com.ifs21033.dinopedia


import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21033.dinopedia.databinding.ActivityMainBinding

class DinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataDino = ArrayList<Dino>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFruits.setHasFixedSize(false)
        dataDino.addAll(getListDino())
        showRecyclerList()


    }



    @SuppressLint("Recycle")
    private fun getListDino(): ArrayList<Dino> {
        val family = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILY, Family::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILY)
        }
        val dinoNames =
            resources.getStringArray(R.array.dino_name)
        val dinoIcons =
            resources.obtainTypedArray(R.array.dino_icon)
        val dinoDescs =
            resources.getStringArray(R.array.dino_desc)
        val dinoChar =
            resources.getStringArray(R.array.dino_char)
        val dinoKel =
            resources.getStringArray(R.array.dino_kel)
        val dinoHabitat =
            resources.getStringArray(R.array.dino_habitat)
        val dinoMakanan =
            resources.getStringArray(R.array.dino_makanan)
        val dinoLength =
            resources.getStringArray(R.array.dino_length)
        val dinoTall =
            resources.getStringArray(R.array.dino_tall)
        val dinoWeight =
            resources.getStringArray(R.array.dino_weight)
        val dinoWeak =
            resources.getStringArray(R.array.dino_weak)
        val dinoFamily =
            resources.getStringArray(R.array.dino_family)
        val indexAwal = family?.indexAwal
        val indexAkhir = family?.indexAkhir
        val listDino = ArrayList<Dino>()

        if (indexAwal != null && indexAkhir != null) {
            for (i in indexAwal..indexAkhir) {
                val dino = Dino(
                    dinoNames[i],
                    dinoIcons.getResourceId(i, -1),
                    dinoDescs[i],
                    dinoChar[i],
                    dinoKel[i],
                    dinoHabitat[i],
                    dinoMakanan[i],
                    dinoLength[i],
                    dinoTall[i],
                    dinoWeight[i],
                    dinoWeak[i],
                    dinoFamily[i]
                )
                listDino.add(dino)
            }
        } else {
            for (i in dinoNames.indices) {
                val dino = Dino(
                    dinoNames[i],
                    dinoIcons.getResourceId(i, -1),
                    dinoDescs[i],
                    dinoChar[i],
                    dinoKel[i],
                    dinoHabitat[i],
                    dinoMakanan[i],
                    dinoLength[i],
                    dinoTall[i],
                    dinoWeight[i],
                    dinoWeak[i],
                    dinoFamily[i]
                )
                listDino.add(dino)
            }
        }
        return listDino
    }
    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFruits.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvFruits.layoutManager =
                LinearLayoutManager(this)
        }
        val listDinoAdapter = ListDinoAdapter(dataDino)
        binding.rvFruits.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object :
            ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }
    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@DinoActivity,
            DetailDinoActivity::class.java)
        intentWithData.putExtra(DetailDinoActivity.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menuInflater.inflate(R.menu.menu_button, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                startActivity(Intent(this, ProfilActivity::class.java)) // Buka ProfilActivity saat item menu diklik
                true
            }
            R.id.action_button -> {
                // Ketika item menu Dino ditekan, buka DinoActivity
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    companion object {
        const val EXTRA_FAMILY = "extra_family"
    }
}
