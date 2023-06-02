import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Math.*;

public class exc10_7 {

    public static void Write(String str, String filename, boolean bool) {

        try (FileWriter writer = new FileWriter(filename, bool)) {
            File file = new File(filename);
            if (!file.exists()) file.createNewFile();

            writer.write(str);
            writer.flush();

        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    public static void main(String[] args) {
        String header = "Диапазон аргумента: -3<=x<=3\nФункция:\nif (x >= 0)\n\tf = -cos(2 * PI * x);\n" +
                "else \n\tf = 3 * PI * x + sin(2 * PI * x);\nВывод в порядке возрастания x\n";
        String filename = "src/test.txt";

        Write(header, filename, false);
        for (double x = -3; x <= 3; x += 0.5) {
            if (x >= 0)
                Write((" f("+x+") = " + (-cos(2 * PI * x)) + "\n"), filename, true);
            else
                Write(("f("+x+") = " + (3 * PI * x + sin(2 * PI * x)) + "\n"), filename, true);
        }
    }
}
