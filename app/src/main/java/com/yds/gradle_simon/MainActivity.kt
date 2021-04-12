package com.yds.gradle_simon

import android.os.Bundle
import android.view.View
import com.yds.customize.util.PopupWindowUtils
import com.yds.gradle_simon.databinding.ActivityMainBinding
import java.util.*

/**
 * ./gradlew install
 * ./gradlew bintrayUpload
 *
 *
 * https://jcenter.bintray.com/com/simon/customize/
 */
class MainActivity : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private val mList: List<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PopupWindowUtils.getInstance().setPopDismissListener {

        }

        initOnClickListener()
    }

    private fun initOnClickListener() {
        binding.btCamerax.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btCamerax -> startActivity(this@MainActivity, CameraViewActivity::class.java)
        }
    }
}