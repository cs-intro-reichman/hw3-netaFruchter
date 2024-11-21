/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemortfefe")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		String ordered_str1 = preProcess(str1);
		String ordered_str2 = preProcess(str2);
		int counter = 0;

		if (ordered_str1.length() != ordered_str2.length()) {
			return false;
		}

		for (int i = 0; i < ordered_str1.length(); i++){
			for (int j = 0; j < ordered_str2.length(); j++){
				if (ordered_str1.charAt(i) == ordered_str2.charAt(j)) {
					counter++;
					break;
				}
			}
			if (counter != i+1) {return false;}
		}
		return true;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		str = str.toLowerCase();
		String new_str = "";

		for (int i = 0; i < str.length(); i++){
			if (str.charAt(i) >= 97 && str.charAt(i) <=122){
				new_str += str.charAt(i);
			}
		}
		return new_str;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		String ordered_str = preProcess(str);
		String mid_str = ordered_str;
		String random_str = "";

		//System.out.println(ordered_str.length());

		for (int i=0; i < ordered_str.length(); i ++) {
			int index = (int)(Math.random() * mid_str.length());
			random_str += mid_str.charAt(index);
			mid_str = mid_str.replaceFirst(mid_str.charAt(index)+"", "");	
		}
		return random_str;
	}
}
