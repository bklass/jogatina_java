/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entidade.Reserva;
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
import rn.ReservaRN;

/**
 *
 * @author Bruna
 */
@Path("reservas")
public class ReservaWS {
    ReservaRN ReservaRN;
    @Context
    private UriInfo context;

    public ReservaWS() {
        ReservaRN = new ReservaRN();
    }
      @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Reserva getReservaPorId(@PathParam("id") Long id) {
        return ReservaRN.buscarPorId(id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reserva> getClientes() {
        return (ReservaRN.listar());

    }
    
    @POST    
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reserva adicionar(Reserva reserva,
    @Context HttpServletResponse response) {

        ReservaRN.inserir(reserva);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new javax.ws.rs.InternalServerErrorException();
        }
        return reserva;
    }
    /**
     * PUT method for updating or creating an instance of ClienteWS
     * @param content representation for the resource
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reserva atualiza(@PathParam("id") Long id,
            Reserva reserva){
        reserva.setId(id);
        Reserva reservaAtualizado = ReservaRN.atualizar(reserva);
        return reservaAtualizado;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Reserva deletar(@PathParam("id") Long id){
        Reserva reservaDeletado = ReservaRN.deletar(id);
        return reservaDeletado;
    }  
    
}
