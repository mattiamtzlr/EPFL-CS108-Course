package T_08_InputOutput;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

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
            There are two different types of subclasses of InputStream, those that take their
            values directly from a file or network connection and those that filter the values of
            another stream.


            Read Methods:
            All of these methods consume the values they read.
            Those that return an int, return the value of the byte as an integer between 0 and 255
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
            These allow to mark a position in the stream to return to later. Rarely used.
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
            Write methods:
                - void write(int b)
                  writes the 8 least significant bits into the stream, the other 24 bits are
                  ignored.

                - void write(bytes[] b, int o, int l)
                  writes the first l bytes from the array b starting at the position o

                - void write(byte[] b)
                  equivalent to write(b, 0, b.length)

                - void flush()
                  forces the values that should be written to the stream (i.e. by the above
                  methods) to actually be written to the filesystem or console.


            Close method:
                - void close()
                  closes the stream, freeing eventual memory, after this, the stream is no longer
                  usable.


            Subclasses of OutputStream:
            Every subclass of InputStream has a corresponding OutputStream subclass. Further,
            there is the following subclass:
                - PrintStream:
                  These are used to print to the standard output or error and are used in
                  the attributes of the class System for out and err.



        Resources ----------------------------------------------------------------------------------
            Resources are objects related to an operating system resource, thus for example
            Input- and OutputStreams.

            To facilitate the closing of a stream, which is very important to prevent files from
            being constantly used, Java offers the try-with-resource notion:

                try (InputStream s = new FileInputStream(...)) {
                    System.out.println(s.available());
                }

            This notion will ensure that streams are always closed even if an exception is raised
             as it runs a try-catch-finally block in the background which can also be augmented
             as seen in the beginning of the main method.



         Character Representation (generally) ------------------------------------------------------
            ASCII (American Standard Code for Information Interchange):
                An ASCII character is represented by an integer between 0 and 127 as ASCII uses 7
                bits to encode its characters. It includes the entire latin alphabet without
                accents, the arabic numbers and punctuation marks. Also included are a handful of
                non-printable control characters.


            Unicode:
                A Unicode character is represented by an integer called its code point which lies
                either:
                    - between       0x000000 and 0x00D7FF,
                    - or between    0x00E000 and 0x10FFFF

                This contains approx. one million different values, the gap in-between the
                intervals is reserved for the UTF-16 standard.

                Unicode offers three different encodings for its characters:
                    - UTF-8 and UTF-16, which are of variable length, meaning a character is
                      represented by a number of bytes depending on its code,

                    - UTF-32, using a fixed size of 4 bytes (32 bits) per character.

                UTF stands for Unicode Transformation Format.


            UTF-8:
                This is the most common of the Unicode encodings. It uses the following amounts
                of bytes for its characters depending on their code point:
                    - 1 byte:   0x000000 to 0x00007F
                    - 2 bytes:  0x000080 to 0x0007FF
                    - 3 bytes:  0x000800 to 0x00FFFF
                    - 4 bytes:  0x010000 t0 0x10FFFF

                A string composed entirely of ASCII characters will use the same code points
                as its ASCII encoding and is entirely contained within the "1-byte"-section.


            UTF-16:
                A character in UTF-16 uses the following amounts of bits, depending on its code
                point:
                    - 2 bytes:  0x000000 to 0x00D7FF    and     0x00E000 to 0x00FFFF
                    - 4 bytes:  0x010000 to 0x10FFFF

                The first range covers practically all the commonly used characters, thus most
                UTF-16 characters use only 16 bits.


         Character Representation (in Java) --------------------------------------------------------
            When Java was developed, Unicode only supported characters with up to 2 bytes (16
            bits) long code points, which is why the 'char' type is limited to 16 bits.

            Strings are defined as chains of characters of type char thus each character in a
            string is also limited to a code point of at most 16 bits if they are composed of
            only characters whose code points lie in the first interval of UTF-16.

            Characters exceeding this range cannot be used in a Java String and have to be
            encoded using their Unicode escape sequences:

                String thumbsUp = "\uD83D\uDC4D";   // 👍


            String methods work only with characters which leads to the following funky behavior:

                thumbsUp.length();                  // 2
                thumbsUp.charAt(0);                 // 0xD83D
                thumbsUp.charAt(1);                 // 0xDC4D
                thumbsUp.codePointCount(0, 2);      // 1
                thumbsUp.codePointAt(0);            // 0x1F44D



        Text Inputs/Outputs ------------------------------------------------------------------------
             A text file is a file containing only a sequence of characters encoded in bytes
             using a given encoding - ASCII, UTF-8 or other.

             The used encoding is usually not an inherit property of the file and thus needs to
             be known before decoding the file. If the encoding is unknown, it is possible to use
             clever statistics to make an educated guess about the used encoding.
             It's also possible to use the encoding of line endings to figure out the encoding,
             the following line endings are commonly used:
                - carriage return (CR), which has code point 13 in ASCII, UTF-8 and others
                - line feed (LF), which has code point 10 in ASCII, UTF-8 and others
                - carriage return followed by line feed (CRLF)

            LF is used by Unix systems (macOS <10 used CR) and CRLF is used by Windows.

            Readers who need to detect line endings generally consider any of the above-mentioned
            sequences to end a line.

            Writers who need to write a line break use the system property line.separator, a
            string whose value can be obtained by the following call:

                System.lineSeparator();


            Readers:
                The class Reader is equivalent to the class InputStream but works on Java
                characters instead of bytes. It provides the same methods except available(), which
                is replaced by:
                    - boolean ready()
                      returns true if it is certain that the next call to read without an
                      argument won't block the program.


            Writers:
                The class Writer is equivalent to the class OutputStream but works on Java
                characters instead of bytes. It provides the same methods.


            Converting between bytes and characters:
                The following classes can be used to interop between bytes and chars:
                    - InputStreamReader
                      given an input stream of bytes and a character encoding, it produces an
                      appropriate Reader.

                    - OutputStreamWriter
                      given an output stream of bytes and a character encoding, it produces an
                      appropriate Writer.


            File specific readers/writers:
                - FileReader
                  represents a reader whose character source is a file on the file system. Its
                  constructor takes as arguments the name of the file as well as the encoding
                  used to turn the bytes of the file back into characters.

                - FileWriter
                  represents a writer whose characters are written into a file on the file system.
                  Its constructor takes the same arguments as FileReader.


            Other Subclasses of Reader / Writer:
                - CharArrayReader & CharArrayWriter

                - StringReader & StringWriter

                - BufferedReader & BufferedWriter
                  These represent a Reader (resp. Writer) with a memory buffer, potentially
                  increasing performance.

                  BufferedReader also adds the method:
                      - String readLine()
                        reads and returns the next line or null if the end of the file has been
                        reached.

                  And BufferedWriter adds the method:
                      - void newLine()
                        writes a end-of-line character

                - LineNumberReader
                  This is a subclass of BufferedReader which keeps track of the current line
                  number (starting at 0) and allows its retrieval for which it provides the
                  following methods:
                      - int getLineNumber()

                      - void setLineNumber(int l)
                        sets the current line number to l, without affecting the underlying
                        reader at all.
     */

    private static final String FILE_NAME_BIN =   "files/bytes.bin";
    private static final String FILE_NAME_TXT =   "files/text.txt";
    private static final String FILE_NAME_UTF8 =  "files/utf-8.txt";
    private static final String FILE_NAME_UTF16 = "files/utf-16.txt";

    public static void main(String[] args) throws IOException {
        System.out.println(STR."\n\nWriting to '\{FILE_NAME_BIN}'...");

        // automatically closes the stream on error
        try (OutputStream output = new FileOutputStream(FILE_NAME_BIN)) {

            // write bytes 0 - 255
            for (int i = 0; i < 256; i++)
                output.write(i);

        } catch (IOException e) {
            throw new IOException("File no good oopsies.");

        } finally {
            System.out.println("done!");
        }

        System.out.println("Normal Version:");
        Dump.hexDump(FILE_NAME_BIN, false);

        System.out.println("\n\nCompressed Version:");
        Dump.hexDump(STR."\{FILE_NAME_BIN}.gz", true);


        System.out.println(STR."\n\nWriting to '\{FILE_NAME_TXT}'...");

        // write "Hello World!"
        try (Writer writer = new FileWriter(FILE_NAME_TXT, StandardCharsets.UTF_8)) {
            writer.write("Hello World!");

        } catch (IOException e) {
            throw new IOException("File no good oopsies.");

        } finally {
            System.out.println("done!");
        }

        System.out.println(STR."\n\nText retrieved from '\{FILE_NAME_TXT}':");

        try (BufferedReader reader = new BufferedReader(
            new FileReader(FILE_NAME_TXT, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null)
                System.out.println(line);
        }

        // transform a UTF-8 encoded file into a UTF-16 encoded file
        try (
                Reader r = new FileReader(FILE_NAME_UTF8, StandardCharsets.UTF_8);
                Writer w = new FileWriter(FILE_NAME_UTF16, StandardCharsets.UTF_16)
        ) {
            r.transferTo(w);
        }

        String content = Files.readString(Path.of(FILE_NAME_UTF8));

        System.out.println(STR."\n\nUTF-8 version of the string '\{content}': ");
        Dump.hexDump(FILE_NAME_UTF8, false);

        System.out.println(STR."\n\nUTF-16 version of the string '\{content}': ");
        Dump.hexDump(FILE_NAME_UTF16, false);
    }
}
