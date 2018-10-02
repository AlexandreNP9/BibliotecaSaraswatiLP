package GUIs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
//import myUtil.CentroDoMonitorMaior;

public class GUIMenuPrincipal extends JFrame {

    private Container cp;
    private Point p;
    private JPanel pnNorte = new JPanel();
    private JPanel pnCentro = new JPanel();
    private JLabel lbTitulo = new JLabel("Biblioteca Saraswati");
    private Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    private JLabel labelComImagemDeTamanhoDiferente = new JLabel();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuCadastros = new JMenu("Cadastros");
//------------------------ Itens do Menu ----------------------------
    private JMenuItem crudModulo = new JMenuItem("Módulo do Sistema");
    private JMenuItem crudTipoObra = new JMenuItem("Tipo de Obra");
    private JMenuItem crudStatus = new JMenuItem("Status");
    private JMenuItem crudAutor = new JMenuItem("Autor");
    private JMenuItem crudTipoUsuario = new JMenuItem("Tipo de Usuário");
    
    private JMenuItem crudObra = new JMenuItem("Obra");
    private JMenuItem crudUsuario = new JMenuItem("Usuário");
    
    private JMenuItem crudTipoUsuarioHasModuloSistema = new JMenuItem("Tipo Usuario Has Modulo Sistema");
    private JMenuItem crudObraHasAutor = new JMenuItem("Obra Has Autor");
    private JMenuItem crudUsuarioExecutaObra = new JMenuItem("Usuario Executa Obra");
    
    public GUIMenuPrincipal() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Biblioteca Saraswati");

        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        pnNorte.add(lbTitulo);
        lbTitulo.setFont(fonte);
        pnNorte.setBackground(Color.LIGHT_GRAY);
        pnCentro.add(labelComImagemDeTamanhoDiferente);
        pnCentro.setBackground(Color.BLACK);

        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        menuBar.add(menuCadastros);
        menuCadastros.add(crudModulo);
        menuCadastros.add(crudTipoObra);
        menuCadastros.add(crudStatus);
        menuCadastros.add(crudAutor);
        menuCadastros.add(crudTipoUsuario);
        menuCadastros.add(crudObra);
        menuCadastros.add(crudUsuario);
        menuCadastros.add(crudObraHasAutor);
        menuCadastros.add(crudTipoUsuarioHasModuloSistema);
        menuCadastros.add(crudUsuarioExecutaObra);
        
        crudModulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDModuloSistema crudModuloSistema = new CRUDModuloSistema();
            }
        });

        crudTipoObra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDTipoObra crudTipoObra1 = new CRUDTipoObra();
            }
        });
        
        crudStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDStatus crudStatus1 = new CRUDStatus();
            }
        });
        
        crudAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDAutor crudAutor1 = new CRUDAutor();
            }
        });
        
        crudTipoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDTipoUsuario crudTipoUsuario1 = new CRUDTipoUsuario();
            }
        });

//        crudObra.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                CRUDObra crudObra1 = new CRUDObra();
//            }
//        });

        crudUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDUsuario crudUsuario1 = new CRUDUsuario();
            }
        });

        crudObraHasAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDObraHasAutor crudObraHasAutor1 = new CRUDObraHasAutor();
            }
        });
        
        crudTipoUsuarioHasModuloSistema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDTipoUsuarioHasModuloSistema crudTipoUsuarioHasModuloSistema1 = new CRUDTipoUsuarioHasModuloSistema();
            }
        });
        
        crudUsuarioExecutaObra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CRUDUsuarioExecutaObra crudUsuarioExecutaObra1 = new CRUDUsuarioExecutaObra();
            }
        });

        setVisible(true);
    } //fecha o contrutor

    public static void main(String[] args) {
        new GUIMenuPrincipal();
    }
}
