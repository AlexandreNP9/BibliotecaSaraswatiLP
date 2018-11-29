package DAOs;

import Entidades.AutorPublicaObra;
import java.util.ArrayList;
import java.util.List;

public class DAOAutorPublicaObra extends DAOGenerico<AutorPublicaObra> {

    public DAOAutorPublicaObra() {
        super(AutorPublicaObra.class);
    }

    public int autoIdAutorPublicaObra() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idAutorPublicaObra) FROM AutorPublicaObra e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<AutorPublicaObra> listByNome(String local) {
        return em.createQuery("SELECT e FROM AutorPublicaObra e WHERE e.localAutorPublicaObra LIKE :local").setParameter("local", "%" + local + "%").getResultList();
    }

    public List<AutorPublicaObra> listById(int id) {
        return em.createQuery("SELECT e FROM AutorPublicaObra e WHERE e.idAutorPublicaObra = :id").setParameter("id", id).getResultList();
    }

    public List<AutorPublicaObra> listInOrderNome() {
        return em.createQuery("SELECT e FROM AutorPublicaObra e ORDER BY e.localAutorPublicaObra").getResultList();
    }

    public List<AutorPublicaObra> listInOrderId() {
        return em.createQuery("SELECT e FROM AutorPublicaObra e ORDER BY e.idAutorPublicaObra").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<AutorPublicaObra> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdAutorPublicaObra() + "-" + lf.get(i).getLocalAutorPublicaObra());
        }
        return ls;
    }
}
