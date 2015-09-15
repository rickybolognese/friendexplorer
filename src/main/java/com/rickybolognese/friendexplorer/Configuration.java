package com.rickybolognese.friendexplorer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.inject.Named;

public class Configuration implements Map<String, String> {
    @Target({ ElementType.FIELD })
    @interface DefaultValue {
        public String value();
    }

    @Target({ ElementType.FIELD })
    @interface Required {}

    static class Key {
        private final String defaultValue;
        private final String name;
        private final boolean required;

        Key(String name, boolean required, String defaultValue) {
            this.name = name;
            this.required = required;
            this.defaultValue = defaultValue;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public String getName() {
            return name;
        }

        public boolean isRequired() {
            return required;
        }
    }

    @DefaultValue("https://api.venmo.com/v1")
    public static final String VENMO_API_URL = "venmo.apiUrl";

    @Required
    public static final String VENMO_ACCESS_TOKEN = "venmo.userId";

    @Required
    public static final String VENMO_USER_ID = "venmo.userId";

    private final Map<String, String> decoratee;

    private Configuration(Map<String, String> decoratee) {
        this.decoratee = decoratee;
    }

    public static Configuration parse(Map<String, String> map) throws ConfigurationParseException, IllegalAccessException {
        final Map<String, String> decoratee = new HashMap<>();

        for (Key key : keys()) {
            final String name = key.getName();
            String value = key.getDefaultValue();

            if (map.containsKey(name)) {
                value = map.get(name);
            } else if (key.isRequired()) {
                throw new ConfigurationParseException("key is required but missing: " + name);
            }

            decoratee.put(name, value);
        }

        return new Configuration(decoratee);
    }

    public static Configuration parse(Properties properties) throws ConfigurationParseException, IllegalAccessException {
        final Map<String, String> map = new HashMap<>();

        for (String propertyName : properties.stringPropertyNames()) {
            map.put(propertyName, properties.getProperty(propertyName));
        }

        return parse(map);
    }

    private static final Key[] keys() throws IllegalAccessException {
        final Field[] declaredFields = Configuration.class.getDeclaredFields();
        final List<Key> keys = new ArrayList<>();

        for (Field field : declaredFields) {
            final int modifiers = field.getModifiers();

            if (!String.class.equals(field.getType())) {
                continue;
            }

            if (!Modifier.isPublic(modifiers)) {
                continue;
            }
            
            if (!Modifier.isStatic(modifiers)) {
                continue;
            }
            
            final String name = (String)field.get(null);
            final DefaultValue defaultValue = field.getAnnotation(DefaultValue.class);
            final boolean required = null != field.getAnnotation(Required.class);

            keys.add(new Key(name, required,
                defaultValue != null ? defaultValue.value() : null));
        }

        return keys.toArray(new Key[keys.size()]);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsKey(Object key) {
        return decoratee.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return decoratee.containsValue(value);
    }

    @Override
    public Set<Map.Entry<String, String>> entrySet() {
        return decoratee.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return decoratee.equals(o);
    }

    @Override
    public String get(Object key) {
        return decoratee.get(key);
    }

    @Override
    public boolean isEmpty() {
        return decoratee.isEmpty();
    }

    @Override
    public int hashCode() {
        return decoratee.hashCode();
    }

    @Override
    public Set<String> keySet() {
        return decoratee.keySet();
    }

    @Override
    public String put(String key, String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(Object key) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public int size() {
        return decoratee.size();
    }

    @Override
    public Collection<String> values() {
        return decoratee.values();
    }
}
