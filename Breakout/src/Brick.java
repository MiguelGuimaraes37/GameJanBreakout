import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Brick {

    private Position position;
    private Rectangle rectangle;

    public Brick(Position position, Rectangle rectangle) {
        this.position = new Position(rectangle.getX(),rectangle.getY());
        this.rectangle = rectangle;
    }

    public Position getPosition() {
        return position;
    }

    public Rectangle getRectangle(){
        return rectangle;
    }

}
