import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Bar {
    private Rectangle bar;
    private Position position;

    public Bar(Rectangle player, Position position) {
        this.bar = player;
        this.position = position;
    }

    public void fill() {
        bar.fill();
    }


    public Position getPosition() {
        return position;
    }

    public void setColor(Color c) {
        bar.setColor(c);
    }
    public void moveRight() {
        position.setX(position.getX()+10);
        bar.translate(10, 0);
    }

    public void moveLeft() {
        position.setX(position.getX()-10);
        bar.translate(-10, 0);
    }
}
