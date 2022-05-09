/**
 * Hoang Huynh
 * CS 110 Final Project
 */
public class Game {
    private ComputerBoard computer;
    private UserBoard player;

    /** A constructor for Game that initializes the computer and player Boards.
     */
    public Game() {
        this.computer = new ComputerBoard("compFleet.txt");
        this.player = new UserBoard("userFleet.txt");
    }

    /** Makes the move of the computer on the player's Board.
     * @return An Array String.
     */
    public String [] makeComputerMove() {
        return player.makeComputerMove();
    }

    /** Makes the move of the player on the computer's Board.
     * @param string A String 
     * @return A String.
     */
    public String makePlayerMove(String string) {
        return computer.makePlayerMove(new Move(string));
    }

    /** Determines if the computer has lost the game by having all their ships sunk.
     * @return A Boolean.
     */
    public boolean computerDefeated() {
        return computer.gameOver();
    }

    /**
     * Determines if the user has lost the game by having all their ships sunk.
     *
     * @return A Boolean.
     */
    public boolean userDefeated() {
        return player.gameOver();
    }

    /**
     * A toString() method that returns both board states in one String.
     *
     * @return A String.
     */
    public String toString() {
        String boardState = "";
        boardState += "\nCOMPUTER\n" + computer + "\n";
        boardState += "\nUSER\n" + player + "\n";
        return boardState;
    }


}