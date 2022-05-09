/**
 * Hoang Huynh
 * CS 110 Final Project
 */
public class Move
{
    //instant variables
    private int row, col;

    /** Creates a Move object from two integers representing the indices
     in a two-dimensional array
     * @param row an int row
     * @param col an int col
     */
    public Move(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    /** Creates a move object from a STring consisting of a letter and a number
     * @param s a String s
     */
    public Move(String s)
    {
        this.row = s.charAt(0) - 65;
        this.col = Integer.parseInt(s.substring(1)) - 1;
    }

    /**
     * Accessor for row
     * @return row of Move
     */
    public int row()
    {
        return row;
    }

    /**
     * Accessor for col
     * @return col of Move
     */
    public int col()
    {
        return col;
    }

    /** Create toString method
     * Returns a 2 to 3-character string consisting of a letter in the range
     * A-J followed by a number in the range 1-10. Provides for ease of display
     * of move values in an interface
     */
    public String toString()
    {
        String [] st = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        return st[row] + (col + 1);
    }
}
