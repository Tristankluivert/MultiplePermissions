package com.kluivert.multiplepermissions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toastfile.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        btnorder.setOnClickListener {

            val checkedSoup = soupGroup.checkedRadioButtonId
            val soup = findViewById<RadioButton>(checkedSoup)

            val rbRos = rosBox.isChecked
            val rbFresh = frBox.isChecked
            val rbPig = pigBox.isChecked
            val rbCow = cowBox.isChecked
            val result = "You selected ${soup.text}" + " with " +
                    (if (rbRos) "\nRoasted Fish" else "") +
                    (if (rbFresh) "\nFresh Fish" else "") +
                    (if (rbPig) "\nPig meat" else "") +
                    (if (rbCow) "\nCow meat" else "")

            tvresult.text = result
        }


       btntoast.setOnClickListener {
           Toast(this).apply {
               duration = Toast.LENGTH_SHORT
               view = layoutInflater.inflate(R.layout.toastfile,toastfile)
               show()
           }
       }


        btnpermis.setOnClickListener {
           requestPermission()
        }

    }



    private fun hasLocation() = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun hasWriteAccess() = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

    private fun hasBackgroundLocation() = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED


    private fun requestPermission(){

        val permissionReq = mutableListOf<String>()


        if(!hasLocation()){
            permissionReq.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if(!hasWriteAccess()){
            permissionReq.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if(!hasBackgroundLocation()){
            permissionReq.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        if(permissionReq.isNotEmpty()){
            ActivityCompat.requestPermissions(this, permissionReq.toTypedArray(), 0)
        }


    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 0 && grantResults.isNotEmpty()){
            for (i in grantResults.indices){
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED ){
                     Log.d("Permission request", "${permissions[i]} granted")
                }
            }
        }

    }
}