package com.rickybolognese.friendexplorer;

import org.apache.commons.cli.CommandLine;

class Arguments {
    static final String VENMO_ACCESS_TOKEN = "vat";
    static final String VENMO_ACCESS_TOKEN_LONG = "venmo-access-token";
    static final String VENMO_USER_ID = "vui";
    static final String VENMO_USER_ID_LONG = "venmo-user-id";

    private final CommandLine commandLine;

    Arguments(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    String getVenmoAccessToken() {
        return commandLine.getOptionValue(VENMO_ACCESS_TOKEN);
    }

    String getVenmoUserId() {
        return commandLine.getOptionValue(VENMO_USER_ID);
    }
}
