package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;

public class LoginBO {
    private UsuarioDAO usuarioDAO;

    public String login(String email, String senha) {
        usuarioDAO = new UsuarioDAO();

        if (email.isEmpty() || senha.isEmpty()) {
            return "E-mail ou Senha estão vazios";
        }

        return usuarioDAO.login(email, senha);
    }
}
