// --== CS400 File Header Information ==--
// Name: Brendan Boyle
// Email: bwboyle@wisc.edu
// Team: DD
// TA: Dan
// Lecturer: Gary

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Child class that adds additional functionality to the Red Black Tree
 */
class RBTExtension extends RedBlackTree<Pokemon> {
	private RedBlackTree<Pokemon> tree; // the Red Black Tree this child class will interact with

	/**
	 * Constructor that takes a Red Black Tree as a parameter
	 *
	 * @param tree the Red Black Tree this child class will interact with
	 */
	public RBTExtension(RedBlackTree<Pokemon> tree) {
		this.tree = tree;
	}

	/**
	 * Retrieves Pokemon with the given ID
	 *
	 * @param id the ID of the Pokemon to be retrieved
	 * @return the Pokemon who's ID matches the one specified
	 */
	public Pokemon getPokemonById(int id) {

		Node<Pokemon> current = tree.root;

		// Searches tree for Pokemon with given ID
		while (current != null) {
			// Current node ID < given ID --> search right subtree
			if (current.data.getDexNum() < id) current = current.rightChild;

			// Current node ID > given ID --> search left subtree
			else if (current.data.getDexNum() > id) current = current.leftChild;

			// Pokemon with given ID is found
			else return current.data;

		}
		// No Pokemon found -> return null
		return null;
	}

	/**
	 * Retrieves Pokemon with the given name
	 *
	 * @param name the name of the Pokemon to be retrieved
	 * @return the Pokemon whos name matches the given name. If no such Pokemon is found, return null
	 */
	public List<Pokemon> getPokemonByName(String name) {
		// List of Pokemon to return
		List<Pokemon> pokemons = new ArrayList<>();

		Iterator<Pokemon> treeItr = tree.iterator();

		// Adds Pokemon to List whos name equals the given name
		while (treeItr.hasNext()) {
			Pokemon p = treeItr.next();
			if (p.getName().toLowerCase().contains(name.toLowerCase())) {
				pokemons.add(p);
			}
		}

		// If no Pokemon with the given name is found
		if (pokemons.isEmpty()) return null;

		return pokemons;
	}


	/**
	 * Retrieves Pokemon with the given generation
	 *
	 * @param int the generation of the Pokemon to be retrieved
	 * @return the Pokemon whos name matches the given generation. If no such Pokemon is found, return null
	 */
	public List<Pokemon> getPokemonByGeneration(int gen) {
		// List of Pokemon to return
		List<Pokemon> pokemons = new ArrayList<>();

		Iterator<Pokemon> treeItr = tree.iterator();

		// Adds Pokemon whos gen is less than or equal to the given number
		while (treeItr.hasNext()) {
			Pokemon p = treeItr.next();
			if (p.getGeneration() == gen) pokemons.add(p);
		}

		// If no Pokemon with the given gen is found
		if (pokemons.isEmpty()) return null;

		return pokemons;
	}

	/**
	 * Retrieves all Pokemon in tree whos total stats are in the bounds specified
	 *
	 * @param low the lower bound of stats
	 * @param high the upper bound of stats
	 * @return List of Pokemon whos Total Stats are in the bounds specified
	 */
	public List<Pokemon> getPokemonInStatRange(int low, int high) {

		List<Pokemon> pokemons = new ArrayList<>();

		// Tree iterator
		Iterator<Pokemon> treeItr = tree.iterator();

		// Adds remaining Pokemon whos stats are less than or equal to the given number
		while (treeItr.hasNext()) {
			Pokemon p = treeItr.next();
			if (p.getTotalStats() >= low && p.getTotalStats() <= high) pokemons.add(p);
		}

		// If no Pokemon were added, null is returned
		if (pokemons.isEmpty()) return null;

		return pokemons;

	}


}

/**
 * Backend class for the Pokedex project
 */
public class Backend implements BackendInterface {
	private RedBlackTree<Pokemon> tree; // Red Black Tree where Pokemon in the Pokedex will be stored

	/**
	 * Uses PokemonReader class to add Pokemon from CSV file to a List
	 *
	 * @param filePath the path to the CSV file
	 */
	public Backend(String filePath) {
		// Initialize tree instance field
		tree = new RedBlackTree<>();

		try {
			// Pokemon data from the given CSV file
			File pokemonData = new File(filePath);
			FileReader f = new FileReader(pokemonData);

			// Add Pokemon data to the tree
			for (Pokemon p : new PokemonReader().readDataSet(f)) {
				try {
					tree.insert(p); // Adds the Pokemon
				}
				// Do not add Pokemon with duplicate IDs
				catch (IllegalArgumentException e) {
					continue;
				}
			}

		} catch(IOException e) {
			System.out.println("Error Importing file"); // Error importing the file

		} catch(DataFormatException e) {
			System.out.println("Invalid format for file"); // Data format is not valid

		}
	}

	/**
	 * Constructor that takes StringReader as argument (to be used in test cases)
	 *
	 * @param s StringReader containing String of Pokemon data
	 */
	public Backend(Reader s) {
		// Initialize tree instance field
		tree = new RedBlackTree<>();

		try {
			// Add all Pokemon from String of Pokemon to the tree
			for (Pokemon p : new PokemonReader().readDataSet(s)) {
				try {
					tree.insert(p); // Adds the pokemon
				}

				catch (IllegalArgumentException e) { // Do not add Pokemon with duplicate IDs
					continue;
				}
			}
		}

		catch (IOException | DataFormatException e) { // Error in StringReader data
			System.out.println("ERROR: Could not parse data.");
		}

	}

	/**
	 * Gets the total pokemon in the tree
	 * @return total pokemon
	 */
	public int getTotalPokemon() {
		return tree.size();
	}

	/**
	 * Gets a pokemon based on an id parameter
	 * @return pokemon corresponding to the id
	 */
	public Pokemon getID(int id) {
		return new RBTExtension(tree).getPokemonById(id);
	}

	/**
	 * Gets a pokemon based on a name parameter
	 * @return pokemon corresponding to the name
	 */
	public List<Pokemon> getName(String name) {
		return new RBTExtension(tree).getPokemonByName(name);
	}

	/**
	 * Gets a pokemon based on a generation parameter
	 * @return pokemon corresponding to the generation
	 */
	public List<Pokemon> getGen(int genNum) {
		return new RBTExtension(tree).getPokemonByGeneration(genNum);
	}

	/**
	 * Gets a pokemon based on a stat range
	 * @return pokemon corresponding to the stat range
	 */
	public List<Pokemon> getBST(int lower, int higher) {
		return new RBTExtension(tree).getPokemonInStatRange(lower, higher);
	}



}
