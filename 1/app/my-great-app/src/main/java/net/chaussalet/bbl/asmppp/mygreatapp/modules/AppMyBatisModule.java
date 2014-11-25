package net.chaussalet.bbl.asmppp.mygreatapp.modules;

import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import static java.text.MessageFormat.format;

public class AppMyBatisModule extends MyBatisModule {

    @Override
    protected void initialize() {
        install(JdbcHelper.MySQL);

        bindDataSourceProviderType(PooledDataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);
        String packageName = this.getClass().getPackage().getName();
        String commonPrefix = packageName.substring(0, packageName.lastIndexOf('.'));
        addMapperClasses(format("{0}.repository", commonPrefix));
    }
}
