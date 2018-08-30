package com.example.tam.gesturekhoapham;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    TextView text;
    int[] hinhanh = {R.drawable.trangchu,R.drawable.giohang,R.drawable.user};
    int position = 0;
    int khoangcach=100;
    int vantoc = 100;
    GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);
        image.setImageResource(hinhanh[0]);
        text = findViewById(R.id.text);
        gestureDetector = new GestureDetector(this,new Mygeture());
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }
    class Mygeture extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e2.getX() - e1.getX() > khoangcach && Math.abs(velocityX)>vantoc){
                Toast.makeText(MainActivity.this, "vuot trai sang phai", Toast.LENGTH_SHORT).show();
                position = position==2?0:position+1;
                image.setImageResource(hinhanh[position]);
            }else if(e1.getX() - e2.getX() > khoangcach && Math.abs(velocityX)>vantoc){
                Toast.makeText(MainActivity.this, "vuot phai sang trai", Toast.LENGTH_SHORT).show();
                position = position==0?2:position-1;
                image.setImageResource(hinhanh[position]);
            }else if(e2.getY() - e1.getY() > khoangcach && Math.abs(velocityY)>vantoc){
                Toast.makeText(MainActivity.this, "VUOT XUONG", Toast.LENGTH_SHORT).show();

            }else if(e1.getY() - e2.getY() > khoangcach && Math.abs(velocityY)>vantoc){
                Toast.makeText(MainActivity.this, "VUOT LEN", Toast.LENGTH_SHORT).show();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
}
