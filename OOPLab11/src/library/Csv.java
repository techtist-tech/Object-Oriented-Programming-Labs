package library;

/**
 * Tiny helper so titles/names containing commas or new lines survive a round trip through a CSV file.
 * Escaping: '%' -> %25, ',' -> %2C, newline -> %0A (and the reverse on reading).
 */
final class Csv {
    private Csv() { }

    static String escape(String s) {
        return s.replace("%", "%25").replace(",", "%2C").replace("\n", "%0A").replace("\r", "");
    }

    static String unescape(String s) {
        return s.replace("%0A", "\n").replace("%2C", ",").replace("%25", "%");
    }

    /** Splits on commas and unescapes each field. The limit -1 keeps trailing empty fields. */
    static String[] parse(String line) {
        String[] parts = line.split(",", -1);
        for (int i = 0; i < parts.length; i++) parts[i] = unescape(parts[i]);
        return parts;
    }
}
