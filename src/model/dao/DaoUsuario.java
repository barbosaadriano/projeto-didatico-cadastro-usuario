package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.domain.Usuario;
import services.Conn;

/**
 *
 * @author Administrador
 */
public class DaoUsuario {

    public boolean excluir(Usuario obj) {
        StringBuilder sql = new StringBuilder();
        PreparedStatement ps;
        try {
            sql.append("delete from usuario "
                    + " where codigo = ?");
            ps = Conn.getInstance().getConnection().prepareStatement(sql.toString());
            ps.setInt(1, obj.getCodigo());
            ps.execute();
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean salvar(Usuario obj) {
        StringBuilder sql = new StringBuilder();
        PreparedStatement ps;
        try {
            if (obj.getCodigo() == null) {
                sql.append("insert into usuario (codigo, nome, login, senha, tipo, `status`) "
                        + " values ( (select coalesce(max(x.codigo),0)+1 from usuario x),?,?,?,?,? )");
                ps = Conn.getInstance().getConnection().prepareStatement(sql.toString());
            } else {
                sql.append("update usuario set nome=?, login=?, "
                        + " senha=?, tipo=?, `status`=? "
                        + "where codigo = ?");
                ps = Conn.getInstance().getConnection().prepareStatement(sql.toString());
                ps.setInt(6, obj.getCodigo());
            }
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getLogin());
            ps.setString(3, obj.getSenha());
            ps.setInt(4, obj.getTipo());
            ps.setInt(5, obj.getStatus());
//            ps.execute();
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> listar(Usuario obj) {
        List<Usuario> lst = new ArrayList<Usuario>();
        StringBuilder sql = new StringBuilder();
        PreparedStatement ps;
        sql.append("select * from usuario ");
        if (obj.getNome() != null && !obj.getNome().equals("")) {
            sql.append(" where nome like '%");
            sql.append(obj.getNome());
            sql.append("%'");
        }
        try {
            ps = Conn.getInstance().getConnection().prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setCodigo(rs.getInt("codigo"));
                u.setNome(rs.getString("nome"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                u.setStatus(rs.getInt("status"));
                u.setTipo(rs.getInt("tipo"));
                lst.add(u);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return lst;
    }

}
