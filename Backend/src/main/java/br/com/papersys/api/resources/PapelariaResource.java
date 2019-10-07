package br.com.papersys.api.resources;

import br.com.papersys.api.model.Papelaria;
import br.com.papersys.api.service.PapelariaService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/Papelaria")
@Path("papelaria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PapelariaResource {
  private PapelariaService service;

  public PapelariaResource(PapelariaService service) {
    this.service = service;
  }

  @ApiOperation(value = "Obtém todos os itens cadastrados.", response = Papelaria.class)
  @GET
  @Path("/listar-todos")
  @UnitOfWork
  public Response buscarTodos() {
    try {
      return Response.ok(service.buscarTodos()).build();
    } catch (Exception ex) {
      return Response.serverError().entity(ex.getMessage()).build();
    }
  }

  @ApiOperation(value = "Obtém um item por identificador.", response = Papelaria.class)
  @GET
  @Path("/{id}")
  @UnitOfWork
  public Response buscarPorId(
      @ApiParam("O identificador do item da papelaria.") @PathParam("id") long id) {
    try {
      var item = service.buscarPorId(id);

      if (item == null) {
        return Response.status(Response.Status.NOT_FOUND)
            .entity("O item com id: " + id + " não foi encontrado.")
            .build();
      }

      return Response.ok().build();
    } catch (Exception ex) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
  }

  @ApiOperation(value = "Cadastra um item na papelaria.", response = Papelaria.class)
  @POST
  @UnitOfWork
  public Response cadastrar(@ApiParam("O contrato para cadastrar um item.") Papelaria papelaria) {
    try {
      var contrato = service.cadastrar(papelaria);
      return Response.status(Response.Status.CREATED).entity(contrato).build();
    } catch (Exception ex) {
      return Response.serverError().entity(ex.getMessage()).build();
    }
  }

  @ApiOperation(
      value = "Edita um item informando seu identificador e contrato no body.",
      response = Papelaria.class)
  @PUT
  @Path("/{id}")
  @UnitOfWork
  public Response editar(
      @ApiParam("O id do item a ser editado.") @PathParam("id") LongParam id,
      @ApiParam("O contrato para editar um item.") Papelaria papelaria) {
    try {
      return Response.ok(service.editar(id.get(), papelaria)).build();
    } catch (Exception ex) {
      return Response.serverError().entity(ex.getMessage()).build();
    }
  }

  @ApiOperation(
      value = "Exclui um item da papelaria informando o identificador.",
      response = String.class)
  @DELETE
  @Path("/{id}")
  @UnitOfWork
  public Response excluir(@ApiParam("O id do item a ser excluído.") @PathParam("id") long id) {
    try {
      service.deletarPorId(id);
      return Response.ok("O item com id " + id + " foi removido com sucesso!!!").build();
    } catch (Exception ex) {
      return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
    }
  }
}
