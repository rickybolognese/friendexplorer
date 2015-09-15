package com.rickybolognese.friendexplorer;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(final String[] args) {
        try {
            final Configuration configuration = Configuration.parse(System.getProperties());
            final Module module = new Module(configuration);
            final Injector injector = Guice.createInjector(module);
            final Application application = injector.getInstance(Application.class);

            application.run();
        } catch (ConfigurationParseException e) {
            LOGGER.error("Failed to parse system properties into application configuration", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("The application has a logical error", e);
        }
    }
}
