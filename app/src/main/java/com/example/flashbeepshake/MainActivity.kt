package com.example.flashbeepshake

import android.content.Context
import android.hardware.camera2.CameraManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val flash_Btn = findViewById<Button>(R.id.flashBtn)
        val beep_Btl = findViewById<Button>(R.id.beepBtn)
        val shake_Btl = findViewById<Button>(R.id.shakeBtn)
        val camManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = camManager.cameraIdList[0]
        var flash_On = false
        val tone = ToneGenerator(AudioManager.STREAM_MUSIC, 100)

        flash_Btn.setOnClickListener{
            if(!flash_On) {
                flash_On = true
                camManager.setTorchMode(cameraId, true)
            }
            else {
                flash_On = false
                camManager.setTorchMode(cameraId, false)
            }
        }

        beep_Btl.setOnClickListener {
            tone.startTone(ToneGenerator.TONE_CDMA_ABBR_INTERCEPT, 600)
        }

        shake_Btl.setOnClickListener{
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                vibrator.vibrate(200)
            }
        }

    }
}
