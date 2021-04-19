package com.yds.gradle_simon.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.view.CameraView
import com.yds.customize.camerax.CameraxHelper
import com.yds.customize.camerax.CameraxReocrderListener
import com.yds.customize.camerax.RecordButton
import com.yds.gradle_simon.R

/**
 * CameraView 启动拍照或拍摄
 * 如果想要拍摄或拍照时无黑屏，最好在初始化CameraView时设置CaptureMode
 */
class CameraViewActivity : AppCompatActivity() {
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

        cameraxHelper = CameraxHelper(this, this, mCameraView)

        initCamerax()
    }

    private fun initCamerax() {
        mRecordButton.setOnRecordListener(object : RecordButton.OnRecordListener {
            override fun onTackPicture() {
                //默认拍照路径
                cameraxHelper.takePicture(true, object : CameraxReocrderListener {
                    override fun takePictureSuccess(imagePath: String) {
                        super.takePictureSuccess(imagePath)
                        Log.i("CameraxHelper", "onImageSaved: $imagePath")
                    }

                    //不用可去除
                    override fun takePictureFail(errorMsg: String) {
                        super.takePictureFail(errorMsg)
                    }

                    //不用可去除
                    override fun updateAlbumResult(path: String) {
                        super.updateAlbumResult(path)
                    }
                })

                //自定义拍照路径
                /*val imagePath = Environment.getExternalStorageDirectory().absolutePath + "/Download/test.jpg"
                cameraxHelper.takePicture(true, imagePath, object : CameraxReocrderListener {
                    override fun takePictureSuccess(imagePath: String) {
                        super.takePictureSuccess(imagePath)
                        Log.i("CameraxHelper", "onImageSaved: $imagePath")
                    }
                })*/
            }

            override fun onRecordVideo() {
                //拍摄自定义路径同拍摄一样设置
                cameraxHelper.takeVideo(false, object : CameraxReocrderListener {
                    override fun takeVideoSuccess(videoPath: String) {
                        super.takeVideoSuccess(videoPath)
                        Log.i("CameraxHelper", "onVideoSaved: $videoPath")
                    }
                    //不用可去除
                    override fun takeVideoFail(errorMsg: String) {
                        super.takePictureFail(errorMsg)
                    }

                    //不用可去除
                    override fun updateAlbumResult(path: String) {
                        super.updateAlbumResult(path)
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