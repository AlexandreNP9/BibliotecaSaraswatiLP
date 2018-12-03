package GUIs;

import DAOs.DAOObra;
import DAOs.DAOAutor;
import DAOs.DAOAutorPublicaObra;
import Entidades.Obra;
import Entidades.Autor;
import Entidades.AutorPublicaObra;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;

public class CRUDAutorPublicaObra extends JDialog {

    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);

    JLabel labelId = new JLabel("Id");
    JTextField textFieldId = new JTextField(45);
    JLabel labelLocal = new JLabel("Local de publicação");
    JTextField textFieldLocal = new JTextField();
    JLabel labelData = new JLabel("Data (dd/MM/yyyy)");
    JTextField textFieldData = new JTextField(0);
    JLabel labelAutor = new JLabel("Autor");
    JTextField textFieldAutor = new JTextField(0);
    JLabel labelObra = new JLabel("Obra");
    JTextField textFieldObra = new JTextField(0);

    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    DAOAutorPublicaObra cl = new DAOAutorPublicaObra();
    AutorPublicaObra autorPublicaObra;
    AutorPublicaObra autorPublicaObraOriginal;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    DAOAutor daoAutor = new DAOAutor();
    DAOObra daoObra = new DAOObra();

    JList jList = new JList();

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean id, boolean local, boolean data, boolean autor, boolean obra) {
        if (id) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEnabled(id);
        textFieldId.setEditable(id);
        textFieldLocal.setEditable(local);
        textFieldData.setEditable(data);
        textFieldAutor.setEditable(autor);
        textFieldObra.setEditable(obra);
    }

    public void zerarAtributos() {
        textFieldId.setText("");
        textFieldLocal.setText("");
        textFieldData.setText("");
        textFieldAutor.setText("");
        textFieldObra.setText("");

    }

    public void zerarAtributos2() {
        textFieldLocal.setText("");
        textFieldData.setText("");
        textFieldAutor.setText("");
        textFieldObra.setText("");

    }

    public CRUDAutorPublicaObra() {
        setTitle("AUTOR PUBLICA OBRA");
        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelId);
        Toolbar1.add(textFieldId);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);  //atributos
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(5, 2));
        centro.add(labelId);
        centro.add(textFieldId);
        centro.add(labelLocal);
        centro.add(textFieldLocal);
        centro.add(labelData);
        centro.add(textFieldData);
        centro.add(labelAutor);
        centro.add(textFieldAutor);
        centro.add(labelObra);
        centro.add(textFieldObra);

        JPanel autor = new JPanel();
        autor.setLayout(new GridLayout(3, 2));

        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        textFieldId.requestFocus();
        textFieldId.selectAll();
        textFieldId.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");
        setLocationRelativeTo(null); // posiciona no centro da tela principal

// Listeners
        textFieldAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoAutor.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    Point lc = textFieldAutor.getLocationOnScreen();
                    lc.x = lc.x + textFieldAutor.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {

                        textFieldAutor.setText(selectedItem);
                    }
                }
            }
        });

        textFieldObra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoObra.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    Point lc = textFieldObra.getLocationOnScreen();
                    lc.x = lc.x + textFieldObra.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {

                        textFieldObra.setText(selectedItem);
                    }
                }
            }
        });
        btnRetrieve.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                autorPublicaObra = new AutorPublicaObra();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços

                if (textFieldId.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                } else {
                    autorPublicaObra.setIdAutorPublicaObra(Integer.parseInt(textFieldId.getText()));
                    autorPublicaObra = cl.obter(autorPublicaObra.getIdAutorPublicaObra());
                    if (autorPublicaObra != null) { //se encontrou na lista
                        textFieldLocal.setText(autorPublicaObra.getLocalAutorPublicaObra());
                        textFieldData.setText(sdf.format(autorPublicaObra.getDataAutorPublicaObra()));

                        Autor autor = daoAutor.obter(autorPublicaObra.getAutorIdAutor().getIdAutor());
                        textFieldAutor.setText(autor.getIdAutor() + "-" + autor.getNomeAutor());

                        Obra obra = daoObra.obter(autorPublicaObra.getObraIdObra().getIdObra());
                        textFieldObra.setText(obra.getIdObra() + "-" + obra.getNomeObra());

                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false, false, false, false);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                        autorPublicaObraOriginal = autorPublicaObra;
                    } else {
                        atvBotoes(true, true, false, false);
                        zerarAtributos2();

                        labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                    }
                }
            }
        }
        );

        btnCreate.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {

                habilitarAtributos(false, true, true, true, true);
                textFieldAutor.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        }
        );
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                if (acao.equals("insert")) {
                    autorPublicaObra = new AutorPublicaObra();
                    autorPublicaObra.setIdAutorPublicaObra(Integer.parseInt(textFieldId.getText()));
                    autorPublicaObra.setLocalAutorPublicaObra(textFieldLocal.getText());
                    try {
                        autorPublicaObra.setDataAutorPublicaObra(sdf.parse(textFieldData.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDAutorPublicaObra.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String[] auxTO = textFieldAutor.getText().split("-");
                    Autor autor = new DAOAutor().obter(Integer.valueOf(auxTO[0]));
                    autorPublicaObra.setAutorIdAutor(autor);

                    String[] auxS = textFieldObra.getText().split("-");
                    Obra obra = new DAOObra().obter(Integer.valueOf(auxS[0]));
                    autorPublicaObra.setObraIdObra(obra);

                    cl.inserir(autorPublicaObra);
                    habilitarAtributos(true, false, false, false, false);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    autorPublicaObra.setIdAutorPublicaObra(Integer.parseInt(textFieldId.getText()));
                    autorPublicaObra.setLocalAutorPublicaObra(textFieldLocal.getText());
                    try {
                        autorPublicaObra.setDataAutorPublicaObra(sdf.parse(textFieldData.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDAutorPublicaObra.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String[] auxTO = textFieldAutor.getText().split("-");
                    Autor autor = new DAOAutor().obter(Integer.valueOf(auxTO[0]));
                    autorPublicaObra.setAutorIdAutor(autor);

                    String[] auxS = textFieldObra.getText().split("-");
                    Obra obra = new DAOObra().obter(Integer.valueOf(auxS[0]));
                    autorPublicaObra.setObraIdObra(obra);

                    cl.atualizar(autorPublicaObra);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false, false, false, false);
                    atvBotoes(false, true, false, false);
                    zerarAtributos();
                    labelAviso.setText("Registro atualizado...");
                }
            }
        }
        );
        btnCancel.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, false, false, false, false);
                mostrarBotoes(true);
            }
        }
        );
        btnList.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {

                acao = "list";
                ListagemAutorPublicaObra guiListagem = new ListagemAutorPublicaObra(cl.list());
            }
        }
        );
        btnUpdate.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true, true, true);
                atvBotoes(false, true, false, false);
            }
        }
        );
//---------------------------------------------------------
        btnDelete.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + autorPublicaObra.getIdAutorPublicaObra() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    cl.remover(autorPublicaObra);
                    zerarAtributos();
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                    atvBotoes(false, true, false, false);
                }
            }
        }
        );
        textFieldId.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe
            ) {
                textFieldId.setBackground(Color.GREEN);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma Id e clic [Pesquisar]");
                }
            }

            @Override
            public void focusLost(FocusEvent fe
            ) {
                textFieldId.setBackground(Color.white);
            }
        }
        );
        textFieldId.addFocusListener(
                new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe
            ) {
                textFieldId.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe
            ) { //ao perder o foco, fica branco
                textFieldId.setBackground(Color.white);
            }
        }
        );
        textFieldLocal.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldLocal.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldLocal.setBackground(Color.white);
            }
        });
        textFieldData.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textFieldData.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                textFieldData.setBackground(Color.white);
            }
        });

        textFieldAutor.addFocusListener(
                new FocusListener() {
            @Override
            public void focusGained(FocusEvent e
            ) {
                textFieldAutor.setBackground(Color.ORANGE);
            }

            @Override
            public void focusLost(FocusEvent e
            ) {
                textFieldAutor.setBackground(Color.white);
            }
        }
        );
        textFieldObra.addFocusListener(
                new FocusListener() {
            @Override
            public void focusGained(FocusEvent e
            ) {
                textFieldObra.setBackground(Color.ORANGE);
            }

            @Override
            public void focusLost(FocusEvent e
            ) {
                textFieldObra.setBackground(Color.white);
            }
        }
        );

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        textFieldObra.getText();
        addWindowListener(
                new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e
            ) {
                // Sai do sistema  
                dispose();
            }
        }
        );
        setModal(
                true);

        setVisible(
                true);//faz a janela ficar visível  
    }

    public static void main(String[] args) {
        new CRUDAutorPublicaObra();
    }
}
