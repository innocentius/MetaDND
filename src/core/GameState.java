package core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;

import core.GameState.PAGE;
import entity.*;

public class GameState {
	// Everything you need to access globally, store it here

	public LinkedHashMap<String, DNDEntity> races;
	public LinkedHashMap<String, DNDEntity> classes;
	public LinkedHashMap<String, DNDEntity> spells;
	public LinkedHashMap<String, DNDEntity> feats;
	public LinkedHashMap<String, DNDEntity> skills;
	public LinkedHashMap<String, DNDEntity> items;
	public LinkedHashMap<String, DNDEntity> abilities;
	public LinkedHashMap<String, DNDEntity> weapons;
	public LinkedHashMap<String, DNDEntity> armor;
	public LinkedHashMap<String, DNDEntity> monsters;
	public LinkedHashMap<String, DNDEntity> traps;
	public LinkedHashMap<String, DNDEntity> deities;
	public static File USERDATAFOLDER; 
	public static File IMAGESFOLDER;

	public static ArrayList<String> windowsOpen = new ArrayList<String>();

	// When building custom content add it to this HashMap. Everything here will
	// be saved to disk to a CustomContent.xml
	public LinkedHashMap<String, DNDEntity> customContent;

	public character currentlyLoadedCharacter;
	public String currCharFilePath;
	public boolean playerMode;

	public SortedMap<String, DNDEntity> searchResults;
	public Semaphore searchResultsLock;

	public static enum PAGE {
		HomeScreen, 
		DungeonMasterScreen, 
		PlayerScreen, 
		DungeonGenerationConfigScreen,
		DungeonViewerScreen,
		CharacterSheetScreen
	}
	
	public static PAGE currentPage;
	
	// ////////////// CONSTANTS /////////////////
	public final static int STRENGTH = 0;
	public final static int DEXTERITY = 1;
	public final static int CONSTITUTION = 2;
	public final static int INTELLIGENCE = 3;
	public final static int WISDOM = 4;
	public final static int CHARISMA = 5;
	public final static String[] abilityScoreTypes = { "STR", "DEX", "CON",
			"INT", "WIS", "CHA" };

	public final static int DEFAULT_WIDTH = 900;
	public final static int DEFAULT_HEIGHT = 700;
	public final static int CHARWIZ_WIDTH = 700;
	public final static int CHARWIZ_HEIGHT = 500;
	
	private final static int SIZE_FINE = 0;
	private final static int SIZE_DIMINUTIVE = 1;
	private final static int SIZE_TINY = 2;
	private final static int SIZE_SMALL = 3;
	private final static int SIZE_MEDIUM = 4;
	private final static int SIZE_LARGE = 5;
	private final static int SIZE_HUGE = 6;
	private final static int SIZE_GARGANTUAN = 7;
	private final static int SIZE_COLOSSAL = 8;
	public final static String[] sizeStrings = { "Fine", "Diminutive", "Tiny",
			"Small", "Medium", "Large", "Huge", "Gargantuan", "Colossal" };
	public final static int[] acAttackSizeMods = { 8, 4, 2, 1, 0, -1, -2, -4,
			-8 };
	public final static int[] grappleSizeMods = { -16, -12, -8, -4, 0, 4, 8,
			12, 16 };
	public final static int[] hideSizeMods = { 16, 12, 8, 4, 0, -4, -8, -12,
			-16 };
	public final static double[] carrySizeMod = { 0.125, 0.25, 0.5, 0.75, 1, 2,
			4, 8, 16 };

	public static String[] schoolsOfMagic = {"Abjuration", "Conjuration", "Divination", "Enchantment", "Evocation", "Illusion", "Necromancy", "Transmutation"};
	
	public SortedMap<String, String> languages;

	public GameState() {
		
		USERDATAFOLDER = new File(System.getProperty("user.dir") + "//User Data");
		IMAGESFOLDER = new File(System.getProperty("user.dir") + "//..//Images");
		
		xmlLoader xmls = new xmlLoader("xmlLoadThread");
		spells = new LinkedHashMap<String, DNDEntity>();
		feats = new LinkedHashMap<String, DNDEntity>();
		skills = new LinkedHashMap<String, DNDEntity>();
		races = new LinkedHashMap<String, DNDEntity>();
		classes = new LinkedHashMap<String, DNDEntity>();
		items = new LinkedHashMap<String, DNDEntity>();
		abilities = new LinkedHashMap<String, DNDEntity>();
		weapons = new LinkedHashMap<String, DNDEntity>();
		armor = new LinkedHashMap<String, DNDEntity>();
		monsters = new LinkedHashMap<String, DNDEntity>();
		traps = new LinkedHashMap<String, DNDEntity>();
		deities = new LinkedHashMap<String, DNDEntity>();
		customContent = new LinkedHashMap<String, DNDEntity>();
		searchResultsLock = new Semaphore(1);
		searchResults = new TreeMap<String, DNDEntity>();
		languages = new TreeMap<String, String>();
		xmls.start();
		USERDATAFOLDER.mkdir();
	}

	public void saveCustomContent() {
		FileWriter fw;
		try {
			fw = new FileWriter(new File(".//XML/CustomContent.xml"));

			fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			fw.write("<CUSTOM>\n");
			for (Map.Entry<String, DNDEntity> entry : Main.gameState.customContent.entrySet()) {
				fw.write(entry.getValue().saveCustomContent());
			}
			fw.write("</CUSTOM>\n");
			fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static boolean isWindowOpen(String wizard) {
		return windowsOpen.contains(wizard);
	}

	public boolean search(String searchString) {

		this.searchResults.clear();

		SearchThread st1 = new SearchThread("Spells");
		SearchThread st2 = new SearchThread("Feats");
		SearchThread st3 = new SearchThread("Skills");
		SearchThread st4 = new SearchThread("Classes");
		SearchThread st5 = new SearchThread("Races");
		SearchThread st6 = new SearchThread("Deities");
		SearchThread st7 = new SearchThread("Traps");
		SearchThread st8 = new SearchThread("Monsters");
		SearchThread st9 = new SearchThread("Items");
		SearchThread st10 = new SearchThread("Weapons");
		SearchThread st11 = new SearchThread("Armor");
		SearchThread st12 = new SearchThread("Abilities");

		st1.start(this.spells, searchString);
		st2.start(this.feats, searchString);
		st3.start(this.skills, searchString);
		st4.start(this.classes, searchString);
		st5.start(this.races, searchString);
		st6.start(this.deities, searchString);
		st7.start(this.traps, searchString);
		st8.start(this.monsters, searchString);
		st9.start(this.items, searchString);
		st10.start(this.weapons, searchString);
		st11.start(this.armor, searchString);
		st12.start(this.abilities, searchString);

		try {
			st1.getSearchThread().join();
			st2.getSearchThread().join();
			st3.getSearchThread().join();
			st4.getSearchThread().join();
			st5.getSearchThread().join();
			st6.getSearchThread().join();
			st7.getSearchThread().join();
			st8.getSearchThread().join();
			st9.getSearchThread().join();
			st10.getSearchThread().join();
			st11.getSearchThread().join();
			st12.getSearchThread().join();
		} catch (InterruptedException e) {
			System.out.println("Error joining threads!");
			return false;
		}
		// System.out.println("All threads joined. Ending search!");
		return true;
	}

}
