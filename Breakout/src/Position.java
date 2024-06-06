public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void decreaseY(int y) {
        this.y-=y;
    }

    public void increaseX(int x) {
        this.x+=x;
    }

    public void increaseY(int y) {
        this.y+=y;
    }

    public void decreaseX(int x) {
        this.x-=x;
    }

    public boolean equals(Position positionInput) {

        return this.getY() == positionInput.getY();
    }
}
