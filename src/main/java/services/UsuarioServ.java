package services;

import DAOs.UsuarioDAO;
import DAOs.UsuarioDAOimpl;
import entities.Usuario;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class UsuarioServ {

    private final UsuarioDAO dao = new UsuarioDAOimpl();

    public List<Usuario> todosUsuarios() {
        return dao.todosUsuarios();
    }

    public Usuario buscarUsuario(Long id){
        return dao.buscarUsuario(id);
    }
    
    public Usuario cargarUsuario(Usuario usuario){
        return dao.cargarUsuario(usuario);
    }
    
}
