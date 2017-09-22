package graphical;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class TileAction implements MouseListener {
    private int tile;
    private Tiles tiles;
    private GraphicalBoard gb;
    private GraphicalInteraction gi;

    public TileAction(GraphicalBoard gb, GraphicalInteraction gi, Tiles tiles, int tile) {
        this.tile = tile;
        this.gb = gb;
        this.gi = gi;
        this.tiles = tiles;
    }

    @Override public void mouseClicked(MouseEvent e) {
        // e.isRightButtonPress escape
        if (e.getButton() == MouseEvent.BUTTON1) { // Left click
            if (tiles.isATileSelected()) {
                if (gi.move(gb, tiles.getSelectedTile(), tile)) {
                    tiles.clearPossibleMoves();
                    gb.togglePlayer();
                }
                tiles.unSelectTile();
            } else {
                if (gi.selected(gb, tile)) {
                    tiles.setSelectedTile(tile);
                }
            }
        }

        if (e.getButton() == MouseEvent.BUTTON3) { // Right Click
            gi.deSelect(gb);
            tiles.unSelectTile();
            tiles.clearPossibleMoves();
        }
    }

    @Override public void mouseEntered (MouseEvent e) {}
    @Override public void mouseExited (MouseEvent e) {}
    @Override public void mousePressed (MouseEvent e) {}
    @Override public void mouseReleased (MouseEvent e) {}
}
