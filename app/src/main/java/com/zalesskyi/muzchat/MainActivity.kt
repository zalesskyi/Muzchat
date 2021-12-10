package com.zalesskyi.muzchat

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.zalesskyi.muzchat.navigation.AppNavProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main), AppNavProvider {

    override fun getNavController(): NavController = findNavController(R.id.nav_host_fragment)
}