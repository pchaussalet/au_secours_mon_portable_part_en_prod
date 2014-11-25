package net.chaussalet.bbl.asmppp.mygreatapp.servlet;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;
import com.google.inject.servlet.GuiceServletContextListener;
import com.googlecode.sli4j.slf4j.Slf4jLoggingModule;
import com.mysql.jdbc.Driver;
import net.chaussalet.bbl.asmppp.mygreatapp.modules.AppMyBatisModule;
import net.chaussalet.bbl.asmppp.mygreatapp.modules.AppServletModule;
import net.chaussalet.bbl.asmppp.mygreatapp.modules.ConfigurationModule;
import net.chaussalet.bbl.asmppp.mygreatapp.modules.StatusModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.google.inject.Guice.createInjector;
import static com.google.inject.matcher.Matchers.any;

public class AppServlet extends GuiceServletContextListener {

    public static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS mygreatapp;";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS mygreatapp.address (" +
            "id INTEGER AUTO_INCREMENT," +
            "firstname VARCHAR(256)," +
            "lastname VARCHAR(256)," +
            "email VARCHAR(256)," +
            "PRIMARY KEY (id)" +
            ") ENGINE INNODB;";


    @Override
    protected Injector getInjector() {
        Injector injector = createInjector(
                new ConfigurationModule(),
                JdbcHelper.MySQL,
                new AppMyBatisModule(),
                new AppServletModule(),
                new StatusModule(),
                new Slf4jLoggingModule(any())
        );
        String url = injector.getInstance(Key.get(String.class, Names.named("JDBC.url"))).replace("/mygreatapp", "/mysql");
        String username = injector.getInstance(Key.get(String.class, Names.named("JDBC.username")));
        String password = injector.getInstance(Key.get(String.class, Names.named("JDBC.password")));
        System.out.println(url);
        try {
            Class.forName(Driver.class.getCanonicalName());
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.createStatement().execute(CREATE_DATABASE);
            connection.createStatement().execute(CREATE_TABLE);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return injector;
    }
}
