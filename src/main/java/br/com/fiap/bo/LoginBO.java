package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.LoginTO;

public class LoginBO {
    private UsuarioDAO usuarioDAO;

    public LoginTO login(String email, String senha) {
        usuarioDAO = new UsuarioDAO();

        if (email.isEmpty() || senha.isEmpty()) {
            return null;
        }

        return usuarioDAO.login(email, senha);
    }
}
