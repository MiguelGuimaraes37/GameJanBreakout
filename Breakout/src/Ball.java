import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;

public class Ball {
    private Ellipse ellipse;
    private Position position;
    private Direction nextDirection;
    private Direction lastDirection;
    private boolean firstMove;


    public Ball(Ellipse ellipse, Position position) {
        this.ellipse = ellipse;
        this.position=position;
        this.lastDirection = Direction.UP;
        this.firstMove=true;
        this.position = new Position(ellipse.getX(),ellipse.getY());
    }

    public Ellipse getEllipse() {
        return ellipse;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setEllipse(Ellipse ellipse) {
        this.ellipse = ellipse;
    }

    public void fill() {
        ellipse.fill();
    }

    public void moveUp() {
        position.decreaseY(10);
        ellipse.translate(0, -10);
    }

    public void moveDown() {
        position.increaseY(10);
        ellipse.translate(0, 10);
    }

    public void moveDiagonalDownLeft() {
        position.decreaseY(10);
        position.decreaseX(10);
        ellipse.translate(-10,10);
    }

    public void moveDiagonalDownRight() {
        position.decreaseY(10);
        position.decreaseX(10);
        ellipse.translate(10,10);
    }

    public void moveDiagonalUpRight() {
        position.decreaseY(10);
        position.increaseX(10);
        ellipse.translate(10,-10);
    }

    public void moveDiagonalUpLeft() {
        position.decreaseY(10);
        position.decreaseX(10);
        ellipse.translate(-10,-10);
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setColor(Color color) {
        ellipse.setColor(color);
    }
}
