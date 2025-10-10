package br.com.fiap.dao;

import br.com.fiap.to.RemedioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RemedioDAO {

    public RemedioTO save(RemedioTO remedio) {
        String sql = "INSERT INTO DDD_REMEDIOS(nome, preco, data_de_fabricacao, data_de_validade) VALUES(?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, remedio.getNome());
            preparedStatement.setDouble(2, remedio.getPreco());
            preparedStatement.setDate(3, Date.valueOf(remedio.getDataFabricacao()));
            preparedStatement.setDate(4, Date.valueOf(remedio.getDataValidade()));
            if (preparedStatement.executeUpdate() > 0) {
                return remedio;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao criar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public RemedioTO update(Long id, RemedioTO remedio) {
        String sql = "UPDATE DDD_REMEDIOS SET nome = ?, preco = ?, data_de_fabricacao = ?, data_de_validade = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, remedio.getNome());
            preparedStatement.setDouble(2, remedio.getPreco());
            preparedStatement.setDate(3, Date.valueOf(remedio.getDataFabricacao()));
            preparedStatement.setDate(4, Date.valueOf(remedio.getDataValidade()));
            preparedStatement.setLong(5, id);
            if (preparedStatement.executeUpdate() > 0) {
                return remedio;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
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
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public RemedioTO findById(Long id) {
        String sql = "SELECT * FROM DDD_REMEDIOS WHERE id = ?";
        RemedioTO remedio = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                remedio = new RemedioTO();
                remedio.setId(resultSet.getLong("id"));
                remedio.setNome(resultSet.getString("nome"));
                remedio.setPreco(resultSet.getDouble("preco"));
                remedio.setDataFabricacao(resultSet.getDate("data_de_fabricacao").toLocalDate());
                remedio.setDataValidade(resultSet.getDate("data_de_validade").toLocalDate());
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return remedio;
    }

    public ArrayList<RemedioTO> findAll() {
        String sql = "SELECT * FROM DDD_REMEDIOS ORDER BY ID";
        ArrayList<RemedioTO> remedios = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    RemedioTO remedio = new RemedioTO();
                    remedio.setId(resultSet.getLong("id"));
                    remedio.setNome(resultSet.getString("nome"));
                    remedio.setPreco(resultSet.getDouble("preco"));
                    remedio.setDataFabricacao(resultSet.getDate("data_de_fabricacao").toLocalDate());
                    remedio.setDataValidade(resultSet.getDate("data_de_validade").toLocalDate());
                    remedios.add(remedio);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar remedios: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return remedios;
    }
}
