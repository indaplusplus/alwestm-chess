import chesspieces.Color;
import chesspieces.Piece;
import chesspieces.PieceType;
import com.google.common.collect.HashBiMap;
import coordination.Vector;
import graphical.GraphicalBoard;
import graphical.PieceDisplay;
import graphical.GraphicalInteraction;

import java.util.ArrayList;
import logic.MoveType;
import runninglogic.Board;
import runninglogic.InputInterpreter;

public class GameBootstrap {
    private static HashBiMap<PieceType, PieceDisplay> typeToWhiteImage;
    private static HashBiMap<PieceType, PieceDisplay> typeToBlackImage;
    static {
        typeToBlackImage = typeToBlackImage.create();
        typeToWhiteImage = typeToWhiteImage.create();

        typeToBlackImage.put(PieceType.PAWN, PieceDisplay.BPawn);
        typeToWhiteImage.put(PieceType.PAWN, PieceDisplay.WPawn);

        typeToBlackImage.put(PieceType.ROOK, PieceDisplay.BRook);
        typeToWhiteImage.put(PieceType.ROOK, PieceDisplay.WRook);

        typeToBlackImage.put(PieceType.KNIGHT, PieceDisplay.BKnight);
        typeToWhiteImage.put(PieceType.KNIGHT, PieceDisplay.WKnight);

        typeToBlackImage.put(PieceType.BISHOP, PieceDisplay.BBishop);
        typeToWhiteImage.put(PieceType.BISHOP, PieceDisplay.WBishop);

        typeToBlackImage.put(PieceType.QUEEN, PieceDisplay.BQueen);
        typeToWhiteImage.put(PieceType.QUEEN, PieceDisplay.WQueen);

        typeToBlackImage.put(PieceType.KING, PieceDisplay.BKing);
        typeToWhiteImage.put(PieceType.KING, PieceDisplay.WKing);
    }
    public static void main(String[] args) {
        GraphicalInteraction gi = new GraphicalInteraction(){
            @Override public boolean move(GraphicalBoard gb, int from, int to) {
                // Test if move is OK
                // Move them graphically
                InputInterpreter inpin;
                try {
                    inpin = convertTo(gb.getBoard().getPieces(), Vector.graphicToVector(from), Vector.graphicToVector(to));
                    gb.getBoard().update(inpin);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                    return false;
                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage());
                    return false;
                }
                System.out.println("It's white turn right now: "+gb.getBoard().getIsWhiteTurn());
                // Basically
                PieceDisplay fD = PieceDisplay.None;
                PieceDisplay tD = PieceDisplay.None;
                for(Piece p : gb.getBoard().getPieces()) {
                    if (p.getPosition().equals(Vector.graphicToVector(from))) {
                        fD = p.getColor() == Color.WHITE ? typeToWhiteImage.get(p.getPieceType()) : typeToBlackImage.get(p.getPieceType());
                    }
                    if (p.getPosition().equals(Vector.graphicToVector(to))) {
                        tD = p.getColor() == Color.WHITE ? typeToWhiteImage.get(p.getPieceType()) : typeToBlackImage.get(p.getPieceType());
                    }
                }
                gb.setPiece(from, fD);
                gb.setPiece(to, tD);
                return true;

            }
            @Override public boolean selected(GraphicalBoard gb, int location) {
                // Set a list of possible moves
                ArrayList<Integer> in = new ArrayList<>();


                gb.setPossibleMoves(in);
                // If the location can be selected
                //gb.check(34);
                return true;

                // Else return false;
            }

            @Override public void newGame(GraphicalBoard gb) {
                // Print all the pieces to the board
                gb.getBoard().initializeBoard();;
                for (Piece p: gb.getBoard().getPieces()) {
                    gb.setPiece(p.getPosition().getGraphicInt(), p.getColor() == Color.WHITE ? typeToWhiteImage.get(p.getPieceType()) : typeToBlackImage.get(p.getPieceType()));
                }
            }
        };
        GraphicalBoard gb = new GraphicalBoard(gi);
        gb.start();
        Board b = new Board();
    }
    private static InputInterpreter convertTo(ArrayList<Piece> pieces, Vector from, Vector to) {
        String beg = "";
        Piece fromP = getPiece(pieces, from);
        if (fromP.getPieceType() == PieceType.PAWN) {
            if (getPiece(pieces, to) != null) {
                beg += fromP.getPosition().getPos().charAt(0);
            }
        } else {
            beg = ""+InputInterpreter.translateCmdMap.inverse().get(getPiece(pieces, from).getPieceType());
        }
        for (Piece p : pieces) {
            if (p.getPosition().getLetter() == fromP.getPosition().getLetter() && p.getPieceType() == fromP.getPieceType() && p.getColor() == fromP.getColor() && !p.getPosition().equals(fromP.getPosition())) {
                beg+=fromP.getPosition().getPos().charAt(0);
            }
        }
        if (getPiece(pieces, to) != null) {
            beg+='x';
        }
        beg+= to.getPos();
        return new InputInterpreter(beg);
    }
    private static Piece getPiece(ArrayList<Piece> pieces, Vector pos) {
        Piece toReturn = null;
        for (Piece p : pieces) {
            if (p.getPosition().equals(pos)) {
                toReturn = p;
            }
        }
        return toReturn;
    }
}