package GUIs;

import DAOs.DAOModuloSistema;
import DAOs.DAOTipoUsuario;
import DAOs.DAOTipoUsuarioHasModuloSistema;
import Entidades.ModuloSistema;
import Entidades.TipoUsuario;
import Entidades.TipoUsuarioHasModuloSistema;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;

public class CRUDTipoUsuarioHasModuloSistemaANTIGO extends JDialog {

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
    JButton btn = new JButton("Obter itens marcados");

    JLabel labelId = new JLabel("Id");
    JTextField textFieldId = new JTextField();
    JLabel labelTipoUsuario = new JLabel("Tipo de Usuário");
    JTextField textFieldTipoUsuario = new JTextField(0);
    JLabel labelModuloSistema = new JLabel("Módulo do Sistema");
    JTextField textFieldModuloSistema = new JTextField(40);

    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    
    DAOTipoUsuarioHasModuloSistema cl = new DAOTipoUsuarioHasModuloSistema();
    
    DAOTipoUsuario daoTipoUsuario = new DAOTipoUsuario();
    DAOModuloSistema daoModuloSistema = new DAOModuloSistema();

    TipoUsuarioHasModuloSistema tipoUsuarioHasModuloSistema;
    TipoUsuarioHasModuloSistema tipoUsuarioHasModuloSistemaOriginal;
    
    ModuloSistema moduloSistema;
    ModuloSistema moduloSistemaOriginal;

    TipoUsuario tipoUsuario;
    TipoUsuario tipoUsuarioOriginal;

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

    private void habilitarAtributos(boolean id, boolean idTipoUsuario, boolean idModuloSistema) {
        if (id) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEnabled(id);
        textFieldId.setEditable(id);
        textFieldTipoUsuario.setEditable(idTipoUsuario);
        textFieldModuloSistema.setEditable(idModuloSistema);
    }

    public void zerarAtributos() {
        textFieldId.setText("");
        textFieldTipoUsuario.setText("");
        textFieldModuloSistema.setText("");

    }

    public void zerarAtributos2() {
        textFieldTipoUsuario.setText("");
        textFieldModuloSistema.setText("");

    }

    public CRUDTipoUsuarioHasModuloSistemaANTIGO() {
        JList lista = new JList();

        // Define o renderizador de células  
        lista.setCellRenderer(new CheckBoxCellRenderer());

        List<ModuloSistema> listaModulo = daoModuloSistema.list();

        Object[] cbArray = new Object[listaModulo.size()];
        for (int i = 0; i < listaModulo.size(); i++) {
            cbArray[i] = new JCheckBox(listaModulo.get(i).getNomeModuloSistema());
        }

        // Atribue os itens à lista    
        lista.setListData(cbArray);
        // Define a seleção única para a lista    
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setTitle("TIPO USUÁRIO HAS MÓDULO DO SISTEMA");
        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelTipoUsuario);
        Toolbar1.add(textFieldTipoUsuario);
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
        centro.setLayout(new GridLayout(4, 2));
        centro.add(labelId);
        centro.add(textFieldId);
        centro.add(labelTipoUsuario);
        centro.add(labelModuloSistema);
        
        aviso.add(labelAviso);
        aviso.add(btn);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        textFieldTipoUsuario.requestFocus();
        textFieldTipoUsuario.selectAll();
        textFieldTipoUsuario.setBackground(Color.ORANGE);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");
        setLocationRelativeTo(null); // posiciona no centro da tela principal

// Listeners
        textFieldTipoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> listaAuxiliar = daoTipoUsuario.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    Point lc = textFieldTipoUsuario.getLocationOnScreen();
                    lc.x = lc.x + textFieldTipoUsuario.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y).getValorRetornado();
                    if (!selectedItem.equals("")) {

                        textFieldTipoUsuario.setText(selectedItem);
                    }
                }
            }
        });
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tipoUsuarioHasModuloSistema = new TipoUsuarioHasModuloSistema();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços

                if (textFieldId.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                } else {
                    tipoUsuarioHasModuloSistema.setIdTipoUsuarioHasModuloSistema(Integer.parseInt(textFieldId.getText()));
                    tipoUsuarioHasModuloSistema = cl.obter(tipoUsuarioHasModuloSistema.getIdTipoUsuarioHasModuloSistema());
                    if (tipoUsuarioHasModuloSistema != null) { //se encontrou na lista
                        TipoUsuario tipoUsuario = daoTipoUsuario.obter(tipoUsuarioHasModuloSistema.getTipoUsuarioIdTipoUsuario().getIdTipoUsuario());
                        
//                        int aux = moduloSistema.getIdModuloSistema().size();
//                        String tipo = "";
//                        String modulo = "";
//
//                        for (int i = 0; i < lista.getModel().getSize(); i++) {
//                            JCheckBox checkbox = (JCheckBox) lista.getModel().getElementAt(i);
//                            if (moduloSistema[i] == true) {
//                                checkbox.setSelected(true);
//                            } else {
//                                checkbox.setSelected(false);
//                            }
//                        }
//
//                        textFieldModuloSistema.setText(moduloSistema.getTipoUsuarioList());
//                        textFieldModuloSistema.setText(moduloSistema.getNomeModuloSistema());
                        atvBotoes(true, true, true, true);
                        habilitarAtributos(true, false, false);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                        moduloSistemaOriginal = moduloSistema;
                    } else {
                        atvBotoes(false, true, false, false);
                        zerarAtributos2();

                        labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                    }
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                habilitarAtributos(false, true, true);
                textFieldModuloSistema.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });

//        btnSave.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                List<> mods = new List();
//                if (acao.equals("insert")) {
//                    moduloSistema = new ModuloSistema();
//
//                    String itens = "";
//                    /*
//           * Loop feito com FOR, para fazer uma varredura
//           * para obter quais itens estao marcados ou não
//                     */ for (int i = 0; i < lista.getModel().getSize(); i++) {
//                        JCheckBox checkbox = (JCheckBox) lista.getModel().getElementAt(i);
//                        if (checkbox.isSelected()) {
//                            mods[i].set(true);
//                        } else {
//                            mods[i].set(false);
//                        }
//                    }
//                    moduloSistema.setTipoUsuarioList(mods);
//                    JOptionPane.showMessageDialog(null, itens);
//
//                    moduloSistema.setIdModuloSistema(Integer.valueOf(textFieldTipoUsuario.getText()));
//                    moduloSistema.setNomeModuloSistema(textFieldModuloSistema.getText());
//
//                    daoModuloSistema.inserir(moduloSistema);
//                    habilitarAtributos(true, false, false);
//                    zerarAtributos();
//                    mostrarBotoes(true);
//                    atvBotoes(false, true, false, false);
//                    labelAviso.setText("Registro inserido...");
//                } else {  //acao = update
//                    moduloSistema.setIdModuloSistema(Integer.valueOf(textFieldTipoUsuario.getText()));
//                    moduloSistema.setNomeModuloSistema(textFieldModuloSistema.getText());
//
//                    daoModuloSistema.atualizar(moduloSistema);
//                    mostrarBotoes(true);
//                    habilitarAtributos(true, false, false);
//                    atvBotoes(false, true, false, false);
//                    zerarAtributos();
//                    labelAviso.setText("Registro atualizado...");
//                }
//            }
//        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                ListagemModuloSistema guiListagem = new ListagemModuloSistema(daoModuloSistema.list());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true);
                atvBotoes(false, true, false, false);
            }
        });
//---------------------------------------------------------
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + moduloSistema.getIdModuloSistema() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoModuloSistema.remover(moduloSistema);
                    zerarAtributos();
                    textFieldTipoUsuario.requestFocus();
                    textFieldTipoUsuario.selectAll();
                    atvBotoes(false, true, false, false);
                }
            }
        });
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String itens = "";
                /*
           * Loop feito com FOR, para fazer uma varredura
           * para obter quais itens estao marcados ou não
                 */ for (int i = 0; i < lista.getModel().getSize(); i++) {
                    JCheckBox checkbox = (JCheckBox) lista.getModel().getElementAt(i);
                    if (checkbox.isSelected()) {
                        itens += "Item com índice " + i + " está marcado\n";
                    } else {
                        itens += "Item com índice " + i + " está desmarcado\n";
                    }
                }
                JOptionPane.showMessageDialog(null, itens);
            }
        });
        // Aqui nós permitimos que as checkboxes sejam marcadas
        // ou desmarcadas com a barra de espaço  
        lista.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    int index = lista.getSelectedIndex();
                    if (index != -1) {
                        JCheckBox checkbox = (JCheckBox) lista.getModel().getElementAt(index);
                        checkbox.setSelected(!checkbox.isSelected());
                        repaint();
                    }
                }
            }
        });

        // Aqui nós permitimos que as checkboxes sejam marcadas
        // ou desmarcadas com o mouse   
        lista.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int index = lista.locationToIndex(e.getPoint());
                if (index != -1) {
                    JCheckBox checkbox = (JCheckBox) lista.getModel().getElementAt(index);
                    checkbox.setSelected(!checkbox.isSelected());
                    repaint();
                }
            }
        });
        textFieldTipoUsuario.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldTipoUsuario.setBackground(Color.ORANGE);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma Id e clic [Pesquisar]");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                textFieldTipoUsuario.setBackground(Color.white);
            }
        });
        textFieldTipoUsuario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldTipoUsuario.setBackground(Color.ORANGE);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldTipoUsuario.setBackground(Color.white);
            }
        });
        textFieldModuloSistema.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldModuloSistema.setBackground(Color.ORANGE);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldModuloSistema.setBackground(Color.white);
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai do sistema  
                dispose();
            }
        });

        setModal(true);

        setVisible(true);//faz a janela ficar visível  
    }

    public static void main(String[] args) {
        new CRUDTipoUsuarioHasModuloSistemaANTIGO();
    }
}
