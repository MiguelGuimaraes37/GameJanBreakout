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

    public static void moveBall(Ball ball, Brick[] bricks, int i) throws InterruptedException {


        while (isValidPosition(getDirection(i), ball.getPosition()) && !hitBrick(ball, bricks)) {

            Thread.sleep(55);

            System.out.println(i);

            switch (getDirection(i)) {
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

    }

    public static Direction getDirection(int i) {

        switch (i) {
            case 0:
                return Direction.UP;
            case 1:
                System.out.println("Sout test");
                return Direction.DIAGONAL_DOWN_RIGHT;
            case 2:
                return Direction.DIAGONAL_DOWN_LEFT;
            case 3:
                return Direction.DIAGONAL_UP_LEFT;
            default:
                return Direction.DIAGONAL_UP_RIGHT;
        }
    }

    private static boolean hitBrick(Ball ball, Brick[] bricks) {

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

    private static int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

}
