package br.com.fiap.dao;

import br.com.fiap.to.MedicoTO;
import br.com.fiap.to.TelefoneTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MedicoDAO {

    public MedicoTO save(MedicoTO medico) {
        String sql = "INSERT INTO T_HR_MEDICOS(nm_medico, cd_crm, ds_especialidade, em_medico, id_telefone, st_medico) VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {

            TelefoneTO telefoneMedico = medico.getTelefone();
            TelefoneDAO telefoneDAO = new TelefoneDAO();
            telefoneMedico = telefoneDAO.save(telefoneMedico);

            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setString(2, medico.getCrm());
            preparedStatement.setString(3, medico.getEspecialidade());
            preparedStatement.setString(4, medico.getEmail());
            preparedStatement.setLong(5, telefoneMedico.getId());
            preparedStatement.setString(6, medico.getStatus());
            if (preparedStatement.executeUpdate() > 0) {
                return medico;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar medico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public MedicoTO update(MedicoTO medico) {
        String sql = "UPDATE T_HR_MEDICOS SET nm_medico = ?, cd_crm = ?, ds_especialidade = ?, em_medico = ?, id_telefone = ?, st_medico = ? WHERE id_medico = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {

            TelefoneTO telefoneMedico = medico.getTelefone();
            if (telefoneMedico != null) {
                TelefoneDAO telefoneDAO = new TelefoneDAO();
                telefoneMedico = telefoneDAO.update(telefoneMedico);
            }

            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setString(2, medico.getCrm());
            preparedStatement.setString(3, medico.getEspecialidade());
            preparedStatement.setString(4, medico.getEmail());
            preparedStatement.setLong(5, telefoneMedico.getId());
            preparedStatement.setString(6, medico.getStatus());
            preparedStatement.setLong(7, medico.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return medico;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar medico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_HR_MEDICOS WHERE id_medico = ?";

        try {
            MedicoTO medico = findById(id);
            if (medico != null && medico.getTelefone() != null) {
                new TelefoneDAO().delete(medico.getTelefone().getId());
            }

            try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                return preparedStatement.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Erro ao excluir medico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public MedicoTO findById(Long id) {
        String sql = "SELECT * FROM T_HR_MEDICOS WHERE id_medico = ?";
        MedicoTO medico = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                medico = new MedicoTO();
                medico.setId(resultSet.getLong("id_medico"));
                medico.setNome(resultSet.getString("nm_medico"));
                medico.setCrm(resultSet.getString("cd_crm"));
                medico.setEspecialidade(resultSet.getString("ds_especialidade"));
                medico.setEmail(resultSet.getString("em_medico"));

                TelefoneDAO telefoneDAO = new TelefoneDAO();
                medico.setTelefone(telefoneDAO.findById(resultSet.getLong("id_telefone")));

                medico.setStatus(resultSet.getString("st_medico"));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar medico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return medico;
    }

    public ArrayList<MedicoTO> findAll() {
        String sql = "SELECT * FROM T_HR_MEDICOS ORDER BY id_medico";
        ArrayList<MedicoTO> medicos = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    MedicoTO medico = new MedicoTO();
                    medico.setId(resultSet.getLong("id_medico"));
                    medico.setNome(resultSet.getString("nm_medico"));
                    medico.setCrm(resultSet.getString("cd_crm"));
                    medico.setEspecialidade(resultSet.getString("ds_especialidade"));
                    medico.setEmail(resultSet.getString("em_medico"));

                    TelefoneDAO telefoneDAO = new TelefoneDAO();
                    medico.setTelefone(telefoneDAO.findById(resultSet.getLong("id_telefone")));

                    medico.setStatus(resultSet.getString("st_medico"));
                    medicos.add(medico);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar medicos: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return medicos;
    }
}
