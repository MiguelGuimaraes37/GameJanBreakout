package io.codeforall.bootcamp;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    private Picture background;
    private Bar bar;
    private Ball ball;

    private ListLines listLines;


    public Game() throws InterruptedException {
        background = new Picture(10, 10, "Breakout/resources/images/gameImage.png");
        bar = new Bar(new Rectangle(410, 800, 125, 20));
        ball = new Ball(new Ellipse(440, 790, 35, 35));
    }

    public void start() throws InterruptedException {
        prepare();

        boolean gameEnd = false;
        int counter = 0;

        while (!gameEnd) {

            while (ballValidPosition(getDirection(counter)) && !hitBrick()) {

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

    private boolean ballValidPosition(Direction d) {

        switch (d) {
            case UP:
                if (ball.getY() - 10 < -50) {
                    return false;
                }
                break;
            case DIAGONAL_DOWN_RIGHT:
                if(ball.getX() - 10 < -50) {
                    return false;
                }
                break;
        }

        return true;
    }

    public static boolean barValidPosition(Direction direction, Bar bar) {

        switch (direction) {
            case RIGHT:
                if (bar.getX() + 10 > 840) {
                    return false;
                }
                break;
            case LEFT:
                if (bar.getX() - 10 < 20) {
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

    private boolean hitBrick() {

        Brick brickDestroyed;

        for(int lineCounter = 0; lineCounter < listLines.getLength(); lineCounter++) {
            for(int brickCounter = 0; brickCounter < listLines.getLine(lineCounter).getLength(); brickCounter++) {

                if(listLines.getLine(lineCounter).getBrick(brickCounter) != null) {

                    if(listLines.getLine(lineCounter).getBrick(brickCounter).getY() == ball.getY()-60
                            && checkWidth(lineCounter, brickCounter)) {

                        brickDestroyed = listLines.getLine(lineCounter).getBrick(brickCounter);
                        brickDestroyed.destroyBrick();
                        deleteBrick(brickCounter, listLines.getLine(lineCounter));
                        System.out.println(ball.getX()  + " Ball");
                        return true;

                    }
                }

            }
        }

        return false;
    }

    private boolean checkWidth(int lineCounter, int brickCounter) {

        return ball.getX() > listLines.getLine(lineCounter).getBrick(brickCounter).getX()

                &&

                ball.getX() < (listLines.getLine(lineCounter).getBrick(brickCounter).getX()

                +


                listLines.getLine(lineCounter).getBrick(brickCounter).getWidth());

    }

    private void deleteBrick(int index, BrickLine line) {
        line.removeBrick(index);
    }


    public void prepare() {
        background.draw();

        listLines = new ListLines();

        ball.setColor(new Color(255,255,255));
        ball.fill();

        bar.setColor(new Color(255, 255, 255));
        bar.fill();

        new Handler(bar, background.getWidth(), background.getHeight(), ball);

    }

}
