package br.com.fiap.bo;

import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.to.PacienteTO;

import java.util.ArrayList;

public class PacienteBO {
    private PacienteDAO pacienteDAO;

    public PacienteTO save(PacienteTO paciente) {
        pacienteDAO = new PacienteDAO();

        return pacienteDAO.save(paciente);
    }

    public PacienteTO update(PacienteTO paciente) {
        pacienteDAO = new PacienteDAO();

        // Se id for vazio
        if (paciente.getId() == null) {
            return null;
        }

        return pacienteDAO.update(paciente);
    }

    public boolean delete(Long id) {
        pacienteDAO = new PacienteDAO();

        // Se id for vazio
        if (id == null) {
            return false;
        }

        return pacienteDAO.delete(id);
    }

    public PacienteTO findById(Long id) {
        pacienteDAO = new PacienteDAO();

        // Se id for vazio
        if (id == null) {
            return null;
        }

        return pacienteDAO.findById(id);
    }

    public ArrayList<PacienteTO> findAll() {
        pacienteDAO = new PacienteDAO();

        return pacienteDAO.findAll();
    }
}
