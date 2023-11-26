package view;

import entity.board.Board;
import entity.board.GameState;
import interface_adapter.easy_game.EasyGameController;
import interface_adapter.easy_game.EasyGameState;
import interface_adapter.easy_game.EasyGameViewModel;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGameState;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.new_game.NewGameState;
import interface_adapter.new_game.NewGameViewModel;
import interface_adapter.pause_game.PauseGameController;
import interface_adapter.pause_game.PauseGameState;
import interface_adapter.pause_game.PauseGameViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class BoardView extends JPanel implements ActionListener, PropertyChangeListener {
    final String viewName = "Board View";

//    private final EasyGameViewModel easyGameViewModel;
//    private final EasyGameController easyGameController;

    private final PauseGameController pauseGameController;
    private final PauseGameViewModel pauseGameViewModel;

    private final EndGameViewModel endGameViewModel;
    private final EndGameController endGameController;
    private final NewGameViewModel newGameViewModel;
    private static final int size = 9; // TODO: change based on newgame input
    private final NewGameState currentState;
    private final JLabel lives;
    private JPanel board = new JPanel();
    final JTextField[][] box = new JTextField[size][size];
    private final JButton endGame;
    private final JButton pauseGame;
    private final JButton makeMove;


    public static void main(String[] args) {
        // The main application window.
        JFrame frame = new JFrame("Sudoku Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setContentPane(new BoardView());
        frame.setVisible(true);

    }

    public BoardView(PauseGameController pauseGameController, PauseGameViewModel pauseGameViewModel,
                     EndGameController endGameController, EndGameViewModel endGameViewModel, NewGameViewModel newGameViewModel) {
        this.pauseGameController = pauseGameController;
        this.pauseGameViewModel = pauseGameViewModel;
        this.endGameController = endGameController;
        this.endGameViewModel = endGameViewModel;
        this.newGameViewModel = newGameViewModel;

        this.currentState = newGameViewModel.getState();

        // TODO: add this to viewmodel property change listener
        board.setLayout(new GridLayout(4, 4));
        board.setAlignmentX(Component.RIGHT_ALIGNMENT);
        board.setSize(500, 500);

        GameState newGameState = currentState.getGame();
        ArrayList values = new ArrayList<>();

        // TODO: get the gameState/ array and use a pointer to iterate over it and change number
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                JTextField number = new JTextField();
                number.setHorizontalAlignment(JTextField.CENTER);
                number.setFont(new Font("Arial", Font.PLAIN, 20));
                box[row][col] = number;
                board.add(number);
            }
        }
        this.add(board);

        lives = new JLabel();
        lives.setText("Lives: ");
        lives.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(lives);

        JLabel timer = new JLabel();
        timer.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(timer);

        JPanel buttons = new JPanel();

        endGame = new JButton("End Game");
        buttons.add(endGame);

        pauseGame = new JButton("Pause Game");
        buttons.add(pauseGame);

        makeMove = new JButton("Make Move");
        buttons.add(makeMove);
        this.add(buttons);

        endGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(endGame)) {
                            EndGameState endGameState = endGameViewModel.getState();
                            endGameController.execute(
                                    endGameState.getUser(),
                                    endGameState.getEndGame(),
                                    endGameState.getTime(),
                                    endGameState.getLives()
                            );
                        }
                    }
                }
        );

        pauseGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(pauseGame)) {
                            PauseGameState pauseGameState = pauseGameViewModel.getState();
                            pauseGameController.execute(
                                    pauseGameState.getUser(),
                                    pauseGameState.getPausedGame(),
                                    pauseGameState.getPastGames()

                            );

                        }
                    }
                }
        );

        makeMove.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(makeMove)) {
                            EasyGameState currentState = easyGameViewModel.getState();
                            easyGameController.execute(
                                    currentState.getEasyGame(),
                                    currentState.getRow(),
                                    currentState.getColumn(),
                                    currentState.getValue()
                            );
                        }
                    }
                }
        );


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
