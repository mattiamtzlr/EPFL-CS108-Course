package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class Dump {
    public static void hexDump(String fileName, boolean compressed) throws IOException {
        // try-with-resource notion
        try (
                InputStream in = compressed
                        ? new GZIPInputStream(new FileInputStream(fileName))
                        : new FileInputStream(fileName)
        ) {
            int b, pos = 0;
            while ((b = in.read()) != -1) {
                if ((pos % 16) == 0)
                    System.out.printf("%n%02x:", pos);
                System.out.printf(" %02x", b);
                pos++;
            }
        }
    }
}
