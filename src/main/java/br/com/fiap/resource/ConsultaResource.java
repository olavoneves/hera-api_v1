package br.com.fiap.resource;

import br.com.fiap.bo.ConsultaBO;
import br.com.fiap.to.ConsultaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/hera-api/consultas")
public class ConsultaResource {
    private ConsultaBO consultaBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid ConsultaTO consulta) {
        consultaBO = new ConsultaBO();
        ConsultaTO resultado = consultaBO.save(consulta);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, @Valid ConsultaTO consulta) {
        consultaBO = new ConsultaBO();
        consulta.setId(id);
        ConsultaTO resultado = consultaBO.update(consulta);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null);
        } else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        consultaBO = new ConsultaBO();
        Response.ResponseBuilder response = null;

        if (consultaBO.delete(id)) {
            response = Response.status(204);
        } else {
            response = Response.status(404);
        }
        return response.build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        consultaBO = new ConsultaBO();
        ConsultaTO resultado = consultaBO.findById(id);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        consultaBO = new ConsultaBO();
        ArrayList<ConsultaTO> resultado = consultaBO.findAll();
        Response.ResponseBuilder response = null;

        if (!resultado.isEmpty()) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }
}
