package com.rickybolognese.friendexplorer;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(final String[] args) {
        final Injector injector = Guice.createInjector(new Module());
        final Application application = injector.getInstance(Application.class);
        application.run(args);
    }
}
