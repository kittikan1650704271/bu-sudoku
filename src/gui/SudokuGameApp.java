package gui;

import gui.panels.SoundWavePlayer;
import gui.model.Player;
import static gui.SudokuGame.color1;
import static gui.SudokuGame.color2;
import static gui.SudokuGame.color3;
import static gui.SudokuGame.color4;
import gui.panels.SignInPanel;
import gui.model.Cell;
import gui.model.CellPosition;
import gui.model.Difficulty;
import gui.model.Generator;
import gui.panels.EmptyHeartImage;
import gui.panels.GamePanel;
import gui.panels.HeartImage;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

/**
 * This is the Sudoku Game (CONTROLLER).
 *
 * @author masahiro
 * @version 1.0
 */
public class SudokuGameApp extends JFrame {

    // Local Model-View Link Variables
    private SoundWavePlayer soundBGM;
    private int levelScore;
    private int FailCount = 0;
    private SudokuGame model;
    private SudokuGamePanel view;
    private String rulesCaller; // -> Tells us where the back button on the rules pane should redirect to based on its caller
    private KeyListener cellKeyListener;
    private MouseListener cellMouseListener;
    private GamePanel gamepanel;
    private int currentMusic;
    private List<Cell> cellList;

    /**
     * Constructs the Sudoku Game Frame
     *
     * @param name title of the application window
     */
    public void SudokuGameAppMain() {
        //super(name);
        this.model = new SudokuGame();
        this.view = new SudokuGamePanel();
        soundBGM = new SoundWavePlayer();
//        soundBGM.shuffleLoopSound(-10);
//        currentMusic = soundBGM.getIndexMusicPlay();

        getContentPane().add(this.view);
        setSize(1000, 550);
        setResizable(false);

        // Fill Difficulty Selector
        for (Difficulty diff : Difficulty.values()) {
            view.getHomePanel().getLevelSelectionModel().addElement(diff);
        }

        // Window Action Listeners
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Exit Sudoku", "Cancel"};
                int result = JOptionPane.showOptionDialog(getParent(), "Are you sure you want to exit the application?\nActive games will not be saved.", "Exit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        // Action Listeners on Welcome Panel
        
        KeyListener signInListener = new KeyListener() {
            @Override
          public void keyPressed(KeyEvent keyEvent) {
            System.out.println("Pressed :" + keyEvent.getKeyText(keyEvent.getKeyCode()));
            if(keyEvent.getKeyText(keyEvent.getKeyCode()) == "Enter"){
                signInEvt();
            }
          }
            @Override
          public void keyReleased(KeyEvent keyEvent) {
//            printIt("Released", keyEvent);
          }
            @Override
          public void keyTyped(KeyEvent keyEvent) {
//            printIt("Typed", keyEvent);
          }
        };
        
        KeyListener signUpListener = new KeyListener() {
            @Override
          public void keyPressed(KeyEvent keyEvent) {
            System.out.println("Pressed :" + keyEvent.getKeyText(keyEvent.getKeyCode()));
            if(keyEvent.getKeyText(keyEvent.getKeyCode()) == "Enter"){
                signUpEvt();
            }
          }
            @Override
          public void keyReleased(KeyEvent keyEvent) {
//            printIt("Released", keyEvent);
          }
            @Override
          public void keyTyped(KeyEvent keyEvent) {
//            printIt("Typed", keyEvent);
          }
        };
        this.view.getWelcomePanel().getSignInPanel().getNameText().addKeyListener(signInListener);
        this.view.getWelcomePanel().getSignInPanel().getPasswordText().addKeyListener(signInListener);
        
        this.view.getWelcomePanel().getSignUpPanel().getFullnameText().addKeyListener(signUpListener);
        this.view.getWelcomePanel().getSignUpPanel().getEmailText().addKeyListener(signUpListener);
        this.view.getWelcomePanel().getSignUpPanel().getPasswordText().addKeyListener(signUpListener);
        
        this.view.getWelcomePanel().getSignUpPanel().getSigninButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundBGM.playSound("click_stereo", -10);
                view.getWelcomePanel().getCardLayoutManager().next(view.getWelcomePanel().getSlider());
            }
        });
        this.view.getWelcomePanel().getSignInPanel().getSigninButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                soundBGM.playSound("click_stereo", -10);
                signInEvt();
            }
        });
        this.view.getWelcomePanel().getSignInPanel().getSignupButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundBGM.playSound("click_stereo", -10);
                view.getWelcomePanel().getCardLayoutManager().next(view.getWelcomePanel().getSlider());
            }
        });
        this.view.getWelcomePanel().getSignUpPanel().getSignupButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpEvt();
            }
        });

        // Action Listeners on Home Panel
        this.view.getHomePanel().getNewGameBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int randomIndex = random.nextInt(14);
                String strRandomIndex = String.valueOf(randomIndex);
                System.out.println("qute num2 " + strRandomIndex);
                view.getLoadingPanel().getCardLayoutManager().show(view.getLoadingPanel().getQuotepanel(), strRandomIndex);
                view.getCardLayoutManager().show(view.getContent(), "loading");
                Timer timer = new Timer(600, new ActionListener() {
                    private int count = 2;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (count >= 0) {
                            count--;
                        } else {
                            soundBGM.playSound("pling", -10);
                            newGame();
                            ((Timer) e.getSource()).stop(); // Stop the timer after 5 iterations
                        }
                    }
                });
                timer.start();
                soundBGM.playSound("click_stereo", -10);
            }

        });

        this.view.getHomePanel().getMusicSelector().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = (String) view.getHomePanel().getMusicSelector().getSelectedItem();
                view.getHomePanel().getStopSound();
                for (int i = 1; i < 10; i++) {
                    if (value.equals("BGM " + i)) {
//                    System.out.println("I'm play!");
                        soundBGM.stopSound();
                        soundBGM.playLoopSound("bgm" + i, -10);
                    } else {
                        soundBGM.stopSound();
                    }
                }
            }
        });

        this.view.getHomePanel().getViewRulesBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundBGM.playSound("click_stereo", -10);
                rulesCaller = "home"; // -> Rules was called from the 'home' panel, so return to it when done
                view.getCardLayoutManager().show(view.getContent(), "rules");
            }
        });
        this.view.getHomePanel().getSignoutBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                soundBGM.playSound("click_stereo", -10);
                Object[] options = {"Yes, sign out", "No way!"};
                int result = JOptionPane.showOptionDialog(getParent(), "Are you sure you want to sign out?", "Leaving Already?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (result == 0) {

                    soundBGM.playSound("close4", -10);
                    view.getCardLayoutManager().show(view.getContent(), "welcome");
                    model.setPlayer(null);
                    model.setPuzzle(null);
                    model.setHintsUsed(0);
                    model.setTimer(null);
                    view.getGamePanel().getHintBtn().setEnabled(true);
                    view.getHomePanel().getLevelSelector().setSelectedIndex(0);
                }

                if (result == 1) {
                    soundBGM.playSound("no1", -10);

                }
            }
        });

        // Actions Listeners on Game & Rules Panel
        this.view.getGamePanel().getHintBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // User wants a hint, check if game has unused hints
                soundBGM.playSound("click_stereo", -10);
                view.getGamePanel().updateHintNumber(Integer.toString((3 - model.getHintsUsed()) - 1));
                if (model.getHintsUsed() < model.getPuzzle().getDifficulty().getMaxHints()) {
                    model.getPuzzle().hint(false);
                    model.setHintsUsed(model.getHintsUsed() + 1);

                    update();
                    System.err.println("HINT USED: " + model.getStringHintsUsed());
                    if (model.getHintsUsed() == model.getPuzzle().getDifficulty().getMaxHints()) {
                        view.getGamePanel().getHintBtn().setEnabled(false);
                        int alzimer = JOptionPane.showOptionDialog(getParent(), "Let's not make it too easy!\nThat was the last hint for this game.\n\nDid you Know?\nSudokus can likely prevent Alzheimer's disease\nand Dementia, so don't make it too easy.", "Out of Hints", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                        if (alzimer == JOptionPane.YES_OPTION) {
                            soundBGM.playSound("yes2", -10);
                        } else {
                            soundBGM.playSound("no2", -10);
                        }

                    }
                    checkGridCompletion();
                }
            }
        });
        this.view.getGamePanel().getViewRulesBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show Rules Panel
                soundBGM.playSound("click_stereo", -10);
                rulesCaller = "game"; // -> Rules was called from the 'game' panel, so return to it when done
                view.getCardLayoutManager().show(view.getContent(), "rules");
            }
        });
        this.view.getRulesPanel().getBackBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundBGM.playSound("click_stereo", -10);
                view.getCardLayoutManager().show(view.getContent(), rulesCaller); // -> Return to caller panel
            }
        });
        this.view.getGamePanel().getEndGameBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                soundBGM.playSound("click_stereo", -10);
                Object[] options = {"That's it", "Cancel"};
                int result = JOptionPane.showOptionDialog(getParent(), "Are you sure you want to end the game?\n\nThis Sudoku is best played in one sitting,\nand can't be continued later.", "Exit?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (result == 0) {
                    view.getGamePanel().setResetHeart(true);
                    view.getGamePanel().changeHeart();
                    view.getGamePanel().setResetHeart(false);
                    view.getCardLayoutManager().show(view.getContent(), "home");
                    destroyGameInstance();

                }
            }
        });

        // Cell Listener Adapters
        this.cellKeyListener = new KeyAdapter() {
            /**
             * Validates the user input for the cell
             *
             * @param evt the key event trigger
             */
            @Override
            public void keyTyped(KeyEvent evt) {
                Cell cell = (Cell) evt.getSource();
                // Disregard entry if not 1-9 or text already exists
                if (!String.valueOf(evt.getKeyChar()).matches("^[1-9]$") || cell.getText().length() == 1) {
                    System.out.println("Input: " + evt.getKeyChar() + " was rejected.");
                    evt.consume();

                } else {
                    // Check if input meets contraints
                    if (!model.getPuzzle().meetsConstraints(cell, Integer.valueOf(String.valueOf(evt.getKeyChar()).trim()))) {
                        FailCount++;
                        view.getGamePanel().setFailed_count(FailCount);
                        System.out.println("this is view fail " + view.getGamePanel().getFailed_count());
                        soundBGM.playSound("pop", -10);
                        view.getGamePanel().changeHeart();

                        if (FailCount == 3) {
                            String[] options = {"Yes, Come on baby!", "No, I'm scare~"};
                            int selection = JOptionPane.showOptionDialog(null, "Do you want to retry?:", "Lose Game",
                                    0, 2, null, options, options[0]);
                            if (selection == 0) {
                                System.out.println("User clicked Yes");

                                //Re-game
                                Timer timer = new Timer(300, new ActionListener() {
                                    private int count = 2;

                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (count >= 0) {
                                            soundBGM.playSound("drink", -10);
                                            count--;
                                        } else {
                                            ((Timer) e.getSource()).stop(); // Stop the timer after 5 iterations
                                        }
                                    }
                                });
                                timer.start();
                                destroyGameInstance();
                                newGame();

                            } else if (selection == 1) {
                                System.out.println("User clicked No");
                                //Sending back to Homepage
                                destroyGameInstance();
                                refreshHomePanel();
                                view.getCardLayoutManager().show(view.getContent(), "home");
                            }
                            FailCount = 0;
                            view.getGamePanel().setResetHeart(true);
                            view.getGamePanel().changeHeart();
                            view.getGamePanel().setResetHeart(false);
                        }
                        System.err.println("VALUE " + evt.getKeyChar() + " AT " + cell.getPosition() + " DOES NOT MEET SUDOKU CONTRAINTS" + FailCount);
                        cell.setText("");
                        cell.setUserValue(0);
                        evt.consume();
                    } else {
                        cell.setUserValue(Integer.parseInt(String.valueOf(evt.getKeyChar()).trim()));
                        //Play draw number sound
                        soundBGM.playSound("drawmap1", -10);

                        //Check Number in table
                        checkFilledNum(cell);
                    }
                    checkGridCompletion();

                }
            }

        };

        this.cellMouseListener = new MouseAdapter() {
            // Cell Hover Attribute
            private Color preActionColor;

            /**
             * Event Handler for mouse button press
             *
             * @param evt the event trigger
             */
            @Override
            public void mousePressed(MouseEvent evt) {
                Cell cell = (Cell) evt.getSource();
                
                // On Right-Click, clear cell
                if (evt.getButton() == MouseEvent.BUTTON3) {
                    cell.setText("");
                    cell.setUserValue(0);
                    view.getGamePanel().rightClickHL();
                }

                cell.selectAll();
                
                if (evt.getButton() == MouseEvent.BUTTON1) {
                    view.getGamePanel().leftClickHL();
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    view.getGamePanel().leftClickReset();
                }

                if (e.getButton() == MouseEvent.BUTTON3) {
                    view.getGamePanel().rightClickReset();
                }
            }

            /**
             * Highlights game constraints for the hovered cell
             *
             * @param evt the cell being hovered
             */
            @Override
            public void mouseEntered(MouseEvent evt) {
                Cell cell = (Cell) evt.getSource();
                preActionColor = cell.getBackground();
                cell.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                // Highlight Valid Cells
                for (Cell aCell : view.getGamePanel().getViewCellList()) {
                    if (cell.getPosition().getRow() == aCell.getPosition().getRow()) {
                        aCell.setBackground(color3.darker().darker());
                    }
                    if (cell.getPosition().getColumn() == aCell.getPosition().getColumn()) {
                        aCell.setBackground(color3.darker().darker());
                    }
                    if (cell.getPosition().getSubgrid() == aCell.getPosition().getSubgrid()) {
                        aCell.setBackground(color3.darker().darker());
                    }
                    checkFilledNum(cell);
                    
                }         
                cell.setBackground(color3);
            }

            /**
             * Restores hover colors from hover event
             *
             * @param evt the hovered cell being exited from
             */
            @Override
            public void mouseExited(MouseEvent evt) {
                Cell cell = (Cell) evt.getSource();

                // Restore Color
                for (Cell aCell : view.getGamePanel().getViewCellList()) {
                    if (aCell.isLocked()) {
                        aCell.setBackground(color1);
                    } else {
                        aCell.setBackground(color2);
                    }
                }

                cell.setBackground(preActionColor);
            }

        };
    }

    // Create new Grid and restart time
    public void newGame() {
        view.getGamePanel().updateHintNumber(Integer.toString((3 - model.getHintsUsed())));
        view.getGamePanel().resetSideNumbers();
        Random random = new Random();
        int randomIndex = random.nextInt(14);
        String strRandomIndex = String.valueOf(randomIndex);
        System.out.println("qute num3 " + strRandomIndex);
        view.getLoadingPanel().getCardLayoutManager().show(view.getLoadingPanel().getQuotepanel(), strRandomIndex);

        // Get Level for the Game
        Difficulty level = Difficulty.valueOf(view.getHomePanel().getLevelSelector().getSelectedItem().toString().toUpperCase());
        System.out.println(level);
        // Generate New Game
        Generator puzzle = new Generator();
        puzzle.generateGrid(level);
        model.setPuzzle(puzzle.getGrid());

        // Configure View
        view.getGamePanel().setViewCellList(model.getPuzzle().getCellList());
        view.getGamePanel().getLevelTitle().setText(String.valueOf(level));
        update();

        // Switch to Game Panel
        view.getCardLayoutManager().show(view.getContent(), "game");

        // Set up Game Timer & Start
        long start = Calendar.getInstance().getTimeInMillis() / 1000;
        model.setTimer(new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long secondsSinceInit = ((Calendar.getInstance().getTimeInMillis() / 1000) - start);
                view.getGamePanel().getTimeLabel().setText(String.format("%02d:%02d", secondsSinceInit / 60 % 60, secondsSinceInit % 60));
            }
        }));
        model.getTimer().setInitialDelay(0);
        model.getTimer().start();
    }

    /**
     * Application entry point.
     *
     * @param args Optional startup arguments
     */
    public static void main(String[] args) {
        SudokuGameApp frame = new SudokuGameApp();
        frame.SudokuGameAppMain();
        //ImageIcon img = new ImageIcon("logo.png");
        //frame.setIconImage(img.getImage());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Signs the user into the application on correct credentials, else rejects
     * them.
     */
    private void signInEvt() {

        Random random = new Random();
        int randomIndex = random.nextInt(14);
        String strRandomIndex = String.valueOf(randomIndex);
        System.out.println("qute num1 " + strRandomIndex);
        view.getLoadingPanel().getCardLayoutManager().show(view.getLoadingPanel().getQuotepanel(), strRandomIndex);

        // Retrieve Details
            
        
        String name = this.view.getWelcomePanel().getSignInPanel().getNameText().getText().trim();
        String password = new String(this.view.getWelcomePanel().getSignInPanel().getPasswordText().getPassword()).trim();
        if (!name.equals("") && !password.equals("")) {
            if (model.getSudokuDB().checkLogin(name, password)) {
                // Set Player
                Player player = model.getSudokuDB().loadPlayer(name, password);
                if (player != null) {
                    model.setPlayer(model.getSudokuDB().loadPlayer(name, password));
                    // Clear Fields
                    view.getWelcomePanel().getSignInPanel().clear();
                    // Show Home Screen
                    view.getCardLayoutManager().show(view.getContent(), "loading");
                    Timer timer = new Timer(600, new ActionListener() {
                        private int count = 2;

                        @Override
                        public void actionPerformed(ActionEvent e) {

                            if (count >= 0) {
                                count--;
                            } else {
                                refreshHomePanel();
                                soundBGM.playSound("open", -10);
                                view.getCardLayoutManager().show(view.getContent(), "home");
                                ((Timer) e.getSource()).stop(); // Stop the timer after 5 iterations
                            }
                        }
                    });
                    timer.start();

                } else {
                    Object[] options = {"OK"};
                    JOptionPane.showOptionDialog(this, "An error occured during sign in, please try again.", "Sign In Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
                }
            } else {
                Object[] options = {"Let me try again"};
                JOptionPane.showOptionDialog(this, "The credentials you have provided are invalid, please enter them correctly or create a new account.", "Invalid Credentials", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
            }
        } else {
            Object[] options = {"Alright"};
            JOptionPane.showOptionDialog(this, "In order to sign in, all fields must be filled out.", "Empty Fields", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        }
    }

    /**
     * Signs the user up and registers them in the database.
     */
    private void signUpEvt() {
        String emailRegex = "(?:[a-z0-9!#$%@&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        // Get User Details
        String fullname = this.view.getWelcomePanel().getSignUpPanel().getFullnameText().getText().trim();
        String email = this.view.getWelcomePanel().getSignUpPanel().getEmailText().getText().trim();
        String password = new String(this.view.getWelcomePanel().getSignUpPanel().getPasswordText().getPassword()).trim();

        if (!fullname.equals("") && !email.equals("") && !password.equals("")) {
            if (email.matches(emailRegex)) {
                if (model.getSudokuDB().registerUser(fullname, email, password)) {
                    view.getWelcomePanel().getCardLayoutManager().next(view.getWelcomePanel().getSlider());
                    // Clear Fields
                    view.getWelcomePanel().getSignUpPanel().clear();
                    Object[] options = {"OK"};
                    JOptionPane.showOptionDialog(this, "Your registration was successful!\n You can now sign in to your account.", "Successful Registration", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
                } else {
                    Object[] options = {"Let me try again"};
                    JOptionPane.showOptionDialog(this, "Your registration was unsuccessful!\nBe sure not to create a duplicate account.", "Unsuccessful Registration", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
                }
            } else {
                // Email doesn't meet requirement
                Object[] options = {"I will correct that"};
                JOptionPane.showOptionDialog(this, "You must provide a valid email address.", "Invalid Email Address", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
            }
        } else {
            // Empty Fields
            Object[] options = {"Alright"};
            JOptionPane.showOptionDialog(this, "In order to register, all fields must be filled out.", "Empty Fields", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
        }
    }

    /**
     * Updates (refreshes) the Home panel.
     */
    private void refreshHomePanel() {
        // Set Player Name Text
        view.getHomePanel().getNameLabel().setText(model.getPlayer().getFullname().toUpperCase());

        // Update Highscore Table
        view.getHomePanel().getTableModel().setRowCount(0);
        updateHighscores(model.getHighscores());
    }

//    public void highlightNum(Cell cell){
//        int k = 1;
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                if (model.getPuzzle().getSubgrids().get(i).get(j).getText().equals(String.valueOf(cell.getUserValue()))) {
//                    k++;
//                    System.out.println(k);
//                    if (k == 9) {
//                        
//                        System.out.println(String.valueOf(cell.getUserValue()) + " is out!");
//                        view.getGamePanel().checkDoneNumber(cell.getUserValue() - 1);
//                        soundBGM.playSound("no3", -10);
//                        System.out.println(model.getPuzzle().getSubgrids().get(0).get(0).getClass());
//                        for (int p = 0; p < 9; p++) {
//                            for (int q = 0; q < 9; q++) {
//                                if (model.getPuzzle().getSubgrids().get(p).get(q).getText().equals(String.valueOf(cell.getUserValue()))) {
//                                    
//          
//                                    cell.setText(String.valueOf(cell.getUserValue()));
//                                    update();
//
//                                }
//                            }
//
//                        }
//
//                    }
//                }
//            }
//
//        }
//    }
    
    
    public void checkFilledNum(Cell cell) {
        int k = 1;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (model.getPuzzle().getSubgrids().get(i).get(j).getText().equals(String.valueOf(cell.getUserValue()))) {
                    k++;
                    System.out.println(k);
                    if (k == 9) {
                        System.out.println(String.valueOf(cell.getUserValue()) + " is out!");
                        view.getGamePanel().checkDoneNumber(cell.getUserValue() - 1);
                        soundBGM.playSound("no3", -10);
                        System.out.println(model.getPuzzle().getSubgrids().get(0).get(0).getClass());
                        for (int p = 0; p < 9; p++) {
                            for (int q = 0; q < 9; q++) {
                                if (model.getPuzzle().getSubgrids().get(p).get(q).getText().equals(String.valueOf(cell.getUserValue()))) {
                                    model.getPuzzle().getSubgrids().get(p).get(q).setEnabled(false);
                                    model.getPuzzle().getSubgrids().get(p).get(q).setLocked(true);
          
                                    cell.setText(String.valueOf(cell.getUserValue()));
                                    update();

                                }
                            }

                        }

                    }
                }
            }

        }

    }

    /**
     * View update event handler.
     */
    private void update() {
        // Set for each cell
        for (Cell cell : this.view.getGamePanel().getViewCellList()) {
            cell.setBackground(color1);
            cell.setForeground(Color.WHITE);
            cell.setFont(new Font("Halvetica Neue", Font.PLAIN, 36));
            cell.setBorder(new LineBorder(Color.BLACK, 0));
            cell.setHorizontalAlignment(JTextField.CENTER);
            cell.setCaretColor(color3);
            cell.setDragEnabled(false);
            cell.setTransferHandler(null);

            // Add subgrid separators
            CellPosition pos = cell.getPosition();
            if (pos.getColumn() == 2 || pos.getColumn() == 5) {
                cell.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, color3));
            } else if (pos.getRow() == 2 || pos.getRow() == 5) {
                cell.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, color3));
            }
            if ((pos.getColumn() == 2 && pos.getRow() == 2) || (pos.getColumn() == 5 && pos.getRow() == 5)
                    || (pos.getColumn() == 2 && pos.getRow() == 5) || (pos.getColumn() == 5 && pos.getRow() == 2)) {
                cell.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, color3));
            }

            // Validate User's Cell Input + Mouse Listeners
            cell.removeKeyListener(cellKeyListener);
            cell.removeMouseListener(cellMouseListener);
            if (cell.isLocked()) {
                cell.setEditable(false);
                cell.setHighlighter(null);
                cell.setCaretColor(color1);
                

            } else {
                cell.setBackground(color2);
                cell.setHighlighter(null);
                cell.addMouseListener(cellMouseListener);
                cell.addKeyListener(cellKeyListener);
            }
            if (cell.isEmpty()) {
                cell.setText("");
            } else {
                cell.setText(String.valueOf(cell.getUserValue()));
            }

            // Adds cell to the view's grid
            this.view.getGamePanel().getGrid().add(cell);
        }
        
    }

    /**
     * Updates the table model with players scores
     *
     * @param tableContent the list of players to include
     */
    public void updateHighscores(ArrayList<Player> tableContent) {
        // Add scores to table
        for (Player p : tableContent) {
            view.getHomePanel().getTableModel().addRow(playerToObjArray(p));
        }
    }

    /**
     * Extracts key score information from Player object into String array
     *
     * @param player the player affected
     * @return a String array with player score and name
     */
    public static Object[] playerToObjArray(Player player) {
        // Split Player into its respective sections
        Object[] initList = new Object[2];
        initList[0] = player.getScore();
        initList[1] = player.getFullname();
        return initList;
    }

    /**
     * Checks the player's current grid's completion
     */
    private void checkGridCompletion() {
        if (this.model.getPuzzle().isFilled()) {
            if (this.model.getPuzzle().isSolved()) {
                puzzleCompleted();
            }
        }
    }

    public static double convertToDecimalTime(String timeString) {
        String[] parts = timeString.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        // Convert minutes to a fraction of an hour
        double decimalMinutes = minutes / 60.0;

        // Combine hours and decimal minutes to get the decimal time
        double decimalTime = hours + decimalMinutes;

        return decimalTime;
    }

    public int scoreCalculate(int scoreGet, double time) {
        int score;
        score = (int) (scoreGet / time);
        return score;
    }

    /**
     * Events which fire at completion of Sudoku grid
     */
    private void puzzleCompleted() {
        //play challenge_complete sound
        soundBGM.playSound("challenge_complete", -10);
        // Stop timer
        this.model.getTimer().stop();
        String gameTime = view.getGamePanel().getTimeLabel().getText();

        // Lock all cells to prevent editing
        for (Cell cell : this.model.getPuzzle().getCellList()) {
            cell.setLocked(true);
        }

        update();

        levelScore = model.getPuzzle().getDifficulty().getMaxScore();
//        System.out.println(convertToDecimalTime(gameTime));
        // Award Points
        this.model.increaseScore(scoreCalculate(levelScore, convertToDecimalTime(gameTime)));
        Object[] options = {"Great!"};
        JOptionPane.showOptionDialog(this, "You have solved the Puzzle.\n\nGame Time: " + gameTime + "\nHints Used: " + this.model.getStringHintsUsed() + "\n\nYour got: " + scoreCalculate(levelScore, convertToDecimalTime(gameTime)) + " points.", "Congratulations!", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);

        //reset heart
        view.getGamePanel().setResetHeart(true);
        view.getGamePanel().changeHeart();
        view.getGamePanel().setResetHeart(false);
        soundBGM.playSound("click_stereo", -10);
        // Return Home
        refreshHomePanel();
        view.getCardLayoutManager().show(view.getContent(), "home");

        // Destroy Game
        destroyGameInstance();
    }

    /**
     * Clears game settings after game
     */
    private void destroyGameInstance() {
        // Destroy Game
        FailCount = 0;
        this.model.setPuzzle(null);
        this.model.setHintsUsed(0);
        this.model.getTimer().stop();
        this.model.setTimer(null);
        view.getGamePanel().getHintBtn().setEnabled(true);
        for (Cell cell : this.view.getGamePanel().getViewCellList()) {
            this.view.getGamePanel().getGrid().remove(cell);
        }
    }

    public ArrayList<Cell> shuffleCells() {
        ArrayList<Cell> shuffledCells = new ArrayList<>(cellList);
        Collections.shuffle(shuffledCells);
        return shuffledCells;
    }

    public SudokuGameApp getSudokuGameApp() {
        return this;
    }
}
