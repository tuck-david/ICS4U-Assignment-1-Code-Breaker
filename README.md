# ICS4U-Code-Breaker
copy your code into the code_breaker.java file and commit the changes.




public static int RecBinS(String[] name, String target, int min, int max) {
		int a = min + max / 2;
		if (name.length == 1 && name[0] != target)
			return -1;
		if ((name[a].compareTo(target) == 0)) {
			return a;
		} else if (name[a].compareTo(target) < 0) {
			return RecBinS(name, target, a + 1, max);
		} else if (name[a].compareTo(target) > 0) {
			return RecBinS(name, target, min, a - 1);
		} else {
			return -1;
		}

	}
