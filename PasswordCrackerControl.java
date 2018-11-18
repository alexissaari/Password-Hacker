import java.util.Arrays;
import java.util.Random;

//http://prembharticodes.blogspot.com/2011/10/brute-force-algorithm-for-password.html

public class PasswordCrackerControl {
	public static void main(String[] args) {
		String password = randomPassword(4);
		char[] charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		PasswordCrackerControl pcm = new PasswordCrackerControl(charset, 1);
		
		Long startTime = System.currentTimeMillis();
		String attempt = pcm.toString();
		while (true) {
			if (attempt.equals(password)) {
				Long endTime = System.currentTimeMillis();
				System.out.println("Password Found: " + attempt);
				System.out.println("Time to search took: " + ((endTime - startTime) / 1000.00) + " seconds");
				break;
			}
			attempt = pcm.toString();
			System.out.println("" + attempt);
			pcm.increment();
		}
	}
	
	public static String randomPassword(int length) {
		char[] charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		String string = "";
		for(int i = 0; i < length; i++) {
			int rnd = new Random().nextInt(charset.length);
			string += charset[rnd];
		}
		
		return string;
	}

	private char[] cs; // Character Set
	private char[] cg; // Current Guess

	public PasswordCrackerControl(char[] characterSet, int guessLength) {
		cs = characterSet;
		cg = new char[guessLength];
		Arrays.fill(cg, cs[0]);
	}

	public void increment() {
		int index = cg.length - 1;
		while (index >= 0) {
			if (cg[index] == cs[cs.length - 1]) {
				if (index == 0) {
					cg = new char[cg.length + 1];
					Arrays.fill(cg, cs[0]);
					break;
				} else {
					cg[index] = cs[0];
					index--;
				}
			} else {
				cg[index] = cs[Arrays.binarySearch(cs, cg[index]) + 1];
				break;
			}
		}
	}

	@Override
	public String toString() {
		return String.valueOf(cg);
	}
}
