
//--== CS400 File Header Information ==--
//Name: Robbie Peissig
//Email: rpeissig@wisc.edu
//Team: Blue
//Role: Front end developer 
//Lecurer: Gary Dahl

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;

/**
 * This class contains tests for the front end of the second Project.
 */
public class TestFrontend {

	/**
	 * 
	 * This method tests if pressing x at any point within the program will
	 * effectively exit the current screen or close the program appropriately
	 * 
	 * 
	 */

	@Test
	public void testCloseProgram() {

		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			// set the input stream to our input (with an x to test of the program exists)
			String input = "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			// instantiate when front end is implemented
			Object frontend = new Frontend();
			// creates a new frontend object that adds the first 3 pokemon in the pokedex to
			// backend
			((Frontend) frontend).run(new Backend(new StringReader(
					"#,Name,Type1,Type2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary\n"
							+ "1,Bulbasaur,Grass,Poision,318,45,49,49,65,65,45,1,FALSE\n"
							+ "2,Ivysaur,Grass,Poision,405,60,62,63,80,80,60,1,FALSE\n"
							+ "3,Venusaur,Grass,Poison,525,80,82,83,100,100,80,1,FALSE\n")));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			boolean tester = false;
			if (frontend != null) {

				tester = true;

			}
			assertEquals(tester, true);

		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * This method tests if pressing A to add a pokemon works correctly, prompts the
	 * correct information, and the pokemon is added to the RBT with the correct
	 * information
	 * 
	 * 
	 */

	@Test
	public void testFindID() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			String input = "f" + System.lineSeparator() + "i" + System.lineSeparator() + 1 + System.lineSeparator()
					+ "x" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			Object frontend = new Frontend();
			// creates a new frontend object that adds the first 3 pokemon in the pokedex to
			// backend
			((Frontend) frontend).run(new Backend(new StringReader(
					"#,Name,Type1,Type2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary\n"
							+ "1,Bulbasaur,Grass,Poision,318,45,49,49,65,65,45,1,FALSE\n"
							+ "2,Ivysaur,Grass,Poision,405,60,62,63,80,80,60,1,FALSE\n"
							+ "3,Venusaur,Grass,Poison,525,80,82,83,100,100,80,1,FALSE\n")));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			String appOutput = outputStreamCaptor.toString();
			boolean tester = false;
			if (frontend != null && appOutput.contains("Bulbasaur")) {

				tester = true;

			}
			assertEquals(tester, true);

		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * This method tests if pressing F to find a pokemon works correctly when having
	 * the CP only
	 * 
	 * 
	 */
	@Test
	public void testFindStats() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			String input = "f" + System.lineSeparator() + "s" + System.lineSeparator() + 405 + System.lineSeparator()
					+ "x" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			Object frontend = new Frontend();
			// creates a new frontend object that adds the first 3 pokemon in the pokedex to
			// backend
			((Frontend) frontend).run(new Backend(new StringReader(
					"#,Name,Type1,Type2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary\n"
							+ "1,Bulbasaur,Grass,Poision,318,45,49,49,65,65,45,1,FALSE\n"
							+ "2,Ivysaur,Grass,Poision,405,60,62,63,80,80,60,1,FALSE\n"
							+ "3,Venusaur,Grass,Poison,525,80,82,83,100,100,80,1,FALSE\n")));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			String appOutput = outputStreamCaptor.toString();
			boolean tester = false;
			if (frontend != null && appOutput.contains("Ivysaur")) {

				tester = true;

			}
			assertEquals(tester, true);

		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * This method tests if pressing F to find a pokemon works correctly when having
	 * the name only
	 * 
	 * 
	 */
	@Test
	public void testFindName() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			String input = "f" + System.lineSeparator() + "n" + System.lineSeparator() + "Pidgey"
					+ System.lineSeparator() + "x" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			Object frontend = new Frontend();
			// creates a new frontend object that adds the first 3 pokemon in the pokedex to
			// backend
			((Frontend) frontend).run(new Backend(new StringReader(
					"#,Name,Type1,Type2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary\n"
							+ "1,Bulbasaur,Grass,Poision,318,45,49,49,65,65,45,1,FALSE\n"
							+ "2,Ivysaur,Grass,Poision,405,60,62,63,80,80,60,1,FALSE\n"
							+ "3,Venusaur,Grass,Poison,525,80,82,83,100,100,80,1,FALSE\n")));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			String appOutput = outputStreamCaptor.toString();
			boolean tester = false;
			if (frontend != null && appOutput.contains("Pidgey")) {

				tester = true;

			}
			assertEquals(tester, true);

		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * This method tests the BST search by range method
	 * 
	 *
	 */
	@Test
	public void testBSTRange() {
		PrintStream standardOut = System.out;
		InputStream standardIn = System.in;
		try {
			String input = "r" + System.lineSeparator() + "c" + System.lineSeparator() + 200 + System.lineSeparator()
					+ 450 + System.lineSeparator() + "x" + System.lineSeparator() + "x";
			InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
			System.setIn(inputStreamSimulator);
			ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
			// set the output to the stream captor to read the output of the front end
			System.setOut(new PrintStream(outputStreamCaptor));
			Object frontend = new Frontend();
			// creates a new frontend object that adds the first 3 pokemon in the pokedex to
			// backend
			((Frontend) frontend).run(new Backend(new StringReader(
					"#,Name,Type1,Type2,Total,HP,Attack,Defense,Sp. Atk,Sp. Def,Speed,Generation,Legendary\n"
							+ "1,Bulbasaur,Grass,Poision,318,45,49,49,65,65,45,1,FALSE\n"
							+ "2,Ivysaur,Grass,Poision,405,60,62,63,80,80,60,1,FALSE\n"
							+ "3,Venusaur,Grass,Poison,525,80,82,83,100,100,80,1,FALSE\n")));
			// set the output back to standard out for running the test
			System.setOut(standardOut);
			// same for standard in
			System.setIn(standardIn);
			String appOutput = outputStreamCaptor.toString();
			boolean tester = false;
			if (frontend != null && appOutput.contains("Pidgey")) {

				tester = true;

			}
			assertEquals(tester, true);

		} catch (Exception e) {
			// make sure stdin and stdout are set correctly after we get exception in test
			System.setOut(standardOut);
			System.setIn(standardIn);
			e.printStackTrace();

		}
	}
}
