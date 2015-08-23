package com.rickybolognese.friendexplorer;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rickybolognese.friendexplorer.venmo.Client;
import com.rickybolognese.friendexplorer.venmo.IClient;
import com.rickybolognese.friendexplorer.venmo.IUser;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(final String[] args) {
        final Injector injector = Guice.createInjector(new Module());
        final Application application = injector.getInstance(Application.class);
        application.run(args);

        final CommandLineParser commandLineParser = new DefaultParser();
        final Options options = new Options();

        final Option maxDepthOption =
            Option.builder("md")
                  .hasArg(true)
                  .longOpt("max-depth")
                  .required(false)
                  .type(Integer.class)
                  .build();

        final Option venmoAccessTokenOption =
            Option.builder("vat")
                  .hasArg(true)
                  .longOpt("venmo-access-token")
                  .required(true)
                  .type(String.class)
                  .build();

        final Option venmoUserIdOption =
            Option.builder("vui")
                  .hasArg(true)
                  .longOpt("venmo-user-id")
                  .required(true)
                  .type(String.class)
                  .build();

        options.addOption(maxDepthOption);
        options.addOption(venmoAccessTokenOption);
        options.addOption(venmoUserIdOption);

        try {
            final CommandLine commandLine = commandLineParser.parse(options, args);
            final String venmoAccessToken = (String) commandLine.getParsedOptionValue("vat");
            final String venmoUserId = (String) commandLine.getParsedOptionValue("vui");

            final HttpClient httpClient = HttpClientBuilder.create().build();

            final IClient client = new Client(venmoAccessToken, "https://api.venmo.com/v1", httpClient);
            final IUser user = client.getUser(venmoUserId);
        } catch (ParseException e) {
            LOGGER.error("Failed to parse command line options", e);
            return;
        }
    }
}
