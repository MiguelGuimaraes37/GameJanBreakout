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
    private int counter;

    public Game() throws InterruptedException {
        background = new Picture(10, 10, "Breakout/resources/images/gameImage.png");
        bar = new Bar(new Rectangle(410, 800, 125, 20));
        ball = new Ball(new Ellipse(440, 790, 35, 35));
    }

    public void start() throws InterruptedException {
        prepare();

        boolean gameEnd = false;

        while (!gameEnd) {

            while (ballValidPosition(ball.getNextDirection()) && !hitBrick()) {

                Thread.sleep(55);
                System.out.println("Direction: " + ball.getNextDirection());

                if(ball.getNextDirection() == Direction.UP) {
                    ball.moveUp();
                } else if (ball.getNextDirection() == Direction.DIAGONAL_DOWN_RIGHT) {
                    ball.moveDiagonalDownRight();
                } else if (ball.getNextDirection() == Direction.DIAGONAL_DOWN_LEFT) {
                    ball.moveDiagonalDownLeft();
                } else if(ball.getNextDirection() == Direction.DIAGONAL_UP_LEFT) {
                    ball.moveDiagonalUpLeft();
                }
                else {
                    ball.moveDiagonalUpRight();
                }

                /*
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
                 */

            }

        }

        System.exit(0);

        }

        private void nextDirection(Direction previousDirection) {

            switch (previousDirection) {
                case UP:
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
                default:
                    ball.setNextDirection(Direction.UP);
                    break;
            }

        }

    private boolean ballValidPosition(Direction d) {

        System.out.println(d.toString());

        switch (d) {
            case UP:
                if (ball.getY() - 10 < -50) {
                    System.out.println("Teste 1");
                    nextDirection(Direction.UP);
                    return false;
                }
                break;
            case DIAGONAL_DOWN_RIGHT:
                if(ball.getX() + 10 > 912) {
                    System.out.println("Teste 2");
                    nextDirection(Direction.DIAGONAL_DOWN_RIGHT);
                    return false;
                }
                break;
            case DIAGONAL_DOWN_LEFT:
                if(ball.getX() - 10 > 10) {
                    nextDirection(Direction.DIAGONAL_DOWN_LEFT);
                    return false;
                }
                break;
            case DIAGONAL_UP_LEFT:;
            if(ball.getX() - 10 > 10) {
                nextDirection(Direction.DIAGONAL_UP_LEFT);
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
                        nextDirection(ball.getNextDirection());
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
