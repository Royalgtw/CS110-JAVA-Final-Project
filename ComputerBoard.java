/**
 * Hoang Huynh
 * CS 110 Final Project
 */

public class ComputerBoard extends Board
{
    /** Passes the filename on to the Board constructor
     * @param filename a String filename
     */
    public ComputerBoard (String filename)
    {
        super(filename);
    }

    /** Takes a move and makes it AGAINST this board. Takes in move to be applied.
     If a ship was sunk, update the layout to change_HIT values to _SUNK values.
     * @param move a Move move
     * @return either null, or, if the move sank a ship, a String along the lines of "You sank My Battleship!"
     */
    public String makePlayerMove (Move move) {
        CellStatus originalCell = applyMoveToLayout(move);
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
            return "You sank My Aircraft_Carrier!";
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
            return "You sank My Battleship!";
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
            return "You sank My Cruiser!";
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
            return "You sank My Destroyer!";
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
            return "You sank My Sub!";
        }
        return null;
    }
    /**
     * Create toString method that returns a String representation of the ComputerBoard,
     * displaying the first character of the String returned by the toString method overridden in CellStatus
     */
    @Override
    public String toString()
    {
        String string = "";
        String [] arrayLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int i = 1; i <= 10; i++) {
            string += "  " + i;
        }
        for (int i = 0; i <= 9; i++)
        {
            string += "\n" + arrayLetters[i] + " ";
            for (int j = 0; j <= 9; j++)
            {
                string += getLayout().get(i).get(j).toString().substring(0, 1);
                string += "  ";
            }
        }
        return string;
    }
}
