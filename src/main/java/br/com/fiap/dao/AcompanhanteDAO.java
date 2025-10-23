package br.com.fiap.dao;

import br.com.fiap.to.AcompanhanteTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class AcompanhanteDAO {

    public AcompanhanteTO save(AcompanhanteTO acompanhante) {
        String sql = "INSERT INTO T_HR_ACOMPANHANTES(nm_acompanhante, id_telefone, ds_parentesco, em_acompanhante, dt_cadastro) VALUES(?, ?, ?, ?, ?)";

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
        String sql = "UPDATE T_HR_ACOMPANHANTES SET nm_acompanhante = ?, id_telefone = ?, ds_parentesco = ?, em_acompanhante = ?, dt_cadastro = ? WHERE id_acompanhante = ?";

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
        String sql = "DELETE FROM T_HR_ACOMPANHANTES WHERE id_acompanhante = ?";

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
        String sql = "SELECT * FROM T_HR_ACOMPANHANTES WHERE id_acompanhante = ?";
        AcompanhanteTO acompanhante = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                acompanhante = new AcompanhanteTO();
                acompanhante.setId(resultSet.getLong("id_acompanhante"));
                acompanhante.setNome(resultSet.getString("nm_acompanhante"));

                TelefoneDAO telefoneDAO = new TelefoneDAO();
                acompanhante.setTelefone(telefoneDAO.findById(resultSet.getLong("id_telefone")));

                acompanhante.setParentesco(resultSet.getString("ds_parentesco"));
                acompanhante.setEmail(resultSet.getString("em_acompanhante"));
                acompanhante.setDataCadastro(resultSet.getTimestamp("dt_cadastro").toLocalDateTime());
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
        String sql = "SELECT * FROM T_HR_ACOMPANHANTES ORDER BY id_acompanhante";
        ArrayList<AcompanhanteTO> acompanhantes = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    AcompanhanteTO acompanhante = new AcompanhanteTO();
                    acompanhante.setId(resultSet.getLong("id_acompanhante"));
                    acompanhante.setNome(resultSet.getString("nm_acompanhante"));

                    TelefoneDAO telefoneDAO = new TelefoneDAO();
                    acompanhante.setTelefone(telefoneDAO.findById(resultSet.getLong("id_telefone")));

                    acompanhante.setParentesco(resultSet.getString("ds_parentesco"));
                    acompanhante.setEmail(resultSet.getString("em_acompanhante"));
                    acompanhante.setDataCadastro(resultSet.getTimestamp("dt_cadastro").toLocalDateTime());
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
