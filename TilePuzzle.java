import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeoutException;

/**
 * TilePuzzle
 */
public class TilePuzzle extends JFrame{

    private final int ROWS = 3;
    private final int COLS = 4;

    private TilePiece[] tiles = new TilePiece[12];
    private TilePiece blankTile;

    public TilePuzzle(){
        //initialise frame
        setTitle("Tile Puzzle");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //initialise grid
        JPanel gridPanel = new JPanel();
        setContentPane(gridPanel);

        GridLayout grid = new GridLayout(3,4);
        gridPanel.setLayout(grid);

        //randomise tiles
        ArrayList<Integer> tileOrder = new ArrayList<Integer>();
        for (int i = 0; i < ROWS*COLS; i++) tileOrder.add(i);
        Collections.shuffle(tileOrder);

        //initialise tiles
        for (int row = 0; row < ROWS; row++){
            for (int col = 0; col < COLS; col++){
                int tileNum = row*COLS+col;
                TilePiece tile = new TilePiece(String.format("tile_%d.jpg", tileOrder.get(tileNum)), col, row);
                tiles[tileNum] = tile;

                //set as blank tile if this is the first tile
                if (tileNum == 0){
                    tile.setIcon(new ImageIcon("blank_tile.jpg"));
                    blankTile = tile;
                }

                //event for when button is pressed
                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        //check tiles are adjacent
                        TilePiece tile = (TilePiece)e.getSource();
                        if(tile.isAdjacentTo(blankTile)){
                            //if so, swap tile images
                            tile.exchangeImageWith(blankTile);
                            blankTile = tile;
                        }
                    }
                });

                gridPanel.add(tile);
            }
        }

        setResizable(false);
        setVisible(true);
    }
}