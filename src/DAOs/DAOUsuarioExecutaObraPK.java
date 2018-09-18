package DAOs;

import Entidades.UsuarioExecutaObraPK;
import java.util.ArrayList;
import java.util.List;
import Entidades.Usuario;
import Entidades.Obra;

public class DAOUsuarioExecutaObraPK extends DAOGenerico<UsuarioExecutaObraPK> {

    public DAOUsuarioExecutaObraPK() {
        super(UsuarioExecutaObraPK.class);
    }

    public int autoIdUsuarioExecutaObraPK() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idUsuarioExecutaObraPK) FROM UsuarioExecutaObraPK e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<UsuarioExecutaObraPK> listByNome(String nome) {
        return em.createQuery("SELECT e FROM UsuarioExecutaObraPK e WHERE e.nomeUsuarioExecutaObraPK LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<UsuarioExecutaObraPK> listById(int id) {
        return em.createQuery("SELECT e FROM UsuarioExecutaObraPK e WHERE e.idUsuarioExecutaObraPK = :id").setParameter("id", id).getResultList();
    }

    public List<UsuarioExecutaObraPK> listInOrderNome() {
        return em.createQuery("SELECT e FROM UsuarioExecutaObraPK e ORDER BY e.nomeUsuarioExecutaObraPK").getResultList();
    }

    public List<UsuarioExecutaObraPK> listInOrderId() {
        return em.createQuery("SELECT e FROM UsuarioExecutaObraPK e ORDER BY e.idUsuarioExecutaObraPK").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<UsuarioExecutaObraPK> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getUsuarioIdUsuario() + "-" + lf.get(i).getObraIdObra());
        }
        return ls;
    }
}
