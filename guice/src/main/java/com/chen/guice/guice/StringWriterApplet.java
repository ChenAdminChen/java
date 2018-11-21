package com.chen.guice.guice;

import java.io.PrintStream;

public class StringWriterApplet implements MyDestination{

    private PrintStream printStream;

    public StringWriterApplet(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void write(String string) {

        printStream.println(string);
    }
}
