package graphical;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.util.ArrayList;

import runninglogic.Board;

public class GraphicalBoard extends JFrame {

    private JLabel statusText;
    private boolean whitesTurn = true;
    private boolean gameIsPlaying = false;
    private Board board = new Board();
    private ArrayList<Integer> selected = new ArrayList<>();
    private Tiles tiles;
    private GraphicalInteraction gi;
    public GraphicalBoard(GraphicalInteraction gi) {
        this.gi = gi;
        this.buildWindow();
    }

    private void buildWindow() {
        this.setTitle("Chess 3.0;");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));

        tiles = new Tiles(this, gi);

        JPanel info = new JPanel(new FlowLayout());

        GraphicalBoard self = this;
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                self.gameIsPlaying = true;
                self.resetBoard();
                gi.newGame(self);
            }
        });

        JButton endGame = new JButton("Resign Game");
        endGame.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e) {
                gi.resign(self);
            }
        });

        statusText = new JLabel("Start A New Game");

        info.add(newGame);
        info.add(statusText);
        info.add(endGame);

        this.getContentPane().add(info);
        this.getContentPane().add(tiles);

        this.pack();
    }

    /**
     * Starts the gameboard.
     */
    public void start() {
        gi.start(this);
        this.setVisible(true);
    }

    /**
     * Ends the game.
     * Displaying the text WINNER.
     */
    public void gameFinished(String endtext) {
        tiles.dissableButtons();
        statusText.setText(endtext);
    }

    public void resetBoard() {
        tiles.resetBoard();
        whitesTurn = true;
        gameIsPlaying = false;
        statusText.setText("Start A Game!");
    }

    public void updateStatusMessage() {
        if (gameIsPlaying) {
            if (whitesTurn) {
                statusText.setText("White players turn");
            } else {
                statusText.setText("Black players turn");
            }
        }
    }
    public void togglePlayer() {
        whitesTurn = !whitesTurn;
    }

    /**
     * Display a piece T at INDEX.
     */
    public void setPiece(int index, PieceDisplay t) {
        tiles.setPiece(index, t);
    }

    /**
     * Mark LOCATION to be at check.
     */
    public void check(int location) {
        tiles.check(location);
    }

    /**
     * Remove the check mark.
     */
    public void removeCheck() {
        tiles.removeCheck();
    }

    /**
     * Highlights the locations in LOCATIONS. Used for displaying possible moves.
     */
    public void setPossibleMoves(ArrayList<Integer> locations) {
        tiles.setPossibleMoves(locations);
    }

    /**
     * Clear the locations highlighted.
     */
    public void clearPossibleMoves() {
        tiles.clearPossibleMoves();
    }
    public Board getBoard() {
        return board;
    }

    public ArrayList<Integer> getSelected() {
        return selected;
    }
}
