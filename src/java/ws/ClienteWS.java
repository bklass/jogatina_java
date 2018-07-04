/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entidade.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import rn.ClienteRN;

/**
 * REST Web Service
 *
 * @author Niffa
 */
@Path("clientes")
public class ClienteWS {

    ClienteRN ClienteRN;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClienteWS
     */
    public ClienteWS() {
        ClienteRN = new ClienteRN();
    }

    /**
     * Retrieves representation of an instance of ws.ClienteWS
     * @return an instance of model.Cliente
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente getClientePorId(@PathParam("id") Long id) {
        return ClienteRN.buscarPorId(id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getClientes() {
        return (ClienteRN.listar());

    }
    
    @POST    
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente adicionar(Cliente cliente,
    @Context HttpServletResponse response) {

        ClienteRN.inserir(cliente);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new javax.ws.rs.InternalServerErrorException();
        }
        return cliente;
    }
    /**
     * PUT method for updating or creating an instance of ClienteWS
     * @param content representation for the resource
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente atualiza(@PathParam("id") Long id,
            Cliente cliente){
        cliente.setId(id);
        Cliente clienteAtualizado = ClienteRN.atualizar(cliente);
        return clienteAtualizado;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente deletar(@PathParam("id") Long id){
        Cliente clienteDeletado = ClienteRN.deletar(id);
        return clienteDeletado;
    }
}
