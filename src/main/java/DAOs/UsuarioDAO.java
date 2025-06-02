/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import entities.Usuario;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface UsuarioDAO {
    
    //LECTURA
    
    List<Usuario> todosUsuarios();
    
    Usuario buscarUsuario(Long id);
    
    
    //ESCRITURA
    
    Usuario cargarUsuario(Usuario usuario);
    
    Usuario modificarUsuario(Usuario usuario);
    
    Boolean eliminarUsuario(Usuario usuario);
}
