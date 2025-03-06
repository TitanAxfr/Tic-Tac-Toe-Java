import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private Player player;

    public GameBoard() {
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setResizable(false);
        setLayout(new GridLayout(3, 3));

        player = new Player();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (!clickedButton.getText().equals("")) return;

        clickedButton.setText(String.valueOf(player.getCurrentPlayer()));

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "Player " + player.getCurrentPlayer() + " Won");
            resetBoard();
        } else if (isFull()) {
            JOptionPane.showMessageDialog(this, "It's a tie");
            resetBoard();
        } else {
            player.switchPlayer();
        }
    }

    private boolean checkWin() {
        char current = player.getCurrentPlayer();

        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(current)) &&
                    buttons[i][1].getText().equals(String.valueOf(current)) &&
                    buttons[i][2].getText().equals(String.valueOf(current))) {
                highlightWinningButtons(buttons[i][0], buttons[i][1], buttons[i][2]);
                return true;
            }

            if (buttons[0][i].getText().equals(String.valueOf(current)) &&
                    buttons[1][i].getText().equals(String.valueOf(current)) &&
                    buttons[2][i].getText().equals(String.valueOf(current))) {
                highlightWinningButtons(buttons[0][i], buttons[1][i], buttons[2][i]);
                return true;
            }
        }

        if (buttons[0][0].getText().equals(String.valueOf(current)) &&
                buttons[1][1].getText().equals(String.valueOf(current)) &&
                buttons[2][2].getText().equals(String.valueOf(current))) {
            highlightWinningButtons(buttons[0][0], buttons[1][1], buttons[2][2]);
            return true;
        }

        if (buttons[2][0].getText().equals(String.valueOf(current)) &&
                buttons[1][1].getText().equals(String.valueOf(current)) &&
                buttons[0][2].getText().equals(String.valueOf(current))) {
            highlightWinningButtons(buttons[2][0], buttons[1][1], buttons[0][2]);
            return true;
        }

        return false;
    }

    private boolean isFull() {
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                if (button.getText().equals("")) return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setText("");
                button.setBackground(getBackground());
            }
        }
        player.resetPlayer();
    }

    private void highlightWinningButtons(JButton... buttons) {
        for (JButton button : buttons) {
            button.setBackground(Color.YELLOW);
        }
    }
}

