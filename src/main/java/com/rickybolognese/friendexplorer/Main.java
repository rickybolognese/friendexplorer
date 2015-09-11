package com.rickybolognese.friendexplorer;

import java.util.Properties;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(final String[] args) {
        final Properties properties = new Properties();
        final Injector injector = Guice.createInjector(new Module(properties));
        final Application application = injector.getInstance(Application.class);

        application.run();
    }
}
