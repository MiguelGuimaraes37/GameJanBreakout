import jdk.internal.dynalink.beans.StaticClass;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    private static Direction[] directions = new Direction[]{};
    private static Direction[] directionsReverse = new Direction[]{};

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

        while (!gameEnd) {

            for(int i = 0 ; i < bricks.length; i++) {
                if(i == 4) {
                    i = 0;
                }

                FieldPosition.moveBall(ball, bricks, Direction.values()[i]);
            }



        }

    }

    public void prepare() throws InterruptedException {

        directions = new Direction[]{Direction.DIAGONAL_DOWN_RIGHT, Direction.DIAGONAL_DOWN_LEFT
                ,Direction.DIAGONAL_UP_LEFT,Direction.DIAGONAL_UP_RIGHT};
        directionsReverse = new Direction[]{Direction.DIAGONAL_DOWN_LEFT, Direction.DIAGONAL_DOWN_RIGHT
                ,Direction.DIAGONAL_UP_RIGHT, Direction.DIAGONAL_UP_RIGHT};


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
