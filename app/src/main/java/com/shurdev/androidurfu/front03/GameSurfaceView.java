package com.shurdev.androidurfu.front03;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

import java.util.Random;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private Bird bird;
    private Enemy enemy;
    private Context context;
    private int score;
    private Random random = new Random();

    private Thread gameThread;
    private volatile boolean running = false;
    private SurfaceHolder holder;
    private long lastUpdateTime;
    private static final long UPDATE_INTERVAL = 50;

    public GameSurfaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        holder = getHolder();
        holder.addCallback(this);

        bird = new Bird(30, 30, getResources());
        enemy = new Enemy(500, 500, getResources());
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            bird.setXY((int) event.getX(), (int) event.getY());
        }
        return true;
    }

    private void updateGame() {
        if (enemy == null) {
            enemy = new Enemy(1000, random.nextInt(800), getResources());
        }
        if (enemy.getCenterX() < -200) {
            enemy = new Enemy(1000, random.nextInt(800), getResources());
        }
        if (bird.intersect(enemy)) {
            enemy = new Enemy(1000, random.nextInt(800), getResources());
            score++;
        }

        bird.move();
        enemy.move();
    }

    private void renderGame(Canvas canvas) {
        if (canvas != null) {
            canvas.drawColor(android.graphics.Color.WHITE);

            bird.draw(canvas);
            enemy.draw(canvas);
        }
    }

    @Override
    public void run() {
        while (running) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastUpdateTime >= UPDATE_INTERVAL) {
                updateGame();
                lastUpdateTime = currentTime;
            }

            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                synchronized (holder) {
                    renderGame(canvas);
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        resume();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        pause();
    }

    public void pause() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SharedPreferences sp = context.getSharedPreferences("BIRD", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("BirdX", bird.getCenterX());
        editor.putInt("BirdY", bird.getCenterY());
        editor.putInt("Score", score);
        editor.apply();
    }

    public void resume() {
        SharedPreferences sp = context.getSharedPreferences("BIRD", Context.MODE_PRIVATE);
        bird = new Bird(sp.getInt("BirdX", 300), sp.getInt("BirdY", 300), getResources());
        score = sp.getInt("Score", 0);

        running = true;
        gameThread = new Thread(this);
        gameThread.start();
        lastUpdateTime = System.currentTimeMillis();
    }
}