import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class BrickFactory {

    public static Brick[] createBricks() {

        Brick[] bricksInput = new Brick[28];

        int xInitialValue = 40;
        int yInitialValue = 50;

        int x = 40;
        int y = 50;

        Position initialPosition = new Position(xInitialValue,yInitialValue);

        Color color = new Color(0,255,0);

        Rectangle initialRectangle = new Rectangle(x,y,125,60);

        for(int i = 0; i < 28; i++) {

            if(i == 0) {
                Brick b1 = new Brick(initialPosition, initialRectangle);
                b1.getGrid().draw();
                b1.getGrid().fill();
                b1.getGrid().setColor(color);
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
            } else if(i == 21) {
                x=xInitialValue;
                y+=65;
                color = new Color(255,255,0);
            }
            else {
                x+=130;
            }

            Brick b2 = new Brick(new Position(x, y), new Rectangle(x, y, 125, 60));
            b2.getGrid().setColor(color);
            b2.getGrid().draw();
            b2.getGrid().fill();
            bricksInput[i] = b2;

        }

        return bricksInput;
    }
}
