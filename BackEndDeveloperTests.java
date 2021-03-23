// --== CS400 File Header Information ==--
// Name: Brendan Boyle
// Email: bwboyle@wisc.edu
// Team: DD
// TA: Dan
// Lecturer: Gary

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.StringReader;

public class BackEndDeveloperTests {

	/**
	 * Test for adding Pokemon to the tree when the Backend is created
	 */
	@Test
	public void testBackendNumberOfPokemon() {
		// Initialize Backend
		Backend b = new Backend(new StringReader(
			"#,Name,Type 1,Type 2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary\n"
			+ "1,Bulbasaur,Grass,Poison,318,45,49,49,65,65,45,1,False\n"
			+ "2,Ivysaur,Grass,Poison,405,60,62,63,80,80,60,1,False\n"
			+ "3,Venusaur,Grass,Poison,525,80,82,83,100,100,80,1,False\n"
			+ "4,Charmander,Fire,,309,39,52,43,60,50,65,1,False\n"
			+ "5,Charmeleon,Fire,,405,58,64,58,80,65,80,1,False\n"
		));
		
		// Case 1: Backend with 5 Pokemon in String data
		assertEquals(5, b.getTotalPokemon());
		
		// Case 2: Backend with empty String data
		Backend empty = new Backend(new StringReader(""));
		assertEquals(0, empty.getTotalPokemon());
	}
	
	/**
	 * Test for finding Pokemon by name
	 */
	@Test
	public void testFindPokemonByName() {
		// Initialize Backend
		Backend b = new Backend(new StringReader(
			"#,Name,Type 1,Type 2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary\n"
			+ "1,Bulbasaur,Grass,Poison,318,45,49,49,65,65,45,1,False\n"
			+ "2,Ivysaur,Grass,Poison,405,60,62,63,80,80,60,1,False\n"
			+ "3,Venusaur,Grass,Poison,525,80,82,83,100,100,80,1,False\n"
			+ "4,Charmander,Fire,,309,39,52,43,60,50,65,1,False\n"
			+ "5,Charmeleon,Fire,,405,58,64,58,80,65,80,1,False\n"
		));
		
		// Case 1: Invalid name
		assertEquals(null, b.getName("Joe"));
		assertEquals(null, b.getName("Pikachu"));
		
		
		// Case 2: Pokemon in tree
		assertEquals("Bulbasaur", b.getName("Bulbasaur").get(0).getName());
		
	}
	
	/**
	 * Test for finding Pokemon by ID
	 */
	@Test
	public void testFindPokemonById() {
		// Initialize Backend
		Backend b = new Backend(new StringReader(
			"#,Name,Type 1,Type 2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary\n"
			+ "1,Bulbasaur,Grass,Poison,318,45,49,49,65,65,45,1,False\n"
			+ "2,Ivysaur,Grass,Poison,405,60,62,63,80,80,60,1,False\n"
			+ "3,Venusaur,Grass,Poison,525,80,82,83,100,100,80,1,False\n"
			+ "4,Charmander,Fire,,309,39,52,43,60,50,65,1,False\n"
			+ "5,Charmeleon,Fire,,405,58,64,58,80,65,80,1,False\n"
		));
		
		// Case 1: Invalid ID
		assertEquals(null, b.getID(900));
		
		// Case 2: Get Pokemon in tree from ID
		assertEquals("Venusaur", b.getID(3).getName());
	}
	
	/**
	 * Test for finding Pokemon by total stat
	 */
	@Test
	public void testFindPokemonByStat() {
		// Initialize Backend
		Backend b = new Backend(new StringReader(
			"#,Name,Type 1,Type 2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary\n"
			+ "1,Bulbasaur,Grass,Poison,318,45,49,49,65,65,45,1,False\n"
			+ "2,Ivysaur,Grass,Poison,405,60,62,63,80,80,60,1,False\n"
			+ "3,Venusaur,Grass,Poison,525,80,82,83,100,100,80,1,False\n"
			+ "4,Charmander,Fire,,309,39,52,43,60,50,65,1,False\n"
			+ "5,Charmeleon,Fire,,405,58,64,58,80,65,80,1,False\n"
		));
		
		// Case 1: No results
		assertEquals(null, b.getBST(10));
		
		// Case 2: Half the Pokemon returned
		assertEquals(2, b.getBST(320).size());
		
		// Case 3: All Pokemon returned
		assertEquals(5, b.getBST(550).size());
	}
	
	/**
	 * Test for finding Pokemon by gen number
	 */
	@Test
	public void testFindPokemonByGen() {
		// Initialize Backend
		Backend b = new Backend(new StringReader(
			"#,Name,Type 1,Type 2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary\n"
			+ "1,Bulbasaur,Grass,Poison,318,45,49,49,65,65,45,1,False\n"
			+ "2,Ivysaur,Grass,Poison,405,60,62,63,80,80,60,1,False\n"
			+ "3,Venusaur,Grass,Poison,525,80,82,83,100,100,80,1,False\n"
			+ "4,Charmander,Fire,,309,39,52,43,60,50,65,1,False\n"
			+ "5,Charmeleon,Fire,,405,58,64,58,80,65,80,1,False\n"
		));
		
		// Case 1: Invalid generation number
		assertEquals(null, b.getGen(900));
		
		// Case 2: Search for Gen 1. Returns all 5 Pokemon
		assertEquals(5, b.getGen(1).size());
	}
	
}
