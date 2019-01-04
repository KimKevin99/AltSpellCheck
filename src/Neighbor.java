import java.util.ArrayList;

public class Neighbor {
	private ArrayList<Character> replace;

	public Neighbor(char[] a) {
		replace = new ArrayList<Character>();

		for (char ind : a) {
			replace.add((Character) ind);
		}

	}

	public ArrayList<Character> getReplace() {
		return replace;
	}

	public void setReplace(ArrayList<Character> replace) {
		this.replace = replace;
	}

}
