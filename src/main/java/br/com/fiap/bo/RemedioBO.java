package br.com.fiap.bo;

import br.com.fiap.dao.RemedioDAO;
import br.com.fiap.to.RemedioTO;

import java.util.ArrayList;

public class RemedioBO {
    private RemedioDAO remedioDAO;

    public RemedioTO save(RemedioTO remedio) {
        remedioDAO = new RemedioDAO();

        return remedioDAO.save(remedio);
    }

    public RemedioTO update(Long id, RemedioTO remedio) {
        remedioDAO = new RemedioDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return remedioDAO.update(id, remedio);
    }

    public boolean delete(Long id) {
        remedioDAO = new RemedioDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return remedioDAO.delete(id);
    }

    public RemedioTO findById(Long id) {
        remedioDAO = new RemedioDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return remedioDAO.findById(id);
    }

    public ArrayList<RemedioTO> findAll() {
        remedioDAO = new RemedioDAO();

        return remedioDAO.findAll();
    }
}
