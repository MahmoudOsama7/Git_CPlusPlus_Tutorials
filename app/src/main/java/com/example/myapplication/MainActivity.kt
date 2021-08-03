package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    //initializing the variable lately not now
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null)
        {
            supportFragmentManager.beginTransaction().replace(R.id.fragHome,HomeFragment()).commitNow()
        }


        val drawerLayout :DrawerLayout=findViewById(R.id.drawer_layout)
        val navView:NavigationView=findViewById(R.id.nav_view)


        toggle=ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener {

            when(it.itemId)
            {
                R.id.nav_home ->
                {
                    supportFragmentManager.beginTransaction().replace(R.id.fragHome,HomeFragment()).commitNow()
                }
                R.id.nav_team -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragHome,TeamFragment()).commitNow()
                }
                R.id.nav_order -> {
                    //implict intent to open any app that will send an email and we state the way of how to send email to specific person and subject
                    startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("mailto:"+"mahmoud.osaamaa@gmail.com"+"?subject="+"message from osama")))
                }

                R.id.nav_share -> {
                   val sent=Intent()
                    sent.action=Intent.ACTION_SEND //to call all applications that can send
                    sent.putExtra(Intent.EXTRA_TEXT,"download application : https://play.google.com/store/apps/details?id=com.example.myapplication")//to send extra data
                    sent.type="text/plain" //define the text type as text/plain
                    startActivity(Intent.createChooser(sent,"Choose your application")) //when opening the menu of application to choose
                    //you can set it's header name as we set here as : choose your application
                }
                R.id.nav_about -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragHome,AboutFragment()).commitNow()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
        {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}