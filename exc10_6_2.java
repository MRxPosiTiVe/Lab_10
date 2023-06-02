import java.io.*;
import java.util.ArrayList;

public class exc10_6_2 {

    public static String readFile(String filename) {
        StringBuilder inputData = new StringBuilder();
        try (FileReader reader = new FileReader(filename)) {
            int c;
            while ((c = reader.read()) != -1) {
                inputData.append((char) c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return inputData.toString();
    }

    public static void writeFile(String filename, String data, boolean append) {
        try (FileWriter writer = new FileWriter(filename, append)) {
            writer.write(data);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static ArrayList<Double> extractWeights(String data) {
        ArrayList<Double> weights = new ArrayList<>();
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] parts = line.split(";");
            if (parts.length >= 4) {
                double weight = Double.parseDouble(parts[2]);
                weights.add(weight);
            }
        }
        return weights;
    }

    public static int countQualifiedAthletes(ArrayList<Double> weights) {
        int count = 0;
        for (double weight : weights) {
            if (weight <= 90) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String filename = "athletes.txt";
        String inputData = readFile(filename);

        ArrayList<Double> weights = extractWeights(inputData);
        int qualifiedAthletesCount = countQualifiedAthletes(weights);

        String result = "\nКоличество спортсменов, удовлетворяющих критериям: " + qualifiedAthletesCount;
        writeFile(filename, result, true);
    }
}
