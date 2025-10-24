package br.com.fiap.resource;

import br.com.fiap.bo.LoginBO;
import br.com.fiap.to.LoginTO;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hera-api/login")
public class LoginResource {
    private LoginBO loginBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@Valid LoginTO login) {
        loginBO = new LoginBO();
        LoginTO resultado = loginBO.login(login.getEmail(), login.getSenha());
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();

    }
}
