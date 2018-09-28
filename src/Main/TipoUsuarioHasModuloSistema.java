/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DAOs.DAOModuloSistema;
import DAOs.DAOTipoUsuario;
import Entidades.ModuloSistema;
import Entidades.TipoUsuario;
import java.util.List;

/**
 *
 * @author a1602896
 */
public class TipoUsuarioHasModuloSistema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ModuloSistema moduloSistema = new ModuloSistema();
        TipoUsuario tipoUsuario = new TipoUsuario();
        DAOModuloSistema daoModuloSistema = new DAOModuloSistema();
        DAOTipoUsuario daoTipoUsuario = new DAOTipoUsuario();

        moduloSistema = daoModuloSistema.obter(1);
        List<TipoUsuario> lo = moduloSistema.getTipoUsuarioList();
        System.out.println("tamanho de lo: " + lo.size());

        lo.add(daoTipoUsuario.obter("1"));
        moduloSistema.setTipoUsuarioList(lo);
        daoModuloSistema.atualizar(moduloSistema);

    }

}
