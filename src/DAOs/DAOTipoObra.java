package DAOs;

import Entidades.TipoObra;
import java.util.ArrayList;
import java.util.List;

public class DAOTipoObra extends DAOGenerico<TipoObra> {

    public DAOTipoObra() {
        super(TipoObra.class);
    }

    public int autoIdTipoObra() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTipoObra) FROM TipoObra e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TipoObra> listByNome(String nome) {
        return em.createQuery("SELECT e FROM TipoObra e WHERE e.nomeTipoObra LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<TipoObra> listById(int id) {
        return em.createQuery("SELECT e FROM TipoObra e WHERE e.idTipoObra = :id").setParameter("id", id).getResultList();
    }

    public List<TipoObra> listInOrderNome() {
        return em.createQuery("SELECT e FROM TipoObra e ORDER BY e.nomeTipoObra").getResultList();
    }

    public List<TipoObra> listInOrderId() {
        return em.createQuery("SELECT e FROM TipoObra e ORDER BY e.idtipoObra").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TipoObra> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdtipoObra() + "-" + lf.get(i).getNometipoObra());
        }
        return ls;
    }
}
