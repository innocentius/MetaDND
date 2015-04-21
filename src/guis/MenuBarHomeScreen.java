package guis;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;


public class MenuBarHomeScreen {
	
	private HomeWindow parent;
	
	public MenuBarHomeScreen(final Shell shell, HomeWindow parent)
	{
		this.parent = parent;
		Menu menuBar = new Menu(shell, SWT.BAR);
        MenuItem cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
        cascadeFileMenu.setText("&File");
        
        MenuItem cascadeToolsMenu = new MenuItem(menuBar, SWT.CASCADE);
        cascadeToolsMenu.setText("&Tools");
        
        
        Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
        cascadeFileMenu.setMenu(fileMenu);
        
        //Save
        MenuItem saveItem = new MenuItem(fileMenu, SWT.PUSH);
        saveItem.setText("&Save");
        

        saveItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	//TODO:
            	
            	}
        });
        
        //Save as
        MenuItem saveAsItem = new MenuItem(fileMenu, SWT.PUSH);
        saveAsItem.setText("&Save As...");
        

        saveAsItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	//TODO
            }
        });
        
        //New
        MenuItem newItem = new MenuItem(fileMenu, SWT.PUSH);
        newItem.setText("&New");
        

        newItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	//TODO
            }
        });
        
        //Open
        MenuItem openItem = new MenuItem(fileMenu, SWT.PUSH);
        openItem.setText("&Open");
        

        openItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	//TODO
            }
        });
        
        //Help
        MenuItem helpItem = new MenuItem(fileMenu, SWT.PUSH);
        helpItem.setText("&Help");
        

        helpItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	//TODO
            }
        });
        //Close
        MenuItem closeItem = new MenuItem(fileMenu, SWT.PUSH);
        closeItem.setText("&Close");
        

        closeItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	shell.dispose();
            }
        });
        

        //Exit
        MenuItem exitItem = new MenuItem(fileMenu, SWT.PUSH);
        exitItem.setText("&Exit");
        

        exitItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	shell.getDisplay().dispose();
                System.exit(0);
            }
        });
        
        
        //Tools Menu
        Menu toolsMenu = new Menu(shell, SWT.DROP_DOWN);
        cascadeToolsMenu.setMenu(toolsMenu);
        
      //Die Roller
        MenuItem dieRollerItem = new MenuItem(toolsMenu, SWT.PUSH);
        dieRollerItem.setText("&Die Roller");
        

        dieRollerItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	new DieWindow(shell.getDisplay());
            }
        });
        
        //Notepad
        MenuItem notepadItem = new MenuItem(toolsMenu, SWT.PUSH);
        notepadItem.setText("&Notepad");
        

        notepadItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	//TODO
            }
        });
        
        //Spell Wizard
        MenuItem spellWizardItem = new MenuItem(toolsMenu, SWT.PUSH);
        spellWizardItem.setText("&Spell Wizard");
        

        spellWizardItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	new Spell_wizard(shell.getDisplay());
            }
        });
        
        //Item Wizard
        MenuItem itemWizardItem = new MenuItem(toolsMenu, SWT.PUSH);
        itemWizardItem.setText("&Item Wizard");
        

        itemWizardItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	new Item_wizard(shell.getDisplay());
            }
        });
        
        //Ability Wizard
        MenuItem itemAbilityItem = new MenuItem(toolsMenu, SWT.PUSH);
        itemAbilityItem.setText("&Ability Wizard");
        
        itemAbilityItem.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent e)
        	{
        		new Ability_wizard(shell.getDisplay());
        	}
        });
        
        //Feat Wizard
        MenuItem itemFeatItem = new MenuItem(toolsMenu, SWT.PUSH);
        itemFeatItem.setText("&Feat Wizard");
        
        itemFeatItem.addSelectionListener(new SelectionAdapter(){
        	public void widgetSelected(SelectionEvent e)
        	{
        	    new Feat_wizard(shell.getDisplay());
        	}
        });
        //Character Generator
        MenuItem charGenItem = new MenuItem(toolsMenu, SWT.PUSH);
        charGenItem.setText("&Character Generator");

        charGenItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
        		new CharacterWizard(shell.getDisplay());
            }
        });
        
        //Dungeon Generator
        MenuItem dunGenItem = new MenuItem(toolsMenu, SWT.PUSH);
        dunGenItem.setText("&Dungeons");
        

        dunGenItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	parent.populateDungeonScreen();
                parent.navigateToDungeonScreen();
            }
        });
        //Character Sheet
        MenuItem dunChaItem = new MenuItem(toolsMenu, SWT.PUSH);
        dunChaItem.setText("&Characters");
        

        dunChaItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                parent.navigateToPlayerScreen();
            }
        });
        //Home Screen
        MenuItem dunHomeItem = new MenuItem(toolsMenu, SWT.PUSH);
        dunHomeItem.setText("&Home Screen");
        
        dunHomeItem.addSelectionListener(new SelectionAdapter()
        {
        public void widgetSelected(SelectionEvent e)
        {
        	parent.navigateToHomeScreen();
        }});
        shell.setMenuBar(menuBar);
	}
}
