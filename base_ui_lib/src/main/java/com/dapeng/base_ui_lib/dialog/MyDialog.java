package com.dapeng.base_ui_lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.AnimatorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import com.dapeng.base_ui_lib.R;
import com.dapeng.base_ui_lib.utils.ScreenUtils;


public class MyDialog extends Dialog {

    private Builder dialogBuilder;
    private Context context;
    public static final int PARAMS_NO_VALUE = 0;
    public static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    public static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;

    public MyDialog(@NonNull Context context) {
        super(context);
    }

    public MyDialog(@NonNull Context context, @StyleRes int themeResId, Builder dialogBuilder) {
        super(context, themeResId);
        this.context = context;
        this.dialogBuilder = dialogBuilder;
    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            if (dialogBuilder.contentView != null) {
                setContentView(dialogBuilder.contentView);
            } else if (dialogBuilder.layoutResId != PARAMS_NO_VALUE) {
                setContentView(dialogBuilder.layoutResId);
            } else {
                setContentView(new View(dialogBuilder.context));
            }

            setCancelable(dialogBuilder.cancelable);
            setCanceledOnTouchOutside(dialogBuilder.isCanTouchOutsideCancel);
            setOnDismissListener(dialogBuilder.dismissListener);

            Window window = getWindow();
            window.setWindowAnimations(dialogBuilder.animationStyle);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void show() {
        super.show();

        try {

            Window dialogWindow = getWindow();
            //dialogWindow.setDimAmount(dialogBuilder.blackRate);
//        WindowManager m = dialogWindow.getWindowManager();
//        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用

            // 获取对话框当前的参数值
            WindowManager.LayoutParams p = dialogWindow.getAttributes();
            // 设置高度和宽度
//        p.height = (int) (d.getHeight() * 0.4); // 高度设置为屏幕的0.6
//        p.width = (int) (d.getWidth() * 0.6); // 宽度设置为屏幕的0.65

            // 高
            p.height = dialogBuilder.heightPixel;
            // 宽
            p.width = dialogBuilder.widthPixel;
            // 设置位置
            p.gravity = dialogBuilder.gravity;
            // 设置 弹框 透明度
            p.alpha = dialogBuilder.alpha;
            // Set the amount of dim behind the window when using WindowManager.LayoutParams.FLAG_DIM_BEHIND. This overrides the default dim amount of that is selected by the Window based on its theme.
            // 黑色背景 //设置透明度  1.0表示完全不透明，而0.0表示完全透明
            p.dimAmount = dialogBuilder.blackRate;
            dialogWindow.setAttributes(p);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void dismiss() {
        super.dismiss();
//        try {
//            Window window = getWindow();
//            WindowManager.LayoutParams p = window.getAttributes();
//            p.alpha = 0f;
//            window.setAttributes(p);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void setOnDismissListener(@Nullable OnDismissListener listener) {
        super.setOnDismissListener(listener);
    }

    public static class Builder {

        private Context context;
        private View contentView;
        private int layoutResId;
        private int widthPixel = WRAP_CONTENT;
        private int heightPixel = WRAP_CONTENT;
        private int themeResId;
        private boolean cancelable;
        private boolean isCanTouchOutsideCancel;
        private OnDismissListener dismissListener;
        private int animationStyle;
        private float blackRate; // 0~1f
        private int gravity;
        private MyDialog myDialog;
        private float alpha; // 0~1f
        private float widthRate; // 0~1f
        private float heightRate; // 0~1f


        public Builder(Context context) {
            this.context = context;
        }

        public Builder setAlpha(float alpha) {
            this.alpha = alpha;
            return this;
        }

        public Builder setContentView(View contentView) {
            this.contentView = contentView;
            return this;
        }

        public Builder setLayoutResId(@LayoutRes int layoutResId) {
            this.layoutResId = layoutResId;
            return this;
        }

        public Builder autoFitWidth(float widthRate) {
            this.widthRate = widthRate;
            return this;
        }

        public Builder autoFitHeight(float heightRate) {
            this.heightRate = heightRate;
            return this;
        }

        public Builder setWidth(int widthPixel) {
            this.widthPixel = widthPixel;
            return this;
        }

        public Builder setHeight(int heightPixel) {
            this.heightPixel = heightPixel;
            return this;
        }

        public Builder setCanTouchOutCancel(boolean isCanTouchOutsideCancel) {
            this.isCanTouchOutsideCancel = isCanTouchOutsideCancel;
            return this;
        }

        public Builder setBackgroundBlackRate(float blackRate) {
            this.blackRate = blackRate;
            return this;
        }

        public Builder setThemeResId(int themeResId) {
            this.themeResId = themeResId;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener dismissListener) {
            this.dismissListener = dismissListener;
            return this;
        }

        public Builder setViewGravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        public Builder setAnimationStyle(int animationStyle) {
            this.animationStyle = animationStyle;
            return this;
        }

        public MyDialog create() {
            if (themeResId == Resources.ID_NULL)
                setThemeResId(R.style.DialogTheme);

            if (widthRate > 0) {
                setWidth((int) (widthRate * ScreenUtils.getScreenWidth(context)));
            } else {
                if (widthPixel == MATCH_PARENT)
                    setWidth(ScreenUtils.getScreenWidth(context));
                else
                    setWidth(widthPixel > 0 ? widthPixel : WRAP_CONTENT);
            }

            if (heightRate > 0) {
                setHeight((int) (heightRate * ScreenUtils.getScreenWidth(context)));
            } else {
                setHeight(heightPixel > 0 ? heightPixel : WRAP_CONTENT);
            }

            if (gravity == Gravity.NO_GRAVITY)
                setViewGravity(Gravity.CENTER);

            if (animationStyle == Resources.ID_NULL)
                setAnimationStyle(R.style.dialog_center_anim);


            myDialog = new MyDialog(context, themeResId, this);
            return myDialog;
        }

    }
}
