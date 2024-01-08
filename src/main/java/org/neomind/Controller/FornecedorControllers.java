package org.neomind.Controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.neomind.Entity.Fornecedor;
import org.neomind.Service.FornecedorService;

import java.util.List;

@Path("fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorControllers {
    @Inject
    private FornecedorService fornecedorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{startIndex}-{endIndex}")
    public List<Fornecedor> getAllFornecedoresPaginados(@PathParam("startIndex") int startIndex, @PathParam("endIndex") int endIndex) {
        return fornecedorService.getAllFornecedoresPaginados(startIndex,endIndex);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Fornecedor> getAllFornecedores() {
        return fornecedorService.getAllFornecedores();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("buscar/fornecedor/{id}")
    public Fornecedor getFornecedorById(@PathParam("id") Long id) {
        return fornecedorService.getFornecedorById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inserir/fornecedor")
    public Response insertFornecedor(List<Fornecedor> fornecedor) throws Exception {
        for (Fornecedor eachFornecedor : fornecedor) {
            if (fornecedorService.validateCnpj(eachFornecedor.getCnpj())) {
                if (fornecedorService.validateEmail(eachFornecedor.getEmail())) {
                    fornecedorService.createFornecedor(eachFornecedor);
                } else {
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity(eachFornecedor.getName()+" E-mail inválido")
                            .build();
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(eachFornecedor.getName()+" CNPJ inválido")
                        .build();
            }
        }

        return Response.ok("Fornecedor inserido com sucesso").entity(fornecedor).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("editar/fornecedor/{id}")
    public Response editFornecedor(@PathParam("id") Long id, Fornecedor fornecedor) throws Exception {
        if (fornecedorService.validateCnpj(fornecedor.getCnpj())) {
            if (fornecedorService.validateEmail(fornecedor.getEmail())) {
                fornecedorService.editFornecedor(id, fornecedor);
                return Response.ok("Fornecedor editado com sucesso").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("E-mail inválido")
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("CNPJ inválido")
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("delete/fornecedor/{id}")
    public Response deleteFornecedor(@PathParam("id") Long id) throws Exception {
        fornecedorService.deleteFornecedor(id);
        return Response.status(Response.Status.ACCEPTED).build();
    }

}
