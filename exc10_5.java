import java.io.*;

import java.util.Scanner;

public class exc10_5 {

    private static boolean isAbbreviation(String word) {
        return word.matches("^[A-ZА-Я0-9]+$");
    }

    public static void Write(String str, String filename) {
        try(PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            writer.println(str);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    public static void Read(String filename, String filename2) {
        BufferedReader br = null;
        try {
            File file2 = new File(filename2);
            if (!file2.exists()) file2.createNewFile();

            br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] sentence = line.split("[.!?]+");
                for (String words : sentence) {
                    if (hasAbbreviation(words)) Write(words, filename2);
                }
            }

        } catch (IOException e) {
            System.out.println("Error " + e);
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                System.out.println("Error " + e);
            }
        }
    }

    private static boolean hasAbbreviation(String sentence) {
        String[] words = sentence.split("\\s+");
        for (String word : words) {
            if (isAbbreviation(word)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String filename1 = "src/exc_10_5(1).txt";
        String filename2 = "src/exc_10_5(2).txt";
        Scanner input = new Scanner(System.in);
        System.out.println("Введите текст, где есть аббревиатуры: ");
        String abbr = input.nextLine();
        Write(abbr, filename1);
        Read(filename1, filename2);
    }
}
