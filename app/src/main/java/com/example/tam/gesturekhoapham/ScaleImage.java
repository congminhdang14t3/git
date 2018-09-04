package com.example.tam.gesturekhoapham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ScaleImage extends AppCompatActivity {
    TextView image;
    ScaleGestureDetector scaleGestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_image);
        image = findViewById(R.id.imageScale);
        scaleGestureDetector = new ScaleGestureDetector(this,
                new ScaleGestureDetector.SimpleOnScaleGestureListener() {
                    float scale = 1.0f;
                    @Override
                    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                        scale*= scaleGestureDetector.getScaleFactor();
                        image.setScaleX(scale);
                        image.setScaleY(scale);
                        return super.onScale(scaleGestureDetector);
                    }

                    @Override
                    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                        Toast.makeText(ScaleImage.this, "Scale begin: "+scale, Toast.LENGTH_SHORT).show();
                        return super.onScaleBegin(scaleGestureDetector);
                    }

                    @Override
                    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
                        Toast.makeText(ScaleImage.this, "Scale end: "+scale, Toast.LENGTH_SHORT).show();
                        super.onScaleEnd(scaleGestureDetector);
                    }
                });
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scaleGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }
}
