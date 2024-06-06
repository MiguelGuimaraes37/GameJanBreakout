import jdk.internal.dynalink.beans.StaticClass;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    private String[] directions;
    private Brick[] bricks;
    private Picture background;
    private Bar bar;
    private Ball ball;


// 440 x
    public Game() throws InterruptedException {
        background = new Picture(10, 10, "resources/gameImage.png");
        bar = new Bar(new Rectangle(410, 800, 125, 20), new Position(410,800));
        ball = new Ball(new Ellipse(440, 790, 35, 35), new Position(440,700));
    }

    public void start() throws InterruptedException {
        prepare();

        boolean gameEnd = false;
        int counter = 0;

        while (!gameEnd) {

            FieldPosition.moveBall(ball, bricks, counter);

            counter++;

                if(counter == 4) {
                    counter=0;
                }

            }

        }





    public void prepare() throws InterruptedException {
        background.draw();

        BrickFactory factory = new BrickFactory();

        bricks = factory.createBricks();

        ball.setColor(new Color(255,255,255));
        ball.fill();

        bar.setColor(new Color(255, 255, 255));
        bar.fill();

        new Handler(bar, background.getWidth(), background.getHeight(), ball);

    }

}
