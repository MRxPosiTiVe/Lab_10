import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class exc10_2 {

    public static void Write(String str, String filename, boolean bool) {

        try(FileWriter writer = new FileWriter(filename, bool)) {
            File file = new File(filename);
            if (!file.exists()) file.createNewFile();

            writer.write(str);
            writer.flush();

        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    public static void main(String[] args) {
        String filename = "src/test_file.txt";
        Write("", filename, false);

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                Write(i + " - " + j + " = " + (i - j) + "\t", filename, true);
            }
            Write("\n", filename, true);
        }
    }
}
