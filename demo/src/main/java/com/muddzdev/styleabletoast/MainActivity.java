package com.muddzdev.styleabletoast;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {


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
    @BindView(R.id.textbold_checkbox)
    CheckBox textboldCheckbox;
    @BindView(R.id.solidbackground_checkbox)
    CheckBox solidbackgroundCheckbox;
    @BindView(R.id.icon_left)
    ImageView iconLeft;
    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.icon_right)
    ImageView iconRight;
    @BindView(R.id.demo_toast)
    LinearLayout demoToast;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        textview.setText("Hello World");
        cornerRadiusSb.setOnSeekBarChangeListener(this);
        strokeWidthSb.setOnSeekBarChangeListener(this);

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
                setStrokeWidth(i, Color.RED);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

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
}
