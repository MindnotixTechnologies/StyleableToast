package com.muddzdev.styleabletoast;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {


    @BindView(R.id.corner_value_txv)
    TextView cornerValueTxv;
    @BindView(R.id.corner_radius_sb)
    AppCompatSeekBar cornerRadiusSb;
    @BindView(R.id.stroke_value_txv)
    TextView strokeValueTxv;
    @BindView(R.id.stroke_width_sb)
    AppCompatSeekBar strokeWidthSb;
    @BindView(R.id.strokecolor_btn)
    Button strokecolorBtn;
    @BindView(R.id.backgroundcolor_btn)
    Button backgroundcolorBtn;
    @BindView(R.id.textcolor_btn)
    Button textcolorBtn;
    @BindView(R.id.icon_left)
    ImageView iconLeft;
    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.icon_right)
    ImageView iconRight;
    @BindView(R.id.demo_toast)
    LinearLayout demoToast;
    @BindView(R.id.text_bold_cb)
    CheckBox textBoldCb;
    @BindView(R.id.icon_left_cb)
    CheckBox iconLeftCb;
    @BindView(R.id.icon_right_cb)
    CheckBox iconRightCb;

    private int strokeColor = Color.RED;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        textBoldCb.setOnCheckedChangeListener(this);
        iconLeftCb.setOnCheckedChangeListener(this);
        iconRightCb.setOnCheckedChangeListener(this);
        cornerRadiusSb.setOnSeekBarChangeListener(this);
        strokeWidthSb.setOnSeekBarChangeListener(this);

        textview.setText("Hello World");
        strokeValueTxv.setText(strokeWidthSb.getProgress() + "dp");
        cornerValueTxv.setText(cornerRadiusSb.getProgress() + "dp");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.corner_radius_sb:
                setCornerRadius(i);
                break;

            case R.id.stroke_width_sb:
                setStrokeWidth(i, strokeColor);
                break;
        }
    }


    @OnClick({R.id.textcolor_btn, R.id.backgroundcolor_btn, R.id.strokecolor_btn})
    public void onColorPickersClicked(View v) {
        switch (v.getId()) {

            case R.id.textcolor_btn:
//                showColorPicker(1);
                break;

            case R.id.backgroundcolor_btn:
//                showColorPicker(2);
                break;

            case R.id.strokecolor_btn:
//                showColorPicker(3);
                break;

        }
    }

    @SuppressLint("SetTextI18n")
    private void setStrokeWidth(int radius, int color) {
        GradientDrawable toastShape = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.styleabletoast_shape);
        strokeValueTxv.setText(radius + "dp");
        int radiusDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, getResources().getDisplayMetrics());
        toastShape.setStroke(radiusDp, color);
        demoToast.setBackground(toastShape);
    }

    @SuppressLint("SetTextI18n")
    private void setCornerRadius(int radius) {
        GradientDrawable toastShape = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.styleabletoast_shape);
        cornerValueTxv.setText(radius + "dp");
        int radiusDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, getResources().getDisplayMetrics());
        toastShape.setCornerRadius(radiusDp);
        demoToast.setBackground(toastShape);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.text_bold_cb:
                textview.setTypeface(textview.getTypeface(), b ? Typeface.BOLD : Typeface.NORMAL);
                break;
            case R.id.icon_left_cb:
                iconLeft.setImageResource(b ? R.drawable.ic_autorenew_black_24dp : 0);
                iconLeft.setVisibility(b ? View.VISIBLE : View.GONE);
                setIconPadding();
                break;
            case R.id.icon_right_cb:
                iconRight.setImageResource(b ? R.drawable.ic_autorenew_black_24dp : 0);
                iconRight.setVisibility(b ? View.VISIBLE : View.GONE);
                setIconPadding();
                break;

        }
    }

    private void setIconPadding(boolean on) {
        if(on){
            int iconPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
            demoToast.setPadding(iconPadding, demoToast.getPaddingTop(), iconPadding, demoToast.getPaddingBottom());
        }

    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
