package com.rickybolognese.friendexplorer;

import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

class Module extends AbstractModule {
    private final Configuration configuration;

    Module(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void configure() {
        Names.bindProperties(binder(), this.configuration);
    }
}
