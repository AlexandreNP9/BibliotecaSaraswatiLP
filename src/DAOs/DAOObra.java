package DAOs;

import Entidades.Obra;
import java.util.ArrayList;
import java.util.List;

public class DAOObra extends DAOGenerico<Obra> {

    public DAOObra() {
        super(Obra.class);
    }

    public int autoIdObra() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idObra) FROM Obra e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Obra> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Obra e WHERE e.nomeObra LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Obra> listById(int id) {
        return em.createQuery("SELECT e FROM Obra e WHERE e.idObra = :id").setParameter("id", id).getResultList();
    }

    public List<Obra> listInOrderNome() {
        return em.createQuery("SELECT e FROM Obra e ORDER BY e.nomeObra").getResultList();
    }

    public List<Obra> listInOrderId() {
        return em.createQuery("SELECT e FROM Obra e ORDER BY e.idObra").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Obra> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdObra() + "-" + lf.get(i).getNomeObra());
        }
        return ls;
    }
}
