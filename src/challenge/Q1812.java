package challenge;
// Determine Color of a Chessboard Square
public class Q1812 {

    public boolean squareIsWhite(String coordinates) {
        int x = coordinates.charAt(0) - 'a';
        int y = coordinates.charAt(1) - '0';

        return (x + y) % 2 == 0;
    }

    public boolean squareIsWhite1(String coordinates) {
        int x = (int) (coordinates.charAt(0) - 'a');
        int y = (int) coordinates.charAt(1);

        return (x + y) % 2 == 0;
    }
}
