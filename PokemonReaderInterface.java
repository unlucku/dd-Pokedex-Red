import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.zip.DataFormatException;

// --== CS400 File Header Information ==--
// Author: Michael Corbishley
// Email: mcorbishley@wisc.edu
// Notes: This interface is part of the starter archive for Project One
//        in spring 2021.
public interface PokemonReaderInterface {

	public List<Pokemon> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException;

}