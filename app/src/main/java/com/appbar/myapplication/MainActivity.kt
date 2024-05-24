package com.appbar.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.appbar.myapplication.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar a toolbar
        setSupportActionBar(binding.appBarMain.toolbar)

        // Configurar a navigation drawer
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Configurar a AppBarConfiguration com a drawer layout e os destinos de navegação
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )

        // Configurar navigation controller com a navigation view
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Configurar FloatingActionButton visivel com base na navegação de deestino
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_home || destination.id == R.id.nav_gallery) {
                binding.appBarMain.fab.visibility = View.VISIBLE
            } else {
                binding.appBarMain.fab.visibility = View.GONE
            }
        }

        // Configurar FloatingActionButton action
        binding.appBarMain.fab.setOnClickListener { view ->
            val instagramUri = Uri.parse("https://www.instagram.com/lounge.bydocostume?igsh=MXc2Nml5NDlweXE2Yg==")
            val intent = Intent(Intent.ACTION_VIEW, instagramUri)
            intent.setPackage("com.instagram.android")

            // Verifica se o Instagram está instalado
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // Se o Instagram não estiver instalado, abre no navegador
                val webIntent = Intent(Intent.ACTION_VIEW, instagramUri)
                startActivity(webIntent)
            }
        }

        // Referencia os botões
        val menuButton: Button = findViewById(R.id.menuButton)
        val eventsButton: Button = findViewById(R.id.eventsButton)

        // Adiciona os botões
        menuButton.setOnClickListener {
            // Ação ao clicar no botão Menu
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        eventsButton.setOnClickListener {
            // Ação ao clicar no botão Eventos
            val intent = Intent(this, EventsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        // Handle up navigation with the navigation controller
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
