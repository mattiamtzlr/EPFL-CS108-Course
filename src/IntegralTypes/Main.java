package IntegralTypes;

import java.util.StringJoiner;

public class Main {
    /* ======================================= Integral Types ======================================

        ----------------------------------------------------------------------------------------
        | type name | number of bits |                       range                             |
        ----------------------------------------------------------------------------------------
        |   byte    |       8        |                     [-128, 127]                         |
        |   short   |       16       |                  [-32'768, 32'767]                      |
        |   char    |       16       |                     [0, 65535]                          |
        |   int     |       32       |            [-2'147'483'648, 2'147'483'647]              |
        |   long    |       64       | [-9'223'372'036'854'775'808, 9'223'372'036'854'775'807] |
        ----------------------------------------------------------------------------------------

        The above are all represented in Two's complement, except char, which is unsigned but used
        for characters and not necessarily integers.



        Arithmetic vs Logical right shift ----------------------------------------------------------
            Arithmetic (>>):
                Copies the MSB to the new position(s) obtained by shifting right.

            Logical (>>>):
                Puts a zero into every new position obtained by shifting right.



        Masks --------------------------------------------------------------------------------------
            By logically and-ing a binary string with one containing a well-chosen number of ones
            (the mask), only the postions of the original string, where the mask has a one are
            obtained.
            The result can then be shifted.



        Java API -----------------------------------------------------------------------------------
            The following are all also available in the Long class.

            Conversion to/from text:
                - Integer.toString(int i, int b)
                  returns the string representing i in the base b where 2 <= b <= 36
                  b can also be omitted

                - Integer.parseInt(String s, int b)
                  inverse operation to the above


            Bit counting:
                - Integer.bitCount(int i)
                  returns the number of bits with value one in the given integer

                - Integer.numberOfLeadingZeros(int i)

                - Integer.numberOfTrailingZeros(int i)


            Bit positions:
                - Integer.lowestOneBit(int i)
                  returns the integer with a one in its binary representation at the position where
                  the passed integer has its lowest one bit.

                - Integer.highestOneBit(int i)
                  same as above but with the highest one bit.


            Bit rotation:
                Rotation works similarly to shifting but the bits "ejected" at one end are added at
                the other.

                - Integer.rotateLeft(int i, int d)
                  returns the result of rotating the passed integer i by d positions to the left.

                - Integer.rotateRight(int i, int d)
                  returns the result of rotating the passed integer i by d positions to the right.


            Bit inversion:
                - Integer.reverse(int i)
                  returns the result of inverting the bits of the passed integer i

                - Integer.reverseBytes(int i)
                  returns the result of inverting the bytes (thus the octets of bits) of the passed
                  integer i.



        Packaging --------------------------------------------------------------------------------
            Bit strings are often used to pack multiple values into one such as colors.
    */

    static StringJoiner sj = new StringJoiner(", ", "{", "}");

    public static void main(String[] args) {
        byte a = (byte) 0b1111_1111;    // -1
        byte b = (byte) 0b1001_1100;    // -100
        byte c = (byte) 0b0100_1000;    // +72

        int maxInt = 0b01111111_11111111_11111111_11111111;     // +2'147'483'647
        long x = 0xFADE_75BCL;  // some random hex value

        int y =    0b00110100_00110110_10001111_00110111;
        int mask = 0b00000000_00000000_11111111_00000000;
        /* applying this mask to y yields only the 2nd octet of bits: 10001111 which is +143 after
           shifting right by 8 places: */
        int y_masked = (y & mask) >> Integer.numberOfTrailingZeros(mask);

        // bitwise inversion:
        byte z = (byte) ~0b1111_0000;   // +15

        int d = Integer.lowestOneBit(0b1011_0000);  // +16 (0001_0000)

        int e = Integer.reverseBytes(0b11111111_00000000_00000000_00000000); // +255

        int red = 0x80; int green = 0xD4; int blue = 0xFF;
        int color = (red << 16) | (green << 8) | blue;  // 32-bit color (light blue)

        add(a); add(b); add(c); add(maxInt); add(x); add(y_masked); add(z); add(d); add(e);
        add(color);
        System.out.println(sj);
    }

    private static <N extends Number> void add(N num) {
        sj.add(String.valueOf(num));
    }
}
