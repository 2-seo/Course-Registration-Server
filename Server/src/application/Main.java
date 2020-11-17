package application;

import view.LoginDialog;

public class Main {

	private LoginDialog loginDialog;
	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.loginDialog = new LoginDialog();
		main.loginDialog.setVisible(true);
		
	}
	
}
