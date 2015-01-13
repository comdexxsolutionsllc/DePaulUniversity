package project.main;

import project.view.UI;

public class Main {
	private Main() {}
	public static void main(String[] args) {
		UI ui;
		ui = new project.view.TextUI();
		Control control = new Control(ui);
		control.run();
	}
}
