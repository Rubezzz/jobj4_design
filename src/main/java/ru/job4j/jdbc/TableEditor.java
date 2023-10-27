package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;
    private Statement statement;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        Connection connection = DriverManager.getConnection(url, login, password);
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
        statement.execute(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("DROP TABLE IF EXISTS %s;", tableName);
        statement.execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("ALTER TABLE %s ADD IF NOT EXISTS %s %s;", tableName, columnName, type);
        statement.execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        statement.execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
        statement.execute(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        var selection = statement.executeQuery(String.format(
                "SELECT * FROM %s LIMIT 1", tableName
        ));
        var metaData = selection.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            buffer.add(String.format("%-15s|%-15s%n",
                    metaData.getColumnName(i), metaData.getColumnTypeName(i))
            );
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties");) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("test_table");
            System.out.println(tableEditor.getTableScheme("test_table"));
            tableEditor.addColumn("test_table", "column1", "int");
            tableEditor.addColumn("test_table", "name", "varchar(50)");
            System.out.println(tableEditor.getTableScheme("test_table"));
            tableEditor.dropColumn("test_table", "name");
            System.out.println(tableEditor.getTableScheme("test_table"));
            tableEditor.renameColumn("test_table", "column1", "renamedColumn");
            System.out.println(tableEditor.getTableScheme("test_table"));
            tableEditor.dropTable("test_table");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}