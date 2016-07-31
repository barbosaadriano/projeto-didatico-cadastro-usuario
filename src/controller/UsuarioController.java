package controller;

import java.util.ArrayList;
import java.util.List;
import model.dao.DaoUsuario;
import model.domain.Usuario;

/**
 *
 * @author Administrador
 */
public class UsuarioController {

    private DaoUsuario dao;
    private Usuario usuarioManipulado;
    private List<Usuario> listaDeUsuario;

    public UsuarioController() {
        this.dao = new DaoUsuario();
        this.listaDeUsuario = new ArrayList<Usuario>();
        this.usuarioManipulado = null;
    }

    public Usuario getUsuarioManipulado() {
        return usuarioManipulado;
    }

    public void setUsuarioManipulado(Usuario usuarioManipulado) {
        this.usuarioManipulado = usuarioManipulado;
    }

    public List<Usuario> getListaDeUsuario() {
        return listaDeUsuario;
    }

    public void setListaDeUsuario(List<Usuario> listaDeUsuario) {
        this.listaDeUsuario = listaDeUsuario;
    }

    public void pesquisar() {
        this.listaDeUsuario = this.dao.listar(this.usuarioManipulado);
        this.novo();
    }

    public void novo() {
        this.usuarioManipulado = new Usuario();
    }

    public void excluir() {
        this.dao.excluir(this.usuarioManipulado);
    }

    public void salvar() {
        this.dao.salvar(this.usuarioManipulado);
    }
}
