package br.com.fiap.dao;

import br.com.fiap.to.AcompanhanteTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class AcompanhanteDAO {

    public AcompanhanteTO save(AcompanhanteTO acompanhante) {
        String sql = "INSERT INTO T_HR_ACOMPANHANTES(nome, telefone_id, parentesco, email, data_cadastro) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, acompanhante.getNome());
            preparedStatement.setLong(2, acompanhante.getTelefone().getId());
            preparedStatement.setString(3, acompanhante.getParentesco());
            preparedStatement.setString(4, acompanhante.getEmail());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(acompanhante.getDataCadastro()));
            if (preparedStatement.executeUpdate() > 0) {
                return acompanhante;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar acompanhante: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public AcompanhanteTO update(AcompanhanteTO acompanhante) {
        String sql = "UPDATE T_HR_ACOMPANHANTES SET nome = ?, telefone_id = ?, parentesco = ?, email = ?, data_cadastro = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, acompanhante.getNome());
            preparedStatement.setLong(2, acompanhante.getTelefone().getId());
            preparedStatement.setString(3, acompanhante.getParentesco());
            preparedStatement.setString(4, acompanhante.getEmail());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(acompanhante.getDataCadastro()));
            preparedStatement.setLong(6, acompanhante.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return acompanhante;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar acompanhante: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_HR_ACOMPANHANTES WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir acompanhante: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public AcompanhanteTO findById(Long id) {
        String sql = "SELECT * FROM T_HR_ACOMPANHANTES WHERE id = ?";
        AcompanhanteTO acompanhante = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                acompanhante = new AcompanhanteTO();
                acompanhante.setId(resultSet.getLong("id"));

                TelefoneDAO telefoneDAO = new TelefoneDAO();
                acompanhante.setTelefone(telefoneDAO.findById(resultSet.getLong("telefone_id")));

                acompanhante.setParentesco(resultSet.getString("parentesco"));
                acompanhante.setEmail(resultSet.getString("email"));
                acompanhante.setDataCadastro(resultSet.getTimestamp("data_cadastro").toLocalDateTime());
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar acompanhante: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return acompanhante;
    }

    public ArrayList<AcompanhanteTO> findAll() {
        String sql = "SELECT * FROM T_HR_ACOMPANHANTES ORDER BY ID";
        ArrayList<AcompanhanteTO> acompanhantes = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    AcompanhanteTO acompanhante = new AcompanhanteTO();
                    acompanhante.setId(resultSet.getLong("id"));

                    TelefoneDAO telefoneDAO = new TelefoneDAO();
                    acompanhante.setTelefone(telefoneDAO.findById(resultSet.getLong("telefone_id")));

                    acompanhante.setParentesco(resultSet.getString("parentesco"));
                    acompanhante.setEmail(resultSet.getString("email"));
                    acompanhante.setDataCadastro(resultSet.getTimestamp("data_cadastro").toLocalDateTime());
                    acompanhantes.add(acompanhante);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar acompanhantes: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return acompanhantes;
    }
}
