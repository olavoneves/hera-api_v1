package br.com.fiap.dao;

import br.com.fiap.to.PacienteTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PacienteDAO {

    public PacienteTO save(PacienteTO paciente) {
        String sql = "INSERT INTO DDD_REMEDIOS(nome, preco, data_de_fabricacao, data_de_validade) VALUES(?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, remedio.getNome());
            preparedStatement.setDouble(2, remedio.getPreco());
            preparedStatement.setDate(3, Date.valueOf(remedio.getDataFabricacao()));
            preparedStatement.setDate(4, Date.valueOf(remedio.getDataValidade()));
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

    public PacienteTO update(Long id, PacienteTO paciente) {
        String sql = "UPDATE DDD_REMEDIOS SET nome = ?, preco = ?, data_de_fabricacao = ?, data_de_validade = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, remedio.getNome());
            preparedStatement.setDouble(2, remedio.getPreco());
            preparedStatement.setDate(3, Date.valueOf(remedio.getDataFabricacao()));
            preparedStatement.setDate(4, Date.valueOf(remedio.getDataValidade()));
            preparedStatement.setLong(5, id);
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
        String sql = "DELETE FROM DDD_REMEDIOS WHERE id = ?";
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
        String sql = "SELECT * FROM DDD_REMEDIOS WHERE id = ?";
        PacienteTO paciente = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                paciente = new PacienteTO();

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
        String sql = "SELECT * FROM DDD_REMEDIOS ORDER BY ID";
        ArrayList<PacienteTO> pacientes = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    PacienteTO paciente = new PacienteTO();

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
