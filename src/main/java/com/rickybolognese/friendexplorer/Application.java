package com.rickybolognese.friendexplorer;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private final ArgumentsParser argumentsParser;

    @Inject
    Application (ArgumentsParser argumentsParser) {
        this.argumentsParser = argumentsParser;
    }

    void run(final String[] args) {
        try {
            final Arguments arguments = argumentsParser.parse(args);
            final String venmoAccessToken = arguments.getVenmoAccessToken();
            final String venmoUserId = arguments.getVenmoUserId();

            LOGGER.info("Venmo Access Token = {}", venmoAccessToken);
            LOGGER.info("Venmo User Id = {}", venmoUserId);
        } catch(ArgumentsParseException e) {
            LOGGER.error("Failed to parse arguments", e);
        }
    }
}
