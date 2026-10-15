package exercises.userlines;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Basic 1: save five lines typed by the user to a file, then read them back. */
public class UserLines {
    public static void main(String[] args) {
        Path file = Path.of("user_lines.txt");
        Scanner in = new Scanner(System.in);

        List<String> lines = new ArrayList<>();
        System.out.println("Type 5 lines of text (press Enter after each):");
        for (int i = 1; i <= 5; i++) {
            System.out.print("Line " + i + ": ");
            if (!in.hasNextLine()) break;
            lines.add(in.nextLine());
        }

        try {
            Files.write(file, lines);
            System.out.println("\nSaved " + lines.size() + " lines to " + file.toAbsolutePath().getFileName());

            System.out.println("Reading them back:");
            int n = 1;
            for (String line : Files.readAllLines(file)) {
                System.out.println(n++ + ": " + line);
            }
        } catch (IOException e) {
            System.out.println("I/O problem: " + e.getMessage());
        }
    }
}
