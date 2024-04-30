package IO;

import java.io.*;

public class Main {
    /* ==================================== Input / Output =========================================

        Mainly concerns java.io and sometimes java.nio (newer classes)
            - java.io uses streams
            - java.nio uses buffers
        java.nio does not replace java.io!

        Generally, I/O uses the following superclasses:

                       |    Input           Output
            -----------|-------------------------------
            Octets     | InputStream    OutputStream
            Characters |    Reader          Writer
              |
              v
            These two are used as files either contain text or binary.



        InputStream --------------------------------------------------------------------------------
            There are two different types of subclasses of InputStream, those that take their values
            directly from a file or network connection and those that filter the values of
            another stream.


            Read Methods:
            All of these methods consume the values they read.
            Those that return an int return the value of the byte as an integer between 0 and 255
            inclusive (unsigned), those that return a byte array, use signed values thus all
            elements of the array are between -128 and 127 inclusive.
                - int read()
                  reads the next byte and returns it as a value between 0 and 255 inclusive or -1
                  if the end of the stream is reached.

                - int readNBytes(byte[] b, int o, int l)
                  reads at most l bytes, places them in the array b beginning at the index o,
                  returns the number of bytes read, which is only less than l if the end of the
                  stream was reached.

                - byte[] readNBytes(int l)
                  similar to the above, but the bytes are placed in a new array which is returned

                - byte[] readAllBytes()
                  reads all bytes resting in the stream and returns them in a new array


            Skip methods:
                - long skip(long n)
                  skips at most n bytes, returns the number of bytes skipped.

                - void skipNBytes(long n)
                  skips exactly n bytes.


            As these methods tend to block the execution of the program until all data has been
            read for example, the following method can be used to get an estimate of how many
            bytes may be read or skipped without blocking:

                int available()

            It is guaranteed that reading / skipping this amount of bytes will not block the
            program.


            Transfer method:
                - long transferTo(OutputStream o)
                  reads all the bytes of the stream and writes them to the given output stream,
                  returns the number of bytes transferred.


            Marking methods:
            These allow to mark a position in the stream to return to later. Rarely used
                - boolean markSupported()
                  returns true iff the stream allows marking

                - void mark(int l)
                  places the marker at the current location and promises that if the reset method
                  is called later, it will be called when at most l bytes have been read from the
                  current position. (?)

                - void reset()
                  returns to the previously set mark.


            Close method:
            Streams always have to be closed to prevent memory leaks.
                - void close()
                  closes the stream, freeing eventual memory, after this, the stream is no longer
                   usable.

            Closing is facilitated by the try-with-resource notion seen below e.g. in the method
            hexDump of the class Dump.


            Subclasses of InputStream:
                - FileInputStream

                - ByteArrayInputStream

                - BufferedInputStream:
                  Takes as argument another input stream and applies a buffered memory to it,
                  thus it reads the bytes of the stream and stores them in an array, from which
                  they can be retrieved when needed. This keeps the file from being in use for a
                  long time.

                - GZIPInputStream:
                  Decompresses .gz files while reading, often used with as a filter to a buffered
                  input stream



        OutputStream -------------------------------------------------------------------------------


     */

    private static final String FILE_NAME = "files/bytes.bin";

    public static void main(String[] args) throws IOException {
        // automatically closes the stream on error
        try (OutputStream output = new FileOutputStream(FILE_NAME)) {

            // writes bytes 0 - 255 into "bytes.bin"
            for (int i = 0; i < 256; i++)
                output.write(i);

        } catch (IOException e) {
            throw new IOException("File no good oopsies.");
            
        } finally {
            System.out.println("all done uwu");
        }

        System.out.println("Normal Version:");
        Dump.hexDump(FILE_NAME, false);

        System.out.println("\n\nCompressed Version:");
        Dump.hexDump(STR."\{FILE_NAME}.gz", true);
    }
}
