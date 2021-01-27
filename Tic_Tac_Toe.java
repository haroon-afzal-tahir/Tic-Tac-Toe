import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        char[] game = new char[9];
        for (int i = 0; i < 9; i++) {
            game[i] = ' ';
        }
        int x = 0;
        int y = 0;
        boolean endgame = false;
        while(true) {
            table(game);
            endgame = analyze(game, endgame);
            if (endgame) {
                break;
            }
            getCoordinates(x, y, game);
        }

    }

    public static void table(char[] game) {
        System.out.println("---------");
        for (int i = 0; i < 9; i+=3) {
            System.out.println(String.format("| %c %c %c |", game[i], game[i+1], game[i+2]));
        }
        System.out.println("---------");
    }

    public static char xWinCheck(char[] game) {
        int xWin = 'X' * 3;

        if(game[0] + game[1] + game[2] == xWin ||
                game[3] + game[4] + game[5] == xWin ||
                game[6] + game[7] + game[8] == xWin ||
                game[0] + game[3] + game[6] == xWin ||
                game[1] + game[4] + game[7] == xWin ||
                game[2] + game[5] + game[8] == xWin ||
                game[0] + game[4] + game[8] == xWin ||
                game[2] + game[4] + game[6] == xWin) {
            return 'X';
        } else {
            return 'N';
        }
    }

    public static char oWinCheck(char[] game) {
        int oWin = 'O' * 3;

        if (game[0] + game[1] + game[2] == oWin ||
                game[3] + game[4] + game[5] == oWin ||
                game[6] + game[7] + game[8] == oWin ||
                game[0] + game[3] + game[6] == oWin ||
                game[1] + game[4] + game[7] == oWin ||
                game[2] + game[5] + game[8] == oWin ||
                game[0] + game[4] + game[8] == oWin ||
                game[2] + game[4] + game[6] == oWin){
            return 'O';
        } else {
            return 'N';
        }
    }

    public static boolean analyze(char[] game, boolean endgame){
        //'X' and 'O' occurrence
        int xCount = 0;
        int oCount = 0;
        for (int i = 0; i < 9; i++) {
            if (game[i] == 'X') {
                xCount++;
            } else if (game[i] == 'O') {
                oCount++;
            }
        }

        if (xWinCheck(game) == 'N' && oWinCheck(game) == 'N' && (xCount + oCount == 9)) {
            System.out.println("Draw");
            endgame = true;
        } else if (xWinCheck(game) == 'X' && oWinCheck(game) == 'N') {
            System.out.println("X wins");
            endgame = true;
        } else if (xWinCheck(game) == 'N' && oWinCheck(game) == 'O') {
            System.out.println("O wins");
            endgame = true;
        } else {
            endgame = false;
        }
        return endgame;
    }

    public static void getCoordinates(int x, int y, char[] game) {
        while (true) {
            System.out.println("Enter the coordinates: ");
            char temp1 = scanner.next().charAt(0);
            char temp2 = scanner.next().charAt(0);

            if ((temp1 > '3' || temp2 > '3') || (temp1 < '1' || temp2 < '1')) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if ((temp1 >= 'A' && temp1 <= 'Z') || (temp2 >= 'A' && temp2 <= 'Z')) {
                System.out.println("You should enter numbers!");
            } else {
                x = temp1 - 48;
                y = temp2 - 48;
                int sum = 0;

                if (x == 3) {
                    sum = 2*x + y - 1;
                } else if (x == 2) {
                    sum = x + y;
                } else if (x == 1) {
                    sum = y - 1;
                }

                if (game[sum] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    placeSymbol(sum, game);
                    break;
                }
            }
        }
    }

    public static void placeSymbol(int sum, char[] game) {
        int xCount = 0;
        int oCount = 0;
        for (int i = 0; i < 9; i++) {
            if (game[i] == 'X') {
                xCount++;
            } else if (game[i] == 'O') {
                oCount++;
            }
        }
        if (xCount == oCount) {
            game[sum] = 'X';
        } else {
            game[sum] = 'O';
        }
    }
}
