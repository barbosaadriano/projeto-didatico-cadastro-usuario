/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class Conn {

    private static Conn instance;
    private Connection conexao;

    private Conn() {
        try {
            this.conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sis_tads_2?zeroDateTimeBehavior=convertToNull", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Conn getInstance() {
        if (Conn.instance == null) {
            Conn.instance = new Conn();
        }
        return Conn.instance;
    }

    public Connection getConnection() {
        return this.conexao;
    }
}
