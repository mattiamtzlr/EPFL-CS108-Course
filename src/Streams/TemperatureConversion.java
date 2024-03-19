package Streams;

import java.util.List;

public final class TemperatureConversion {

    /*
        Streams ------------------------------------------------------------------------------------
            A stream is a sequence of elements supporting sequential and parallel aggregate
            operations.

            There are three specialisations of Stream, each more efficient in there respective type:
                - DoubleStream
                - LongStream
                - IntStream

        Stream Methods -----------------------------------------------------------------------------
            Methods called on streams are separated into three categories:
                - Source Methods:
                  Generate a stream based on a source, for example stream()

                - Intermediate Methods:
                  Transform the values of the stream and return a new stream, for example map() or
                  filter()

                - Terminal Methods:
                  Terminate the stream and convert it to another type or object, for example
                  toList()

            These methods are used to create pipelines, which consist of a source method, zero or
            more intermediate methods and a terminal method. The temp conversion below is a
            pipeline.

     */

    public static void main(String[] args) {
        List<String> tempF = List.of("0", "40", "-40", "", "100");

        List<String> tempC = tempF.stream()
                .filter((l -> !l.isEmpty()))                // remove empty entries
                .mapToDouble(Double::parseDouble)           // parse all entries as doubles
                .map(f -> (f - 32d) * (5d / 9d))            // calculate celsius equivalent
                .mapToObj(f -> String.format("%.3f", f))    // convert to string and round
                .toList();                                  // collect all entries in a list

        System.out.println(tempC);
    }
}
