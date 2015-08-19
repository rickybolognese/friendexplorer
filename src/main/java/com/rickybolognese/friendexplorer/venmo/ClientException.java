package com.rickybolognese.friendexplorer.venmo;

class ClientException extends RuntimeException {
    ClientException(String message) {
        super(message);
    }

    ClientException(Throwable cause) {
        super(cause);
    }
}
