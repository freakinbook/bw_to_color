package com.example.android.bwtocolor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {
    boolean visible = false;
    Lock lock = new ReentrantLock();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void paint(View view) {
        if (visible){
            visible = false;
            ImageView coloredImage = (ImageView) findViewById(R.id.colored);
            Thread t = new Thread(() -> makeInvisible(coloredImage));
            t.start();
            return;
        }
        if(!visible){
            visible = true;
            ImageView coloredImage = (ImageView) findViewById(R.id.colored);
            Thread t = new Thread(() -> makeVisible(coloredImage));
            t.start();
            return;
        }
    }

    public void makeVisible(View view){
        float alpha = 0.0f;
        while (alpha < 1.0){
            view.setAlpha(alpha);
            alpha += 0.1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        view.setAlpha(1.0f);
    }

    public void makeInvisible(View view){
        float alpha = 1.0f;
        while (alpha > 0.0){
            view.setAlpha(alpha);
            alpha -= 0.1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        view.setAlpha(0.0f);
    }
}