// --== CS400 File Header Information ==--
// Name: Brendan Boyle
// Email: bwboyle@wisc.edu
// Team: DD
// TA: Dan
// Lecturer: Gary

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

public class Backend implements BackendInterface {
    private RedBlackTree<Pokemon> tree; // Red Black Tree where Pokemon in the Pokedex will be stored
    private List<Pokemon> allPokemon; // List of all Pokemon from the CSV file

    /**
     * Uses PokemonReader class to add Pokemon from CSV file to a List
     * 
     * @param args the path to the CSV file
     */
    public Backend(String[] args) {

        try {
            // Pokemon data from the given CSV file
            File pokemonData = new File(args[0]);
            FileReader f = new FileReader(pokemonData);

            // Adds Pokemon from file to a List
            allPokemon = new PokemonReader().readDataSet(f);
        } 


		catch(IOException e) {
			System.out.println("Error Importing file"); // Error importing the file
		}

		catch(DataFormatException e) {
			System.out.println("Invalid format for file"); // Data format is not valid
		}


        // Initialize remaining instance fields
        tree =  new RedBlackTree<>();

    }

    /**
     * Gets the Pokemon from the CSV data whose name matches the one specified
     * 
     * @param name the name of the Pokemon to be retrieved
     * @return
     */
    private Pokemon getPokemon(String name) {
        
        for (Pokemon p: allPokemon) {
            
            if (p.getName().equals(name)) return p;

        }

        throw new NoSuchElementException(name + " is not a valid Pokemon name.");

    }
    
    /**
     * Gets five Pokemon in the Pokedex, starting at
     * 
     * @param start 
     * @return
     */
    public List<Pokemon> getPokemonInPokedex(int start) {

        // List for all Pokemon in the tree
        List<Pokemon> pokemons = new ArrayList<Pokemon>();

        // If no items are in the tree, return an empty list
        if (tree.isEmpty()) return pokemons;
        
        // Adds all items in the tree to the List
        for (Iterator<Pokemon> i = tree.iterator(); i.hasNext();) {
            Pokemon p = i.next();
            pokemons.add(p); 
        }

        if (tree.size() < 5) return pokemons.subList(start, tree.size()-1);

        // Return the list, starting at index specified
        return pokemons.subList(start, start+4);

    }

    /**
     * Adds Pokemon to the tree
     * 
     * @param name the name of the Pokemon to be added
     */
    public void addPokemon(String name) {

        tree.insert(getPokemon(name)); // Adds Pokemon to tree
        System.out.println(name + " added sucessfully. ");

    }

    /**
     * Searches the tree for a Pokemon with the name specified
     * 
     * @param name the name of the Pokemon to be searched for
     * @return the Pokemon if it is found, null otherwise
     */
    public Pokemon findPokemonByName(String name) {

        Pokemon pokemon = getPokemon(name);

        if (tree.contains(pokemon)) {

            return pokemon;

        } 

        return null;

    }

    /**
     * Searches the tree for Pokemon whos total stats match the value specified
     * 
     * @param cp the total stats value of the Pokemon to be searched for
     * @return
     */
    public List<Pokemon> findPokemonByCP(int cp) {

        // List of Pokemon whos total stats match the specified value
        List<Pokemon> pokemons = new ArrayList<Pokemon>();

        for (Iterator<Pokemon> i = tree.iterator(); i.hasNext();) {
            Pokemon p = i.next();
            
            if (p.getTotalStats() == cp) pokemons.add(p);
        }

        return pokemons;

    }


    public static void main(String[] args) {
        Backend b = new Backend(args);

        b.addPokemon("Bulbasaur");

        System.out.println(b.findPokemonByName("Charmander"));
    }
    
}
