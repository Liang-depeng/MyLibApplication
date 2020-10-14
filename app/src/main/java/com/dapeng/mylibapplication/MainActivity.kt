package com.dapeng.mylibapplication

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dapeng.base_ui_lib.dialog.MyDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_01?.setOnClickListener {
            MyDialog.Builder(this).setAlpha(1f)
                    .setAnimationStyle(R.style.dialog_bottom_anim)
                    .setWidth(MyDialog.MATCH_PARENT)
                    .setHeight(MyDialog.WRAP_CONTENT)
                    .setCancelable(true)
                    .setCanTouchOutCancel(true)
                    .setBackgroundBlackRate(0.6f)
                    .setViewGravity(Gravity.BOTTOM)
                    .setLayoutResId(R.layout.dialog_bottom_test_layout)
                    .create().show()
        }

        btn_02?.setOnClickListener {
            MyDialog.Builder(this).setAlpha(1f)
                    .setAnimationStyle(R.style.dialog_center_anim)
                    .autoFitWidth(0.8f)
                    .autoFitHeight(0.2f)
                    .setCancelable(true)
                    .setCanTouchOutCancel(true)
                    .setBackgroundBlackRate(0.6f)
                    .setViewGravity(Gravity.CENTER)
                    .setLayoutResId(R.layout.dialog_bottom_test_layout)
                    .create().show()
        }
    }
}