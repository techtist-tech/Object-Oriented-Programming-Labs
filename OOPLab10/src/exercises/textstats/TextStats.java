package exercises.textstats;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

/**
 * Intermediate 1: read a text file and print the number of lines, words and characters.
 * Usage: run with no arguments to analyse sample.txt (created if missing), or pass another file path
 * as the first program argument.
 */
public class TextStats {
    public static void main(String[] args) {
        Path file = Path.of(args.length > 0 ? args[0] : "sample.txt");

        try {
            if (args.length == 0 && !Files.exists(file)) {
                Files.writeString(file, """
                    Object oriented programming is fun.
                    Classes and objects model the real world.

                    Streams make file processing declarative.
                    """);
            }

            List<String> lines = Files.readAllLines(file);
            long words = lines.stream()
                .map(String::trim)
                .filter(l -> !l.isEmpty())
                .mapToLong(l -> l.split("\\s+").length)
                .sum();
            long chars = lines.stream().mapToLong(String::length).sum();      // excluding line terminators

            System.out.println("File:       " + file.getFileName());
            System.out.println("Lines:      " + lines.size());
            System.out.println("Words:      " + words);
            System.out.println("Characters: " + chars + " (without line breaks)");
        } catch (NoSuchFileException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
