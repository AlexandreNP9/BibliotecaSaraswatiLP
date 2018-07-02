package DAOs;

import Entidades.TipoUsuario;
import java.util.ArrayList;
import java.util.List;

public class DAOTipoUsuario extends DAOGenerico<TipoUsuario> {

    public DAOTipoUsuario() {
        super(TipoUsuario.class);
    }

    public int autoIdTipoUsuario() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTipoUsuario) FROM TipoUsuario e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TipoUsuario> listByNome(String nome) {
        return em.createQuery("SELECT e FROM TipoUsuario e WHERE e.nomeTipoUsuario LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<TipoUsuario> listById(int id) {
        return em.createQuery("SELECT e FROM TipoUsuario e WHERE e.idTipoUsuario = :id").setParameter("id", id).getResultList();
    }

    public List<TipoUsuario> listInOrderNome() {
        return em.createQuery("SELECT e FROM TipoUsuario e ORDER BY e.nomeTipoUsuario").getResultList();
    }

    public List<TipoUsuario> listInOrderId() {
        return em.createQuery("SELECT e FROM TipoUsuario e ORDER BY e.idTipoUsuario").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TipoUsuario> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTipoUsuario() + "-" + lf.get(i).getNomeTipoUsuario());
        }
        return ls;
    }
}
