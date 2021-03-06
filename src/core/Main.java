package core;
import guis.HomeWindow;
import guis.Startscreen;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.*;

public class Main {
	public static GameState gameState;
	public static HomeWindow homeWindow;
	public static Font boldFont; 

	public static void main(String[] args) {
		gameState = new GameState();
		
		Display display = new Display();
		boldFont = new Font(display, new FontData( display.getSystemFont().getFontData()[0].getName(), 12, SWT.BOLD ));

		homeWindow = new HomeWindow(display);
		
		display.dispose();
		Main.exitProgram();
	}
	
	public static void exitProgram(){
		Main.gameState.saveCustomContent();
		System.out.println("Exiting");
		System.exit(0);
	}

}
