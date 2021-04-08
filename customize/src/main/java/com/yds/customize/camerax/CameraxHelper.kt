package com.yds.customize.camerax

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.hardware.Camera
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.VideoCapture
import androidx.camera.view.CameraView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.permissionx.guolindev.PermissionX
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author YDS
 * @date 2021/4/7
 * @discribe Camerax拍照 摄像 工具
 */
@SuppressLint("MissingPermission")
class CameraxHelper(context: FragmentActivity, lifecycleOwner: LifecycleOwner, cameraView: CameraView) {
    private val TAG = this.javaClass.simpleName

    private var mContext: FragmentActivity = context
    private var mLifecycleOwner: LifecycleOwner = lifecycleOwner
    private var mCameraView: CameraView = cameraView
    // private var mCaptureMode: CameraView.CaptureMode = captureMode  //类型 拍照CameraView.CaptureMode.IMAGE 拍摄mCameraView.captureMode = CameraView.CaptureMode.VIDEO

    private var mExecutorService: ExecutorService? = null
    private var mLensFacing = CameraSelector.LENS_FACING_BACK     //摄像头方向

    @SuppressLint("SdCardPath")
    private var imagePath = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.absolutePath + File.separator + System.currentTimeMillis().toString() + ".jpg"
    private var videoPath = mContext.getExternalFilesDir(Environment.DIRECTORY_MOVIES)!!.absolutePath + File.separator + System.currentTimeMillis().toString() + ".mp4"

    init {
        PermissionX.init(mContext)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA
                )
                .onExplainRequestReason { scope, deniedList, beforeRequest ->
                    scope.showRequestReasonDialog(
                            deniedList,
                            "即将申请的权限是程序必须依赖的权限",
                            "我已明白"
                    )
                }
                .onForwardToSettings { scope, deniedList ->
                    scope.showForwardToSettingsDialog(
                            deniedList,
                            "您需要去应用程序设置当中手动开启权限",
                            "我已明白"
                    )
                }
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) { //所有申请的权限都已通过
                        mCameraView.bindToLifecycle(mLifecycleOwner)
                        // mCameraView.captureMode = CameraView.CaptureMode.MIXED

                        mExecutorService = Executors.newSingleThreadExecutor()
                    } else {
                        Toast.makeText(
                                mContext,
                                "您拒绝了如下权限：$deniedList",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
    }

    /**
     * 转换摄像头
     */
    fun switchCamera() {
        mLensFacing = if (mLensFacing == CameraSelector.LENS_FACING_FRONT) {
            CameraSelector.LENS_FACING_BACK
        } else {
            CameraSelector.LENS_FACING_FRONT
        }
        mCameraView.cameraLensFacing = mLensFacing
    }

    /**
     * 拍照 自定义路径
     */
    fun takePicture(updateAlbum: Boolean, imagePath: String, listener: CameraxReocrderListener) {
        this.imagePath = imagePath
        takePicture(updateAlbum, listener)
    }

    /**
     * 拍照
     */
    fun takePicture(updateAlbum: Boolean, listener: CameraxReocrderListener) {
        //创建图片保存的文件地址
        val file = File(imagePath)

        //设置拍照模式
        val captureMode = mCameraView.captureMode
        if (captureMode!=CameraView.CaptureMode.IMAGE){
            mCameraView.captureMode = CameraView.CaptureMode.IMAGE
        }

        val metadata = ImageCapture.Metadata()
        metadata.isReversedHorizontal = mLensFacing == CameraSelector.LENS_FACING_FRONT
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(file)
                .setMetadata(metadata)
                .build()
        mCameraView.takePicture(outputFileOptions, mExecutorService!!, object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val filePath = file.absolutePath
                listener.takePictureSuccess(filePath)

                //是否更新相册
                var savedUri = outputFileResults.savedUri
                if (savedUri == null) {
                    savedUri = Uri.fromFile(file)
                }
                if (updateAlbum) {
                    updateAlbum(savedUri, listener)
                }
            }

            override fun onError(exception: ImageCaptureException) {
                listener.takePictureFail(exception.message.toString())
                Log.e(TAG, "Photo capture failed: " + exception.message, exception)
            }
        })
    }

    /**
     * 拍摄 自定义路径
     */
    fun takeVideo(updateAlbum: Boolean, videoPath: String, listener: CameraxReocrderListener) {
        this.videoPath = videoPath
        takeVideo(updateAlbum, listener)
    }

    /**
     * 拍摄
     */
    fun takeVideo(updateAlbum: Boolean, listener: CameraxReocrderListener) {
        //创建视频保存的文件地址
        val file = File(videoPath)

        //设置拍照模式
        val captureMode = mCameraView.captureMode
        if (captureMode!=CameraView.CaptureMode.VIDEO){
            mCameraView.captureMode = CameraView.CaptureMode.VIDEO
        }

        val metadata = VideoCapture.Metadata()
        mCameraView.startRecording(file, mExecutorService!!, object : VideoCapture.OnVideoSavedCallback {
            override fun onVideoSaved(outputFileResults: VideoCapture.OutputFileResults) {
                val filePath = file.absolutePath
                listener.takeVideoSuccess(filePath)

                //是否更新相册
                if (updateAlbum) {
                    updateAlbum(Uri.fromFile(file), listener)
                }
            }

            override fun onError(videoCaptureError: Int, message: String, cause: Throwable?) {
                listener.takeVideoFail(message)
                Log.e(TAG, "video capture failed: $message")
            }
        })
    }

    /**
     * 停止拍照或拍摄
     */
    fun stopRecord() {
        try {
            mCameraView.stopRecording()
        } catch (e: Exception) {
            Log.d(TAG, "CameraxHelper Stop Record Exception: ${e.message}")
        }
    }

    /**
     * 文件保存
     */
    private fun updateAlbum(savedUri: Uri?, listener: CameraxReocrderListener) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mContext.sendBroadcast(Intent(Camera.ACTION_NEW_PICTURE, savedUri))
        }
        val mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap
                .getFileExtensionFromUrl(savedUri!!.path))
        MediaScannerConnection.scanFile(mContext, arrayOf(File(savedUri.path).absolutePath), arrayOf(mimeTypeFromExtension)) { path, uri ->
            listener.updateAlbumResult(File(savedUri.path).absolutePath)
            // Log.d(TAG, "Image capture scanned into media store: ${File(savedUri.path).absolutePath}")
        }
    }

    /**
     * 退出时 停止CameraView 销毁ExecutorService
     */
    fun destroy() {
        stopRecord()
        mExecutorService?.shutdown()
    }

}