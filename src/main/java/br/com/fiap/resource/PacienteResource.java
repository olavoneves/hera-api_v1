package br.com.fiap.resource;

import br.com.fiap.bo.PacienteBO;
import br.com.fiap.to.PacienteTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/hera-api/pacientes")
public class PacienteResource {
    private PacienteBO pacienteBO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid PacienteTO paciente) {
        pacienteBO = new PacienteBO();
        PacienteTO resultado = pacienteBO.save(paciente);
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id,@Valid PacienteTO paciente) {
        pacienteBO = new PacienteBO();
        PacienteTO resultado = pacienteBO.update(id, paciente);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);

        return response.build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Response.ResponseBuilder response = null;

        if (pacienteBO.delete(id)) {
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
        pacienteBO = new PacienteBO();
        PacienteTO resultado = pacienteBO.findById(id);
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
        pacienteBO = new PacienteBO();
        ArrayList<PacienteTO> resultado = pacienteBO.findAll();
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
