package com.ifs21033.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21033.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataFruits = ArrayList<Family>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFruits.setHasFixedSize(false)
        dataFruits.addAll(getListFruits())
        showRecyclerList()

    }
    @SuppressLint("Recycle")
    private fun getListFruits(): ArrayList<Family> {
        val dataName =
            resources.getStringArray(R.array.family_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.family_icon)
        val dataDesc =
            resources.getStringArray(R.array.family_desc)
        val dataDescription =
            resources.getStringArray(R.array.family_descrip)
        val dataPeriod =
            resources.getStringArray(R.array.family_period)
        val dataChar =
            resources.getStringArray(R.array.family_char)
        val dataHabitat =
            resources.getStringArray(R.array.family_habitat)
        val dataPerilaku =
            resources.getStringArray(R.array.family_perilaku)
        val dataClassi =
            resources.getStringArray(R.array.family_classi)
        val listFamily = ArrayList<Family>()
        for (i in dataName.indices) {
            val fruit = Family(dataName[i],
                dataIcon.getResourceId(i, -1),dataDesc[i], dataDescription[i], dataPeriod[i],dataChar[i],
                dataHabitat[i], dataPerilaku[i],dataClassi[i],)
            listFamily.add(fruit)
        }
        return listFamily
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
        val listFamilyAdapter = ListFruitAdapter(dataFruits)
        binding.rvFruits.adapter = listFamilyAdapter
        listFamilyAdapter.setOnItemClickCallback(object :
            ListFruitAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Family) {
                showSelectedFruit(data)
            }
        })
    }
    private fun showSelectedFruit(family: Family) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_FAMILY, family)
        startActivity(intentWithData)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                startActivity(Intent(this, ProfilActivity::class.java)) // Buka ProfilActivity saat item menu diklik
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}