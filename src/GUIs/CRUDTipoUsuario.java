package GUIs;

import DAOs.DAOTipoUsuario;
import Entidades.TipoUsuario;
import static com.sun.glass.ui.Cursor.setVisible;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

public class CRUDTipoUsuario extends JDialog {

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

    JLabel labelIdTipoUsuario = new JLabel("Id");
    JTextField textFieldIdTipoUsuario = new JTextField(0);
    JLabel labelNomeTipoUsuario = new JLabel("Nome");
    JTextField textFieldNomeTipoUsuario = new JTextField(40);

    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    DAOTipoUsuario cl = new DAOTipoUsuario();
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

    private void habilitarAtributos(boolean id, boolean nome) {
        if (id) {
            textFieldIdTipoUsuario.requestFocus();
            textFieldIdTipoUsuario.selectAll();
        }
        textFieldIdTipoUsuario.setEnabled(id);
    }
    
    public void zerarAtributos() {
        //textFieldIdTipoUsuario.setText("");
        textFieldIdTipoUsuario.setText("");
    }
    
   

    public CRUDTipoUsuario() {
        setTitle("TIPO DE USUÁRIO");
        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelIdTipoUsuario);
        Toolbar1.add(textFieldIdTipoUsuario);
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
        centro.setLayout(new GridLayout(3, 2));
        centro.add(labelIdTipoUsuario);
        centro.add(textFieldIdTipoUsuario);
        centro.add(labelNomeTipoUsuario);
        centro.add(textFieldNomeTipoUsuario);

        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        textFieldIdTipoUsuario.requestFocus();
        textFieldIdTipoUsuario.selectAll();
        textFieldIdTipoUsuario.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");
        setLocationRelativeTo(null); // posiciona no centro da tela principal

// Listeners
        
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tipoUsuario = new TipoUsuario();
                textFieldIdTipoUsuario.setText(textFieldIdTipoUsuario.getText().trim());//caso tenham sido digitados espaços

                if (textFieldIdTipoUsuario.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    textFieldIdTipoUsuario.requestFocus();
                    textFieldIdTipoUsuario.selectAll();
                } else {
                    tipoUsuario.setIdTipoUsuario(Integer.valueOf(textFieldIdTipoUsuario.getText()));
                    tipoUsuario = cl.obter(tipoUsuario.getIdTipoUsuario());
                    if (tipoUsuario != null) { //se encontrou na lista
                        textFieldIdTipoUsuario.setText(tipoUsuario.getNomeTipoUsuario());

                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                        tipoUsuarioOriginal = tipoUsuario;
                    } else {
                        atvBotoes(true, true, false, false);
                        zerarAtributos();

                        labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                    }
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                habilitarAtributos(false, true);
                textFieldIdTipoUsuario.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    tipoUsuario = new TipoUsuario();
                    tipoUsuario.setIdTipoUsuario(Integer.valueOf(textFieldIdTipoUsuario.getText()));
                    tipoUsuario.setNomeTipoUsuario(textFieldIdTipoUsuario.getText());

                    cl.inserir(tipoUsuario);
                    habilitarAtributos(true, false);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                    textFieldIdTipoUsuario.setText("");
                } else {  //acao = update
                    tipoUsuario.setIdTipoUsuario(Integer.valueOf(textFieldIdTipoUsuario.getText()));
                    tipoUsuario.setNomeTipoUsuario(textFieldIdTipoUsuario.getText());

                    cl.atualizar(tipoUsuario);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false);
                    atvBotoes(false, true, false, false);
                    zerarAtributos();
                    labelAviso.setText("Registro atualizado...");
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                ListagemTipoUsuario guiListagem = new ListagemTipoUsuario(cl.list());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true);
                atvBotoes(false, true, false, false);
            }
        });
//---------------------------------------------------------
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + tipoUsuario.getIdTipoUsuario() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    cl.remover(tipoUsuario);
                    zerarAtributos();
                    textFieldIdTipoUsuario.requestFocus();
                    textFieldIdTipoUsuario.selectAll();
                    atvBotoes(false, true, false, false);
                }
            }
        });
        textFieldIdTipoUsuario.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldIdTipoUsuario.setBackground(Color.GREEN);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma Id e clic [Pesquisar]");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                textFieldIdTipoUsuario.setBackground(Color.white);
            }
        });
        textFieldIdTipoUsuario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldIdTipoUsuario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldIdTipoUsuario.setBackground(Color.white);
            }
        });
        textFieldIdTipoUsuario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldIdTipoUsuario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldIdTipoUsuario.setBackground(Color.white);
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
        new CRUDTipoUsuario();
    }
}
