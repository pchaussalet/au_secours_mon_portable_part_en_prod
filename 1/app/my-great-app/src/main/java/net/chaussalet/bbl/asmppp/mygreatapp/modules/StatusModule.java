package net.chaussalet.bbl.asmppp.mygreatapp.modules;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class StatusModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("app.version")).toInstance(getClass().getPackage().getImplementationVersion());
    }
}
