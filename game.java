package gameing1;



	import java.awt.Font;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JOptionPane;

	public class game extends JFrame implements ActionListener { //implements means used as interface
		// jframe is java class
		private int board = 3;
		private boolean crosscheck = true;
		private JButton[][] buttons = new JButton[board][board];//to store

		private enum gamestatus {
			xwon, owon, tie, incomplete,
		}

		public game() {
			super.setTitle("tic tac toe"); // to st tittle
			super.setSize(800, 800); //to set size 
			GridLayout layout = new GridLayout(board, board);//to make cross  check design
			super.setLayout(layout); 
			Font font = new Font("comic sans ms", 1, 250);// to make font t 
			for (int row = 0; row < board; row++) {
				for (int col = 0; col < board; col++) {
					JButton btn = new JButton();
					btn.setFont(font);
					btn.addActionListener(this); // reaction on console
					buttons[row][col] = btn;
					super.add(btn);
				}
			}
			super.setResizable(false); // size of game screen not change
			super.setVisible(true); // to visible the screen 
			

		}

		@Override
		public void actionPerformed (ActionEvent e) {
			// TODO Auto-generated method stub
			JButton c = (JButton) e.getSource();
			makemove(c);
			gamestatus gs = getgamestatus();
			if (gs == gamestatus.incomplete) {
				return;
			}
			declarewinner(gs);
			int choice = JOptionPane.showConfirmDialog(this, "Restart?");
			if (choice == JOptionPane.YES_OPTION) {
				for (int row = 0; row < board; row++) {
					for (int col = 0; col < board; col++) {
						buttons[row][col].setText("");// to to  blank after restart
					}
				}
				this.crosscheck = true;
			} else {
				super.dispose(); // to close the game on no 
			}
		}

		private void makemove(JButton c) {
			String text = c.getText();
			if (text.length() > 0) {
				JOptionPane.showMessageDialog(this, "invalid move");
				return;
			}
			if (crosscheck) {
				c.setText("x");

			} else
				c.setText("o");
			crosscheck = !crosscheck;
		}

		private gamestatus getgamestatus() {
			String text1 = "";
			String text2 = "";
			String text3 = "";
			for (int row = 0; row < board; row++) {
				
				//row
				text1 = buttons[row][0].getText();
				text2 = buttons[row][1].getText();
				text3 = buttons[row][2].getText();
	// condition of winning
				if (text1.equals(text2) && text2.equals(text3) && text1.length() != 0) {
					if (text1.equals("x")) {
						return gamestatus.xwon;
					} else {
						return gamestatus.owon;
					}
				}
			} 
			//col
			for (int col = 0; col < board; col++) {
				text1 = buttons[0][col].getText();
				text2 = buttons[1][col].getText();
				text3 = buttons[2][col].getText();
				if (text1.equals(text2) && text2.equals(text3) && text1.length() != 0) {
					if (text1.equals("x")) {
						return gamestatus.xwon;
					} else {
						return gamestatus.owon;
					}
				}
			}
			// check diagonal 1
			text1 = buttons[0][0].getText();
			text2 = buttons[1][1].getText();
			text3 = buttons[2][2].getText();
			if (text1.equals(text2) && text2.equals(text3) && text1.length() != 0) {
				if (text1.equals("x")) {
					return gamestatus.xwon;
				} else {
					return gamestatus.owon;
				}
			}
			// check diag 2
			text1 = buttons[0][2].getText();
			text2 = buttons[1][1].getText();
			text3 = buttons[2][1].getText();
			if (text1.equals(text2) && text2.equals(text3) && text1.length() != 0) {
				if (text1.equals("x")) {
					return gamestatus.xwon;
				} else {
					return gamestatus.owon;
				}
			}

			// incomplt
			for (int row = 0; row < buttons.length; row++) {
				for (int col = 0; col < buttons[row].length; col++) {
					if (buttons[row][col].getText().length() == 0) {
						return gamestatus.incomplete;
					}
				}
			}
			return gamestatus.tie;

		}

		private void declarewinner(gamestatus gs) {
			if (gs == gamestatus.xwon)
				JOptionPane.showMessageDialog(this, "xwon");
			else if (gs == gamestatus.owon)
				JOptionPane.showMessageDialog(this, "owon");
			else
				JOptionPane.showMessageDialog(this, "tie");
		}
	}


