package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.util.ArrayList;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO;

    public UsuarioTO save(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.save(usuario);
    }

    public UsuarioTO update(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();

        // Se id for vazio
        if (usuario.getId() == null) {
            return null;
        }

        return usuarioDAO.update(usuario);
    }

    public boolean delete(Long id) {
        usuarioDAO = new UsuarioDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return usuarioDAO.delete(id);
    }

    public UsuarioTO findById(Long id) {
        usuarioDAO = new UsuarioDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return usuarioDAO.findById(id);
    }

    public ArrayList<UsuarioTO> findAll() {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.findAll();
    }
}
