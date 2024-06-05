import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

import java.util.concurrent.Delayed;

public class Ball {
    private Ellipse ball;
    private Position position;

    private Direction lastDirection;
    private boolean firstMove;


    public Ball(Ellipse ball, Position position) {
        this.ball = ball;
        this.position=position;
        this.lastDirection = FieldPosition.randomDirection(this);
        this.firstMove=true;
    }

    public Ellipse getEllipse() {
        return ball;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setBall(Ellipse ball) {
        this.ball = ball;
    }

    public void fill() {
        ball.fill();
    }

    public void moveUp() {
        position.decreaseY(10);
        ball.translate(0, -10);
    }

    public void moveDown() {
        position.increaseY(10);
        ball.translate(0, ball.getY());
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setColor(Color color) {
        ball.setColor(color);
    }
}
