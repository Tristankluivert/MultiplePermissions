package com.kluivert.multiplepermissions

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_nav.*

class NavActivity : AppCompatActivity() {

    lateinit var listener : NavController.OnDestinationChangedListener
   lateinit var drawerLayout: DrawerLayout
    lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)


        setSupportActionBar(toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_ham)
        drawerLayout = findViewById(R.id.drawerLayout)
        navController = Navigation.findNavController(this, R.id.navfragment)
        NavigationUI.setupWithNavController(navCoView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)


        listener = NavController.OnDestinationChangedListener{controller, destination, arguments ->

            if (destination.id == R.id.moviesFrag){
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_ham)
            }else if(destination.id == R.id.musicFrag){

            }else if (destination.id == R.id.booksFrag){

            }

        }

    }




    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }



  override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,drawerLayout)
    }


}