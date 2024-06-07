package com.appbar.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        // Configurar FloatingActionButton visível com base na navegação de destino
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
        updateMenu(menu) // Atualiza o menu com base no estado de login
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
                true
            }
            R.id.action_register -> {
                startActivity(Intent(this, RegisterActivity::class.java))
                true
            }
            R.id.action_logout -> {
                logoutUser()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateMenu(menu: Menu) {
        val isLoggedIn = isUserLoggedIn()
        menu.findItem(R.id.action_login).isVisible = !isLoggedIn
        menu.findItem(R.id.action_register).isVisible = !isLoggedIn
        menu.findItem(R.id.action_logout).isVisible = isLoggedIn
    }

    private fun isUserLoggedIn(): Boolean {
        // Adicione a lógica para verificar se o usuário está logado
        // Exemplo:
        // return FirebaseAuth.getInstance().currentUser != null
        return false
    }

    private fun logoutUser() {
        // Adicione a lógica para deslogar o usuário
        // Exemplo:
        // FirebaseAuth.getInstance().signOut()
        recreate()
    }

    override fun onSupportNavigateUp(): Boolean {
        // Handle up navigation with the navigation controller
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
