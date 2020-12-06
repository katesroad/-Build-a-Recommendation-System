import edu.duke.*;

public class SampleOne {
	public static void runHello() {
		FileResource res = new FileResource("hello_unicode.txt");
		for (String line : res.lines()) {
			System.out.println(line);
		}
	}

	public static  void main(String[] args) {
		runHello();
	}
}


