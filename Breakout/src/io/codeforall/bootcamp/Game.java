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
        ball = new Ball(new Ellipse(440, 780, 35, 35));
    }

    public void prepare() {
        background.draw();

        listLines = new ListLines();

        ball.setColor(new Color(255,255,255));
        ball.fill();

        bar.setColor(new Color(200, 33, 41));
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

            gameEnd = checkGameStatus();

            }

        Thread.sleep(100);
        System.exit(0);

        }

    private boolean ballValidPosition(Direction d) throws InterruptedException {

        if (hitUp() && (d == Direction.DIAGONAL_UP_RIGHT || d == Direction.DIAGONAL_UP_LEFT || d == Direction.UP)) {
            if (d == Direction.DIAGONAL_UP_LEFT) {
                ball.setNextDirection(Direction.DIAGONAL_DOWN_LEFT);
            } else {
                ball.setNextDirection(Direction.DIAGONAL_DOWN_RIGHT);
            }
            return false;
        }
        else if(hitRight() && (d == Direction.DIAGONAL_UP_RIGHT || d == Direction.DIAGONAL_DOWN_RIGHT)) {
            if(d == Direction.DIAGONAL_UP_RIGHT) {
                ball.setNextDirection(Direction.DIAGONAL_UP_LEFT);
            }
            else {
                ball.setNextDirection(Direction.DIAGONAL_DOWN_LEFT);
            }
            return false;
        }
        else if (hitLeft() && (d == Direction.DIAGONAL_UP_LEFT || d == Direction.DIAGONAL_DOWN_LEFT)) {
            if(d == Direction.DIAGONAL_UP_LEFT) {
                ball.setNextDirection(Direction.DIAGONAL_UP_RIGHT);
            }
            else {
                ball.setNextDirection(Direction.DIAGONAL_DOWN_RIGHT);
            }
            return false;
        }else if(hitBar() && (d == Direction.DIAGONAL_DOWN_LEFT || d == Direction.DIAGONAL_DOWN_RIGHT)) {
            if(d == Direction.DIAGONAL_DOWN_RIGHT) {
                ball.setNextDirection(Direction.DIAGONAL_UP_RIGHT);
            } else {
                ball.setNextDirection(Direction.DIAGONAL_UP_LEFT);
            }
            return false;
        } else if(hitDown() && (d == Direction.DIAGONAL_DOWN_LEFT || d == Direction.DIAGONAL_DOWN_RIGHT)) {
            Thread.sleep(50);
            ball.delete();
            System.exit(0);
        } else if(hitDownBrick() && (d == Direction.UP || d == Direction.DIAGONAL_UP_LEFT || d == Direction.DIAGONAL_UP_RIGHT)) {
            System.out.println("HitDownBrick");
            if (d == Direction.DIAGONAL_UP_LEFT) {
                ball.setNextDirection(Direction.DIAGONAL_DOWN_LEFT);
            } else {
                ball.setNextDirection(Direction.DIAGONAL_DOWN_RIGHT);
            }
            return false;
        }   else if(hitRightBrick() && (d == Direction.DIAGONAL_UP_LEFT || d == Direction.DIAGONAL_DOWN_LEFT)) {
                if(d == Direction.DIAGONAL_UP_LEFT) {
                    ball.setNextDirection(Direction.DIAGONAL_UP_RIGHT);
                } else {
                    ball.setNextDirection(Direction.DIAGONAL_DOWN_RIGHT);
                }
                return false;
        } else if(hitUpBrick() && (d == Direction.DIAGONAL_DOWN_LEFT || d == Direction.DIAGONAL_DOWN_RIGHT)) {
            if(d == Direction.DIAGONAL_DOWN_LEFT) {
                ball.setNextDirection(Direction.DIAGONAL_UP_LEFT);
            }else {
                ball.setNextDirection(Direction.DIAGONAL_UP_RIGHT);
            }
            return false;
        } else if(hitLeftBrick() && (d == Direction.DIAGONAL_UP_RIGHT || d == Direction.DIAGONAL_DOWN_RIGHT)) {
            if(d == Direction.DIAGONAL_UP_RIGHT) {
                ball.setNextDirection(Direction.DIAGONAL_UP_LEFT);
            }  else {
                ball.setNextDirection(Direction.DIAGONAL_DOWN_LEFT);
            }
            return false;
        }

        return true;
    }

    public boolean hitUpBrick() {

        for(int lineIndex = 0; lineIndex < listLines.getLength(); lineIndex++) {

            BrickLine line = listLines.getLine(lineIndex);

            for(int brickIndex = 0; brickIndex < line.getLength(); brickIndex++) {

                Brick brick = line.getBrick(brickIndex);

                if(
                        (ball.getY() + ball.getHeight() >= brick.getY() && ball.getY() + ball.getHeight() <= brick.getMaxY())

                                &&

                                (ball.getX() >= brick.getX() && ball.getX() <= brick.getMaxX()) ) {

                    brick.destroyBrick();
                    deleteBrick(brickIndex, line);
                    return true;

                }

            }

        }

        return false;
    }

    public boolean hitRightBrick() {

        for(int lineIndex = 0; lineIndex < listLines.getLength(); lineIndex++) {

            BrickLine line = listLines.getLine(lineIndex);

            for(int brickIndex = 0; brickIndex < line.getLength(); brickIndex++) {

                Brick brick = line.getBrick(brickIndex);

                if(ball.getNextDirection() == Direction.DIAGONAL_DOWN_LEFT) {
                    if(
                            (ball.getY() + ball.getHeight() >= brick.getY() && ball.getY() + ball.getHeight() <= brick.getMaxY())

                                    &&

                                    (ball.getX() >= brick.getX() && ball.getX() <= brick.getMaxX()) ) {

                        brick.destroyBrick();
                        deleteBrick(brickIndex, line);
                        return true;

                    }
                }
                else {
                    if(
                            (ball.getY() - ball.getHeight() <= brick.getMaxY() && ball.getY() - ball.getHeight() >= brick.getY())

                                    &&

                                    (ball.getX() >= brick.getX() && ball.getX() <= brick.getMaxX()) ) {

                        System.exit(0);
                        brick.destroyBrick();
                        deleteBrick(brickIndex, line);
                        return true;

                    }
                }
            }

        }

        return false;
    }

    public boolean hitLeftBrick() {

        for(int lineIndex = 0; lineIndex < listLines.getLength(); lineIndex++) {

            BrickLine line = listLines.getLine(lineIndex);

            for(int brickIndex = 0; brickIndex < line.getLength(); brickIndex++) {

                Brick brick = line.getBrick(brickIndex);


                if(ball.getNextDirection() == Direction.DIAGONAL_DOWN_RIGHT) {
                    if (
                            (ball.getY() + ball.getHeight() >= brick.getY() && ball.getY() + ball.getHeight() <= brick.getMaxY())

                                    &&

                                    (ball.getX() >= brick.getX() && ball.getX() <= brick.getMaxX())) {


                        brick.destroyBrick();
                        deleteBrick(brickIndex, line);
                        return true;

                    }
                } else {
                    if (
                            (ball.getY() - ball.getHeight() <= brick.getMaxY() && ball.getY() - ball.getHeight() >= brick.getY())

                                    &&

                                    (ball.getX() >= brick.getX() && ball.getX() <= brick.getMaxX())) {

                        brick.destroyBrick();
                        deleteBrick(brickIndex, line);
                        return true;

                    }
                }

            }

        }

        return false;
    }

    public boolean hitDownBrick() {

        for(int lineIndex = 0; lineIndex < listLines.getLength(); lineIndex++) {

            BrickLine line = listLines.getLine(lineIndex);

            for(int brickIndex = 0; brickIndex < line.getLength(); brickIndex++) {

                Brick brick = line.getBrick(brickIndex);

                System.out.println(ball.getX() + " Ball X");
                System.out.println(brick.getX() + " Brick X");
                System.out.println(brick.getMaxX() + " Brick Max X");
                System.out.println("Expression: " + (ball.getX() >= brick.getX() && ball.getX() <= brick.getMaxX()) + "\n");

                if(
                        (ball.getY() - ball.getHeight() <= brick.getMaxY() && ball.getY() - ball.getHeight() >= brick.getY())

                                &&

                        (ball.getX() >= brick.getX() && ball.getX() <= brick.getMaxX()) ) {
                    brick.destroyBrick();
                    deleteBrick(brickIndex, line);
                    return true;
                }

            }

        }

        return false;
    }

    public boolean hitLeft() {
       return  ball.getX() == (background.getX()+10);
    }

    public boolean hitRight() {

        return ball.getX() + ball.getWidth() > background.getWidth();
    }

    public boolean hitDown() {
       return  (ball.getY() + ball.getHeight()) > background.getHeight();
    }

    public boolean hitUp() {
        return  ball.getY() == (background.getX()+10);
    }

    public boolean hitBar() {

        return (ball.getY() == (bar.getY() - 30))

                && ball.getX() + (ball.getWidth() / 2) >= bar.getX()

                && ball.getX() + (ball.getWidth() / 2) <= (bar.getX() + bar.getWidth());

    }

    public boolean checkGameStatus() {
        int countEmpty = 0;

        for(int i = 0; i < listLines.getLength(); i++) {
            if(listLines.getLine(i).itsEmpty()) {
                countEmpty++;
            }
        }

        return countEmpty == listLines.getLength();
    }


    public static boolean barValidPosition(Direction direction, Bar bar, int fieldX, int fieldWidth) {

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

    private void deleteBrick(int index, BrickLine line) {
        line.removeBrick(index);
    }

}
