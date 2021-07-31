/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author GUI
 */
@Named(value="controleEstado")
@ViewScoped
public class ControleEstado implements Serializable {
    @EJB
    private EstadoDAO<Estado> dao;
    private Estado objeto;
    
    public ControleEstado(){
        
    }
    
    public String listar(){
        return "/privado/estado/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Estado();
    }
    
    public void alterar(Object id){
        try{
            this.objeto = dao.localizar(id);
        }catch(Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id){
        try{
            objeto = dao.localizar(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso");
            
        }catch(Exception e){
            Util.mensagemErro("Erro ao remover objeto: "+Util.getMensagemErro(e));
        }
    }

    public void salvar(){
        System.out.println("obejto:" + objeto.getNome());
        try{
            if(objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        
        }catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public EstadoDAO<Estado> getDao() {
        return dao;
    }

    public void setDao(EstadoDAO<Estado> dao) {
        this.dao = dao;
    }

    public Estado getObjeto() {
        return objeto;
    }

    public void setObjeto(Estado objeto) {
        this.objeto = objeto;
    }

    
}
