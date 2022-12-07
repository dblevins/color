package org.example;

import org.tomitribe.swizzle.stream.StreamBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class App {

//    public static void main(String[] args) throws IOException {
//        final InputStream in = System.in;
//        final PrintStream out = System.out;
//
//        copy(in, out);
//    }

    private static void copy(InputStream in, PrintStream out) throws IOException {
        while (true) {
            final int read = in.read();
            if (read == -1) break;
            out.write(read);
            out.flush();
        }
    }

    public static void main(String[] args) throws IOException {
        StreamBuilder stream = StreamBuilder.create(System.in);

        for (String arg : args) {
            final String[] split = arg.split(":");

            stream = stream.replace(split[0], String.format("\033[38;5;%sm%s\033[0m", split[1], split[0]));
        }

        copy(stream.get(), System.out);
    }

    private static void copy(InputStream from, OutputStream to) throws IOException {
        byte[] buffer = new byte[5];

        int length;
        while ((length = from.read(buffer)) != -1) {
            to.write(buffer, 0, length);
            to.flush();
        }

        to.flush();
    }
}
