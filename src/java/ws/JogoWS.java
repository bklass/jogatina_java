/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import entidade.Jogo;
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
import rn.JogoRN;

/**
 * REST Web Service
 *
 * @author Bruna
 */
@Path("jogos")
public class JogoWS {
    
    JogoRN jogoRN;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of JogoWS
     */
    public JogoWS() {
        jogoRN = new JogoRN();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Jogo getJogoPorId(@PathParam("id") Long id) {
        return jogoRN.buscarPorId(id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Jogo> getJogos() {
        return (jogoRN.listar());

    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Jogo adicionar(Jogo jogo,
            @Context HttpServletResponse response) {

        jogoRN.inserir(jogo);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException ex) {
            throw new javax.ws.rs.InternalServerErrorException();
        }
        return jogo;
    }
    

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Jogo atualiza(@PathParam("id") Long id,
            Jogo jogo){
        jogo.setId(id);
        Jogo jogoAtualizado = jogoRN.atualizar(jogo);
        return jogoAtualizado;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Jogo deletar(@PathParam("id") Long id){
        Jogo jogoDeletado = jogoRN.deletar(id);
        return jogoDeletado;
    }
}
