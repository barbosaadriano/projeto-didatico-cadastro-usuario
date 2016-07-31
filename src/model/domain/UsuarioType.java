/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain;

/**
 *
 * @author Administrador
 */
enum UsuarioType {

    ADMINISTRADOR, USUARIO;

    public static UsuarioType getADMINISTRADOR() {
        return ADMINISTRADOR;
    }

    public static UsuarioType getUSUARIO() {
        return USUARIO;
    }
}
