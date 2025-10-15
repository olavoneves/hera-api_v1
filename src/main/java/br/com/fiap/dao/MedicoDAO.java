package br.com.fiap.dao;

import br.com.fiap.to.MedicoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MedicoDAO {

    public MedicoTO save(MedicoTO medico) {
        String sql = "INSERT INTO T_HR_MEDICOS(nome, crm, especialidade, email, telefone, status) VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setString(2, medico.getCrm());
            preparedStatement.setString(3, medico.getEspecialidade());
            preparedStatement.setString(4, medico.getEmail());
            preparedStatement.setLong(5, medico.getTelefone().getId());
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
        String sql = "UPDATE T_HR_MEDICOS SET nome = ?, crm = ?, especialidade = ?, email = ?, telefone = ?, status = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, medico.getNome());
            preparedStatement.setString(2, medico.getCrm());
            preparedStatement.setString(3, medico.getEspecialidade());
            preparedStatement.setString(4, medico.getEmail());
            preparedStatement.setLong(5, medico.getTelefone().getId());
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
        String sql = "DELETE FROM T_HR_MEDICOS WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir medico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public MedicoTO findById(Long id) {
        String sql = "SELECT * FROM T_HR_MEDICOS WHERE id = ?";
        MedicoTO medico = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                medico = new MedicoTO();
                medico.setId(resultSet.getLong("id"));
                medico.setNome(resultSet.getString("nome"));
                medico.setCrm(resultSet.getString("crm"));
                medico.setEspecialidade(resultSet.getString("especialidade"));
                medico.setEmail(resultSet.getString("email"));
                medico.setTelefone(resultSet.getObject());
                medico.setStatus(resultSet.getString("status"));
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
        String sql = "SELECT * FROM T_HR_MEDICOS ORDER BY ID";
        ArrayList<MedicoTO> medicos = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    MedicoTO medico = new MedicoTO();
                    medico.setId(resultSet.getLong("id"));
                    medico.setNome(resultSet.getString("nome"));
                    medico.setCrm(resultSet.getString("crm"));
                    medico.setEspecialidade(resultSet.getString("especialidade"));
                    medico.setEmail(resultSet.getString("email"));
                    medico.setTelefone(resultSet.getObject());
                    medico.setStatus(resultSet.getString("status"));
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
