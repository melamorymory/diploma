package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static final QueryRunner runner = new QueryRunner();
    private static String dbURL = System.getProperty("url");
    private static String dbUser = System.getProperty("user");
    private static String dbPass = System.getProperty("password");

    public static void deleteData() throws SQLException {
        try (var conn = DriverManager.getConnection(dbURL, dbUser, dbPass)) {
            runner.update(conn, "DELETE FROM credit_request_entity;");
            runner.update(conn, "DELETE FROM order_entity;");
            runner.update(conn, "DELETE FROM payment_entity;");
        }
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        String paymentStatus;
        try (var conn = DriverManager.getConnection(dbURL, dbUser, dbPass)) {
            paymentStatus = runner.query(conn, "SELECT status FROM payment_entity", new ScalarHandler<>());
        }
        return paymentStatus;
    }

    @SneakyThrows
    public static String getCreditStatus() {
        String creditStatus;
        try (var conn = DriverManager.getConnection(dbURL, dbUser, dbPass)) {
            creditStatus = runner.query(conn, "SELECT status FROM credit_request_entity", new ScalarHandler<>());
        }
        return creditStatus;
    }
}
