package io.codeforall.bootcamp;

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


    public Game() throws InterruptedException {
        background = new Picture(10, 10, "Breakout/resources/images/gameImage.png");
        bar = new Bar(new Rectangle(410, 800, 125, 20), new Position(410,800));
        ball = new Ball(new Ellipse(440, 790, 35, 35), new Position(440,700));
    }

    public void start() throws InterruptedException {
        prepare();

        boolean gameEnd = false;
        int counter = 0;

        while (!gameEnd) {

            while (ballValidPosition(getDirection(counter), ball.getPosition()) && !hitBrick(ball, bricks)) {

                Thread.sleep(55);

                switch (getDirection(counter)) {
                    case UP:
                        ball.moveUp();
                        break;
                    case DIAGONAL_DOWN_RIGHT:
                        System.out.println("DIAGONAL DOWN RIGHT");
                        ball.moveDiagonalDownRight();
                        break;
                    case DIAGONAL_DOWN_LEFT:
                        ball.moveDiagonalDownLeft();
                        break;
                    case DIAGONAL_UP_LEFT:
                        ball.moveDiagonalUpLeft();
                        break;
                    case DIAGONAL_UP_RIGHT:
                        ball.moveDiagonalUpRight();
                        break;
                }

            }

            counter++;

            if(counter == 4) {
                counter=0;
            }



        }

        System.exit(0);

        }

    private boolean ballValidPosition(Direction d, Position p1) {

        switch (d) {
            case UP:
                if (p1.getY() - 10 < -50) {
                    return false;
                }
                break;
            case DIAGONAL_DOWN_RIGHT:
                if(p1.getX() - 10 < -50) {
                    return false;
                }
                break;
        }

        return true;
    }

    public static boolean barValidPosition(Direction direction, Position position) {

        switch (direction) {
            case RIGHT:
                if (position.getX() + 10 > 840) {
                    return false;
                }
                break;
            case LEFT:
                if (position.getX() - 10 < 20) {
                    return false;
                }
                break;
        }

        return true;

    }

    public static Direction getDirection(int counter) {

        switch (counter) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.DIAGONAL_DOWN_RIGHT;
            case 2:
                return Direction.DIAGONAL_DOWN_LEFT;
            case 3:
                return Direction.DIAGONAL_UP_LEFT;
            default:
                return Direction.DIAGONAL_UP_RIGHT;
        }

    }

    private boolean hitBrick(Ball ball, Brick[] bricks) {

        Position ballPosition = ball.getPosition();

        for(int i = 0; i < bricks.length; i++) {

            if(bricks[i] == null) {
                continue;
            }

            if (ballPosition.getY()-60 == bricks[i].getPosition().getY()) {
                bricks[i] = null;
                return true;
            }
        }

        return false;
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
