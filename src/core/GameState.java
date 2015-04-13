package core;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.Semaphore;

import entity.DNDEntity;

public class GameState {
	//Everything you need to access globally, store it here 
	
	public LinkedHashMap<String, DNDEntity> races;
	public LinkedHashMap<String, DNDEntity> classes;
	public LinkedHashMap<String, DNDEntity> spells;
	public LinkedHashMap<String, DNDEntity> feats;
	public LinkedHashMap<String, DNDEntity> skills;
	public LinkedHashMap<String, DNDEntity> items;
	public LinkedHashMap<String, DNDEntity> weapons;
	public LinkedHashMap<String, DNDEntity> armor;
	public LinkedHashMap<String, DNDEntity> monsters;
	public LinkedHashMap<String, DNDEntity> traps;
	public static final File USERDATAFOLDER = new File(System.getProperty("user.dir") + "//" + "User Data");	
	
	// 0 = homeScreen
	// 1 = dungeonScreen
	// 2 = dungeonViewer
	public static int PAGE_NUMBER = -1;
	
	//When building custom content add it to this HashMap. Everything here will be saved to disk to a CustomContent.xml
	public LinkedHashMap<String, DNDEntity> customContent;
	
	public character currentlyLoadedCharacter;
	public String currCharFilePath;
	public boolean playerMode;
	
	public HashMap<String, DNDEntity> searchResults;
	public Semaphore searchResultsLock;
	
	
	//////////////// CONSTANTS /////////////////
	public final static int STRENGTH = 0;
	public final static int DEXTERITY = 1;
	public final static int CONSTITUTION = 2;
	public final static int INTELLIGENCE = 3;
	public final static int WISDOM = 4;
	public final static int CHARISMA = 5;
	public final static String[] abilityScoreTypes = {"STR", "DEX", "CON", "INT", "WIS", "CHA"};
	
	private final static int SIZE_FINE = 0;
	private final static int SIZE_DIMINUTIVE = 1;
	private final static int SIZE_TINY = 2;
	private final static int SIZE_SMALL = 3;
	private final static int SIZE_MEDIUM = 4;
	private final static int SIZE_LARGE = 5;
	private final static int SIZE_HUGE = 6;
	private final static int SIZE_GARGANTUAN = 7;
	private final static int SIZE_COLOSSAL = 8;
	public final static String[] sizeStrings = {"Fine", "Diminutive", "Tiny", "Small", "Medium", "Large", "Huge", "Gargantuan", "Colossal" };
	
	
	public GameState(){
		spells = new LinkedHashMap<String, DNDEntity>();
		feats = new LinkedHashMap<String, DNDEntity>();
		skills = new LinkedHashMap<String, DNDEntity>();
		races = new LinkedHashMap<String, DNDEntity>();
		classes = new LinkedHashMap<String, DNDEntity>();
		items = new LinkedHashMap<String, DNDEntity>();
		weapons = new LinkedHashMap<String, DNDEntity>();
		armor = new LinkedHashMap<String, DNDEntity>();
		monsters = new LinkedHashMap<String, DNDEntity>();
		traps = new LinkedHashMap<String, DNDEntity>();
		searchResultsLock = new Semaphore(1);
		searchResults = new HashMap<String, DNDEntity>();
		USERDATAFOLDER.mkdir();
	}
	
	public void saveCustomContent(){
		
	}
	
	public boolean search(String searchString){
		SearchThread st1 = new SearchThread("Spells");
		SearchThread st2 = new SearchThread("Feats");
		SearchThread st3 = new SearchThread("Skills");
		SearchThread st4 = new SearchThread("Classes");
		SearchThread st5 = new SearchThread("Races");
		
		st1.start(this.spells, searchString);
		st2.start(this.feats, searchString);
		st3.start(this.skills, searchString);
		st4.start(this.classes, searchString);
		st5.start(this.races, searchString);
		
		try {
			st1.getSearchThread().join();
			st2.getSearchThread().join();
			st3.getSearchThread().join();
			st4.getSearchThread().join();
			st5.getSearchThread().join();
		} catch (InterruptedException e) {
			System.out.println("Error joining threads!");
			return false;
		}
		System.out.println("All threads joined. Ending search!");
		return true;
	}

}
