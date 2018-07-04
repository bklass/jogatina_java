/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entidade.Mesa;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import rn.MesaRN;

/**
 *
 * @author Bruna
 */
@Path("mesas")
public class MesaWS {
    MesaRN mesaRN;
    
    @Context
    private UriInfo context;
    
    public MesaWS(){
        mesaRN = new MesaRN();
        
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mesa getMesaPorId(@PathParam("id") Long id) {
        return mesaRN.buscarPorId(id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Mesa> getMesas() {
        return (mesaRN.listar());

    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mesa adicionar(Mesa mesa,
            @Context HttpServletResponse response) {

        mesaRN.inserir(mesa);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new javax.ws.rs.InternalServerErrorException();
        }
        return mesa;
    }
    

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mesa atualiza(@PathParam("id") Long id,
            Mesa mesa){
        mesa.setId(id);
        Mesa mesaAtualizado = mesaRN.atualizar(mesa);
        return mesaAtualizado;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mesa deletar(@PathParam("id") Long id){
        Mesa mesaDeletado = mesaRN.deletar(id);
        return mesaDeletado;
    }
    
}
