// --== CS400 File Header Information ==--
// Name: Michael Corbishley
// Email: mcorbishley@wisc.edu
// Team:  Red Team
// Group: DD
// TA: dkiel2@wisc.edu
// Lecturer: Florian
// Notes to Grader: N/A
import java.util.List;

public interface PokemonInterface extends Comparable<Pokemon> {
	// #,Name,Type 1,Type 2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary
	
	public String getName(); //returns the name of the pokemon
	public String getType1(); //returns the pokemons first type
	public String getType2(); //returns the pokemons second type
	public int getDexNum(); //returns pokemons number in the pokedex
	public int getTotalStats(); //returns the given pokemons total stats
	public int getHP(); //returns the given pokemons total hp
	public int getAttack(); //returns the pokemons attack stat
	public int getDefense(); //returns the pokemons defense stat
	public int getSpAttack(); //returns the pokemons special attack stat
	public int getSpDefense(); //returns the pokemons special defense stat
	public int getSpeed(); //returns the pokemons speed stat
	public int getGeneration(); //returns what generation the pokemon is from
	public boolean isLegengary(); //returns true is the pokemon is considered a legendary pokemon

}
