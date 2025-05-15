package com.shurdev.androidurfu.task02;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class GameField extends View {

    protected Bird bird = new Bird(30, 30, getResources());
    protected Enemy enemy = new Enemy(500, 500, getResources());
    protected Context context;
    private int score;

    private Random random = new Random();

    public GameField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        new GameTimer(50).start();
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        bird.setXY((int) event.getX(), (int) event.getY());
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bird.draw(canvas);
        enemy.draw(canvas);
    }

    public void pause() {
        SharedPreferences sp = context.getSharedPreferences("BIRD", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("BirdX", bird.getCenterX());
        editor.putInt("BirdY", bird.getCenterY());
        editor.commit();
    }

    public void resume() {
        SharedPreferences sp = context.getSharedPreferences("BIRD", Context.MODE_PRIVATE);
        bird = new Bird(sp.getInt("BirdX", 300), sp.getInt("BirdY", 300), getResources());
        bird.setXY(sp.getInt("BirdX", 300), sp.getInt("BirdY", 300));
    }

    class GameTimer extends CountDownTimer {

        public GameTimer(long countDownInterval) {
            super(Integer.MAX_VALUE, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            if (enemy == null) {
                enemy = new Enemy(1000, random.nextInt(800), getResources());
            }
            if (enemy.getCenterX() < -200) {
                enemy = new Enemy(1000, random.nextInt(800), getResources());
            }
            if(bird.intersect(enemy)){
                enemy = new Enemy(1000, random.nextInt(800), getResources());
                score++;
            }
            bird.move();
            enemy.move();
            invalidate();
        }

        @Override
        public void onFinish() {

        }
    }
}
