package entity;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

/*
 * Generic entity class, extend this when creating searchable entities
 */
public abstract class DNDEntity {
	
	enum type{
		SPELL,
		FEAT,
		SKILL,
		ITEM,
		WEAPON,
		ARMOR,
		RACE,
		CLASS,
		MONSTER,
		TRAP,
		DUNGEON
	}
	
	type TYPE; //Enumerated type, must have to determine what type of entity this is
	String name;
	String description;
	LinkedHashMap<String, String> passedData; //Data passed in for entity constructor, make sure this isn't NULL otherwise tooltip windows won't work
	
	
	//TODO Replace void with actual window object
	public String getName(){
		return this.name;
	}
	public String getDescription(){
		return this.description;
	}
	
	public type getEntityType(){
		return this.TYPE;
	}
	
	public void toTooltipWindow(){
		
		
		Display display = new Display();
		Shell shell = new Shell(display);
		Monitor monitor = display.getPrimaryMonitor();
	    Rectangle bounds = monitor.getBounds();
	    
	    int WIDTH = 700;
		int HEIGHT = (int)(bounds.height * 2.0/3.0);
		
		ScrolledComposite sc = new ScrolledComposite(shell, SWT.V_SCROLL);
		sc.setBounds(0, 0, WIDTH - 20, HEIGHT - 50);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		
		Composite c = new Composite(sc, SWT.NONE);
		sc.setContent(c);
		c.setSize(c.computeSize(SWT.DEFAULT, SWT.DEFAULT));	
		GridLayout layout = new GridLayout(1, false);
		c.setLayout(layout);
		
		Font boldFont = new Font(display, new FontData( display.getSystemFont().getFontData()[0].getName(), 12, SWT.BOLD ));
		
		for (Map.Entry<String, String> entry : passedData.entrySet()){
			Label titleLabel = new Label(c, SWT.LEFT);
			titleLabel.setText(entry.getKey());
			titleLabel.setFont(boldFont);
			titleLabel.pack();
			Label textLabel = new Label(c, SWT.LEFT);
			//This guy finds a space every 120 characters and makes a new line, nice text formatting for the tooltip windows
			String parsedStr = entry.getValue().replaceAll("(.{120} )", "$1\n");
			parsedStr = parsedStr.replaceAll("\t", "");
			textLabel.setText(parsedStr);
			textLabel.pack();
		}
		
		int heightSum = 0;
		for(int i = 0; i < c.getChildren().length; i++){
			heightSum += c.getChildren()[i].getSize().y + layout.verticalSpacing;
		}	
		sc.setMinHeight(heightSum);
		c.pack();
	    shell.setLocation((int)(bounds.width * .75) - c.getSize().x / 2, (int)(bounds.height * .05));
		shell.pack();
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
