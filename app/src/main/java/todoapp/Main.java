package todoapp;

import java.sql.Connection;

import util.ConnecionFactory;

public class Main {
    
    public static void main(String[] args) {
        Connection c = ConnecionFactory.getConnection();

        ConnecionFactory.closeConnection(c);
    }
}
