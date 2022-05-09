/**
 * Hoang Huynh
 * CS 110 Final Project
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Board
{
    //instance variables
    private ArrayList<ArrayList<CellStatus>> layout;
    private Fleet fleet;
    public final int SIZE = 0;

    /** Create a Board constructor that initializes layout, initially
     * setting all cells to CellStatus.NOTHING. Gets information from
     * file and add ships to that layout.
     */
    public Board(String filename)
    {
        this.fleet = new Fleet();
        layout = new ArrayList<>();
        for (int i = 0; i <= 9; i++)
        {
            ArrayList<CellStatus> arr = new ArrayList<>();
            layout.add(arr);
            for (int j = 0; j <= 9; j++)
            {
                layout.get((layout.size() - 1)).add(CellStatus.NOTHING);
            }
        }
        try {
            Scanner infile = new Scanner(new File(filename));
            while (infile.hasNext()) {
                String line = infile.nextLine();
                int startRowNum = line.charAt(2) - 65;
                int startColNum = Integer.parseInt(line.substring(3, 4)) - 1;
                int endRowNum = line.charAt(5) - 65;
                int endColNum = Integer.parseInt(line.substring(6, 7)) - 1;
                if (line.substring(0, 1).equals("A")) {
                    layout.get(startRowNum).set(startColNum, CellStatus.AIRCRAFT_CARRIER);
                    layout.get(endRowNum).set(endColNum, CellStatus.AIRCRAFT_CARRIER);
                    if (startRowNum == endRowNum) {
                        layout.get(startRowNum).set(startColNum + 1, CellStatus.AIRCRAFT_CARRIER);
                        layout.get(startRowNum).set(startColNum + 2, CellStatus.AIRCRAFT_CARRIER);
                        layout.get(startRowNum).set(startColNum + 3, CellStatus.AIRCRAFT_CARRIER);
                    } else {
                        layout.get(startRowNum + 1).set(startColNum, CellStatus.AIRCRAFT_CARRIER);
                        layout.get(startRowNum + 2).set(startColNum, CellStatus.AIRCRAFT_CARRIER);
                        layout.get(startRowNum + 3).set(startColNum, CellStatus.AIRCRAFT_CARRIER);
                    }

                } else if (line.substring(0, 1).equals("B")) {
                    layout.get(startRowNum).set(startColNum, CellStatus.BATTLESHIP);
                    layout.get(endRowNum).set(endColNum, CellStatus.BATTLESHIP);
                    if (startRowNum == endRowNum) {
                        layout.get(startRowNum).set(startColNum + 1, CellStatus.BATTLESHIP);
                        layout.get(startRowNum).set(startColNum + 2, CellStatus.BATTLESHIP);
                    } else {
                        layout.get(startRowNum + 1).set(startColNum, CellStatus.BATTLESHIP);
                        layout.get(startRowNum + 2).set(startColNum, CellStatus.BATTLESHIP);
                    }
                } else if (line.substring(0, 1).equals("C")) {
                    layout.get(startRowNum).set(startColNum, CellStatus.CRUISER);
                    layout.get(endRowNum).set(endColNum, CellStatus.CRUISER);
                    if (startRowNum == endRowNum) {
                        layout.get(startRowNum).set(startColNum + 1, CellStatus.CRUISER);
                    } else {
                        layout.get(startRowNum + 1).set(startColNum, CellStatus.CRUISER);
                    }
                } else if (line.substring(0, 1).equals("D")) {
                    layout.get(startRowNum).set(startColNum, CellStatus.DESTROYER);
                    layout.get(endRowNum).set(endColNum, CellStatus.DESTROYER);

                } else if (line.substring(0, 1).equals("S")) {
                    layout.get(startRowNum).set(startColNum, CellStatus.SUB);
                    layout.get(endRowNum).set(endColNum, CellStatus.SUB);
                    if (startRowNum == endRowNum) {
                        layout.get(startRowNum).set(startColNum + 1, CellStatus.SUB);
                    } else {
                        layout.get(startRowNum + 1).set(startColNum, CellStatus.SUB);
                    }
                }
            }
        }
        catch (FileNotFoundException noFile)
        {
            System.out.println(filename + "File was not found.");
        }
    }

    /** Applies a move to layout. If the targeted cell does not contain a ship, it is set to
     CellStatus.NOTHING_HIT. If it contains a ship, the cell is changed from, for instance,
     CellStatus.SUB to CellStatus.SUB_HIT. It returns the original CellStatus of the
     targeted cell.
     * @param move a Move move
     * @return
     */
    public CellStatus applyMoveToLayout(Move move)
    {
        CellStatus originalCell = getLayout().get(move.row()).get(move.col());
        if (getLayout().get(move.row()).get(move.col()).equals(CellStatus.NOTHING))
        {
            getLayout().get(move.row()).set(move.col(), CellStatus.NOTHING_HIT);
        }
        else if (getLayout().get(move.row()).get(move.col()).equals(CellStatus.AIRCRAFT_CARRIER))
        {
            getLayout().get(move.row()).set(move.col(), CellStatus.AIRCRAFT_CARRIER_HIT);
        }
        else if (getLayout().get(move.row()).get(move.col()).equals(CellStatus.BATTLESHIP))
        {
            getLayout().get(move.row()).set(move.col(), CellStatus.BATTLESHIP_HIT);
        }
        else if (getLayout().get(move.row()).get(move.col()).equals(CellStatus.CRUISER))
        {
            getLayout().get(move.row()).set(move.col(), CellStatus.CRUISER_HIT);
        }
        else if (getLayout().get(move.row()).get(move.col()).equals(CellStatus.DESTROYER))
        {
            getLayout().get(move.row()).set(move.col(), CellStatus.DESTROYER_HIT);
        }
        else if (getLayout().get(move.row()).get(move.col()).equals(CellStatus.SUB))
        {
            getLayout().get(move.row()).set(move.col(), CellStatus.SUB_HIT);
        }
        return originalCell;
    }
    public boolean moveAvailable(Move move)
    {
        return getLayout().get(move.row()).get(move.col()).equals(CellStatus.AIRCRAFT_CARRIER) ||
                getLayout().get(move.row()).get(move.col()).equals(CellStatus.BATTLESHIP) ||
                getLayout().get(move.row()).get(move.col()).equals(CellStatus.CRUISER) ||
                getLayout().get(move.row()).get(move.col()).equals(CellStatus.DESTROYER) ||
                getLayout().get(move.row()).get(move.col()).equals(CellStatus.SUB) ||
                getLayout().get(move.row()).get(move.col()).equals(CellStatus.NOTHING);
    }
    /**
     * @return a reference to the layout
     */
    public ArrayList<ArrayList<CellStatus>> getLayout()
    {
        return layout;
    }

    /**
     * @return a reference to the Fleet object
     */
    public Fleet getFleet()
    {
        return fleet;
    }

    /**
     * @return true if all ships have been suck, false otherwise
     */
    public boolean gameOver()
    {
        return getFleet().gameOver();
    }
}
