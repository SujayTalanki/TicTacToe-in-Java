import java.util.*;

/**
 * Tic Tac Toe against a random CPU or a friend
 */
public class TicTacToe {

    /* Instance variables */
    private static ArrayList<Integer> playerSpots = new ArrayList<>();
    private static ArrayList<Integer> cpuSpots = new ArrayList<>();
    private static int mode;
    private static String player1;
    private static String player2;

    /**
     * Main method that puts the program together
     * @param args input to the main method
     */
    public static void main(String[] args) {
        boolean noWinner = true;
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        askUser();
        while (noWinner) {
            turn(gameBoard, "user", mode);
            if (!checkWinner().equals("no winner yet")) break;
            board(gameBoard);
            printSpaces();

            turn(gameBoard, "cpu", mode);
            if (!checkWinner().equals("no winner yet")) break;
            board(gameBoard);
            printSpaces();
        }
        board(gameBoard);
        printSpaces();
        System.out.println(checkWinner());
    }

    /**
     * Prints the board with each players' markers
     * @param gameBoard the current board
     */
    public static void board(char[][] gameBoard) {
        for (char[] array : gameBoard) {
            for (char space : array) {
                System.out.print(space);
            }
            System.out.println();
        }
    }

    /**
     * Asks the user which mode they want to play
     */
    public static void askUser() {
        boolean validResponse = false;
        while (!validResponse) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter 0 to play against a cpu or enter 1 for two-player mode:");
            mode = scanner.nextInt();
            if (mode == 1) {
                System.out.println("Enter Player 1's name:");
                player1 = scanner.next();
                System.out.println("Enter Player 2's name:");
                player2 = scanner.next();
                validResponse = true;
            } else if (mode == 0) {
                player1 = "user";
                player2 = "cpu";
                validResponse = true;
            } else {
                System.out.println("Sorry! You must enter either 0 or 1");
            }
        }
    }

    /**
     * Executes the player's or cpu's turn
     * @param gameBoard the current board
     * @param player the current player's turn
     * @param mode either the cpu mode or the two-player mode
     */
    public static void turn(char[][] gameBoard, String player, int mode) {
        char marker; int spot = 0; boolean validSpot = true;
        Scanner scanner = new Scanner(System.in);
        if (player.equals("user")) {
            marker = 'X';
            while (validSpot) {
                System.out.println("Enter a number from 1-9:");
                spot = scanner.nextInt();
                if (!playerSpots.contains(spot) && !cpuSpots.contains(spot)) {
                    printSpaces();
                    validSpot = false;
                } else {
                    System.out.println("Sorry! This spot has already been taken.");
                }
            }
            playerSpots.add(spot);
        } else {
            marker = 'O';
            if (mode == 0) {
                while (validSpot) {
                    spot = new Random().nextInt(9) + 1;
                    if (!playerSpots.contains(spot) && !cpuSpots.contains(spot)) validSpot = false;
                }
            } else {
                while (validSpot) {
                    System.out.println("Enter a number from 1-9:");
                    spot = scanner.nextInt();
                    if (!playerSpots.contains(spot) && !cpuSpots.contains(spot)) {
                        printSpaces();
                        validSpot = false;
                    } else {
                        System.out.println("Sorry! This spot has already been taken.");
                    }
                }
            }
            cpuSpots.add(spot);
        }
        switch (spot) {
            case 1:
                gameBoard[0][0] = marker;
                break;
            case 2:
                gameBoard[0][2] = marker;
                break;
            case 3:
                gameBoard[0][4] = marker;
                break;
            case 4:
                gameBoard[2][0] = marker;
                break;
            case 5:
                gameBoard[2][2] = marker;
                break;
            case 6:
                gameBoard[2][4] = marker;
                break;
            case 7:
                gameBoard[4][0] = marker;
                break;
            case 8:
                gameBoard[4][2] = marker;
                break;
            case 9:
                gameBoard[4][4] = marker;
                break;
        }
    }

    /**
     * Checks if there is a winner or if the game is tied
     * @return the outcome message (win, loss, or tie)
     */
    public static String checkWinner() {
        List<List> winning = new ArrayList<List>();
        winning.add(Arrays.asList(1, 2, 3));
        winning.add(Arrays.asList(4, 5, 6));
        winning.add(Arrays.asList(7, 8, 9));
        winning.add(Arrays.asList(1, 4, 7));
        winning.add(Arrays.asList(2, 5, 8));
        winning.add(Arrays.asList(3, 6, 9));
        winning.add(Arrays.asList(1, 5, 9));
        winning.add(Arrays.asList(7, 5, 3));

        for (List arraylist : winning) {
            if (playerSpots.containsAll(arraylist)) {
                if (mode == 0) return "Congratulations, you won!";
                else return player1 + " wins!";
            } else if (cpuSpots.containsAll(arraylist)) {
                if (mode == 0) return "Sorry, you lost!";
                else return player2 + " wins!";
            }
        }

        if (playerSpots.size() + cpuSpots.size() == 9) {
            return "Tie!";
        }
        return "no winner yet";
    }

    /**
     * Prints spaces for a nice look
     */
    public static void printSpaces() {
        System.out.println();
        System.out.println("------");
        System.out.println();
    }

}
