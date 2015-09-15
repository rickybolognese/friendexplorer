package com.rickybolognese.friendexplorer;

import java.lang.annotation.Annotation;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @Inject
    Application (@Named(Configuration.VENMO_USER_ID) String venmoUserId) {
    }

    void run() {
    }
}
