package br.com.fiap.dao;

import br.com.fiap.to.TelefoneTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TelefoneDAO
{

    public TelefoneTO save(TelefoneTO telefone) {
        String sql = "INSERT INTO T_HR_TELEFONES(ddd, numero, tipoDeTelefone) VALUES(?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, telefone.getDdd());
            preparedStatement.setString(2, telefone.getNumero());
            preparedStatement.setString(3, telefone.getTipoDeTelefone());
            if (preparedStatement.executeUpdate() > 0) {
                return telefone;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar telefone: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public TelefoneTO update(TelefoneTO telefone) {
        String sql = "UPDATE T_HR_TELEFONES SET ddd = ?, numero = ?, tipoDeTelefone = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, telefone.getDdd());
            preparedStatement.setString(2, telefone.getNumero());
            preparedStatement.setString(3, telefone.getTipoDeTelefone());
            preparedStatement.setLong(4, telefone.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return telefone;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar telefone: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_HR_TELEFONES WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir telefone: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public TelefoneTO findById(Long id) {
        String sql = "SELECT * FROM T_HR_TELEFONES WHERE id = ?";
        TelefoneTO telefone = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                telefone = new TelefoneTO();
                telefone.setId(resultSet.getLong("id"));
                telefone.setDdd(resultSet.getString("ddd"));
                telefone.setNumero(resultSet.getString("numero"));
                telefone.setTipoDeTelefone(resultSet.getString("tipo_de_telefone"));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar telefone: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return telefone;
    }

    public ArrayList<TelefoneTO> findAll() {
        String sql = "SELECT * FROM T_HR_TELEFONES ORDER BY ID";
        ArrayList<TelefoneTO> telefones = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    TelefoneTO telefone = new TelefoneTO();
                    telefone.setId(resultSet.getLong("id"));
                    telefone.setDdd(resultSet.getString("ddd"));
                    telefone.setNumero(resultSet.getString("numero"));
                    telefone.setTipoDeTelefone(resultSet.getString("tipo_de_telefone"));
                    telefones.add(telefone);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar telefones: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return telefones;
    }
}
