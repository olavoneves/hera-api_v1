package br.com.fiap.bo;

import br.com.fiap.dao.MedicoDAO;
import br.com.fiap.to.MedicoTO;

import java.util.ArrayList;

public class MedicoBO {
    private MedicoDAO medicoDAO;

    public MedicoTO save(MedicoTO medico) {
        medicoDAO = new MedicoDAO();

        return medicoDAO.save(medico);
    }

    public MedicoTO update(MedicoTO medico) {
        medicoDAO = new MedicoDAO();

        // Se id for vazio
        if (medico.getId() == null) {
            return null;
        }

        return medicoDAO.update(medico);
    }

    public boolean delete(Long id) {
        medicoDAO = new MedicoDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return medicoDAO.delete(id);
    }

    public MedicoTO findById(Long id) {
        medicoDAO = new MedicoDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return medicoDAO.findById(id);
    }

    public ArrayList<MedicoTO> findAll() {
        medicoDAO = new MedicoDAO();

        return medicoDAO.findAll();
    }
}
