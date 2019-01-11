package com.example.guillermoandres.appusingdexterlibrary

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

/**
 *Como se ha cambiado el Theme usando "android:Theme.Material" que pertenece a Material desing
 * cambiaremos --> class MainActivity : AppCompatActivity(), por --> class MainActivity : Activity(),
 * esto ya que este cambio no vale para versones de android menores a la 5.0
 */
class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
