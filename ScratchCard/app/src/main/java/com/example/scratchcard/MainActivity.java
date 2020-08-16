package com.example.scratchcard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imgv;
    private Bitmap alterBitmap;
    private double nX,nY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgv = findViewById(R.id.imageView);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.card);
        alterBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int statesHeight = 0;
        int resourceId = this.getResources().getIdentifier("status_bar_height","dimen","android");
        if (resourceId > 0){
            statesHeight = this.getResources().getDimensionPixelOffset(resourceId);
        }

        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (this.getTheme().resolveAttribute(android.R.attr.actionBarSize,tv,true)){
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }

        nX = bitmap.getWidth()*1.0/dm.widthPixels;
        nY = bitmap.getHeight()*1.0/(dm.heightPixels-statesHeight-actionBarHeight);

        Canvas canvas = new Canvas(alterBitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        Matrix matrix = new Matrix();
        canvas.drawBitmap(bitmap,matrix,paint);

        imgv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try {
                    int x = (int) motionEvent.getX();
                    int y = (int) motionEvent.getY();

                    for (int i=-200;i<200;i++){
                        for (int j=-200;j<200;j++){
                            if (Math.sqrt((i*i+j*j))<=200){
                                alterBitmap.setPixel((int) (x*nX+i),(int) (y*nY+j),Color.TRANSPARENT);
                            }
                        }
                    }

                    imgv.setImageBitmap(alterBitmap);
                } catch (Exception e){
                    e.printStackTrace();
                }

                return true;
            }
        });
    }
}