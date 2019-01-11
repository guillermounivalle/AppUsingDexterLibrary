package com.example.guillermoandres.appusingdexterlibrary

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.karumi.dexter.Dexter
import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest;
import android.content.Context
import android.media.session.MediaSession
import android.support.v4.content.ContextCompat
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.security.Permission


/**
 *Como se ha cambiado el Theme usando "android:Theme.Material" que pertenece a Material desing
 * cambiaremos --> class MainActivity : AppCompatActivity(), por --> class MainActivity : Activity(),
 * esto ya que este cambio no vale para versones de android menores a la 5.0
 */
class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonCamera.setOnClickListener {checkCameraPermissions()}
    }

    private fun checkCameraPermissions(){
        val context = this
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(object: PermissionListener {

                    //Lògica para cuando acepten los permisos
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        textViewCamera.text = getString(R.string.permissions_status_granted)
                        textViewCamera.setTextColor(ContextCompat.getColor(context, R.color.colorPermissionStatusGranted))
                    }

                    //Lògica para cuando deniegan los permisos
                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        if(response.isPermanentlyDenied) {
                            textViewCamera.text = getString(R.string.permissions_status_denied_permanently)
                            textViewCamera.setTextColor(ContextCompat.getColor(context, R.color.colorPermissionStatusPermanentlyDenied))
                        }else {
                            textViewCamera.text = getString(R.string.permissions_status_denied)
                            textViewCamera.setTextColor(ContextCompat.getColor(context, R.color.colorPermissionStatusDenied))
                        }
                    }

                    //Lògica para cuando deniegan los permisos permanentemente
                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest?, token: PermissionToken) {
                        token.continuePermissionRequest()
                    }

                }).check()
    }
}
