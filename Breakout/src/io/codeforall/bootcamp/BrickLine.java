package io.codeforall.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import java.util.ArrayList;
import java.util.List;

public class BrickLine {

    private List<Brick> bricks;
    private int lineY;

    public BrickLine(int lineY, Color color) {
        this.lineY = lineY;


        bricks = new ArrayList<>();

        int x = 40;
        int rectangleWidth = 125;
        int rectangleHeight = 60;

        for(int i = 0; i < 7; i++) {

            if(i == 0) {
                bricks.add(new Brick(x , lineY, rectangleWidth, rectangleHeight, color));
                continue;
            }

            x+=130;

            bricks.add(new Brick(x,lineY, rectangleWidth, rectangleHeight, color));
        }
    }

    public BrickLine() {

    }

    public int getLineY() {
        return lineY;
    }

    public void removeBrick(int index) {
        bricks.remove(index);
    }

    public int getLength() {
        return bricks.size();
    }

    public Brick getBrick(int index) {
        return bricks.get(index);
    }

}
