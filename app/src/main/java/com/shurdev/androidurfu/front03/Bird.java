package com.shurdev.androidurfu.front03;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.shurdev.androidurfu.R;

import java.util.LinkedList;

public class Bird extends Unit {

    private Bitmap bird = null;
    private LinkedList<Rect> list = new LinkedList<>();
    private Rect dist;

    public Bird(int x, int y, Resources resources) {
        bird = BitmapFactory.decodeResource(resources, R.drawable.twitter);
        for (int i = 0; i < 4; i++) {
            list.add(new Rect(i * bird.getHeight(), 0, (i + 1) * bird.getHeight(), bird.getHeight()));
        }
        dist = new Rect(x, y, x + bird.getHeight(), y + bird.getHeight());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bird, list.getFirst(), dist, new Paint());
        list.addLast(list.getFirst());
        list.removeFirst();
    }

    @Override
    public void move() {
        if (Math.abs(dist.centerX() - x) < 6) {

        } else if (dist.centerX() > x) {
            dist.left -= 10;
            dist.right -= 10;
        } else {
            dist.left += 10;
            dist.right += 10;
        }

        if (Math.abs(dist.centerY() - y) < 6) {

        } else if (dist.centerY() > y) {
            dist.top -= 10;
            dist.bottom -= 10;
        } else {
            dist.top += 10;
            dist.bottom += 10;
        }
    }

    @Override
    public int getCenterX() {
        return dist.centerX();
    }

    @Override
    public int getCenterY() {
        return dist.centerY();
    }

    @Override
    public int getRadius() {
        return (dist.right - dist.left) / 2;
    }
}
