import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    private Rectangle field;
    private Picture picture;
    private Bar bar;

    private Ball ball;



    public Game() {
        field = new Rectangle(10, 10, 962, 819);
        picture = new Picture(10, 10, "resources/gameImage.png");
        bar = new Bar(new Rectangle(410, 800, 125, 20), new Position(410,800));
        ball = new Ball(new Ellipse(440, 790, 35, 35), new Position(440,790));
    }

    public void start() {

          prepare();

    }

    public void prepare() {

        field.draw();

        picture.draw();

        ball.fill();

        bar.setColor(new Color(255, 255, 255));
        bar.fill();
        new Handler(bar, field.getWidth(), field.getHeight(), ball);

        FieldPosition.moveBall(ball);
    }

}
