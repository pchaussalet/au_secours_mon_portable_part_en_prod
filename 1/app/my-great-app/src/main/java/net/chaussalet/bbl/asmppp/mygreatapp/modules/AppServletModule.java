package net.chaussalet.bbl.asmppp.mygreatapp.modules;

import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import static com.google.inject.Scopes.SINGLETON;
import static java.text.MessageFormat.format;

public class AppServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        super.configureServlets();
        String packageName = this.getClass().getPackage().getName();
        String commonPrefix = packageName.substring(0, packageName.lastIndexOf('.'));
        ResourceConfig resourceConfig = new PackagesResourceConfig(format("{0}.resources", commonPrefix));
        for (Class<?> resource: resourceConfig.getClasses()) {
            bind(resource);
        }

        bind(JacksonJsonProvider.class).in(SINGLETON);

        serve("/*").with(GuiceContainer.class);
    }
}
