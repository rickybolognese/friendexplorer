package com.rickybolognese.friendexplorer;

class Application {
    private final ArgumentsParser argumentsParser;

    Application (ArgumentsParser argumentsParser) {
        this.argumentsParser = argumentsParser;
    }

    void run(final String[] args) {
        try {
            final Arguments arguments = argumentsParser.parse(args);
        } catch(ArgumentsParseException e) {
        }
    }
}
