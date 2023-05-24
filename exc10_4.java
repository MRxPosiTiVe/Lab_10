import java.io.*;
import java.util.Scanner;


public class exc10_4 {

    public static void Write(String str) {

        try (FileWriter writer = new FileWriter("src/exc_10_4.txt")) {

            File file = new File("src/exc_10_4.txt");
            if (!file.exists()) file.createNewFile();
            writer.write(str);
            writer.flush();

        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    public static void printWordsWithUpperCase(String filename) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (containsUppercase(word)) {
                        System.out.println(word);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }


    private static boolean containsUppercase(String word) {
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите текст: ");
        String text = input.nextLine();
        String filename = "src/exc_10_4.txt";
        Write(text);
        printWordsWithUpperCase(filename);

    }
}
