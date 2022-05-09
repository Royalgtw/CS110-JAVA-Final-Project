/**
 * Hoang Huynh
 * CS 110 Final Project
 */
import java.util.ArrayList;
import java.util.Random;

public class UserBoard extends Board
{
    //instance variables
    private ArrayList<Move> moves;
    private Random rand;

    /** Passes the filename on to the Board constructor.
     * Initialize the Random object and the ArrayList of all possible Moves
     * @param filename a String filename
     */
    public UserBoard(String filename)
    {
        super(filename);
        this.rand = new Random();
        this.moves = new ArrayList<>();
        for (int i = 0; i<= 9; i++)
        {
            for (int j = 0; j <= 9; j++)
            {
                moves.add(new Move(i, j));
            }
        }
    }
    /** Computer move against UserBoard. Selects and makes a move AGAINST this board.
     * @return an array of two Strings. The first is the move the computer made in user readable form.
     * The second is either null, or, if the move resulted in a ship being sunk, a string along the lines
     * of "You sunk My Battleship!"
     */
    public Move pickRandomMove()
    {
        int randInt = rand.nextInt(moves.size());
        Move userMove = moves.get(randInt);
        moves.remove(randInt);
        return userMove;
    }
    public String [] makeComputerMove()
    {
        Move move = pickRandomMove();
        CellStatus originalCell = applyMoveToLayout(move);
        String [] arrayString = {move.toString(), null};
        //if Aircraft_Carrier ship is hit
        if (originalCell.equals(CellStatus.AIRCRAFT_CARRIER))
        {
            if (getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER))
                for (int i = 0; i <= 9; i++) {
                    for (int j = 0; j <= 9; j++) {
                        if (getLayout().get(i).get(j).equals(CellStatus.AIRCRAFT_CARRIER_HIT))
                            getLayout().get(move.row()).set(move.col(), CellStatus.AIRCRAFT_CARRIER_SUNK);
                    }
                }
            arrayString[1] = "You sank My Aircraft_Carrier";
        }
        //if battleship ship is hit
        if (originalCell.equals(CellStatus.BATTLESHIP))
        {
            if (getFleet().updateFleet(ShipType.ST_BATTLESHIP))
                for (int i = 0; i <= 9; i++) {
                    for (int j = 0; j <= 9; j++) {
                        if (getLayout().get(i).get(j).equals(CellStatus.BATTLESHIP_HIT))
                            getLayout().get(move.row()).set(move.col(), CellStatus.BATTLESHIP_SUNK);
                    }
                }
            arrayString[1] = "You sank My Battleship!";
        }
        //if cruiser ship is hit
        if (originalCell.equals(CellStatus.CRUISER))
        {
            if (getFleet().updateFleet(ShipType.ST_CRUISER))
                for (int i = 0; i <= 9; i++) {
                    for (int j = 0; j <= 9; j++) {
                        if (getLayout().get(i).get(j).equals(CellStatus.CRUISER_HIT))
                            getLayout().get(move.row()).set(move.col(), CellStatus.CRUISER_SUNK);
                    }
                }
            arrayString[1] = "You sank My Cruiser!";
        }
        //if destroyer ship is hit
        if (originalCell.equals(CellStatus.DESTROYER))
        {
            if (getFleet().updateFleet(ShipType.ST_DESTROYER))
                for (int i = 0; i <= 9; i++) {
                    for (int j = 0; j <= 9; j++) {
                        if (getLayout().get(i).get(j).equals(CellStatus.DESTROYER_HIT))
                            getLayout().get(move.row()).set(move.col(), CellStatus.DESTROYER_SUNK);
                    }
                }
            arrayString[1] = "You sank My Destroyer!";
        }
        //if sub ship is hit
        if (originalCell.equals(CellStatus.SUB))
        {
            if (getFleet().updateFleet(ShipType.ST_SUB))
                for (int i = 0; i <= 9; i++) {
                    for (int j = 0; j <= 9; j++) {
                        if (getLayout().get(i).get(j).equals(CellStatus.SUB_HIT))
                            getLayout().get(move.row()).set(move.col(), CellStatus.SUB_SUNK);
                    }
                }
            arrayString[1] = "You sank My Sub!";
        }
        return arrayString;
    }
    /**
     * Create toString method
     */
    @Override
    public String toString()
    {
        String string = "";
        String [] arrayLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int i = 1; i <= 10; i++) {
            string += "  " + i;
        }
        for (int i = 0; i<= 9; i++)
        {
            string += "\n" + arrayLetters[i] + " ";
            for (int j = 0; j <= 9; j++)
            {
                string += getLayout().get(i).get(j).toString().substring(1, 2);
                string += "  ";
            }
        }
        return string;
    }
}
