package br.com.fiap.dao;

import br.com.fiap.to.EnderecoTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EnderecoDAO {

    public EnderecoTO save(EnderecoTO endereco) {
        String sql = "INSERT INTO T_HR_ENDERECOS(cd_cep, ds_logradouro, ds_complemento, nm_bairro, sg_estado) VALUES(?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql,  new String[] {"id_endereco"})) {
            preparedStatement.setString(1, endereco.getCep());
            preparedStatement.setString(2, endereco.getLogradouro());
            preparedStatement.setString(3, endereco.getComplemento());
            preparedStatement.setString(4, endereco.getBairro());
            preparedStatement.setString(5, endereco.getEstado());

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        endereco.setId(rs.getLong(1));
                    }
                }
            }
            return endereco;

        } catch (Exception e) {
            System.out.println("Erro ao criar endereco: " + e.getMessage());
        }
        return null;
    }

    public EnderecoTO update(EnderecoTO endereco) {
        String sql = "UPDATE T_HR_ENDERECOS SET cd_cep = ?, ds_logradouro = ?, ds_complemento = ?, nm_bairro = ?, sg_estado = ? WHERE id_endereco = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, endereco.getCep());
            preparedStatement.setString(2, endereco.getLogradouro());
            preparedStatement.setString(3, endereco.getComplemento());
            preparedStatement.setString(4, endereco.getBairro());
            preparedStatement.setString(5, endereco.getEstado());
            preparedStatement.setLong(6, endereco.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return endereco;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar endereco: " + e.getMessage());
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_HR_ENDERECOS WHERE id_endereco = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir endereco: " + e.getMessage());
        }
        return false;
    }

    public EnderecoTO findById(Long id) {
        String sql = "SELECT * FROM T_HR_ENDERECOS WHERE id_endereco = ?";
        EnderecoTO endereco = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                endereco = new EnderecoTO();
                endereco.setId(resultSet.getLong("id_endereco"));
                endereco.setCep(resultSet.getString("cd_cep"));
                endereco.setCep(resultSet.getString("ds_logradouro"));
                endereco.setCep(resultSet.getString("ds_complemento"));
                endereco.setCep(resultSet.getString("nm_bairro"));
                endereco.setCep(resultSet.getString("sg_estado"));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar endereco: " + e.getMessage());
        }
        return endereco;
    }

    public ArrayList<EnderecoTO> findAll() {
        String sql = "SELECT * FROM T_HR_ENDERECOS ORDER BY id_endereco";
        ArrayList<EnderecoTO> enderecos = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    EnderecoTO endereco = new EnderecoTO();
                    endereco.setId(resultSet.getLong("id_endereco"));
                    endereco.setCep(resultSet.getString("cd_cep"));
                    endereco.setCep(resultSet.getString("ds_logradouro"));
                    endereco.setCep(resultSet.getString("ds_complemento"));
                    endereco.setCep(resultSet.getString("nm_bairro"));
                    endereco.setCep(resultSet.getString("sg_estado"));
                    enderecos.add(endereco);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar enderecos: " + e.getMessage());
        }
        return enderecos;
    }
}
