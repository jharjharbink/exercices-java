package org.example.exo.student;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDealer {
    String url = "jdbc:postgresql://localhost:5432/Client";
    String username = "postgres";
    String password = "postgres";

    @Getter
    Connection connection;

    public ConnectionDealer() {
        this.connection = createConnection();
    }

    public Connection createConnection(){
        try {
            this.connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("La connexion est ok");
            } else {
                System.out.println("Connexion echoué");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
