package com.rickybolognese.friendexplorer;

import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

class Module extends AbstractModule {
    private final Properties properties;

    Module(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void configure() {
        Names.bindProperties(binder(), this.properties);
    }
}
