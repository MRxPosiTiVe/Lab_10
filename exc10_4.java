import java.io.*;
import java.util.Scanner;

public class exc10_5 {

    public static void Write(String str, String filename){

        try(FileWriter writer = new FileWriter(filename)) {
            File file = new File(filename);
            if (!file.exists()) file.createNewFile();

            writer.write(str);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    public static void Write(Runnable action, String filename) {

        try(FileWriter writer = new FileWriter(filename)) {
            File file = new File(filename);
            if (!file.exists()) file.createNewFile();



            writer.flush();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    private static boolean isVowels(char ch) {
        String vowels = "aeuiu";
        return vowels.contains(Character.toString(ch));
    }


public static void hasMoreVowels(String filename, FileWriter writer) {
    BufferedReader br = null;

    try {
        br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                int vowelsCount = 0;
                int consonantsCount = 0;

                for (char c : word.toCharArray()) {
                    if (isVowels(c)) {
                        vowelsCount++;
                    } else {
                        consonantsCount++;
                    }
                }

                if (vowelsCount > consonantsCount) {
                    writer.write(word + "\n");
                }
            }
        }
    } catch (IOException e) {
        System.out.println("Error " + e);
    } finally {
        try {
            if (br != null) {
                br.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing the BufferedReader: " + e);
        }
    }
}

    private static void extractWords(String filename, FileWriter writer) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            int ch;

            while ((ch = br.read()) != -1) {
                char currentChar = (char) ch;

                if (Character.isLetterOrDigit(currentChar)) {
                    sb.append(currentChar);
                } else {
                    if (sb.length() > 0) {
                        String word = sb.toString();
                        writer.write(word + "\n");
                        sb.setLength(0); 
                    }
                }
            }

            if (sb.length() > 0) {
                String word = sb.toString();
                writer.write(word + "\n");
            }

        } catch (IOException e) {
            System.out.println("Error " + e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing the BufferedReader: " + e);
            }
        }
    }



    public static void main(String[] args) {
        String filename = "src/exc_10_4.txt";
        String answer_file = "src/exc_10_4ANS.txt";
        Scanner input = new Scanner(System.in);
        System.out.println("Введите текст с этими разделителями «_.,;:\\n\\t!?»");
        String text = input.nextLine();
        Write(text, filename);

        try (FileWriter writer = new FileWriter(answer_file)) {
            hasMoreVowels(filename, writer);
            extractWords(filename, writer);
            writer.flush();
            System.out.println("Результаты записаны в файл exc_10_4res.txt");
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }
}
