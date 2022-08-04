import java.util.*;

public class TicTacToe {

    private static ArrayList<Integer> playerSpots = new ArrayList<>();
    private static ArrayList<Integer> cpuSpots = new ArrayList<>();

    public static void main(String[] args) {

        boolean noWinner = true;
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        while (noWinner) {
            turn(gameBoard, "user");
            if (!checkWinner().equals("no winner yet")) break;
            board(gameBoard);
            printSpaces();

            turn(gameBoard, "cpu");
            if (!checkWinner().equals("no winner yet")) break;
            board(gameBoard);
            printSpaces();
        }
        board(gameBoard);
        printSpaces();
        System.out.println(checkWinner());

    }

    public static void board(char[][] gameBoard) {

        for (char[] array : gameBoard) {
            for (char space : array) {
                System.out.print(space);
            }
            System.out.println();
        }

    }

    public static void turn(char[][] gameBoard, String player) {

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
            while (validSpot) {
                spot = new Random().nextInt(9) + 1;
                if (!playerSpots.contains(spot) && !cpuSpots.contains(spot)) validSpot = false;
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
                return "Congratulations, you won!";
            } else if (cpuSpots.containsAll(arraylist)) {
                return "Sorry, you lost!";
            }
        }

        if (playerSpots.size() + cpuSpots.size() == 9) {
            return "Tie!";
        }
        return "no winner yet";

    }

    public static void printSpaces() {
        System.out.println();
        System.out.println("------");
        System.out.println();
    }

}
