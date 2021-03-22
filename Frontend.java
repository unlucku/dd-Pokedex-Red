// --== CS400 File Header Information ==--
// Name: Robbie Peisig
// Email: rpeissig@wisc.edu
// Team: DD Blue
// Role: Frontend Developer
// TA: Daniel Kiel
// Lecturer: Gary Dahl

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.util.List;

public class Frontend {
	public Scanner in = new Scanner(System.in); // Scanner to get inputs.

	/**
	 * creates a new Frontend object
	 */
	public Frontend() {
		
	}

	/**
	 * Runs the entire program.
	 * 
	 * @param args the csv file path.
	 * @throws IOException         if something goes wrong when reading the file
	 * @throws DataFormatException if the data isn't formatted properly
	 */
	public static void main(String[] args) throws IOException, DataFormatException {
		Frontend frontend = new Frontend();
		BackendInterface Back = new Backend(args[0]);
		frontend.run(Back);
	}

	public void run(BackendInterface Back) {
		System.out.println("Welcome to the Pokedex! Are you looking for pokemon? Press f to find them!");
		System.out.println("(Or press x to quit if you are lame)");
		String input = in.next();
		while (!input.equalsIgnoreCase("x")) {
			if (input.equalsIgnoreCase("f")) {
				
				findPokemon(Back);

			}

		}
		System.out.println("Thanks for checking out the Pokedex, see you next time!");

	}
	
	public void updateDisplay(PokemonInterface foundPokemon)
	{
		System.out.println("");
		System.out.println("After looking through inputted information, here are your results:\n");
		if(foundPokemon != null)
		{
			System.out.println(foundPokemon.getName() + "(" + foundPokemon.getType1() + " Type): ");
			System.out.println("\tTotal Stat is " + foundPokemon.getTotalStats() + " and generation is " + foundPokemon.getGeneration() );
		}
		else
		{
			System.out.println("There are no pokemon with the information you gave.");
		}
		System.out.println("Please input another command.");
	}
	
	public void updateDisplayArr(List<PokemonInterface> foundPokemon)
	{
		System.out.println("");
		System.out.println("After looking through inputted information, here are your results:\n");
		
		if(foundPokemon != null)
		{
			for(int i = 0; i<foundPokemon.size(); i++) {
				System.out.println(foundPokemon.get(i).getName() + "(" + foundPokemon.get(i).getType1() + " Type): ");
				System.out.println("\tTotal Stat is " + foundPokemon.get(i).getTotalStats() + " and generation is " + foundPokemon.get(i).getGeneration() );
				
			}
			
		}
		else
		{
			System.out.println("There are no pokemon with the information you gave.");
		}
		System.out.println("Please input another command.");
	}
	
	private void findPokemon(BackendInterface back)
	{
		System.out.println("How would you like to find your pokemon?");
		System.out.println("\t'n' - find by name");
		System.out.println("\t's' - find by total stats");
		System.out.println("\t'g' - find by generation");
		System.out.println("\t'i' - find by ID number");
		System.out.println("");
		System.out.println("Press x to return to home page.");
		
		String input = in.next();
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
					int num = in.nextInt();
					updateDisplayArr(back.getBST(num));
					break;
				}
				case "g":
				{
					System.out.println("Type generation of pokemon you are searching for: (Press enter when done)");
					int num = in.nextInt();
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
				default:
				{
					System.out.println("That is not a valid input, please select one of the inputs above.");
					break;
				}
			}
			input = in.next();
		}
		System.out.println("Returning to home.");
	
	}
}
