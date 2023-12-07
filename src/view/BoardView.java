package view;

import entity.board.GameState;
import interface_adapter.end_game.EndGameController;
import interface_adapter.end_game.EndGameViewModel;
import interface_adapter.make_move.MakeMoveController;
import interface_adapter.pause_game.PauseGameController;
import interface_adapter.pause_game.PauseGameViewModel;
import interface_adapter.play_game.PlayGameState;
import interface_adapter.play_game.PlayGameViewModel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * View for the BoardView which extends JPanel. Also implements ActionListener and PropertyChangeListener
 */
public class BoardView extends JPanel implements ActionListener, PropertyChangeListener {
    private final Color blue = new Color(97, 150, 242);
    public final String viewName = "PLAY GAME";
    private static int size = 4;
    private final PlayGameState currentState;
    private final JLabel lives;
    private JPanel board = new JPanel();
    private JTextField[][] box;
    private final JButton endGame;
    private final JButton pauseGame;
    private final JButton makeMove;
    private final JButton startPlaying;
    private final JButton rules;
    private final JPanel buttons;
    private final JLabel timerLabel;

    /**
     * Constructor for Board View
     * @param pauseGameController the controller for pause game, is PauseGameController object
     * @param pauseGameViewModel the view model for pause game, is PauseGameViewModel object
     * @param playGameViewModel the view model for play game, is PlayGameViewModel object
     * @param makeMoveController the controller for make move use case, is MakeMoveController object
     * @param endGameController the controller for end game, is EndGameController object
     * @param endGameViewModel the view model for end game, is EndGameViewModel object
     */

    public BoardView(PauseGameController pauseGameController, PauseGameViewModel pauseGameViewModel,
                                 EndGameController endGameController, EndGameViewModel endGameViewModel,
                                 PlayGameViewModel playGameViewModel, MakeMoveController makeMoveController) {

        playGameViewModel.addPropertyChangeListener(this);
        this.currentState = playGameViewModel.getState();

        // Creating the Title of the View
        JLabel title = new JLabel("Play Smart Sudoku");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 30));
        Color darkblue = new Color(50, 78, 156);
        title.setForeground(darkblue);
        title.setBorder(new CompoundBorder(title.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(title);

        // Setting the Size of The Board Based on The State's Difficulty
        GameState newGameState = new GameState(currentState.getDifficulty());
        if (currentState.getDifficulty() == 1) {size = 4;}
        else {size = 9;}

        // Setting Up The Layout of The Board
        box = new JTextField[size][size];

        board.setLayout(new GridLayout(size, size));
        board.setAlignmentX(Component.CENTER_ALIGNMENT);
        // board.setSize(500, 500);
        this.add(board);


        // Add Lives to The GUI
        lives = new JLabel();
        lives.setText("LIVES: ".concat(String.valueOf(newGameState.getLives())));
        lives.setFont(new Font("Consolas", Font.ITALIC, 20));
        Color white = Color.white;
        lives.setBackground(white);
        Color black = Color.black;
        lives.setForeground(black);
        lives.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(lives);

        timerLabel = new JLabel();
        int countdownTime = 200;
        String start = LocalDateTime.now().toString(); // initialize outside of action listener
        currentState.setStartTime(LocalDateTime.parse(start));

        Timer timer = new Timer(1000, new ActionListener() { //delay is 1 second
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime currentTime = LocalDateTime.now();
                Duration elapsedTime = Duration.between((currentState.getStartTime()), currentTime);
                long remainingTimeInSeconds = countdownTime - elapsedTime.getSeconds();

               if (remainingTimeInSeconds >= 0) {
                   int hours = (int) (remainingTimeInSeconds / 3600);
                   int minutes = (int) ((remainingTimeInSeconds % 3600) / 60);
                   int seconds = (int) (remainingTimeInSeconds % 60);

                   timerLabel.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
               } else {
                   timerLabel.setText("00:00:00");
               }
            }
        });
        timer.setRepeats(true);
        timer.start();

//        // Add Timer to GUI
        //timer.setText();
        timerLabel.setFont(new Font("Consolas", Font.ITALIC, 20));
        timerLabel.setBackground(darkblue);
        timerLabel.setForeground(black);
        timerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(timerLabel);

        // Add Buttons to GUI
        buttons = new JPanel();
        endGame = new CustomButton("End Game", darkblue, white);
        endGame.setFont(new Font("Verdana", Font.BOLD, 16));
        endGame.setBackground(white);
        endGame.setForeground(darkblue);
        endGame.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(endGame);


        pauseGame = new CustomButton("Pause Game", darkblue, white);
        pauseGame.setFont(new Font("Verdana", Font.BOLD, 16));
        pauseGame.setBackground(white);
        pauseGame.setForeground(darkblue);
        pauseGame.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(pauseGame);


        makeMove = new CustomButton("Make Move", darkblue, white);
        makeMove.setFont(new Font("Verdana", Font.BOLD, 16));
        makeMove.setBackground(white);
        makeMove.setForeground(darkblue);
        makeMove.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        buttons.add(makeMove);

        rules = new CustomButton("Game Rules", darkblue, white);
        rules.setFont(new Font("Verdana", Font.BOLD, 16));
        rules.setBackground(white);
        rules.setForeground(darkblue);
        rules.setAlignmentX(JLabel.TOP_ALIGNMENT);
        buttons.add(rules);

        startPlaying = new CustomButton("Start Playing Puzzle", darkblue, white);
        startPlaying.setFont(new Font("Verdana", Font.BOLD, 16));
        startPlaying.setBackground(white);
        startPlaying.setForeground(darkblue);
        startPlaying.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        JPanel startPlayingPanel = new JPanel();
        startPlayingPanel.setBorder(new CompoundBorder(buttons.getBorder(), new EmptyBorder(10,40,10,40)));
        startPlayingPanel.add(startPlaying);

        this.add(startPlayingPanel);

        buttons.setBorder(new CompoundBorder(buttons.getBorder(), new EmptyBorder(10,40,10,40)));
        this.add(buttons);
        buttons.setVisible(false);
        timerLabel.setVisible(false);
        lives.setVisible(false);

        rules.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(rules)) {
                            JOptionPane.showMessageDialog(board,
                                    "Welcome to SmartSudoku. We're playing sudoku with a twist.\n" +
                                            "Here are the rules: \n" +
                                            "We have a n x n board where squares need to be filled in with numbers from 1 to n with no repeated numbers in each line, horizontally and vertically.\n" +
                                            "There are also sqrt(n) boxes⏹️ that need to be filled with numbers from 1 to n without repeating.\n" +
                                            "Each time you input a number from 1 to n:\n" +
                                            "- If it is correct, the board will shift 🔀 to a new board with new numbers. The numbers you inputted will stay the same.\n" +
                                            "- If it is wrong, then you lose a life. You have 5 lives in total ♥️♥️♥️♥️♥️\n" +
                                            "You can always pause at any time.\n" +
                                            "There is a timer that keeps track of how long you've spent on the game.\n" +
                                            "HAVE FUN AND ENJOY THE MUSIC!");


                        }
                    }
                }
        );

        // Creating Action Listeners
        startPlaying.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Trigger the property change event when the button is clicked
                firePropertyChange("startPlaying", false, true);
                if (e.getSource().equals(startPlaying)) {
                    // Trigger the property change event when the button is clicked
                    firePropertyChange("startPlaying", false, true);
                    boardReset(buttons, timerLabel);
                }
            }
        });

        endGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(endGame)) {
                            currentState.setTime(currentState.getTimePlayed());
                            endGameController.execute(
                                    currentState.getUserName(),
                                    currentState.getCurrentGame(),
                                    currentState.getTime(),
                                    currentState.getLives(),
                                    currentState.getScores());
                        }
                    }
                }
        );


        pauseGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(pauseGame)) {
                            try {
                                pauseGameController.execute(
                                        currentState.getUserName(),
                                        currentState.getCurrentGame(),
                                        currentState.getCurrentGame().getPastStates()

                                );
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                    }
                }
        );

        makeMove.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(makeMove)) {
                            String enteredNumber = "";
                            int x = -1;
                            int y = -1;
                            int inputCount = 0;

                            // When this is clicked, we parse through board to gather the info from what was inputted
                            for (int row = 0; row < size; row++) {
                                for (int col = 0; col < size; col++) {
                                    if (box[row][col].isEditable() && !box[row][col].getText().isEmpty()) {
                                        // Lock the entered number and disable all other JTextFields
                                        enteredNumber = box[row][col].getText();
                                        x = col;
                                        y = row;
                                        box[row][col].setEditable(false);
                                        inputCount ++;

                                    }
                                    // Disable Editting Temporarily
                                    box[row][col].setEditable(false);
                                }
                            }

                            // Checks to see if the user inputted exactly one value
                            if (!(inputCount == 1)) {
                                JOptionPane.showMessageDialog(board, "Please Enter Exactly One Value Per Round");
                                boardReset(buttons, timerLabel);
                            }
                            else {

                                try {
                                    // Checks to see if the user inputted an integer
                                    int enteredNum = Integer.parseInt(enteredNumber);

                                    // Checks to see if the integer entered is a valid number for the board size
                                    if (enteredNum > size || enteredNum < 1) {
                                        JOptionPane.showMessageDialog(board, "Input must be less than or equal to ".concat(String.valueOf(size)).concat(" and greater than 0"));
                                        // Re-enable editing
                                        // Disable all other JTextFields
                                        for (int i = 0; i < size; i++) {
                                            for (int j = 0; j < size; j++) {
                                                if (!box[i][j].getText().isEmpty()) {
                                                    int cellValue = Integer.parseInt(box[i][j].getText());
                                                    if (cellValue > size || cellValue < 1) {  // the box that was inputted a number that was too gets reset to being empty
                                                        box[i][j].setText("");
                                                    }
                                                }
                                                if (box[i][j].getText().isEmpty()) {
                                                    box[i][j].setEditable(true);
                                                }
                                            }
                                        }

                                    }

                                    // if everything is fine, update the view
                                    else {
                                        // recreate the board based on the new scrambled board
                                        // ASSUMPTION: playgameviewmodel.getState.currentGame has been set to the new scrambled board

                                        currentState.setCurrentGame(makeMoveController.execute(Integer.parseInt(enteredNumber), x, y, currentState.getCurrentGame()));
                                        lives.setText("LIVES: ".concat(String.valueOf(currentState.getCurrentGame().getLives())));

                                        boardReset(buttons, timerLabel);



                                        if (playGameViewModel.getState().getCurrentGame().getCurrBoard().noSpacesLeft()) {
                                            JOptionPane.showMessageDialog(board, " Congratulations!!! You Solved The Puzzle!!!");
                                            endGameController.execute(
                                                    currentState.getUserName(),
                                                    currentState.getCurrentGame(),
                                                    currentState.getTime(),
                                                    currentState.getLives(),
                                                    currentState.getScores()
                                            );
                                        }
                                    }


                                } catch (NumberFormatException | IOException ignored) {
                                    JOptionPane.showMessageDialog(board, "Input Must Be an Integer");

                                    // Re-enable editing
                                    for (int i = 0; i < size; i++) {
                                        for (int j = 0; j < size; j++) {
                                            if (!(box[i][j].getText().matches("\\d+"))) {  // the box that was inputted a non-integer gets reset to being empty
                                                box[i][j].setText("");
                                            }
                                            if (box[i][j].getText().isEmpty()) {
                                                box[i][j].setEditable(true);
                                            }
                                        }
                                    }
                                }
                            }

                            BoardView.this.setLayout(new BoxLayout(BoardView.this, BoxLayout.Y_AXIS));

                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Records the action performed
     * @param e the action that was performed
     */

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * Resets the board
     * @param buttons buttons on the screen
     * @param timer time label
     */

    public void boardReset(JPanel buttons, JLabel timer) {
            // Set the layout manager to BoxLayout with Y_AXIS
            BoardView.this.setLayout(new BoxLayout(BoardView.this, BoxLayout.Y_AXIS));
            GameState newGameState = currentState.getCurrentGame();
            if (currentState.getDifficulty() == 1) {size = 4;}
            else {size = 9;}
            board.removeAll();
            box = new JTextField[size][size];
            board.setLayout(new GridLayout(size, size));

            ArrayList<Integer> values = newGameState.getCurrBoard().toArray();
            int i = 0;
            int cellsize = 5;
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    JTextField number = new JTextField();
                    number.setSize(new Dimension(cellsize, cellsize));
                    number.setHorizontalAlignment(JTextField.CENTER);
                    number.setFont(new Font("Arial", Font.PLAIN, 20));

                    if (values.get(i) != 0) {
                        number.setText(String.valueOf(values.get(i)));
                        number.setEditable(false);
                    }

                    box[row][col] = number;
                    board.add(number);
                    i++;
                }
            }
        board.revalidate();
        board.repaint();
        startPlaying.setVisible(false);
        buttons.setVisible(true);
        timer.setVisible(true);
        lives.setVisible(true);
        BoardView.this.setLayout(new BoxLayout(BoardView.this, BoxLayout.Y_AXIS));
    }

    /**
     * Records and notifies of any property change
     * @param evt the propertychange event that is fired by the viewmodel
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("PLAYGAMESTATE")) {
            boardReset(buttons, timerLabel);
        }
    }
}
