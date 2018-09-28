/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIs;

import DAOs.DAOAutor;
import DAOs.DAOObra;
import Entidades.Autor;
import Entidades.Obra;
import java.util.List;

/**
 *
 * @author alexa
 */
public class CRUDObraHasAutor {

    public static void main(String[] args) {
        Autor autor = new Autor();
        Obra obra = new Obra();
        DAOAutor daoAutor = new DAOAutor();
        DAOObra daoObra = new DAOObra();

        autor = daoAutor.obter(1);
        List<Obra> lo = autor.getObraList();
        System.out.println("tamanho de lo: " + lo.size());

        lo.add(daoObra.obter("1"));
        autor.setObraList(lo);
        daoAutor.atualizar(autor);
    }

}
