package DAOs;

import Entidades.ModuloSistema;
import java.util.ArrayList;
import java.util.List;

public class DAOModuloSistema extends DAOGenerico<ModuloSistema> {

    public DAOModuloSistema() {
        super(ModuloSistema.class);
    }

    public int autoIdModuloSistema() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idModuloSistema) FROM ModuloSistema e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<ModuloSistema> listByNome(String nome) {
        return em.createQuery("SELECT e FROM ModuloSistema e WHERE e.nomeModuloSistema LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<ModuloSistema> listById(int id) {
        return em.createQuery("SELECT e FROM ModuloSistema e WHERE e.idModuloSistema = :id").setParameter("id", id).getResultList();
    }

    public List<ModuloSistema> listInOrderNome() {
        return em.createQuery("SELECT e FROM ModuloSistema e ORDER BY e.nomeModuloSistema").getResultList();
    }

    public List<ModuloSistema> listInOrderId() {
        return em.createQuery("SELECT e FROM ModuloSistema e ORDER BY e.idModuloSistema").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<ModuloSistema> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdModuloSistema() + "-" + lf.get(i).getNomeModuloSistema());
        }
        return ls;
    }
}
