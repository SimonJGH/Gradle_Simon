package com.yds.gradle_simon

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.CameraView
import com.yds.customize.camerax.CameraxHelper
import com.yds.customize.camerax.CameraxReocrderListener
import com.yds.customize.camerax.RecordButton

/**
 * CameraView 启动拍照或拍摄
 */
class CameraViewActivity : BaseActivity() {
    private lateinit var mRecordButton: RecordButton
    private lateinit var mBtnCameraSwitch: ImageButton
    private lateinit var mCameraView: CameraView

    private lateinit var cameraxHelper: CameraxHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_view)

        mCameraView = findViewById(R.id.cameraView)
        mRecordButton = findViewById(R.id.recordButton)
        mBtnCameraSwitch = findViewById(R.id.ibSwitchCamera)

        mBtnCameraSwitch.setOnClickListener(View.OnClickListener { v: View? ->
            cameraxHelper.switchCamera()
        })

        cameraxHelper = CameraxHelper(this@CameraViewActivity, this, mCameraView)

        initCamerax()
    }

    private fun initCamerax() {
        mRecordButton.setOnRecordListener(object : RecordButton.OnRecordListener {
            override fun onTackPicture() {
                val imagePath = Environment.getExternalStorageDirectory().absolutePath + "/Download/aaaa.jpg"
                Log.i("CameraxHelper", "imagePath: $imagePath")
                cameraxHelper.takePicture(true, imagePath, object : CameraxReocrderListener {
                    override fun takePictureSuccess(imagePath: String) {
                        super.takePictureSuccess(imagePath)
                        Log.i("CameraxHelper", "onImageSaved: $imagePath")
                    }
                })
            }

            override fun onRecordVideo() {
                cameraxHelper.takeVideo(false, object : CameraxReocrderListener {
                    override fun takeVideoSuccess(videoPath: String) {
                        super.takeVideoSuccess(videoPath)
                        Log.i("CameraxHelper", "onVideoSaved: $videoPath")
                    }
                })
            }

            override fun onFinish() {
                cameraxHelper.stopRecord()
            }
        })
    }

    override fun onDestroy() {
        cameraxHelper.destroy()
        super.onDestroy()
    }
}