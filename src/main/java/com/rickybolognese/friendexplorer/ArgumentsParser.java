package com.rickybolognese.friendexplorer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

class ArgumentsParser {
    private final CommandLineParser commandLineParser;
    private final Options options;

    ArgumentsParser() {
        commandLineParser = new DefaultParser();
        options = new Options();

        options.addOption(Option.builder("md")
            .hasArg(true)
            .longOpt("max-depth")
            .required(false)
            .type(Integer.class)
            .build());

        options.addOption(Option.builder(Arguments.VENMO_ACCESS_TOKEN)
            .hasArg(true)
            .longOpt(Arguments.VENMO_ACCESS_TOKEN_LONG)
            .required(true)
            .type(String.class)
            .build());

        options.addOption(Option.builder(Arguments.VENMO_USER_ID)
            .hasArg(true)
            .longOpt(Arguments.VENMO_USER_ID_LONG)
            .required(true)
            .type(String.class)
            .build());
    }

    Arguments parse(String[] args) throws ArgumentsParseException {
        try {
            return new Arguments(commandLineParser.parse(options, args));
        } catch(ParseException e) {
            throw new ArgumentsParseException(e);
        }
    }
}
