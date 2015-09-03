package com.rickybolognese.friendexplorer;

import org.apache.commons.cli.ParseException;

class ArgumentsParseException extends Exception {
    ArgumentsParseException(ParseException e) {
        super(e);
    }
}
