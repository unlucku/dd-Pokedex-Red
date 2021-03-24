// --== CS400 File Header Information ==--
// Name: Brendan Boyle
// Email: bwboyle@wisc.edu
// Team: DD
// TA: Dan
// Lecturer: Gary

import java.util.List;

public interface BackendInterface {

	public Pokemon getID(int id);

	public List<Pokemon> getName(String name);
	
	public List<Pokemon> getGen(int gen);
	
	public List<Pokemon> getBST(int lower, int higher);

}

