package br.com.fiap.bo;

import br.com.fiap.dao.ConsultaDAO;
import br.com.fiap.to.ConsultaTO;

import java.util.ArrayList;

public class ConsultaBO {
    private ConsultaDAO consultaDAO;

    public ConsultaTO save(ConsultaTO medico) {
        consultaDAO = new ConsultaDAO();

        return consultaDAO.save(medico);
    }

    public ConsultaTO update(ConsultaTO medico) {
        consultaDAO = new ConsultaDAO();

        // Se id for vazio
        if (medico.getId() == null) {
            return null;
        }

        return consultaDAO.update(medico);
    }

    public boolean delete(Long id) {
        consultaDAO = new ConsultaDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return consultaDAO.delete(id);
    }

    public ConsultaTO findById(Long id) {
        consultaDAO = new ConsultaDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return consultaDAO.findById(id);
    }

    public ArrayList<ConsultaTO> findAll() {
        consultaDAO = new ConsultaDAO();

        return consultaDAO.findAll();
    }
}
