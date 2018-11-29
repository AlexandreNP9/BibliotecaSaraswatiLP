package DAOs;

import Entidades.ModuloSistema;
import java.util.ArrayList;
import java.util.List;

public class DAOModuloSistemaHasTipoUsuario extends DAOGenerico<ModuloSistema> {

    public DAOModuloSistemaHasTipoUsuario() {
        super(ModuloSistema.class);
    }

    public int autoIdModuloSistemaHasTipoUsuario() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idModuloSistemaHasTipoUsuario) FROM ModuloSistemaHasTipoUsuario e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

//    public List<ModuloSistemaHasTipoUsuario> listByNome(String nome) {
//        return em.createQuery("SELECT e FROM ModuloSistemaHasTipoUsuario e WHERE e.nomeModuloSistemaHasTipoUsuario LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
//    }

    public List<ModuloSistema> listById(int id) {
        return em.createQuery("SELECT e FROM ModuloSistemaHasTipoUsuario e WHERE e.idModuloSistemaHasTipoUsuario = :id").setParameter("id", id).getResultList();
    }

//    public List<ModuloSistemaHasTipoUsuario> listInOrderNome() {
//        return em.createQuery("SELECT e FROM ModuloSistemaHasTipoUsuario e ORDER BY e.nomeModuloSistemaHasTipoUsuario").getResultList();
//    }

    public List<ModuloSistema> listInOrderId() {
        return em.createQuery("SELECT e FROM ModuloSistemaHasTipoUsuario e ORDER BY e.idModuloSistemaHasTipoUsuario").getResultList();
    }

//    public List<String> listInOrderNomeStrings(String qualOrdem) {
//        List<ModuloSistemaHasTipoUsuario> lf;
//        if (qualOrdem.equals("id")) {
//            lf = listInOrderId();
//        } else {
//            lf = listInOrderNome();
//        }
//
//        List<String> ls = new ArrayList<>();
//        for (int i = 0; i < lf.size(); i++) {
//            ls.add(lf.get(i).getIdModuloSistemaHasTipoUsuario() + "-" + lf.get(i).getNomeModuloSistemaHasTipoUsuario());
//        }
//        return ls;
//    }
}
