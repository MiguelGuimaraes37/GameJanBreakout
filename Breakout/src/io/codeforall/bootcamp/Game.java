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

    public void prepare() {
        background.draw();

        listLines = new ListLines();

        ball.setColor(new Color(255,255,255));
        ball.fill();

        bar.setColor(new Color(255, 255, 255));
        bar.fill();

        new Handler(bar, background.getWidth(), background.getHeight(), ball, background.getX());

    }

    public void start() throws InterruptedException {
        prepare();

        boolean gameEnd = false;

        while (!gameEnd) {

            while (ballValidPosition(ball.getNextDirection())) {

                Thread.sleep(55);

                if (ball.getNextDirection() == Direction.UP) {
                    ball.moveUp();
                } else if (ball.getNextDirection() == Direction.DIAGONAL_DOWN_RIGHT) {
                    ball.moveDiagonalDownRight();
                } else if (ball.getNextDirection() == Direction.DIAGONAL_DOWN_LEFT) {
                    ball.moveDiagonalDownLeft();
                } else if (ball.getNextDirection() == Direction.DIAGONAL_UP_LEFT) {
                    ball.moveDiagonalUpLeft();
                } else {
                    ball.moveDiagonalUpRight();
                }

            }


            }

        }

        private void nextDirection(Direction currentDirection) {

            switch (currentDirection) {
                case UP:
                case DIAGONAL_UP_RIGHT:
                        ball.setNextDirection(Direction.DIAGONAL_DOWN_RIGHT);
                     break;
                case DIAGONAL_DOWN_RIGHT:
                    ball.setNextDirection(Direction.DIAGONAL_DOWN_LEFT);
                    break;
                case DIAGONAL_DOWN_LEFT:
                    ball.setNextDirection(Direction.DIAGONAL_UP_LEFT);
                    break;
                case DIAGONAL_UP_LEFT:
                    ball.setNextDirection(Direction.DIAGONAL_UP_RIGHT);
                    break;
            }
        }

    private void nextDirectionByDownRight(Direction currentDirection) {

        switch (currentDirection) {
            case DIAGONAL_DOWN_RIGHT:
                ball.setNextDirection(Direction.DIAGONAL_UP_RIGHT);
                break;
            case DIAGONAL_UP_RIGHT:
                ball.setNextDirection(Direction.DIAGONAL_UP_LEFT);
                break;
            case DIAGONAL_UP_LEFT:
                ball.setNextDirection(Direction.DIAGONAL_DOWN_LEFT);
                break;
            case DIAGONAL_DOWN_LEFT:
                ball.setNextDirection(Direction.DIAGONAL_DOWN_RIGHT);
                break;
        }

    }

    private void nextDirectionByDownLeft(Direction currentDirection) {

        switch (currentDirection) {
            case DIAGONAL_DOWN_LEFT:
                ball.setNextDirection(Direction.DIAGONAL_UP_LEFT);
                break;
            case DIAGONAL_UP_LEFT:
                ball.setNextDirection(Direction.DIAGONAL_UP_RIGHT);
                break;
            case DIAGONAL_UP_RIGHT:
                ball.setNextDirection(Direction.DIAGONAL_DOWN_RIGHT);
            case DIAGONAL_DOWN_RIGHT:
                ball.setNextDirection(Direction.DIAGONAL_DOWN_LEFT);
        }
    }

        private void checkDirection(Direction currentDirection, boolean hitBar) {

            if(currentDirection == Direction.DIAGONAL_DOWN_RIGHT && hitBar) {
                nextDirectionByDownRight(currentDirection);
            }
            else if(currentDirection == Direction.DIAGONAL_DOWN_LEFT && hitBar) {
                nextDirectionByDownLeft(currentDirection);
            }
            else {
                nextDirection(currentDirection);
            }
        }


    private boolean ballValidPosition(Direction d) throws InterruptedException {

        switch (d) {

            case UP:
                if(hitUp()) {
                    nextDirection(Direction.UP);
                    return false;
                }
                break;

            case DIAGONAL_DOWN_RIGHT:

                if(hitBall()) {
                    checkDirection(Direction.DIAGONAL_DOWN_RIGHT, true);
                    return false;
                }

                if (hitDown()) {
                    ball.delete();
                    Thread.sleep(50);
                    System.exit(0);
                    break;
                }

                if(hitRight()) {
                    checkDirection(Direction.DIAGONAL_DOWN_RIGHT, false);
                    return false;
                }
                break;

            case DIAGONAL_DOWN_LEFT:

                if(hitBall()) {
                    checkDirection(Direction.DIAGONAL_DOWN_LEFT, true);
                }
                else {
                    if(hitDown()) {
                        ball.delete();
                        Thread.sleep(50);
                        System.exit(0);
                    }
                }
                break;

            case DIAGONAL_UP_LEFT:;
                    if(hitUp() || hitLeft()) {
                        checkDirection(Direction.DIAGONAL_UP_LEFT, false);
                        return false;
                    }
                    break;
            case DIAGONAL_UP_RIGHT:
                if(hitUp() || hitRight()) {
                    nextDirection(Direction.DIAGONAL_UP_RIGHT);
                    return false;
                }
        }



        return true;
    }

    public boolean hitLeft() {
       return  ball.getX() == (background.getX()+10);
    }

    public boolean hitRight() {
       return  ball.getX() + ball.getWidth() > background.getWidth();
    }

    public boolean hitDown() {
       return  (ball.getY() + ball.getHeight()) > background.getHeight();
    }

    public boolean hitUp() {
       return  ball.getY() == (background.getX()+10);
    }

    public boolean hitBall() {

        /*

        System.out.println(ball.getY() + " Ball Y");
        System.out.println(bar.getY()  + " Bar Y");
        System.out.println(bar.getX() + " Bar X");
        System.out.println(ball.getX() + " Ball X");

         */

        return (ball.getY() == (bar.getY() - 30))

                && ball.getX() >= bar.getX()

                && ball.getX() <= (bar.getX() + bar.getWidth());

    }

    public static boolean barValidPosition(Direction direction, Bar bar, int fieldX, int fieldWidth) {

        System.out.println(bar.getX() + " GetX Bar");
        System.out.println(fieldX + " Field");

        switch (direction) {
            case RIGHT:
                if (bar.getX() + bar.getWidth() > fieldWidth) {
                    return false;
                }
                break;
            case LEFT:
                if (bar.getX() < (fieldX+20)) {
                    return false;
                }
                break;
        }

        return true;

    }

    private boolean hitBrick() {

        Brick brickDestroyed;

        for(int lineCounter = 0; lineCounter < listLines.getLength(); lineCounter++) {
            for(int brickCounter = 0; brickCounter < listLines.getLine(lineCounter).getLength(); brickCounter++) {

                    if(checkHeight(lineCounter, brickCounter) && checkWidth(lineCounter, brickCounter)) {

                        brickDestroyed = listLines.getLine(lineCounter).getBrick(brickCounter);
                        brickDestroyed.destroyBrick();
                        deleteBrick(brickCounter, listLines.getLine(lineCounter));
                        System.out.println(ball.getX()  + " Ball");
                        nextDirection(ball.getNextDirection());
                        return true;

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

    public boolean checkHeight(int lineCounter, int brickCounter) {

        return ball.getX() - ball.getHeight() >= listLines.getLine(lineCounter).getBrick(brickCounter).getY()

                &&

                ball.getX() - ball.getHeight() <=  listLines.getLine(lineCounter).getBrick(brickCounter).getHeight() +

                        listLines.getLine(lineCounter).getBrick(brickCounter).getY();
    }

    private void deleteBrick(int index, BrickLine line) {
        line.removeBrick(index);
    }



}
