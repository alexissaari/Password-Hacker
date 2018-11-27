import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Random;

//http://prembharticodes.blogspot.com/2011/10/brute-force-algorithm-for-password.html

public class PasswordCrackerMultiThread {

	static String[] passwords = { "rpCc", "5p8u", "nIzw", "Mq4A", "tZAx", "9Es0", "8eCo", "tzbs", "DVwq", "gUNo",
			"kxpq", "nYnH", "vwFb", "xAns", "yNfB", "HK3L", "oX5n", "uoBD", "H1xf", "NwJd", "DJLW", "qIOD", "4WT8",
			"nHXk", "AeQ4", "NDKL", "XhaX", "yFQ7", "Vdow", "r9py", "UOOI", "6Zue", "CE5c", "8WPr", "T1WY", "t1tO",
			"eCYH", "7KOB", "Zt7Q", "zftq", "tSK1", "jttJ", "Xng0", "2JW1", "re2U", "GkaR", "BpD9", "kjKM", "4b4T",
			"Nxtf" };

	public static void main(String[] args) {
		/*
		 * while (true) { if (attempt.equals(password)) { Long endTime =
		 * System.currentTimeMillis(); System.out.println("Password Found: " + attempt);
		 * System.out.println("Time to search took: " + ((endTime - startTime) /
		 * 1000.00) + " seconds"); break; } attempt = pcm.toString();
		 * System.out.println("" + attempt); pcm.increment(); }
		 */
		int ran = new Random().nextInt(passwords.length);
		String password = passwords[49];
		Long startTime = System.currentTimeMillis();
		for (int i = 1; i < 64; i++) {
			MultiThreadMulti object = new MultiThreadMulti("input" + i + ".txt", startTime);
			object.run();
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("Time to run all threads took: " + ((endTime - startTime) / 1000.00) + " seconds");
	}

	static class MultiThreadSingle extends Thread {

		String fileName = "C:\\Users\\alexis.saari\\Desktop\\Password-Hacker-master\\Password-Hacker-master\\inputs\\";
		String password;
		Long startTime;

		public MultiThreadSingle(String fileName, String password, Long startTime) {
			this.fileName += fileName;
			this.password = password;
			this.startTime = startTime;
		}

		public void run() {
			try {
				/*
				 * // Displaying the thread that is running System.out.println ("Thread " +
				 * Thread.currentThread().getId() + " is running");
				 */

				File multithreadFile = new File(fileName);

				BufferedReader br = new BufferedReader(new FileReader(multithreadFile));

				// char[] charset =
				// "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
				// PasswordCrackerMultiThread pcm = new PasswordCrackerMultiThread(charset, 1);
				String attempt;// pcm.toString();
				while ((attempt = br.readLine()) != null) {
					if (attempt.equals(password)) {
						Long endTime = System.currentTimeMillis();
						System.out.println("Password Found: " + attempt);
						System.out.println("Time to search took: " + ((endTime - startTime) / 1000.00) + " seconds");
						break;
					}
				}
			} catch (Exception e) {
				// Throwing an exception
				System.err.println(e.toString());
			}

		}

	}

	static class MultiThreadMulti extends Thread {

		String fileName = "C:\\Users\\alexis.saari\\Desktop\\Password-Hacker-master\\Password-Hacker-master\\inputs\\";
		Long startTime;

		public MultiThreadMulti(String fileName, Long startTime) {
			this.fileName += fileName;
			this.startTime = startTime;
		}

		public void run() {
			try {
				/*
				 * // Displaying the thread that is running System.out.println ("Thread " +
				 * Thread.currentThread().getId() + " is running");
				 */

				File multithreadFile = new File(fileName);

				BufferedReader br = new BufferedReader(new FileReader(multithreadFile));
				
				//char[] charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
				//PasswordCrackerMultiThread pcm = new PasswordCrackerMultiThread(charset, 1);
				String attempt;//pcm.toString();
				while ((attempt = br.readLine()) != null) {
					for(String password : passwords) {
						if (attempt.equals(password)) {
							Long endTime = System.currentTimeMillis();
							System.out.println("Password Found: " + attempt);
							System.out.println("Time to search took: " + ((endTime - startTime) / 1000.00) + " seconds");
							break;
						}
					}
				}
			} catch (Exception e) {
				// Throwing an exception
				System.err.println(e.toString());
			}

		}

	}

	/*
	 * public static String randomPassword(int length) { char[] charset =
	 * "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray(
	 * ); String string = ""; for(int i = 0; i < length; i++) { int rnd = new
	 * Random().nextInt(charset.length); string += charset[rnd]; }
	 * 
	 * return string; }
	 */

	private char[] cs; // Character Set
	private char[] cg; // Current Guess

	public PasswordCrackerMultiThread(char[] characterSet, int guessLength) {
		cs = characterSet;
		cg = new char[guessLength];
		Arrays.fill(cg, cs[0]);
	}

	/*
	 * public void increment() { int index = cg.length - 1; while (index >= 0) { if
	 * (cg[index] == cs[cs.length - 1]) { if (index == 0) { cg = new char[cg.length
	 * + 1]; Arrays.fill(cg, cs[0]); break; } else { cg[index] = cs[0]; index--; } }
	 * else { cg[index] = cs[Arrays.binarySearch(cs, cg[index]) + 1]; break; } } }
	 */

	@Override
	public String toString() {
		return String.valueOf(cg);
	}
}
