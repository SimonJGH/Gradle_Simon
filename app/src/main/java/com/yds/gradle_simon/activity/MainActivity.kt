package com.yds.gradle_simon.activity

import android.os.Bundle
import android.view.View
import com.yds.customize.util.IntentUtil
import com.yds.customize.view.FunctionLoadingDialog
import com.yds.gradle_simon.R
import com.yds.gradle_simon.databinding.ActivityMainBinding
import com.yds.gradle_simon.mvp.BaseActivity
import com.yds.gradle_simon.mvp.BasePresenter
import com.yds.gradle_simon.mvp.BaseContract
import java.util.*

/**
 * ./gradlew install
 * ./gradlew bintrayUpload
 *
 *
 * https://jcenter.bintray.com/com/simon/customize/
 */
class MainActivity() : BaseActivity<BaseContract, BasePresenter<BaseContract>>(), View.OnClickListener, BaseContract {

    private lateinit var binding: ActivityMainBinding
    private val mList: List<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initOnClickListener()
    }

    private fun initOnClickListener() {
        binding.btCamerax.setOnClickListener(this)
        binding.btTest.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btTest -> IntentUtil.startActivity(this@MainActivity, TempActivity::class.java)
            R.id.btCamerax -> IntentUtil.startActivity(this@MainActivity, CameraViewActivity::class.java)

        }
    }

    override fun initView(): BaseContract? {
        return this
    }

    override fun initPresenter(): BasePresenter<BaseContract>? {
        return BasePresenter()
    }

}