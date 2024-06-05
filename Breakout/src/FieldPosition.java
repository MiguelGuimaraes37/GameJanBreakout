public class FieldPosition {


    public static boolean isValidPosition(Direction d, Position p1) {

        System.out.println("Position X " + p1.getX());
        System.out.println("Position Y " + p1.getY());


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
                if (p1.getY() - 10 < 10) {
                    return false;
                }
                break;
        }

        System.out.println("True");
        return true;
    }

    public static void moveBall(Ball ball) throws InterruptedException {


        if(ball.isFirstMove()) {

            while(isValidPosition(Direction.UP, ball.getPosition())) {
                System.out.println("Test");
                ball.moveUp();
            }

            ball.setFirstMove(false);
        }

    }

    private static int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static Direction randomDirection(Ball ball) {

        int randomFirstMove = getRandom(1,3);

        if(ball.isFirstMove()) {

            ball.setFirstMove(false);

            switch (randomFirstMove) {
                case 1:
                    return Direction.UP;
                case 2:
                    return Direction.DIAGONAL_UP_LEFT;
                case 3:
                    return Direction.DIAGONAL_UP_RIGHT;
            }
        }

        return null;
    }

}
