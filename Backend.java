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
    private RedBlackTree<Pokemon> tree;

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
        
        // Searches tree
        while (current != null) {
            if (current.data.getDexNum() < id) current = current.rightChild;
            
            else if (current.data.getDexNum() > id) current = current.leftChild;
            
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

        List<Pokemon> pokemons = new ArrayList<>();

        Iterator<Pokemon> treeItr = tree.iterator();

        // If first Pokemon's name matches given name, return that Pokemon
        if (treeItr.hasNext() && treeItr.next().getName().contains(name))
			pokemons.add(treeItr.next());
        
        // In-order node traversal. Returns a Pokemon if its name matches the given name
		while (treeItr.hasNext()) {
            if (treeItr.next().getName().contains(name))
                pokemons.add(treeItr.next());
		}

        // If no Pokemon were added, null is returned
        if (pokemons.isEmpty()) return null;

        return pokemons;
    }

    public List<Pokemon> getPokemonByGeneration(int gen) {
        // List of Pokemon whos Generation is less than or equal to the given number
        List<Pokemon> pokemons = new ArrayList<>();

        Iterator<Pokemon> treeItr = tree.iterator();

        // First item added is first Pokemon whos gen are less than or equal to the given number
        if (treeItr.hasNext() && treeItr.next().getGeneration() == gen)
			pokemons.add(tree.iterator().next());
        
        // Adds remaining Pokemon whos gen are less than or equal to the given number
		while (treeItr.hasNext()) {
            if (treeItr.next().getGeneration() == gen)
			    pokemons.add(treeItr.next());
		}

        // If no Pokemon were added, null is returned
        if (pokemons.isEmpty()) return null;

        return pokemons;
    }

    /**
     * Retrieves all Pokemon in tree whos total stats are lower than the number specified
     * 
     * @param high
     * @return List of Pokemon whos Total Stats are lower than number specified
     */
    public List<Pokemon> getPokemonInStatRange(int high) {

        // List of Pokemon whos Total Stats are less than or equal to the given number
        List<Pokemon> pokemons = new ArrayList<>();

        Iterator<Pokemon> treeItr = tree.iterator();

        // First item added is first Pokemon whos stats are less than or equal to the given number
        if (treeItr.hasNext() && treeItr.next().getTotalStats() <= high)
			pokemons.add(treeItr.next());
        
        // Adds remaining Pokemon whos stats are less than or equal to the given number
		while (treeItr.hasNext()) {
            if (treeItr.next().getTotalStats() <= high)
			    pokemons.add(treeItr.next());
		}

        // If no Pokemon were added, null is returned
        if (pokemons.isEmpty()) return null;

        return pokemons;

    }

    
}

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

    public int getTotalPokemon() {
        return tree.size();
    }

    public Pokemon getID(int id) {

        return new RBTExtension(tree).getPokemonById(id);
    }

    public List<Pokemon> getName(String name) {

        return new RBTExtension(tree).getPokemonByName(name);
    }

    public List<Pokemon> getGen(int genNum) {
        return new RBTExtension(tree).getPokemonByGeneration(genNum);
    }

    public List<Pokemon> getBST(int stats) {
        return new RBTExtension(tree).getPokemonInStatRange(stats);
    }

}
