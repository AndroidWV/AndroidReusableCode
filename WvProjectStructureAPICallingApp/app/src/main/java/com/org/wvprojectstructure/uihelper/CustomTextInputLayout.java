package com.org.wvprojectstructure.uihelper;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import com.org.wvprojectstructure.R;
import com.org.wvprojectstructure.utils.CommonMethods;

public class CustomTextInputLayout extends TextInputLayout {

    private Context wContext;
    private TextView errorView;

    public CustomTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.wContext = context;
    }

    @Override
    public void setErrorEnabled(boolean enabled) {
        super.setErrorEnabled(enabled);
        if (!enabled) {
            if (errorView != null) {
                errorView.setTextColor(CommonMethods.getColorWrapper(wContext, android.R.color.holo_red_dark));
            }
            return;
        }
        try {
            errorView = this.findViewById(R.id.textinput_error);
            FrameLayout errorViewParent = (FrameLayout) errorView.getParent();
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            errorViewParent.setLayoutParams(params);
            errorView.setPadding(0, 15, 0, 15);
            errorView.setGravity(Gravity.END);
            errorView.setTextColor(CommonMethods.getColorWrapper(wContext, android.R.color.holo_red_dark));
        } catch (Exception e) {
            // At least log what went wrong
            e.printStackTrace();
        }
    }

    public static Spannable setErrorMessage(Context context, String message) {
        Spannable span = new SpannableString("  " + message);
        @SuppressLint("UseCompatLoadingForDrawables") Drawable android = context.getResources().getDrawable(R.mipmap.ic_error);
        android.setBounds(0, 0, 20, 20);
        CenteredImageSpan image = new CenteredImageSpan(android);
        span.setSpan(image, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return span;
    }
}
