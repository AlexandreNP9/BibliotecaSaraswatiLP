package DAOs;

import Entidades.ModuloSistema;
import Entidades.TipoUsuarioHasModuloSistema;
import java.util.List;

public class DAOTipoUsuarioHasModuloSistema extends DAOGenerico<TipoUsuarioHasModuloSistema> {

    public DAOTipoUsuarioHasModuloSistema() {
        super(ModuloSistema.class);
    }

    public int autoIdModuloSistemaHasTipoUsuario() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTipoUsuarioHasModuloSistema) FROM TipoUsuarioHasModuloSistema e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

//    public List<ModuloSistemaHasTipoUsuario> listByNome(String nome) {
//        return em.createQuery("SELECT e FROM ModuloSistemaHasTipoUsuario e WHERE e.nomeModuloSistemaHasTipoUsuario LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
//    }

    public List<TipoUsuarioHasModuloSistema> listById(int id) {
        return em.createQuery("SELECT e FROM TipoUsuarioHasModuloSistema e WHERE e.idTipoUsuarioHasModuloSistema = :id").setParameter("id", id).getResultList();
    }

//    public List<ModuloSistemaHasTipoUsuario> listInOrderNome() {
//        return em.createQuery("SELECT e FROM ModuloSistemaHasTipoUsuario e ORDER BY e.nomeModuloSistemaHasTipoUsuario").getResultList();
//    }

    public List<TipoUsuarioHasModuloSistema> listInOrderId() {
        return em.createQuery("SELECT e FROM TipoUsuarioHasModuloSistema e ORDER BY e.idTipoUsuarioHasModuloSistema").getResultList();
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
