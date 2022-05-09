/**
 * Hoang Huynh
 * CS 110 Final Project
 */
public class Fleet
{
    //initialize Ship fields
    private Ship battleShip, aircraftCarrier, cruiser, sub, destroyer;
    private boolean sunk;
    public Fleet()
    {
        battleShip = new BattleShip();
        aircraftCarrier = new AircraftCarrier();
        cruiser = new Cruiser();
        sub = new Sub();
        destroyer = new Destroyer();
    }

    /**
     * Informs the appropriate ship that it has been hit
     * @param s a ShipType s
     * @return true if all ships have been sunk, false if not
     */
    public boolean updateFleet(ShipType s)
    {
        if (s == ShipType.ST_BATTLESHIP)
        {
            return battleShip.hit();
        }
        else if (s == ShipType.ST_AIRCRAFT_CARRIER)
        {
            return aircraftCarrier.hit();
        }
        else if (s == ShipType.ST_CRUISER)
        {
            return cruiser.hit();
        }
        else if (s == ShipType.ST_SUB)
        {
            return sub.hit();
        }
        else
        {
            return destroyer.hit();
        }
    }

    /** Returns true if all ships have been sunk, returns false if not
     * @return
     */
    public boolean gameOver()
    {
        return battleShip.hit() && aircraftCarrier.hit() &&
                cruiser.hit() && sub.hit() && destroyer.hit();

    }
}
