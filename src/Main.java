import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The main program. Doesn't really do all that much by itself, other than
 * prompting the user for a sentence and constructing a recommended sentence.
 * Throws a FilenotFoundException.
 * 
 * @author KK
 *
 */
public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Please enter a sentence!");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] sentence = input.split("\\s+");
		Keyboard keyboard;
		String[] suggestedSentence = new String[sentence.length];
		for (int i = 0; i < sentence.length; i++) {
			keyboard = new Keyboard(sentence[i]);
			String recommend = keyboard.generateWords();
			if (recommend != null) {
				suggestedSentence[i] = recommend;
			} else {
				suggestedSentence[i] = sentence[i];
			}
		}
		System.out.println("Did you mean: ");
		for (String word : suggestedSentence) {
			System.out.print(word + " ");
		}
	}

}
