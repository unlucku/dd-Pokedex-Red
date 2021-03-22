// --== CS400 File Header Information ==--
// Name: Brendan Boyle
// Email: bwboyle@wisc.edu
// Team: DD
// TA: Dan
// Lecturer: Gary

import java.util.List;

public interface BackendInterface {
	/**
	 * Uses the given ID to find a Pokemon that has a matching ID.
	 * @param id the id that we are comparing.
	 * @return the Pokemon with the matching ID or null if it doesn't.
	 */
	public Pokemon getID(int id);

    /**
	 * 
	 * @param name
	 * @return
	 */
	public List<Pokemon> getName(String name);
	
	/**
	 * 
	 * @param gen
	 * @return
	 */
	public List<Pokemon> getGen(int gen);
	
	/**
	 * 
	 * @param bst
	 * @return
	 */
	public List<Pokemon> getBST(int bst);
	
	
}

