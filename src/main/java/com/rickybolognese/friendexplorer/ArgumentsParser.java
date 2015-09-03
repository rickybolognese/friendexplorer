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

        options.addOption(Option.builder("vat")
            .hasArg(true)
            .longOpt("venmo-access-token")
            .required(true)
            .type(String.class)
            .build());

        options.addOption(Option.builder("vui")
            .hasArg(true)
            .longOpt("venmo-user-id")
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
