package net.chaussalet.bbl.asmppp.mygreatapp.modules;

import com.google.inject.AbstractModule;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static com.google.common.io.Resources.asByteSource;
import static com.google.common.io.Resources.getResource;
import static com.google.inject.name.Names.bindProperties;
import static java.text.MessageFormat.format;

public class ConfigurationModule extends AbstractModule {
    @Override
    protected void configure() {
        Properties properties = new Properties();
        InputStream inputStream;
        String configPath = System.getenv("ASMPP_CONF");
        try {
            if (configPath != null) {
                inputStream = new FileInputStream(configPath);
            } else {
                inputStream = asByteSource(getResource("application.conf")).openBufferedStream();
            }
            properties.load(inputStream);
            bindProperties(binder(), properties);
        } catch (java.io.IOException e) {
            String message = format("Unable to load configuration from {0} : {1}", configPath, e.getMessage());
            System.out.println(message);
            throw new RuntimeException(message, e);
        }
    }
}
