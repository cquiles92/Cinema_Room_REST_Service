package cinema.model;

public class Seat {
    protected final int row;
    protected final int column;
    protected final int price;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        price = row <= 4 ? 10 : 8;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }
}
