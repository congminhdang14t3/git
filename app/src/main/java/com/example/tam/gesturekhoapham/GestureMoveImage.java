package com.example.tam.gesturekhoapham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GestureMoveImage extends AppCompatActivity {
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_move_image);
        image = findViewById(R.id.image);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(250,250);
        layoutParams.leftMargin = 50;
        layoutParams.topMargin = 50;
        image.setLayoutParams(layoutParams);
        image.setOnTouchListener(new View.OnTouchListener() {
            RelativeLayout.LayoutParams params;
            float dx = 0, dy = 0,x = 0,y = 0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction() & motionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        params = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        dx = motionEvent.getRawX() - params.leftMargin;
                        dy = motionEvent.getRawY() - params.topMargin;break;
                    case MotionEvent.ACTION_MOVE:

                        params.leftMargin = (int) (motionEvent.getRawX() - dx);
                        params.topMargin = (int) (motionEvent.getRawY() - dy);
                        params.rightMargin=params.leftMargin + 5*params.width;
                        params.bottomMargin=params.topMargin + 10*params.height;
                        view.setLayoutParams(params);break;
                }
                return true;
            }
        });
    }
}
