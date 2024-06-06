public class FieldPosition {


    public static boolean isValidPosition(Direction d, Position p1) {

        switch (d) {
            case RIGHT:
                if (p1.getX() + 10 > 840) {
                    return false;
                }
                break;
            case LEFT:
                if (p1.getX() - 10 < 20) {
                    return false;
                }
                break;
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

    public static void moveBall(Ball ball, Brick[] bricks, Direction currentDirection) throws InterruptedException {

        while (isValidPosition(currentDirection, ball.getPosition()) && !hitBrick(ball, bricks)) {

            Thread.sleep(55);

            if(currentDirection == Direction.UP) {
                ball.moveUp();
            } else if(currentDirection == Direction.DIAGONAL_DOWN_RIGHT) {
                ball.moveDiagonalDownRight();
            } else if (currentDirection == Direction.DIAGONAL_DOWN_LEFT) {
                ball.moveDiagonalDownLeft();
            } else if(currentDirection == Direction.DIAGONAL_UP_LEFT) {
                ball.moveDiagonalUpLeft();
            } else if(currentDirection == Direction.DIAGONAL_UP_RIGHT) {
                ball.moveDiagonalUpRight();
            }

        }

    }

    private static boolean hitBrick(Ball ball, Brick[] bricks) {

        Position ballPosition = ball.getPosition();

        for(int i = 0; i < bricks.length; i++) {

            if(bricks[i] == null) {
                System.out.println("aaa");
            }

            if (ballPosition.getY()-60 == bricks[i].getPosition().getY()) {
                bricks[i] = null;
                return true;
            }
        }

        return false;
    }

    private static int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
