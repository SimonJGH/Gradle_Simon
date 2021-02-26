package com.dubhe.camera

import androidx.camera.core.*
import com.yds.customize.camerax.CameraXCustomPreviewView
import com.yds.customize.camerax.CameraXFocusImageView
import com.yds.customize.util.RepeatClickRefuseUtil
import java.util.*
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.annotation.NonNull
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import java.io.File
import java.text.SimpleDateFormat
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * @author YDS
 * @date 2021/2/26
 * @discribe camerax帮助类
 */
object CameraXHelper {
    private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

    private var cameraProvider: ProcessCameraProvider? = null        //相机信息
    private var preview: Preview? = null                             //预览对象
    private var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA  //当前相机
    private var camera: Camera? = null                               //相机对象
    private var imageCapture: ImageCapture? = null                   //拍照用例
    private var videoCapture: VideoCapture? = null                   //录像用例

    /**
     * 初始化camerax
     */
    fun initCamerax(
            activity: Activity,
            lifecycleOwner: LifecycleOwner,
            viewFinder: CameraXCustomPreviewView,
            cameraXFocusView: CameraXFocusImageView
    ) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(activity)
        cameraProviderFuture.addListener(Runnable {
            cameraProvider = cameraProviderFuture.get()//获取相机信息
            //预览配置
            preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(viewFinder.createSurfaceProvider())
                    }
            imageCapture = ImageCapture.Builder().build()//拍照用例配置
            videoCapture = VideoCapture.Builder()//录像用例配置
//                .setTargetAspectRatio(AspectRatio.RATIO_16_9) //设置高宽比
//                .setTargetRotation(viewFinder.display.rotation)//设置旋转角度
//                .setAudioRecordSource(AudioSource.MIC)//设置音频源麦克风
                    .build()

            try {
                cameraProvider?.unbindAll()//先解绑所有用例
                camera = cameraProvider?.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture,
                        videoCapture
                )//绑定用例
            } catch (exc: Exception) {
                Log.e("Simon", "Use case binding failed", exc)
            }

            initCameraxTouchListener(viewFinder, cameraXFocusView)
        }, ContextCompat.getMainExecutor(activity))
    }

    /**
     * 设置camerax触控事件
     */
    private fun initCameraxTouchListener(
            viewFinder: CameraXCustomPreviewView,
            cameraXFocusView: CameraXFocusImageView
    ) {
        val zoomState = camera!!.cameraInfo.zoomState
        viewFinder.setCustomTouchListener(object : CameraXCustomPreviewView.CustomTouchListener {
            override fun zoom(delta: Float) {
                //双指缩放
                zoomState.value?.let {
                    val currentZoomRatio = it.zoomRatio
                    camera!!.cameraControl.setZoomRatio(currentZoomRatio * delta)
                }
            }

            override fun click(x: Float, y: Float) {
                //点击对焦
                if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                    val factory = viewFinder.createMeteringPointFactory(cameraSelector)
                    val point = factory.createPoint(x, y)
                    val action = FocusMeteringAction.Builder(point, FocusMeteringAction.FLAG_AF)
                            .setAutoCancelDuration(3, TimeUnit.SECONDS)
                            .build()
                    cameraXFocusView.startFocus(Point(x.toInt(), y.toInt()))
                    val future: ListenableFuture<*> =
                            camera!!.cameraControl.startFocusAndMetering(action)
                    future.addListener(Runnable {
                        try {
                            val result = future.get() as FocusMeteringResult
                            if (result.isFocusSuccessful) {
                                cameraXFocusView.onFocusSuccess()
                            } else {
                                cameraXFocusView.onFocusFailed()
                            }
                        } catch (e: Exception) {
                            Log.e("", "", e)
                        }
                    }, Executors.newSingleThreadExecutor())
                }
            }

            override fun doubleClick(x: Float, y: Float) {
                //双击放大缩小
                zoomState.value?.let {
                    val currentZoomRatio = it.zoomRatio
                    if (currentZoomRatio > it.minZoomRatio) {
                        camera!!.cameraControl.setLinearZoom(0f)
                    } else {
                        camera!!.cameraControl.setLinearZoom(0.5f)
                    }
                }
            }

            override fun longClick(x: Float, y: Float) {}
        })
    }

    /**
     * 设置相机摄像头方向
     *
     * isFront true-前置摄像头 flase-后置摄像头
     */
    fun setCameraOrientation(isFront: Boolean) {
        RepeatClickRefuseUtil.clickButton(object : RepeatClickRefuseUtil.OnClickListener {
            override fun onClickEnabled() {
                cameraSelector = if (isFront) {
                    CameraSelector.DEFAULT_FRONT_CAMERA
                } else {
                    CameraSelector.DEFAULT_BACK_CAMERA
                }
            }
        })
    }

    /**
     * 拍照
     */
    fun takePhoto(context: Context, listener: OnTakePhotoListener) {
        val imageCapture = imageCapture ?: return
        val file = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).path +
                        "/" + SimpleDateFormat(
                        FILENAME_FORMAT, Locale.CHINA
                ).format(System.currentTimeMillis()) + ".jpg"
        )
        RepeatClickRefuseUtil.clickButton(object : RepeatClickRefuseUtil.OnClickListener {
            override fun onClickEnabled() {
                val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()
                imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(context),
                        object : ImageCapture.OnImageSavedCallback {
                            override fun onError(exc: ImageCaptureException) {
                                listener.onFailed(exc.message.toString())
                            }

                            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                                val savedUri = Uri.fromFile(file)
                                listener.onSuccess(savedUri.toString())
                            }
                        })
            }
        })
    }

    /**
     * 启动拍摄
     */
    @SuppressLint("RestrictedApi")
    fun startTakeVideo(onTakeVideoListener: OnTakeVideoListener) {
        //视频保存路径
        val file = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).path + "/" + SimpleDateFormat(
                        FILENAME_FORMAT, Locale.CHINA
                ).format(System.currentTimeMillis()) + ".mp4"
        )
        RepeatClickRefuseUtil.clickButton(object : RepeatClickRefuseUtil.OnClickListener {
            override fun onClickEnabled() {
                //开始录像
                videoCapture?.startRecording(
                        file,
                        Executors.newSingleThreadExecutor(),
                        object : VideoCapture.OnVideoSavedCallback {
                            override fun onVideoSaved(@NonNull file: File) {
                                //保存视频成功回调，会在停止录制时被调用
                                onTakeVideoListener.onSuccess(file)
                            }

                            override fun onError(
                                    videoCaptureError: Int,
                                    message: String,
                                    cause: Throwable?
                            ) {
                                //保存失败的回调，可能在开始或结束录制时被调用
                                onTakeVideoListener.onFailed(message)
                            }
                        })
            }
        })
    }

    /**
     * 停止拍摄
     */
    @SuppressLint("RestrictedApi")
    fun stopTakeVideo() {
        RepeatClickRefuseUtil.clickButton(object : RepeatClickRefuseUtil.OnClickListener {
            override fun onClickEnabled() {
                //停止录制
                videoCapture?.stopRecording()
            }
        })
    }

    @SuppressLint("RestrictedApi")
    fun destroyCamerax() {
        //清除预览
        preview?.clear()
    }


    /**
     * 拍照回调
     */
    interface OnTakePhotoListener {
        fun onSuccess(imagePath: String)
        fun onFailed(errorMsg: String) {}
    }

    /**
     * 拍摄回调
     */
    interface OnTakeVideoListener {
        fun onSuccess(file: File)
        fun onFailed(errorMsg: String) {}
    }

}