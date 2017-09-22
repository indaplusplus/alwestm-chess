package graphical;

public enum PieceDisplay {
    None(""),

    WKing("\u2654"),
    WQueen("\u2655"),
    WRook("\u2656"),
    WBishop("\u2657"),
    WKnight("\u2658"),
    WPawn("\u2659"),

    BKing("\u265A"),
    BQueen("\u265B"),
    BRook("\u265C"),
    BBishop("\u265D"),
    BKnight("\u265E"),
    BPawn("\u265F");

    private String s;

    PieceDisplay(String s) {
        this.s = s;
    }

    public String toString() {
        return s;
    }
}
