// --== CS400 File Header Information ==--
// Name: Brendan Boyle
// Email: bwboyle@wisc.edu
// Team: DD
// TA: Dan
// Lecturer: Gary

import java.util.List;

public interface BackendInterface {
    
    public List<Pokemon> getPokemonInPokedex(int start);

    public void addPokemon(String name);

    public Pokemon findPokemonByName(String name);

    public List<Pokemon> findPokemonByCP(int cp);

}
