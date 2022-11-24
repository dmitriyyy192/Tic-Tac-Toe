package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TicTacToeController implements Initializable {
    @FXML
    private Button btn_exit;
    @FXML
    private Button btn_menu;
    @FXML
    private Button btn_play;
    @FXML
    private Button btn_reset;
    @FXML
    private Button field1;
    @FXML
    private Button field2;
    @FXML
    private Button field3;
    @FXML
    private Button field4;
    @FXML
    private Button field5;
    @FXML
    private Button field6;
    @FXML
    private Button field7;
    @FXML
    private Button field8;
    @FXML
    private Button field9;

    @FXML
    private CheckBox check_bot;
    @FXML
    private CheckBox check_player;

    @FXML
    private Tab tab_game;
    @FXML
    private Tab tab_menu;
    @FXML
    private TabPane tabPane;

    @FXML
    private Label turnLabel;

    private boolean botTurnIsFirst = true; //переменная, отвечающая за то, кто первый ходит

    private int cntOfMoves = 0; //количество ходов
    private int emptyFieldCount = 9; //количество пустых полей

    private Button fields[]; //массив полей/кнопок

    private String botSymbol = "O";
    private String userSymbol = "X";

    private boolean thereIsAWinner = false; //переменная, отвечающая за то, есть ли победитель

    @FXML
    void btn_exit_click(MouseEvent event) {
        System.exit(1);
    }

    @FXML
    void btn_menu_click(MouseEvent event) {
        tab_game.setDisable(true);
        tab_menu.setDisable(false);
        tabPane.getSelectionModel().select(0);

        for (var f : fields) {
            f.setText("");
            f.setDisable(false);
        }

        cntOfMoves = 0;
        emptyFieldCount = 9;
        thereIsAWinner = false;
        turnLabel.setText("");
    }

    @FXML
    void btn_play_click(MouseEvent event) {
        tab_game.setDisable(false);
        tab_menu.setDisable(true);
        tabPane.getSelectionModel().select(1);
        for (var f : fields) {
            f.setText("");
            f.setDisable(false);
        }
        if (botTurnIsFirst) {
            chooseBotStep();
        }
    }

    @FXML
    void btn_reset_click(MouseEvent event) {
        for (var f : fields) {
            f.setText("");
            f.setDisable(false);
        }
        cntOfMoves = 0;
        emptyFieldCount = 9;
        thereIsAWinner = false;
        turnLabel.setText("");

        if (botTurnIsFirst) {
            chooseBotStep();
        }
    }

    @FXML
    void check_bot_click(MouseEvent event) {
        check_bot.setSelected(true);
        check_player.setSelected(false);
        botTurnIsFirst = true;
    }

    @FXML
    void check_player_click(MouseEvent event) {
        check_bot.setSelected(false);
        check_player.setSelected(true);
        botTurnIsFirst = false;
    }


    @FXML
    void field1_click(MouseEvent event) {
        checkForMove(0);
    }

    @FXML
    void field2_click(MouseEvent event) {
        checkForMove(1);
    }

    @FXML
    void field3_click(MouseEvent event) {
        checkForMove(2);
    }

    @FXML
    void field4_click(MouseEvent event) {
        checkForMove(3);
    }

    @FXML
    void field5_click(MouseEvent event) {
        checkForMove(4);
    }

    @FXML
    void field6_click(MouseEvent event) {
        checkForMove(5);
    }

    @FXML
    void field7_click(MouseEvent event) {
        checkForMove(6);
    }

    @FXML
    void field8_click(MouseEvent event) {
        checkForMove(7);
    }

    @FXML
    void field9_click(MouseEvent event) {
        checkForMove(8);
    }

    private boolean isEven(int num) {
        return num % 2 == 0 ? true : false;
    }


    private void viewWinner(String winner) {
        if (thereIsAWinner) {
            turnLabel.setText("Winner is " + winner);
            for (var el : fields) {
                el.setDisable(true);
            }
        }
    }

    public void checkForMove(int fieldNum) {
        if (botTurnIsFirst) {
            if (isEven(cntOfMoves)) {
                chooseBotStep();
                userStep(fieldNum);
            } else {
                userStep(fieldNum);
                if (!thereIsAWinner) {
                    chooseBotStep();
                }
            }
        } else {
            if (isEven(cntOfMoves)) {
                userStep(fieldNum);
                if (!thereIsAWinner) {
                    chooseBotStep();
                }
            } else {
                chooseBotStep();
            }
        }
        fields[fieldNum].setDisable(true);
    }

    private void userStep(int fNum) {
        if (emptyFieldCount != 0) {
            fields[fNum].setText(userSymbol);
            fields[fNum].setDisable(true);
            check_win();
            if (!thereIsAWinner) {
                cntOfMoves++;
                emptyFieldCount--;
            }
        } else {
            check_win();
        }
    }

    private void chooseBotStep() {
        if (emptyFieldCount != 0) {
            if (field5.getText().equals("")) {
                botStep(4, botSymbol);
            } else if (field1.getText().equals(botSymbol) && field4.getText().equals(botSymbol) && field7.getText().equals("")) { //1 столбец
                botStep(6, botSymbol);
            } else if (field1.getText().equals(botSymbol) && field7.getText().equals(botSymbol) && field4.getText().equals("")) {
                botStep(3, botSymbol);
            } else if (field4.getText().equals(botSymbol) && field7.getText().equals(botSymbol) && field1.getText().equals("")) {
                botStep(0, botSymbol);
            } else if (field2.getText().equals(botSymbol) && field5.getText().equals(botSymbol) && field8.getText().equals("")) { //2 столбец
                botStep(7, botSymbol);
            } else if (field2.getText().equals(botSymbol) && field8.getText().equals(botSymbol) && field5.getText().equals("")) {
                botStep(4, botSymbol);
            } else if (field5.getText().equals(botSymbol) && field8.getText().equals(botSymbol) && field2.getText().equals("")) {
                botStep(1, botSymbol);
            } else if (field3.getText().equals(botSymbol) && field6.getText().equals(botSymbol) && field9.getText().equals("")) { //3 столбец
                botStep(8, botSymbol);
            } else if (field3.getText().equals(botSymbol) && field9.getText().equals(botSymbol) && field6.getText().equals("")) {
                botStep(5, botSymbol);
            } else if (field6.getText().equals(botSymbol) && field9.getText().equals(botSymbol) && field3.getText().equals("")) {
                botStep(2, botSymbol);
            } else if (field1.getText().equals(botSymbol) && field2.getText().equals(botSymbol) && field3.getText().equals("")) { //1 строка
                botStep(2, botSymbol);
            } else if (field1.getText().equals(botSymbol) && field3.getText().equals(botSymbol) && field2.getText().equals("")) {
                botStep(1, botSymbol);
            } else if (field2.getText().equals(botSymbol) && field3.getText().equals(botSymbol) && field1.getText().equals("")) {
                botStep(0, botSymbol);
            } else if (field4.getText().equals(botSymbol) && field5.getText().equals(botSymbol) && field6.getText().equals("")) { //2 строка
                botStep(5, botSymbol);
            } else if (field4.getText().equals(botSymbol) && field6.getText().equals(botSymbol) && field5.getText().equals("")) {
                botStep(4, botSymbol);
            } else if (field5.getText().equals(botSymbol) && field6.getText().equals(botSymbol) && field4.getText().equals("")) {
                botStep(3, botSymbol);
            } else if (field7.getText().equals(botSymbol) && field8.getText().equals(botSymbol) && field9.getText().equals("")) { //3 строка
                botStep(8, botSymbol);
            } else if (field7.getText().equals(botSymbol) && field9.getText().equals(botSymbol) && field8.getText().equals("")) {
                botStep(7, botSymbol);
            } else if (field8.getText().equals(botSymbol) && field9.getText().equals(botSymbol) && field7.getText().equals("")) {
                botStep(6, botSymbol);
            } else if (field1.getText().equals(botSymbol) && field5.getText().equals(botSymbol) && field9.getText().equals("")) { //левая диагональ
                botStep(8, botSymbol);
            } else if (field1.getText().equals(botSymbol) && field9.getText().equals(botSymbol) && field5.getText().equals("")) {
                botStep(4, botSymbol);
            } else if (field5.getText().equals(botSymbol) && field9.getText().equals(botSymbol) && field1.getText().equals("")) {
                botStep(0, botSymbol);
            } else if (field3.getText().equals(botSymbol) && field5.getText().equals(botSymbol) && field7.getText().equals("")) { //правая диагональ
                botStep(6, botSymbol);
            } else if (field3.getText().equals(botSymbol) && field7.getText().equals(botSymbol) && field5.getText().equals("")) {
                botStep(4, botSymbol);
            } else if (field5.getText().equals(botSymbol) && field7.getText().equals(botSymbol) && field3.getText().equals("")) {
                botStep(2, botSymbol);
            } else if (field1.getText().equals(userSymbol) && field4.getText().equals(userSymbol) && field7.getText().equals("")) { //1 столбец
                botStep(6, botSymbol);
            } else if (field1.getText().equals(userSymbol) && field7.getText().equals(userSymbol) && field4.getText().equals("")) {
                botStep(3, botSymbol);
            } else if (field4.getText().equals(userSymbol) && field7.getText().equals(userSymbol) && field1.getText().equals("")) {
                botStep(0, botSymbol);
            } else if (field2.getText().equals(userSymbol) && field5.getText().equals(userSymbol) && field8.getText().equals("")) { //2 столбец
                botStep(7, botSymbol);
            } else if (field2.getText().equals(userSymbol) && field8.getText().equals(userSymbol) && field5.getText().equals("")) {
                botStep(4, botSymbol);
            } else if (field5.getText().equals(userSymbol) && field8.getText().equals(userSymbol) && field2.getText().equals("")) {
                botStep(1, botSymbol);
            } else if (field3.getText().equals(userSymbol) && field6.getText().equals(userSymbol) && field9.getText().equals("")) { //3 столбец
                botStep(8, botSymbol);
            } else if (field3.getText().equals(userSymbol) && field9.getText().equals(userSymbol) && field6.getText().equals("")) {
                botStep(5, botSymbol);
            } else if (field6.getText().equals(userSymbol) && field9.getText().equals(userSymbol) && field3.getText().equals("")) {
                botStep(2, botSymbol);
            } else if (field1.getText().equals(userSymbol) && field2.getText().equals(userSymbol) && field3.getText().equals("")) { //1 строка
                botStep(2, botSymbol);
            } else if (field1.getText().equals(userSymbol) && field3.getText().equals(userSymbol) && field2.getText().equals("")) {
                botStep(1, botSymbol);
            } else if (field2.getText().equals(userSymbol) && field3.getText().equals(userSymbol) && field1.getText().equals("")) {
                botStep(0, botSymbol);
            } else if (field4.getText().equals(userSymbol) && field5.getText().equals(userSymbol) && field6.getText().equals("")) { //2 строка
                botStep(5, botSymbol);
            } else if (field4.getText().equals(userSymbol) && field6.getText().equals(userSymbol) && field5.getText().equals("")) {
                botStep(4, botSymbol);
            } else if (field5.getText().equals(userSymbol) && field6.getText().equals(userSymbol) && field4.getText().equals("")) {
                botStep(3, botSymbol);
            } else if (field7.getText().equals(userSymbol) && field8.getText().equals(userSymbol) && field9.getText().equals("")) { //3 строка
                botStep(8, botSymbol);
            } else if (field7.getText().equals(userSymbol) && field9.getText().equals(userSymbol) && field8.getText().equals("")) {
                botStep(7, botSymbol);
            } else if (field8.getText().equals(userSymbol) && field9.getText().equals(userSymbol) && field7.getText().equals("")) {
                botStep(6, botSymbol);
            } else if (field1.getText().equals(userSymbol) && field5.getText().equals(userSymbol) && field9.getText().equals("")) { //левая диагональ
                botStep(8, botSymbol);
            } else if (field1.getText().equals(userSymbol) && field9.getText().equals(userSymbol) && field5.getText().equals("")) {
                botStep(4, botSymbol);
            } else if (field5.getText().equals(userSymbol) && field9.getText().equals(userSymbol) && field1.getText().equals("")) {
                botStep(0, botSymbol);
            } else if (field3.getText().equals(userSymbol) && field5.getText().equals(userSymbol) && field7.getText().equals("")) { //правая диагональ
                botStep(6, botSymbol);
            } else if (field3.getText().equals(userSymbol) && field7.getText().equals(userSymbol) && field5.getText().equals("")) {
                botStep(4, botSymbol);
            } else if (field5.getText().equals(userSymbol) && field7.getText().equals(botSymbol) && field3.getText().equals("")) {
                botStep(2, botSymbol);
            } else {
                randomBotStep();
            }
            emptyFieldCount--;

            check_win();
            if (!thereIsAWinner) {
                cntOfMoves++;
            }
        } else {
            check_win();
        }
    }

    private void botStep(int num, String symb) {
        fields[num].setText(symb);
        fields[num].setDisable(true);
    }

    private void randomBotStep() {
        int num;
        do {
            num = (int) (Math.random() * 9);
        } while (fields[num].getText() != "");
        fields[num].setText(botSymbol);
        fields[num].setDisable(true);
    }

    private void check_win() {
        if (field1.getText().equals(botSymbol) && field4.getText().equals(botSymbol) && field7.getText().equals(botSymbol)) { //1 столбец
            thereIsAWinner = true;
            viewWinner(botSymbol);
        } else if (field2.getText().equals(botSymbol) && field5.getText().equals(botSymbol) && field8.getText().equals(botSymbol)) { //2 столбец
            thereIsAWinner = true;
            viewWinner(botSymbol);
        } else if (field3.getText().equals(botSymbol) && field6.getText().equals(botSymbol) && field9.getText().equals(botSymbol)) { //3 столбец
            thereIsAWinner = true;
            viewWinner(botSymbol);
        } else if (field1.getText().equals(botSymbol) && field2.getText().equals(botSymbol) && field3.getText().equals(botSymbol)) { //1 строка
            thereIsAWinner = true;
            viewWinner(botSymbol);
        } else if (field4.getText().equals(botSymbol) && field5.getText().equals(botSymbol) && field6.getText().equals(botSymbol)) { //2 строка
            thereIsAWinner = true;
            viewWinner(botSymbol);
        } else if (field7.getText().equals(botSymbol) && field8.getText().equals(botSymbol) && field9.getText().equals(botSymbol)) { //3 строка
            thereIsAWinner = true;
            viewWinner(botSymbol);
        } else if (field1.getText().equals(botSymbol) && field5.getText().equals(botSymbol) && field9.getText().equals(botSymbol)) { //левая диагональ
            thereIsAWinner = true;
            viewWinner(botSymbol);
        } else if (field3.getText().equals(botSymbol) && field5.getText().equals(botSymbol) && field7.getText().equals(botSymbol)) { //правая диагональ
            thereIsAWinner = true;
            viewWinner(botSymbol);
        } else if (field1.getText().equals(userSymbol) && field4.getText().equals(userSymbol) && field7.getText().equals(userSymbol)) { //1 столбец
            thereIsAWinner = true;
            viewWinner(userSymbol);
        } else if (field2.getText().equals(userSymbol) && field5.getText().equals(userSymbol) && field8.getText().equals(userSymbol)) { //2 столбец
            thereIsAWinner = true;
            viewWinner(userSymbol);
        } else if (field3.getText().equals(userSymbol) && field6.getText().equals(userSymbol) && field9.getText().equals(userSymbol)) { //3 столбец
            thereIsAWinner = true;
            viewWinner(userSymbol);
        } else if (field1.getText().equals(userSymbol) && field2.getText().equals(userSymbol) && field3.getText().equals(userSymbol)) { //1 строка
            thereIsAWinner = true;
            viewWinner(userSymbol);
        } else if (field4.getText().equals(userSymbol) && field5.getText().equals(userSymbol) && field6.getText().equals(userSymbol)) { //2 строка
            thereIsAWinner = true;
            viewWinner(userSymbol);
        } else if (field7.getText().equals(userSymbol) && field8.getText().equals(userSymbol) && field9.getText().equals(userSymbol)) { //3 строка
            thereIsAWinner = true;
            viewWinner(userSymbol);
        } else if (field1.getText().equals(userSymbol) && field5.getText().equals(userSymbol) && field9.getText().equals(userSymbol)) { //левая диагональ
            thereIsAWinner = true;
            viewWinner(userSymbol);
        } else if (field3.getText().equals(userSymbol) && field5.getText().equals(userSymbol) && field7.getText().equals(userSymbol)) { //правая диагональ
            thereIsAWinner = true;
            viewWinner(userSymbol);
        } else if (emptyFieldCount == 0 && thereIsAWinner == false) {
            draw();
        }
    }

    private void draw() {
        for (var f : fields) {
            f.setDisable(true);
        }
        turnLabel.setText("Draw!");
        thereIsAWinner = true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fields = new Button[]{field1, field2, field3, field4, field5, field6, field7, field8, field9};
        tab_game.setDisable(true);
        check_bot.setSelected(true);
    }
}