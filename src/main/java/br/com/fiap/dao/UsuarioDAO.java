package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO {

    public UsuarioTO save(UsuarioTO usuario) {
        String sql = "INSERT INTO T_HR_USUARIOS(nome, email, senha) VALUES(?, ?, ?)";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());
            if (preparedStatement.executeUpdate() > 0) {
                return usuario;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public UsuarioTO update(UsuarioTO usuario) {
        String sql = "UPDATE T_HR_USUARIOS SET nome = ?, email = ?, senha = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setLong(4, usuario.getId());
            if (preparedStatement.executeUpdate() > 0) {
                return usuario;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM T_HR_USUARIOS WHERE id = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Erro ao excluir usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public UsuarioTO findById(Long id) {
        String sql = "SELECT * FROM T_HR_USUARIOS WHERE id = ?";
        UsuarioTO usuario = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new UsuarioTO();
                usuario.setId(resultSet.getLong("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuario;
    }

    public ArrayList<UsuarioTO> findAll() {
        String sql = "SELECT * FROM T_HR_USUARIOS ORDER BY ID";
        ArrayList<UsuarioTO> usuarios = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setId(resultSet.getLong("id"));
                    usuario.setNome(resultSet.getString("nome"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setSenha(resultSet.getString("senha"));
                    usuarios.add(usuario);
                }
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuarios: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuarios;
    }

    public String login(String email, String senha) {
        String sql = "SELECT usuario FROM T_HR_USUARIOS usuario WHERE email = ? AND senha = ?";

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);
            if (preparedStatement.executeUpdate() > 0) {
                return "Usuário está autorizado para acessar aplicação";
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Erro ao logar usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
