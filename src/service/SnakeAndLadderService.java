package service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ds.Board;
import ds.Ladder;
import ds.Player;
import ds.Snake;

public class SnakeAndLadderService {
    private final Board snakeAndLadderBoard;
    private final Queue<Player> players;
    private int noOfPlayers;
    private static final int DEFAULT_BOARD_SIZE = 100;
    private final int size;

    SnakeAndLadderService(int size) {
        this.snakeAndLadderBoard = new Board(size);
        this.players = new LinkedList<Player>();
        this.size = size;
    }

    public SnakeAndLadderService() {
        this(DEFAULT_BOARD_SIZE);
    }

    public void setPlayers(List<Player> players) {
        this.noOfPlayers = players.size();
        HashMap<String, Integer> playerPieces = new HashMap<String, Integer>();
        for (Player p : players) {
            this.players.offer(p);
            playerPieces.put(p.getPlayerName(), 0);
        }
        this.snakeAndLadderBoard.setPlayerpieces(playerPieces);
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakeAndLadderBoard.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders) {
        this.snakeAndLadderBoard.setLadders(ladders);
    }

    public void startGame() {

        while (!isGameEnded()) {
            int diceValue = getCurrentRollValue();
            Player curPlayer = this.players.poll();
            movePosition(curPlayer, diceValue);
            if (isPlayerWon(curPlayer)) {
                System.out.println("Player " + curPlayer.getPlayerName() + " won the game!!");
                this.snakeAndLadderBoard.getPlayerpieces().remove(curPlayer.getPlayerName());
            } else {
                this.players.offer(curPlayer);
            }
        }
    }

    public boolean isGameEnded() {
        return this.players.size() < noOfPlayers;
    }

    public int getCurrentRollValue() {
        return DiceService.roll();
    }

    public void movePosition(Player player, int diceValue) {

        int currPosition = this.snakeAndLadderBoard.getPlayerpieces().get(player.getPlayerName());

        int nextPosition = currPosition + diceValue;

        if (nextPosition > this.size) {
            nextPosition = currPosition;
        } else {
            nextPosition = getNewPositionWithSnakesLadders(nextPosition);
        }

        this.snakeAndLadderBoard.getPlayerpieces().put(player.getPlayerName(), nextPosition);

        System.out.println(
                "Player " + player.getPlayerName() + " moved from position " + currPosition + " to " + nextPosition);
    }

    public int getNewPositionWithSnakesLadders(int nextPosition) {

        int nextnewPositon;
        do {
            nextnewPositon = nextPosition;
            for (Snake nextSnake : this.snakeAndLadderBoard.getSnakes()) {
                if (nextSnake.getStart() == nextPosition)
                    nextPosition = nextSnake.getEnd();
            }

            for (Ladder nextLadder : this.snakeAndLadderBoard.getLadders()) {
                if (nextLadder.getStart() == nextPosition)
                    nextPosition = nextLadder.getEnd();
            }
        } while (nextnewPositon != nextPosition);
        return nextPosition;

    }

    public boolean isPlayerWon(Player player) {

        return this.snakeAndLadderBoard.getPlayerpieces().get(player.getPlayerName()) == size;
    }
}
