package pl.perekmichal.models.database;

import pl.perekmichal.models.utils.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnector {

    private static DatabaseConnector instance = new DatabaseConnector(); // SINGLETON
    public static DatabaseConnector getIstance() {
        return instance;
    }

    private Connection connection; //POLĄCZENIE

    private DatabaseConnector() {
        connect();
    }

    private void connect(){ //METODA DO POŁĄCZNIENIA
        try {
            Class.forName("com.mysql.jdbc.Driver"); //refleksja w javie ktora psuje obiektowosc
            connection = DriverManager.getConnection(Config.MYSQL_LINK, Config.MYSQL_LOGIN, Config.MYSQL_PASSWORD);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("It Works!!!");
    }

    public PreparedStatement createStatment (String SQL){ // PROWADNICA DO ZAPYTAŃ ODPOWIADA ZA TWORZENIE ZAPYTAN
        try {
            return connection.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
