// --== CS400 File Header Information ==--
// Name: Michael Corbishley
// Email: mcorbishley@wisc.edu
// Team:  Red Team
// Group: DD
// TA: dkiel2@wisc.edu
// Lecturer: Florian
// Notes to Grader: N/A
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class PokemonReader implements PokemonReaderInterface{

	@Override
	public List<Pokemon> readDataSet(Reader inputFileReader)
			throws FileNotFoundException, IOException, DataFormatException {
		List<Pokemon> Pokedex = new ArrayList<Pokemon>();
		String[] text = readInputReader(inputFileReader).split("\n");
		int cutoff = text[0].split(",").length;
		for (int i = 1; i < text.length; i++) {
			ArrayList<String> info = parsePokemon(text[i]);
			if (info.size() != cutoff) {
				throw new DataFormatException("Invalid Number of Columns");
			}

			
			//Pokedex.add(new Pokemon((int)info.get(0),(String)info.get(1),(String)info.get(2),(String)info.get(3),(int)info.get(4),(int)info.get(5),(int)info.get(6),(int)info.get(7),(int)info.get(8),(int)info.get(9),(int)info.get(10),(int)info.get(11),(boolean)info.get(12)));
			
			Pokedex.add(new Pokemon(
				Integer.parseInt(info.get(0)),
				info.get(1),
				info.get(2),
				info.get(3),
				Integer.parseInt(info.get(4)),
				Integer.parseInt(info.get(5)),
				Integer.parseInt(info.get(6)),
				Integer.parseInt(info.get(7)),
				Integer.parseInt(info.get(8)),
				Integer.parseInt(info.get(9)),
				Integer.parseInt(info.get(10)),
				Integer.parseInt(info.get(11)),
				Boolean.valueOf(info.get(12))
			));

		}
		return Pokedex;
	}
	
	public String readInputReader(Reader r) throws IOException {
		StringBuffer toReturn = new StringBuffer();
		int z = 0;
		while ((z = r.read()) != -1) {
			toReturn.append((char) z);
		}
		return toReturn.toString();
	}

	public ArrayList<String> parsePokemon(String e) {
		Object[] text = e.split(",");
		ArrayList<String> toReturn = new ArrayList<String>();
		for(int i = 0; i < text.length; i++) {
			toReturn.add(String.valueOf(text[i]));
		}
		return toReturn;
	}
}
