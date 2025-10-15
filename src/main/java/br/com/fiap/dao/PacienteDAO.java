package br.com.fiap.dao;

import br.com.fiap.to.PacienteTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PacienteDAO {

    public PacienteTO save(PacienteTO paciente) {
        String sql = "INSERT INTO T_HR_PACIENTES(nome, email, sexo, telefone_id, status, consultasRestantes, faltas, possuiDeficiencia, tipoDeficiencia, videoEnviado, dataNascimento, endereco_id, preferenciaContato, dataCadastro, ultimaAtualizacao, acompanhante_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getEmail());
            preparedStatement.setString(3, paciente.getSexo());
            preparedStatement.setLong(4, paciente.getTelefone().getId());
            preparedStatement.setString(5, paciente.getStatus());
            preparedStatement.setInt(6, paciente.getConsultasRestantes());
            preparedStatement.setInt(7, paciente.getFaltas());
            preparedStatement.setString(8, paciente.isPossuiDeficiencia() ? "1" : "0");
            preparedStatement.setString(9, paciente.getTipoDeficiencia());
            preparedStatement.setString(10, paciente.isVideoEnviado() ? "1" : "0");
            preparedStatement.setDate(11,  java.sql.Date.valueOf(paciente.getDataNascimento()));
            preparedStatement.setLong(12, paciente.getEndereco().getId());
            preparedStatement.setString(13, paciente.getPreferenciaContato());
            preparedStatement.setTimestamp(14, java.sql.Timestamp.valueOf(paciente.getDataCadastro()));
            preparedStatement.setTimestamp(15, java.sql.Timestamp.valueOf(paciente.getUltimaAtualizacao()));
            preparedStatement.setLong(16, paciente.getAcompanhante().getId());
            if (preparedStatement.executeUpdate() > 0) {
                return paciente;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public PacienteTO update(PacienteTO paciente) {
        String sql = "UPDATE T_HR_PACIENTES SET nome = ?, email = ?, sexo = ?, telefone_id = ?, status = ?, consultasRestantes = ?, faltas = ?, possuiDeficiencia = ?, tipoDeficiencia = ?, videoEnviado = ?, dataNascimento = ?, endereco_id = ?, preferenciaContato = ?, dataCadastro = ?, ultimaAtualizacao = ?, acompanhante_id = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setString(2, paciente.getEmail());
            preparedStatement.setString(3, paciente.getSexo());
            preparedStatement.setLong(4, paciente.getTelefone().getId());
            preparedStatement.setString(5, paciente.getStatus());
            preparedStatement.setInt(6, paciente.getConsultasRestantes());
            preparedStatement.setInt(7, paciente.getFaltas());
            preparedStatement.setString(8, paciente.isPossuiDeficiencia() ? "1" : "0");
            preparedStatement.setString(9, paciente.getTipoDeficiencia());
            preparedStatement.setString(10, paciente.isVideoEnviado() ? "1" : "0");
            preparedStatement.setDate(11, java.sql.Date.valueOf(paciente.getDataNascimento()));
            preparedStatement.setLong(12, paciente.getEndereco().getId());
            preparedStatement.setString(13, paciente.getPreferenciaContato());
            preparedStatement.setTimestamp(14, java.sql.Timestamp.valueOf(paciente.getDataCadastro()));
            preparedStatement.setTimestamp(15, java.sql.Timestamp.valueOf(paciente.getUltimaAtualizacao()));
            preparedStatement.setLong(16, paciente.getAcompanhante().getId());
            preparedStatement.setLong(17, paciente.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return paciente;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_HR_PACIENTES WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public PacienteTO findById(Long id) {
        String sql = "SELECT * FROM T_HR_PACIENTES WHERE id = ?";
        PacienteTO paciente = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                paciente = new PacienteTO();
                // Setar atributos
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return paciente;
    }

    public ArrayList<PacienteTO> findAll() {
        String sql = "SELECT * FROM T_HR_PACIENTES ORDER BY ID";
        ArrayList<PacienteTO> pacientes = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    PacienteTO paciente = new PacienteTO();
                    // Setar atributos
                    pacientes.add(paciente);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar pacientes: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pacientes;
    }
}
