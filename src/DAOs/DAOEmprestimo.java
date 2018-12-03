package DAOs;

import Entidades.Emprestimo;
import Entidades.Emprestimo;
import java.util.ArrayList;
import java.util.List;

public class DAOEmprestimo extends DAOGenerico<Emprestimo> {

    public DAOEmprestimo() {
        super(Emprestimo.class);
    }

    public int autoIdEmprestimo() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idEmprestimo) FROM Emprestimo e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Emprestimo> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Emprestimo e WHERE e.nomeEmprestimo LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Emprestimo> listById(int id) {
        return em.createQuery("SELECT e FROM Emprestimo e WHERE e.idEmprestimo = :id").setParameter("id", id).getResultList();
    }

    public List<Emprestimo> listInOrderNome() {
        return em.createQuery("SELECT e FROM Emprestimo e ORDER BY e.nomeEmprestimo").getResultList();
    }

    public List<Emprestimo> listInOrderId() {
        return em.createQuery("SELECT e FROM Emprestimo e ORDER BY e.idEmprestimo").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Emprestimo> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdEmprestimo() + "-" + lf.get(i).getUsuarioIdUsuario() + lf.get(i).getObraIdObra());
        }
        return ls;
    }
}
