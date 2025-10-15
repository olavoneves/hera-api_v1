package br.com.fiap.bo;

import br.com.fiap.dao.MedicoDAO;
import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.MedicoTO;
import br.com.fiap.to.UsuarioTO;

import java.util.ArrayList;

public class MedicoBO {
    private MedicoDAO medicoDAO;

    public MedicoTO save(MedicoTO medico) {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.save(usuario);
    }

    public MedicoTO update(MedicoTO medico) {
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

    public MedicoTO findById(Long id) {
        usuarioDAO = new UsuarioDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return usuarioDAO.findById(id);
    }

    public ArrayList<MedicoTO> findAll() {
        usuarioDAO = new UsuarioDAO();

        return usuarioDAO.findAll();
    }
}
