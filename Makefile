run: compile
	java -cp . Frontend

compile: Frontend.class

Frontend.class: Frontend.java BackEndDeveloperTests.class FrontendTests.class PokemonTests.class Backend.class BackendInterface.class Frontend.class Pokemon.class PokemonInterface.class PokemonReader.class PokemonReaderInterface.class RedBlackTree.class SortedCollectionInterface.class
	javac -cp .:junit5.jar Frontend.java
	
BackEndDeveloperTests.class: BackEndDeveloperTests.java
	javac -cp .:junit5.jar BackEndDeveloperTests.java
	
FrontendTests.class: FrontendTests.java
	javac -cp .:junit5.jar FrontendTests.java
	
PokemonTests.class: PokemonTests.java
	javac -cp .:junit5.jar PokemonTests.java
	
Backend.class: Backend.java
	javac Backend.java
	
BackendInterface.class: BackendInterface.java
	javac BackendInterface.java
	
Frontend.class: Frontend.java
	javac Frontend.java
	
Pokemon.class: Pokemon.java
	javac Pokemon.java

PokemonInterface.class: PokemonInterface.java
	javac PokemonInterface.java
	
PokemonReader.class: PokemonReader.java
	javac PokemonReader.java
	
PokemonReaderInterface.class: PokemonReaderInterface.java
	javac PokemonReaderInterface.java
	
RedBlackTree.class: RedBlackTree.java
	javac RedBlackTree.java
	
SortedCollectionInterface.class: SortedCollectionInterface.java
	javac SortedCollectionInterface.java
	
test: testData testBackend testFrontend

testFrontend: FrontendTests.class
	java -jar junit5.jar -cp . --scan-classpath -n FrontendTests

testBackend: BackEndDeveloperTests.class
	java -jar junit5.jar -cp . --scan-classpath -n BackEndDeveloperTests

testData: PokemonTests.class
	java -jar junit5.jar -cp . --scan-classpath -n PokemonTests

clean:
	$(RM) *.class
