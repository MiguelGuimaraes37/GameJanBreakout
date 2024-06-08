package io.codeforall.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class BrickFactory {

    public Brick[] createBricks() {

        Brick[] bricksInput = new Brick[21];

        int xInitialValue = 40;
        int yInitialValue = 50;

        int x = 40;
        int y = 50;

        Position initialPosition = new Position(xInitialValue,yInitialValue);

        Color color = new Color(0,255,0);

        Rectangle initialRectangle = new Rectangle(x,y,125,60);

        for(int i = 0; i < 21; i++) {

            if(i == 0) {
                Brick b1 = new Brick(initialPosition, initialRectangle, color);
                b1.getRectangle().fill();
                b1.getRectangle().setColor(color);
                bricksInput[i] = b1;
                continue;
            }

            if(i == 7) {
                x=xInitialValue;
                y=yInitialValue+65;
                color = new Color(135,206,250);
            } else if (i == 14) {
                x=xInitialValue;
                y+=65;
                color = new Color(255,0,0);
            } else {
                x+=130;
            }

            bricksInput[i] = new Brick(new Position(x, y), new Rectangle(x, y, 125, 60), color);

        }

        return bricksInput;

    }
}
