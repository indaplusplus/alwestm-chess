package graphical;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.ArrayList;
import chesspieces.PieceType;

class Tiles extends JPanel {

    private JButton[] tiles = new JButton[64];;

    private int selectedTile = -1;
    private int checkLocation = -1;
    private ArrayList<Integer> possibleLocations = new ArrayList<>();

    private GraphicalInteraction gi;
    private GraphicalBoard gb;

    private final Color dark = new Color(205,133,63);
    private final Color light = new Color(255, 255, 255);
    private final Color indicated = new Color(0, 255, 0);
    private final Color selected = new Color(135,206,250);
    private final Color check = new Color(255,70,0);

    public Tiles(GraphicalBoard gb, GraphicalInteraction gi) {
        this.gb = gb;
        this.gi = gi;
        build();
    }

    private void build() {
        this.setLayout(new GridLayout(0,8));
        final int tileSize = 80; // This should later on be dependant on the size of the window.

        final Font changeFontSize = new Font("", Font.PLAIN, tileSize);

        for (int i = 0; i < 64; i++) {
            JButton b = new JButton();
            b.setMargin(new Insets(0,0,0,0)); // No Margin
            b.setFont(changeFontSize);

            b.setPreferredSize(new Dimension(tileSize, tileSize));
            b.addMouseListener(new TileAction(gb, gi, this, i));

            tiles[i] = b;
            this.add(tiles[i]);
        }

        this.paintBoard();
    }

    /**
     * Repaint the board with the apropriate colors.
     */
    private void paintBoard() {
        for(int i = 0; i < tiles.length; i++) {
            JButton b = tiles[i];
            if ((i + i/8) % 2 == 0) {
                b.setBackground(light);
            } else {
                b.setBackground(dark);
            }
        }
        for (int i : possibleLocations) {
            tiles[i].setBackground(indicated);
        }
        if (selectedTile != -1) {
            tiles[selectedTile].setBackground(selected);
        }
        if (checkLocation != -1) {
            tiles[checkLocation].setBackground(check);
        }
    }

    public void dissableButtons() {
    }

    public void enableButtons() {
    }

    public void check(int location) {
        checkLocation = location;
    }

    public void removeCheck(){
        checkLocation = -1;
    }

    public void resetBoard() {
        this.unSelectTile();
        this.clearPossibleMoves();
        this.removeCheck();
        for (JButton b : tiles) {
            b.setText(PieceDisplay.None.toString());
        }
        this.paintBoard();
    }

    public void setPiece(int index, PieceDisplay p) {
        tiles[index].setText(p.toString());
    }

    /**
     * Is a tile selected by the user.
     */
    public boolean isATileSelected() {
        return selectedTile != -1;
    }

    /**
     * Return index to the tile selected by the suer.
     */
    public int getSelectedTile() {
        return selectedTile;
    }

    /**
     * Change what tile selected tile is.
     */
    public void setSelectedTile(int tile) {
        selectedTile = tile;
        this.paintBoard();
    }

    /**
     * Sets the selected tile to not be selected.
     */
    public void unSelectTile() {
        this.setSelectedTile(-1);
    }

    /**
     * Highlights the locations in LOCATIONS. Used for displaying possible moves.
     */
    public void setPossibleMoves(ArrayList<Integer> locations) {
        possibleLocations = locations;
        this.paintBoard();
    }

    /**
     * Clear the locations highlighted.
     */
    public void clearPossibleMoves() {
        possibleLocations = new ArrayList<>();
        this.paintBoard();
    }
}
