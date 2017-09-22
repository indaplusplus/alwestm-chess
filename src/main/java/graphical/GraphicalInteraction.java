package graphical;

public abstract class GraphicalInteraction {
    /**
     * Start is excecuted when the GameBoard starts.
     */
    public void start(GraphicalBoard gb){}

    /**
     * Never runs ATM
     */
    public void end(GraphicalBoard gb) {}

    /**
     * The move function runns when a move is trying to be made
     * return false if the move is impossible
     * return true if the move worked.
     */
    public abstract boolean move(GraphicalBoard gb, int from, int to);

    /**
     * Runns when a tile has been selected.
     * A great place to add possible moves.
     * return false if none of the correct pieces is on the tile
     * else return true
     */
    public abstract boolean selected(GraphicalBoard gb, int location);

    /**
     * Well, tile is no longer selected.
     * if you care...
     */
    public void deSelect(GraphicalBoard gb) {}

    /**
     * Runns when the new game button is pressed.
     */
    public void newGame(GraphicalBoard gb) {}

    /**
     * Runns when the resign button is pressed
     */
    public void resign(GraphicalBoard gb) {}
}
