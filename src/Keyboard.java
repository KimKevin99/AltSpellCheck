import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Keyboard {

	ArrayList<Neighbor> perm;
	HashSet<String> dictionary;
	HashSet<String> suggested;
	String currentWord;

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

	public String recommend() {
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
