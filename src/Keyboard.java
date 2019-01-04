import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * A class to manage how suggested words are generated. The keyboard name as a
 * class doesn't actually help at all. It's better described as a word
 * generating function. Throws a FileNotFoundException in the case that the file
 * used as the dictionary cannot be found.
 * 
 * @author KK
 *
 */
public class Keyboard {

	ArrayList<Neighbor> perm;
	HashSet<String> dictionary;
	HashSet<String> suggested;
	String currentWord;

	/**
	 * The only constructor that is meant to be used. Sets up all of the appropriate
	 * resources necessary to generate other possible words. Also sets up a
	 * dictionary for future use.
	 * 
	 * @param input The word that should be prepared to find other words that may or
	 *              may not be a match.
	 * @throws FileNotFoundException In case that someone tampers with the file that
	 *                               is meant to be the dictionary.
	 */
	public Keyboard(String input) throws FileNotFoundException {
		currentWord = input;
		perm = new ArrayList<Neighbor>();
		dictionary = new HashSet<String>();
		suggested = new HashSet<String>();
		for (char a : input.toLowerCase().toCharArray()) {
			perm.add(getNeighbor(a));
		}
		File f = new File("dictionary.txt");
		Scanner read = new Scanner(f);
		while (read.hasNext()) {
			dictionary.add(read.nextLine());
		}

	}

	/**
	 * A public method that is meant to generate all possible words. Will return the
	 * best possible recommendation as a String.
	 * 
	 * @return A valid word in the dictionary that is meant to represent the best
	 *         possible match to what the user may have meant.
	 */
	public String generateWords() {
		generateWords(perm, "");
		return recommend();
	}

	private void generateWords(ArrayList<Neighbor> spot, String soFar) {
		if (spot.isEmpty()) {
			if (dictionary.contains(soFar)) {
				suggested.add(soFar);
			}
		} else {
			Neighbor temp = spot.remove(0);
			spot.add(0, temp);
			ArrayList<Character> currentLetter = temp.getReplace();
			for (char current : currentLetter) {
				spot.remove(0);
				generateWords(spot, soFar + current);
				spot.add(0, temp);

			}
		}
	}

	/**
	 * A private method. Finds and returns a String that is the best match for what
	 * the word might be. If the input word is already a valid word, it will just
	 * return the input word. If not, it determines what the best match is by
	 * checking for the number of characters that have to be changed in order to
	 * create a valid word.
	 * 
	 * @return The closest valid word, determined by the number of characters that
	 *         need to be changed from the input word.
	 */
	private String recommend() {
		int different = currentWord.length();
		String toReturn = null;
		int temp = 0;
		if (dictionary.contains(currentWord)) {
			return currentWord;
		} else {
			for (String suggestion : suggested) {
				for (int i = 0; i < suggestion.length(); i++) {
					if (suggestion.charAt(i) != currentWord.charAt(i)) {
						temp = temp + 1;
					}

				}
				if (temp < different) {
					toReturn = suggestion;
					different = temp;
				}
				temp = 0;
			}
			return toReturn;

		}
	}

	/**
	 * Used to set up Neighbor classes that have the appropriate "neighbor"
	 * characters, which are characters that are around each letter in a QUERTY
	 * keyboard.
	 * 
	 * @param ind The letter being evaluated.
	 * @return A neighbor class that represents all of the other letters the user
	 *         may have pressed.
	 */
	private Neighbor getNeighbor(Character ind) {
		Neighbor toReturn = null;

		switch (ind) {
		case 'q':
			char[] nearQ = { 'q', 'w', 's' };
			toReturn = new Neighbor(nearQ);
			break;

		case 'a':
			char[] nearA = { 'q', 'w', 's', 'x', 'a' };
			toReturn = new Neighbor(nearA);
			break;

		case 'z':
			char[] nearZ = { 's', 'x', 'a' };
			toReturn = new Neighbor(nearZ);
			break;

		case 'w':
			char[] nearW = { 'q', 'w', 's', 'd', 'a', 'e' };
			toReturn = new Neighbor(nearW);
			break;

		case 's':
			char[] nearS = { 'q', 'w', 'e', 's', 'z', 'x', 'c' };
			toReturn = new Neighbor(nearS);
			break;

		case 'x':
			char[] nearX = { 'a', 's', 'd', 'z', 'x', 'd', 'c' };
			toReturn = new Neighbor(nearX);
			break;

		case 'e':
			char[] nearE = { 'w', 'e', 'r', 's', 'd', 'f' };
			toReturn = new Neighbor(nearE);
			break;

		case 'd':
			char[] nearD = { 'w', 'e', 'r', 's', 'd', 'f', 'x', 'c' };
			toReturn = new Neighbor(nearD);
			break;

		case 'c':
			char[] nearC = { 's', 'd', 'f', 'x', 'c', 'v' };
			toReturn = new Neighbor(nearC);
			break;

		case 'r':
			char[] nearR = { 'e', 'r', 't', 'd', 'f', 'g' };
			toReturn = new Neighbor(nearR);
			break;

		case 'f':
			char[] nearF = { 'e', 'r', 't', 'd', 'f', 'g', 'c', 'v', 'b' };
			toReturn = new Neighbor(nearF);
			break;

		case 'v':
			char[] nearV = { 'd', 'f', 'g', 'c', 'v', 'b' };
			toReturn = new Neighbor(nearV);
			break;

		case 't':
			char[] nearT = { 'r', 't', 'y', 'f', 'g', 'h' };
			toReturn = new Neighbor(nearT);
			break;

		case 'g':
			char[] nearG = { 'r', 't', 'y', 'f', 'g', 'h', 'v', 'b' };
			toReturn = new Neighbor(nearG);
			break;

		case 'b':
			char[] nearB = { 'f', 'g', 'h', 'v', 'b', 'n' };
			toReturn = new Neighbor(nearB);
			break;

		case 'y':
			char[] nearY = { 't', 'y', 'u', 'g', 'h', 'j' };
			toReturn = new Neighbor(nearY);
			break;

		case 'h':
			char[] nearH = { 't', 'y', 'u', 'g', 'h', 'j', 'b', 'n' };
			toReturn = new Neighbor(nearH);
			break;

		case 'n':
			char[] nearN = { 'g', 'h', 'j', 'b', 'n', 'm' };
			toReturn = new Neighbor(nearN);
			break;

		case 'u':
			char[] nearU = { 'y', 'u', 'i', 'h', 'j', 'k' };
			toReturn = new Neighbor(nearU);
			break;

		case 'j':
			char[] nearJ = { 'y', 'u', 'i', 'h', 'j', 'k', 'n', 'm' };
			toReturn = new Neighbor(nearJ);
			break;

		case 'm':
			char[] nearM = { 'j', 'k', 'l', 'n', 'm' };
			toReturn = new Neighbor(nearM);
			break;

		case 'i':
			char[] nearI = { 'u', 'i', 'o', 'j', 'k', 'l' };
			toReturn = new Neighbor(nearI);
			break;

		case 'k':
			char[] nearK = { 'u', 'i', 'o', 'j', 'k', 'l', 'm' };
			toReturn = new Neighbor(nearK);
			break;

		case 'o':
			char[] nearO = { 'i', 'o', 'p', 'k', 'l' };
			toReturn = new Neighbor(nearO);
			break;

		case 'l':
			char[] nearL = { 'i', 'o', 'p', 'k', 'l' };
			toReturn = new Neighbor(nearL);
			break;

		case 'p':
			char[] nearP = { 'o', 'p', 'l' };
			toReturn = new Neighbor(nearP);
			break;

		}
		return toReturn;

	}

}
