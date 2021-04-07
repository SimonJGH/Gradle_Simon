package com.yds.customize.camerax

/**
 * @author YDS
 * @date 2021/4/7
 * @discribe Camerax 拍照 拍摄 结果回调
 */
interface CameraxReocrderListener {
    fun takePictureSuccess(imagePath: String) {}
    fun takePictureFail(errorMsg: String) {}

    fun takeVideoSuccess(videoPath: String) {}
    fun takeVideoFail(errorMsg: String) {}

    //更新相册回调
    fun updateAlbumResult(path: String){}
}