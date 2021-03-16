// --== CS400 File Header Information ==--
// Name: Michael Corbishley
// Email: mcorbishley@wisc.edu
// Team:  Red Team
// Group: DD
// TA: dkiel2@wisc.edu
// Lecturer: Florian
// Notes to Grader: N/A
import java.util.List;

public class Pokemon implements PokemonInterface{
	// #,Name,Type 1,Type 2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary
	private int dexNum;
	private String name;
	private String type1;
	private String type2;
	private int totalStats;
	private int hp;
	private int attack;
	private int defense;
	private int spAttack;
	private int spDefense;
	private int speed;
	private int generation;
	private boolean isLegendary;
	
	public Pokemon(int id, String name, String type1, String type2, int totalStats, int hp, int attack, int defense, int spAttack, int spDefense, int speed1, int generationA, boolean isLegend) {
		this.dexNum=id;
		this.name=name;
		this.type1=type1;
		this.type2=type2;
		this.totalStats=totalStats;
		this.hp=hp;
		this.attack=attack;
		this.defense=defense;
		this.spAttack=spAttack;
		this.spDefense=spDefense;
		this.speed=speed1;
		this.generation=generationA;
		this.isLegendary=isLegend;
		
	}


	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getDexNum() {
		return this.dexNum;
	}

	@Override
	public int getAttack() {
		return this.attack;
	}

	@Override
	public int getDefense() {
		return this.defense;
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public String getType1() {
		return this.type1;
	}

	@Override
	public String getType2() {
		return this.type2;
	}

	@Override
	public int getSpAttack() {
		return this.spAttack;
	}

	@Override
	public int getSpDefense() {
		return this.spDefense;
	}

	@Override
	public int getGeneration() {
		return this.generation;
	}

	@Override
	public boolean isLegengary() {
		return this.isLegendary;
	}

	@Override
	public int getTotalStats() {
		return this.totalStats;
	}

	@Override
	public int getHP() {
		return this.hp;
	}
	
	@Override
	public String toString() {
		return this.name + "(" + this.getType1() + "," + this.getType2() + ")\n"
				+ "Total Stats: " +this.getTotalStats() +"\n"
				+ "Attack: " + this.getAttack() + ", Defense: " +this.getDefense() + ", Speed: " +this.getSpeed() +"\n"
				+ "Special Attack: " +this.getSpAttack()+", Special Defense: " +this.getSpDefense() +"\n" 
				+ "Generation: " +this.getGeneration() +", IsLegendary: " +this.isLegengary();
	}


	@Override
	public int compareTo(PokemonInterface a) {
		if(a==null || !a.getClass().equals(Pokemon.class))
			throw new IllegalArgumentException("Comparison object is either not a pokemon or null");
		if(this.getDexNum()>a.getDexNum())
			return 1;
		else if(this.getDexNum()<a.getDexNum())
			return -1;
		else 
			return 0;
	}

}
