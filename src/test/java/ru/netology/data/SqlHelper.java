package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {
    private static QueryRunner runner = new QueryRunner();

    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");
    private static Connection connection;

    @SneakyThrows
    public static Connection getConnect(){
        try {
            connection = DriverManager.getConnection(url, user, password);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return connection;
    }
    @SneakyThrows
    public static String getPaymentApprovedStatus(){
        String codeSQL = "SELECT * FROM payment_entity JOIN order_entity ON transaction_id = payment_id where status = 'APPROVED'";
        try (Connection connection = getConnect()){
            PaymentModel result = runner.query(connection,codeSQL, new BeanHandler<>(PaymentModel.class));
            return result.getStatus();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }
    @SneakyThrows
    public static String getPaymentDeclinedStatus(){
        String codeSQL = "SELECT * FROM payment_entity JOIN order_entity ON transaction_id = payment_id where status = 'DECLINED'";
        try (Connection connection = getConnect()){
            PaymentModel result = runner.query(connection, codeSQL, new BeanHandler<>(PaymentModel.class));
            return result.getStatus();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }
    @SneakyThrows
    public static String getCreditApprovedStatus(){
        String codeSQL = "SELECT * FROM credit_request_entity JOIN order_entity ON bank_id = credit_id where status = 'APPROVED'";
        try (Connection connection = getConnect()){
            CreditModel result = runner.query(connection, codeSQL, new BeanHandler<>(CreditModel.class));
            return result.getStatus();
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
    @SneakyThrows
    public static String getCreditDeclinedStatus(){
        String codeSQL = "SELECT * FROM credit_request_entity JOIN order_entity ON bank_id = credit_id where status = 'DECLINED'";
        try (Connection connection = getConnect()){
            CreditModel result = runner.query(connection, codeSQL, new BeanHandler<>(CreditModel.class));
            return result.getStatus();
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}