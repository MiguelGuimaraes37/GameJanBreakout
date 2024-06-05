import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Brick {

    private Position position;
    private Rectangle grid;

    public Brick(Position position, Rectangle grid) {
        this.position = position;
        this.grid = grid;
    }

    public Position getPosition() {
        return position;
    }

    public Rectangle getGrid(){
        return grid;
    }

}
