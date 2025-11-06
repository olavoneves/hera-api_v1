package br.com.fiap.dao;

import br.com.fiap.to.LoginTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO {

    public UsuarioTO save(UsuarioTO usuario) {
        String sql = "INSERT INTO T_HR_USUARIOS(nm_usuario, em_usuario, pw_usuario) VALUES(?, ?, ?)";

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
        String sql = "UPDATE T_HR_USUARIOS SET nm_usuario = ?, em_usuario = ?, pw_usuario = ? WHERE id_usuario = ?";

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
        String sql = "DELETE FROM T_HR_USUARIOS WHERE id_usuario = ?";

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
        String sql = "SELECT * FROM T_HR_USUARIOS WHERE id_usuario = ?";
        UsuarioTO usuario = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new UsuarioTO();
                usuario.setId(resultSet.getLong("id_usuario"));
                usuario.setNome(resultSet.getString("nm_usuario"));
                usuario.setEmail(resultSet.getString("em_usuario"));
                usuario.setSenha(resultSet.getString("pw_usuario"));
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
        String sql = "SELECT * FROM T_HR_USUARIOS ORDER BY id_usuario";
        ArrayList<UsuarioTO> usuarios = new ArrayList<>();

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setId(resultSet.getLong("id_usuario"));
                    usuario.setNome(resultSet.getString("nm_usuario"));
                    usuario.setEmail(resultSet.getString("em_usuario"));
                    usuario.setSenha(resultSet.getString("pw_usuario"));
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

    public LoginTO login(String email, String senha) {
        String sql = "SELECT usuario.id_usuario, usuario.em_usuario, usuario.pw_usuario FROM T_HR_USUARIOS usuario WHERE em_usuario = ? AND pw_usuario = ?";
        LoginTO login = null;

        try (PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                login = new LoginTO();
                login.setIdUsuario(resultSet.getLong("id_usuario"));
                login.setEmail(resultSet.getString("em_usuario"));
                login.setSenha(resultSet.getString("pw_usuario"));
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("Erro ao logar usuario: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return login;
    }
}
