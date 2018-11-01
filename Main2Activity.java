package com.example.delamey.myapplication5;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {


    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.reset)
    Button reset;
    @BindView(R.id.yellow)
    ImageView yellow;
    @BindView(R.id.blue)
    ImageView blue;
    @BindView(R.id.black)
    ImageView black;
    @BindView(R.id.pink)
    ImageView pink;
    private DrawingWithoutBezier drawingWithoutBezier;
    private Canvas canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        drawingWithoutBezier = new DrawingWithoutBezier(this);
        framelayout.addView(drawingWithoutBezier);
    }


    @OnClick({R.id.yellow, R.id.blue, R.id.black, R.id.pink,R.id.reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.yellow:
                Paint paint=new Paint();
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(3);
                paint.setColor(Color.BLACK);
                drawingWithoutBezier.setPaint(paint);
                break;
            case R.id.blue:
                drawingWithoutBezier.getPaint().setColor(Color.BLUE);
                break;
            case R.id.black:
                drawingWithoutBezier.getPaint().setColor(Color.BLACK);
                break;
            case R.id.pink:
                drawingWithoutBezier.getPaint().setColor(getResources().getColor(R.color.pink));
                break;
            case R.id.reset:
                drawingWithoutBezier.getmPath().reset();
                drawingWithoutBezier.clear();
                break;
        }
    }
}
