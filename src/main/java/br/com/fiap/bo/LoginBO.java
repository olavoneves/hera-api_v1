package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

public class LoginBO {
    private UsuarioDAO usuarioDAO;

    public UsuarioTO login(String email, String senha) {
        usuarioDAO = new UsuarioDAO();

        if (email.isEmpty() || senha.isEmpty()) {
            return null;
        }

        return usuarioDAO.login(email, senha);
    }
}
