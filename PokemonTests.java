// --== CS400 File Header Information ==--
// Name: Michael Corbishley
// Email: mcorbishley@wisc.edu
// Team:  Red Team
// Group: DD
// TA: dkiel2@wisc.edu
// Lecturer: Florian
// Notes to Grader: N/A
import static org.junit.Assert.*;

import java.awt.List;

import org.junit.jupiter.api.Test;

public class PokemonTests {
	
	
	@Test public void testGetType() {
		Pokemon Bulbasaur = new Pokemon(1,"Bulbasaur","Grass","Poison",318,45,49,49,65,65,45,1,false);
		Pokemon Mew = new Pokemon(151,"Mew","Psychic","",600,100,100,100,100,100,100,1,true);
		if(!Bulbasaur.getType1().equals("Grass")||!Bulbasaur.getType2().equals("Poison")||!Mew.getType1().equals("Psychic")||!Mew.getType2().equals(""))
			fail("Test not yet implemented");
}
	
	@Test public void testGetGeneration() {
		Pokemon Bulbasaur = new Pokemon(1,"Bulbasaur","Grass","Poison",318,45,49,49,65,65,45,1,false);
		if(Bulbasaur.getGeneration()!=1)
			fail("Test not yet implemented");
}

	@Test public void testGetSpecialAttack() {
		Pokemon Bulbasaur = new Pokemon(1,"Bulbasaur","Grass","Poison",318,45,49,49,65,65,45,1,false);
		if(Bulbasaur.getSpAttack()!=65)
			fail("Test not yet implemented");
}

	@Test public void testGetAttack() {
		Pokemon Bulbasaur = new Pokemon(1,"Bulbasaur","Grass","Poison",318,45,49,49,65,65,45,1,false);
		if(Bulbasaur.getAttack()!=49)
			fail("Test not yet implemented");
}

	@Test public void testGetSpeed() {
		Pokemon Bulbasaur = new Pokemon(1,"Bulbasaur","Grass","Poison",318,45,49,49,65,65,45,1,false);
		if(Bulbasaur.getSpeed()!=45)
			fail("Test not yet implemented");
}
	
	@Test public void testIsLegend() {
		Pokemon Bulbasaur = new Pokemon(1,"Bulbasaur","Grass","Poison",318,45,49,49,65,65,45,1,false);
		Pokemon Mew = new Pokemon(151,"Mew","Psychic","",600,100,100,100,100,100,100,1,true);
		if(Bulbasaur.isLegengary()||!Mew.isLegengary())
			fail("Test not yet implemented");
}
}
