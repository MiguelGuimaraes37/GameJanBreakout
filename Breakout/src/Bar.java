import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Bar {
    private Rectangle rectangle;
    private Position position;

    public Bar(Rectangle player, Position position) {
        this.rectangle = player;
        this.position = new Position(player.getX(),player.getY());
    }

    public void fill() {
        rectangle.fill();
    }


    public Position getPosition() {
        return position;
    }

    public void setColor(Color c) {
        rectangle.setColor(c);
    }
    public void moveRight() {
        position.setX(position.getX()+10);
        rectangle.translate(10, 0);
    }

    public void moveLeft() {
        position.setX(position.getX()-10);
        rectangle.translate(-10, 0);
    }
}
