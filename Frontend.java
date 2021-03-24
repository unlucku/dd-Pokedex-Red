// --== CS400 File Header Information ==--
// Name: Robbie Peisig
// Email: rpeissig@wisc.edu
// Team: DD Blue
// Role: Frontend Developer
// TA: Daniel Kiel
// Lecturer: Gary Dahl
// Notes To Grader: This was submitted with team red, but this was written by team blue as
// our frontend developer dropped the course.

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Frontend {
	public Scanner in = new Scanner(System.in); // Scanner to get inputs.

	/**
	 * Runs the entire program.
	 *
	 * @param args the csv file path.
	 * @throws IOException         if something goes wrong when reading the file
	 * @throws DataFormatException if the data isn't formatted properly
	 */
	public static void main(String[] args) throws IOException, DataFormatException {
		Frontend frontend = new Frontend();
		if (args.length != 0) {
			Backend backend = new Backend(args[0]);
			frontend.run(backend);
		}
		else {
			Backend backend = new Backend("pokemon.csv");
			frontend.run(backend);
		}
	}

	/**
	 * Starts the frontend
	 * @param Backend
	 */
	public void run(Backend Back) {
		System.out.println("Welcome to the Pokedex! Are you looking for pokemon? Press f to find them!");
		System.out.println("(Or press x to quit if you are lame)");
		String input = in.nextLine();
		while (!input.equalsIgnoreCase("x")) {
			if (input.equalsIgnoreCase("f")) {

				findPokemon(Back);

			}

		}
		System.out.println("Thanks for checking out the Pokedex, see you next time!");

	}

	/**
	 * Shows the results of the search
	 * @param foundPokemon matching the search
	 */
	public void updateDisplay(Pokemon foundPokemon)
	{
		System.out.println("");
		System.out.println("============/============");
		System.out.println("After looking through inputted information, here are your results:\n");
		if(foundPokemon != null)
		{
			System.out.println(foundPokemon.toString());
		}
		else
		{
			System.out.println("There are no pokemon with the information you gave.");
		}
		System.out.println("============/============");
		System.out.println("How would you like to find your pokemon?");
		System.out.println("\t'n' - find by name");
		System.out.println("\t's' - find by total stats");
		System.out.println("\t'g' - find by generation");
		System.out.println("\t'i' - find by ID number");
		System.out.println("Press x to return to home page.");
	}

	/**
	 * Shows the results of the search
	 * @param list of foundPokemon matching the search
	 */
	public void updateDisplayArr(List<Pokemon> foundPokemon)
	{
		System.out.println("");
		System.out.println("============/============");
		System.out.println("After looking through inputted information, here are your results:\n");

		if(foundPokemon != null)
		{
			for(int i = 0; i<foundPokemon.size(); i++) {
				System.out.println(foundPokemon.get(i).toString());
				System.out.println();

			}

		}
		else
		{
			System.out.println("There are no pokemon with the information you gave.");
		}
		System.out.println("============/============");
		System.out.println("How would you like to find your pokemon?");
		System.out.println("\t'n' - find by name");
		System.out.println("\t's' - find by total stats");
		System.out.println("\t'g' - find by generation");
		System.out.println("\t'i' - find by ID number");
		System.out.println("Press x to return to home page.");
	}
	/**
	 * Prompt to start a search for a pokemon
	 * @param Backend
	 */
	private void findPokemon(Backend back)
	{
		System.out.println("How would you like to find your pokemon?");
		System.out.println("\t'n' - find by name");
		System.out.println("\t's' - find by total stats");
		System.out.println("\t'g' - find by generation");
		System.out.println("\t'i' - find by ID number");
		System.out.println("Press x to quit the app.");

		String input = in.nextLine();
		while(!input.equalsIgnoreCase("x"))
		{
			switch(input)
			{
				case "n":
				{
					System.out.println("Type name you are searching for: (Press enter when done)");
					String name = in.nextLine();
					updateDisplayArr(back.getName(name));
					break;
				}
				case "s":
				{
					System.out.println("Type stat number you are searching for: (Press enter when done)");
					int num = Integer.parseInt(in.nextLine());
					updateDisplayArr(back.getBST(num));
					break;
				}
				case "g":
				{
					System.out.println("Type generation of pokemon you are searching for: (Press enter when done)");
					int num = Integer.parseInt(in.nextLine());
					updateDisplayArr(back.getGen(num));
					break;
				}
				case "i":
				{
					System.out.println("Type ID number you are searching for: (Press enter when done)");
					int num = in.nextInt();
					updateDisplay(back.getID(num));
					break;
				}
				case "x":
				{
					System.out.println("Exiting...");
					System.exit(0);
				}
				default:
				{
					System.out.println("That is not a valid input, please select one of the inputs above.");
					break;
				}
			}
			input = in.nextLine();
		}
		System.out.println("Returning to home.");

	}
}
