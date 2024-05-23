import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import ds.Ladder;
import ds.Player;
import ds.Snake;
import service.SnakeAndLadderService;

public class MainApp {
    public static void main(String[] args) throws Exception {
        // Player
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of snakes -> ");
        int noOfSnakes = sc.nextInt();
        List<Snake> snakes = new ArrayList<Snake>();

        /*
        The key represents the position on the board where the snake's head is located,
        and the value represents the position where the snake's tail ends.
        Landing on a snake's head means the player will move down to the snake's tail.
         */
        for (int i = 0; i < noOfSnakes; i++) {
            System.out.println("Enter the head position of snake " + (i + 1) + " -> ");
            int head = sc.nextInt();
            System.out.println("Enter the tail position of snake " + (i + 1) + " -> ");
            int tail = sc.nextInt();
            snakes.add(new Snake(head, tail)); // e.g., 16, 6 // 47, 26 // 49, 11
        }

        System.out.println("Enter number of ladders -> ");
        int noOfLadders = sc.nextInt();
        List<Ladder> ladders = new ArrayList<Ladder>();

        /*
        The key represents the position on the board where the ladder starts (bottom of the ladder),
        and the value represents the position where the ladder ends (top of the ladder).
        Landing on the bottom of a ladder means the player will move up to the top.
         */
        for (int i = 0; i < noOfLadders; i++) {
            System.out.println("Enter the bottom position of ladder " + (i + 1) + " -> ");
            int bottom = sc.nextInt();
            System.out.println("Enter the top position of ladder " + (i + 1) + " -> ");
            int top = sc.nextInt();
            ladders.add(new Ladder(bottom, top)); // e.g., 1, 38 // 4, 14 // 9, 31
        }

        System.out.println("Enter number of players -> ");
        int noOfPlayers = sc.nextInt();
        sc.nextLine();  // Consume newline left-over

        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < noOfPlayers; i++) {
            System.out.println("Enter name of player " + (i + 1) + " -> ");
            players.add(new Player(sc.nextLine()));
        }


        System.out.println("Game is starting -------> ");
        SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();
        snakeAndLadderService.setLadders(ladders);
        snakeAndLadderService.setSnakes(snakes);
        snakeAndLadderService.setPlayers(players);
        snakeAndLadderService.startGame();
        sc.close();

    }
}
