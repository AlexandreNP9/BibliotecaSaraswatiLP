///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package GUIs;
//
//import DAOs.DAOAutor;
//import DAOs.DAOModuloSistema;
//import DAOs.DAOObra;
//import Entidades.Autor;
//import Entidades.ModuloSistema;
//import Entidades.Obra;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.GridLayout;
//import java.util.List;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.JToolBar;
//
///**
// *
// * @author alexa
// */
//public class CRUDObraHasAutor extends JDialog {
//
//    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
//    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
//    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
//    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
//    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
//    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
//    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
//    JButton btnCreate = new JButton(iconeCreate);
//    JButton btnRetrieve = new JButton(iconeRetrieve);
//    JButton btnUpdate = new JButton(iconeUpdate);
//    JButton btnDelete = new JButton(iconeDelete);
//    JButton btnSave = new JButton(iconeSave);
//    JButton btnCancel = new JButton(iconeCancel);
//    JButton btnList = new JButton(iconeListar);
//
//    JTextField textFieldIdAutor = new JTextField(0);
//    JTextField textFieldIdObra = new JTextField(40);
//
//    JPanel aviso = new JPanel();
//    JLabel labelAviso = new JLabel("");
//    String acao = "";//variavel para facilitar insert e update
//    Autor autor = new Autor();
//    Obra obra = new Obra();
//    DAOAutor daoAutor = new DAOAutor();
//    DAOObra daoObra = new DAOObra();
//    
//    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
//        btnCreate.setEnabled(c);
//        btnRetrieve.setEnabled(r);
//        btnUpdate.setEnabled(u);
//        btnDelete.setEnabled(d);
//        btnList.setEnabled(r);
//    }
//
//    public void mostrarBotoes(boolean visivel) {
//        btnCreate.setVisible(visivel);
//        btnRetrieve.setVisible(visivel);
//        btnUpdate.setVisible(visivel);
//        btnDelete.setVisible(visivel);
//        btnList.setVisible(visivel);
//        btnSave.setVisible(!visivel);
//        btnCancel.setVisible(!visivel);
//    }
//
//    private void habilitarAtributos(boolean idAutor, boolean idObra) {
//        if (idAutor) {
//            textFieldIdAutor.requestFocus();
//            textFieldIdAutor.selectAll();
//        }
//        textFieldIdAutor.setEnabled(idAutor);
//        textFieldIdAutor.setEditable(idAutor);
//        textFieldIdObra.setEditable(idObra);
//    }
//
//    public void zerarAtributos() {
//        textFieldIdAutor.setText("");
//        textFieldIdObra.setText("");
//
//    }
//
//    public void zerarAtributos2() {
//        textFieldIdObra.setText("");
//
//    }
//    
//    public CRUDObraHasAutor() {
//        setTitle("OBRA HAS AUTOR");
//        setSize(600, 400);//tamanho da janela
//        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
//        setBackground(Color.CYAN);//cor do fundo da janela
//        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes
//
//        atvBotoes(false, true, false, false);
//        habilitarAtributos(true, false);
//        btnCreate.setToolTipText("Inserir novo registro");
//        btnRetrieve.setToolTipText("Pesquisar por chave");
//        btnUpdate.setToolTipText("Alterar");
//        btnDelete.setToolTipText("Excluir");
//        btnList.setToolTipText("Listar todos");
//        btnSave.setToolTipText("Salvar");
//        btnCancel.setToolTipText("Cancelar");
//        JToolBar Toolbar1 = new JToolBar();
//        Toolbar1.add(labelIdA);
//        Toolbar1.add(textFieldId);
//        Toolbar1.add(btnRetrieve);
//        Toolbar1.add(btnCreate);
//        Toolbar1.add(btnUpdate);
//        Toolbar1.add(btnDelete);
//        Toolbar1.add(btnSave);
//        Toolbar1.add(btnCancel);
//        Toolbar1.add(btnList);
//        btnSave.setVisible(false);
//        btnCancel.setVisible(false);  //atributos
//        JPanel centro = new JPanel();
//        centro.setLayout(new GridLayout(3, 2));
//        centro.add(labelId);
//        centro.add(textFieldId);
//        centro.add(labelNome);
//        centro.add(textFieldNome);
//    }
//    
//
//    public static void main(String[] args) {
//        Autor autor = new Autor();
//        Obra obra = new Obra();
//        DAOAutor daoAutor = new DAOAutor();
//        DAOObra daoObra = new DAOObra();
//
//        autor = daoAutor.obter(1);
//        List<Obra> lo = autor.getObraList();
//        System.out.println("tamanho de lo: " + lo.size());
//
//        lo.add(daoObra.obter("1"));
//        autor.setObraList(lo);
//        daoAutor.atualizar(autor);
//    }
//
//}
