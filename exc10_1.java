import java.util.*;
import java.io.*;

public class exc10_1 {

    public static void writeToFile(String data, boolean append) {
        try (FileWriter writer = new FileWriter("output.txt", append)) {
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static List<String> readFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lines;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Введите символ для анализа: ");
        String symbol = input.nextLine();

        System.out.print("Длина массива: ");
        int len = input.nextInt();

        String alphabet = "ё1234567890-=йцукенгшщзхъфывапролджэячсмитьбю.!№;%:?*()_+, /[]{}@#$^&~ЁЙЦУ" +
                "КЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮqwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        Random random = new Random();

        writeToFile(" ", false);
        for (int i = 0; i < len; i++) {
            char c = alphabet.charAt(random.nextInt(alphabet.length()));
            writeToFile(String.valueOf(c), true);
        }

        List<String> fileLines = readFromFile("output.txt");

        StringBuilder output = new StringBuilder();

        int counter = 0;
        for (String line : fileLines) {
            char[] characters = line.toCharArray();
            for (char c : characters) {
                if (Character.isLowerCase(c) && Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CYRILLIC) {
                    counter++;
                }
            }
        }
        output.append("\nКоличество строчных русских букв: ").append(counter).append("\n");

        int symbolCount = 0;
        for (String line : fileLines) {
            char[] characters = line.toCharArray();
            for (char c : characters) {
                if (String.valueOf(c).equals(symbol)) {
                    symbolCount++;
                }
            }
        }
        output.append("Количество символов '").append(symbol).append("' в массиве: ").append(symbolCount).append("\n");

        if (symbol.matches("[0-9]+")) {
            int digit = Integer.parseInt(symbol);
            int digitCountInArray = 0;
            for (String line : fileLines) {
                char[] characters = line.toCharArray();
                for (char c : characters) {
                    if (Character.isDigit(c) && Integer.parseInt(String.valueOf(c)) == digit) {
                        digitCountInArray++;
                    }
                }
            }
            if (digitCountInArray >= 2) {
                output.append("Среди символов есть 2 и более цифры, входящие в число ").append(digit).append("\n");
            }
        } else {
            output.append("Введенный символ не является числом!\n");
        }

        int bracketPairsCount = 0;
        for (int i = 0; i < fileLines.size() - 1; i++) {
            String current = fileLines.get(i);
            String next = fileLines.get(i + 1);
            if ((current.equals("(") && next.equals(")")) ||
                    (current.equals("[") && next.equals("]")) ||
                    (current.equals("{") && next.equals("}"))) {
                bracketPairsCount++;
            }
        }
        if (bracketPairsCount > 0) {
            output.append("Количество соседних закрытых скобок: ").append(bracketPairsCount).append("\n");
        }

        int spaceCounter = 0;
        for (String line : fileLines) {
            if (line.equals(" ")) {
                spaceCounter++;
            } else {
                spaceCounter = 0;
            }
            if (spaceCounter > 2) {
                output.append("Обнаружено более двух подряд идущих пробелов.\n");
                break;
            }
        }

        boolean foundSequence = false;
        for (int i = 0; i < fileLines.size() - 2; i++) {
            String num1 = fileLines.get(i);
            String num2 = fileLines.get(i + 1);
            String num3 = fileLines.get(i + 2);
            if (num1.matches("\\d") && num2.matches("\\d") && num3.matches("\\d")) {
                int n1 = Integer.parseInt(num1);
                int n2 = Integer.parseInt(num2);
                int n3 = Integer.parseInt(num3);
                if (n1 > n2 && n2 > n3) {
                    foundSequence = true;
                    break;
                }
            }
        }
        if (foundSequence) {
            output.append("Существуют такие натуральные i и j, что i < k < j < п и si, и si+1 убывающая последовательность цифр, а sj и sj+1 возрастающая последовательность цифр.\n");
        } else {
            output.append("Не найдено таких натуральных i и j, что i < k < j < п и si, и si+1 убывающая последовательность цифр, а sj и sj+1 возрастающая последовательность цифр.\n");
        }

        writeToFile(output.toString(), true);
        System.out.println("Результаты записаны в файл 'output.txt'.");
    }
}

