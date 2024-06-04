public class FieldPosition {


    public static boolean isValidPosition(Direction d, Position p1) {

        System.out.println("Position X " + p1.getX());
        System.out.println("Position Y " + p1.getY());


        switch (d) {
            case RIGHT:
                if(p1.getX()+10 > 730) {
                    return false;
                }
                break;
            case LEFT:
                if(p1.getX()-10 < -90) {
                    return false;
                }
                break;
        }

        return true;
    }

    public static void moveBall(Ball ball) {
        Direction direction;
        Position position = ball.getPosition();


        direction = randomDirection(ball);



        switch (direction) {
            case UP:
                ball.moveUp();
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
                    return Direction.DIAGONAL_LEFT;
                case 3:
                    return Direction.DIAGONAL_RIGHT;
            }
        }

        return null;
    }

}
