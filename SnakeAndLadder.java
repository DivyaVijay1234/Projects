import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class SnakeAndLadder {
     
    final static int WIN_POINT = 100;
     
    static Map<Integer, Integer> snake = new HashMap<>();
    static Map<Integer, Integer> ladder = new HashMap<>();
    
    static {
        snake.put(99, 54);
        snake.put(70, 55);
        snake.put(52, 42);
        snake.put(25, 2);
        snake.put(95, 72);
         
        ladder.put(6, 25);
        ladder.put(11, 40);
        ladder.put(60, 85);
        ladder.put(46, 90);
        ladder.put(17, 69);
    }

    public static void main(String[] args) {
        SnakeAndLadder game = new SnakeAndLadder();
        game.startGame();
    }

    public int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1; // Adding 1 to get values between 1 and 6
    }

    public int calculatePlayerValue(int player, int diceValue) {
        player = player + diceValue;

        if (player > WIN_POINT) {
            player = player - diceValue;
            return player;
        }

        if (snake.containsKey(player)) {
            System.out.println("Swallowed by snake");
            player = snake.get(player);
        }

        if (ladder.containsKey(player)) {
            System.out.println("Climb up the ladder");
            player = ladder.get(player);
        }

        return player;
    }

    public boolean isWin(int player) {
        return player == WIN_POINT;
    }

    public void startGame() {
        int player1 = 0, player2 = 0;
        int currentPlayer = -1;
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.println(currentPlayer == -1 ? "\n\nFIRST PLAYER TURN" : "\n\nSECOND PLAYER TURN");
            System.out.println("Press 'r' to roll the dice");
            input = scanner.next();

            int diceValue = rollDice();

            if (currentPlayer == -1) {
                player1 = calculatePlayerValue(player1, diceValue);
                System.out.println("First Player Position: " + player1);
                System.out.println("Second Player Position: " + player2);
                System.out.println("------------------");
                if (isWin(player1)) {
                    System.out.println("First player wins");
                    return;
                }
            } else {
                player2 = calculatePlayerValue(player2, diceValue);
                System.out.println("First Player Position: " + player1);
                System.out.println("Second Player Position: " + player2);
                System.out.println("------------------");
                if (isWin(player2)) {
                    System.out.println("Second player wins");
                    return;
                }
            }

            currentPlayer = -currentPlayer;

        } while ("r".equals(input));
    }
}
