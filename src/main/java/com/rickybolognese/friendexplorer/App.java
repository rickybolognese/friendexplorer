package com.rickybolognese.friendexplorer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) {
        CommandLineParser commandLineParser = new DefaultParser();
        Options options = new Options();

        Option maxDepth =
            Option.builder("md")
                  .hasArg(true)
                  .longOpt("max-depth")
                  .required(false)
                  .type(Integer.class)
                  .build();

        Option venmoAccessToken =
            Option.builder("vat")
                  .hasArg(true)
                  .longOpt("venmo-access-token")
                  .required(true)
                  .type(String.class)
                  .build();

        Option venmoUserId =
            Option.builder("vui")
                  .hasArg(true)
                  .longOpt("venmo-user-id")
                  .required(true)
                  .type(String.class)
                  .build();

        options.addOption(maxDepth);
        options.addOption(venmoAccessToken);
        options.addOption(venmoUserId);

        try {
            CommandLine commandLine = commandLineParser.parse(options, args);
        } catch (ParseException e) {
            LOGGER.error("Failed to parse command line options", e);
            return;
        }
    }
}
