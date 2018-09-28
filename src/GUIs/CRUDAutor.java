package GUIs;

import DAOs.DAOAutor;
import Entidades.Autor;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

public class CRUDAutor extends JDialog {

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
    JTextField textFieldId = new JTextField(0);
    JLabel labelSobrenome = new JLabel("Sobrenome");
    JTextField textFieldSobrenome = new JTextField(40);
    JLabel labelNome = new JLabel("Nome");
    JTextField textFieldNome = new JTextField(40);
    JLabel labelNascimento = new JLabel("Data de nascimento");
    JTextField textFieldNascimento = new JTextField(40);
    JLabel labelFalecimento = new JLabel("Data de falecimento (se aplicável ou VIVO)");
    JTextField textFieldFalecimento = new JTextField(40);
    JLabel labelImagem = new JLabel("Imagem");
    JTextField textFieldImagem = new JTextField(0);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    DAOAutor cl = new DAOAutor();
    Autor autor;
    Autor autorOriginal;

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

    private void habilitarAtributos(boolean id, boolean sobrenome, boolean nome, boolean nascimento, boolean falecimento, boolean imagem) {
        if (id) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEnabled(id);
        textFieldId.setEditable(id);
        textFieldSobrenome.setEditable(sobrenome);
        textFieldNome.setEditable(nome);
        textFieldNascimento.setEditable(nascimento);
        textFieldFalecimento.setEditable(falecimento);
        textFieldImagem.setEditable(imagem);
    }

    public void zerarAtributos() {
        textFieldId.setText("");
        textFieldSobrenome.setText("");
        textFieldNome.setText("");
        textFieldNascimento.setText("");
        textFieldFalecimento.setText("");
        textFieldImagem.setText("");

    }

    public void zerarAtributos2() {

        textFieldSobrenome.setText("");
        textFieldNome.setText("");
        textFieldNascimento.setText("");
        textFieldFalecimento.setText("");
        textFieldImagem.setText("");

    }

    public CRUDAutor() {
        setTitle("AUTOR");
        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false, false);
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
        centro.setLayout(new GridLayout(7, 2));
        centro.add(labelId);
        centro.add(textFieldId);
        centro.add(labelSobrenome);
        centro.add(textFieldSobrenome);
        centro.add(labelNome);
        centro.add(textFieldNome);
        centro.add(labelNascimento);
        centro.add(textFieldNascimento);
        centro.add(labelFalecimento);
        centro.add(textFieldFalecimento);
        centro.add(labelImagem);
        centro.add(textFieldImagem);
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
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                autor = new Autor();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços

                if (textFieldId.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                } else {
                    autor.setIdAutor(Integer.valueOf(textFieldId.getText()));
                    autor = cl.obter(autor.getIdAutor());
                    if (autor != null) { //se encontrou na lista
                        textFieldSobrenome.setText(autor.getSobrenomeAutor());
                        textFieldNome.setText(autor.getNomeAutor());
                        textFieldNascimento.setText(sdf.format(autor.getNascimentoAutor()));
                        if (sdf.format(autor.getFalecimentoAutor()).equals("9999")) {
                            textFieldFalecimento.setText("VIVO");
                        } else {
                            textFieldFalecimento.setText(sdf.format(autor.getFalecimentoAutor()));
                        }
                        textFieldImagem.setText(autor.getImagemAutor());
                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false, false, false, false, false);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                        autorOriginal = autor;
                    } else {
                        atvBotoes(true, true, false, false);
                        zerarAtributos2();

                        labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                    }
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                habilitarAtributos(false, true, true, true, true, true);
                textFieldSobrenome.requestFocus();
                textFieldFalecimento.setText("VIVO");
                textFieldImagem.setText("0");
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    autor = new Autor();
                    autor.setIdAutor(Integer.valueOf(textFieldId.getText()));
                    autor.setSobrenomeAutor(textFieldSobrenome.getText());
                    autor.setNomeAutor(textFieldNome.getText());
                    try {
                        autor.setNascimentoAutor(sdf.parse(textFieldNascimento.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDAutor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String anoFalecimento;
                    if (textFieldFalecimento.getText().equals("VIVO")){
                            anoFalecimento = "9999";
                        } else {
                            anoFalecimento = textFieldFalecimento.getText();
                        }
                    try {
                        autor.setFalecimentoAutor(sdf.parse(anoFalecimento));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDAutor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    autor.setImagemAutor(textFieldImagem.getText());
                    cl.inserir(autor);
                    habilitarAtributos(true, false, false, false, false, false);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    autor.setIdAutor(Integer.valueOf(textFieldId.getText()));
                    autor.setSobrenomeAutor(textFieldSobrenome.getText());
                    autor.setNomeAutor(textFieldNome.getText());
                    try {
                        autor.setNascimentoAutor(sdf.parse(textFieldNascimento.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDAutor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String anoFalecimento;
                    if (textFieldFalecimento.getText().equals("VIVO")){
                            anoFalecimento = "9999";
                        } else {
                            anoFalecimento = textFieldFalecimento.getText();
                        }
                    try {
                        autor.setFalecimentoAutor(sdf.parse(anoFalecimento));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDAutor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    autor.setImagemAutor(textFieldImagem.getText());

                    cl.atualizar(autor);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false, false, false, false, false);
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
                habilitarAtributos(true, false, false, false, false, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                ListagemAutor guiListagem = new ListagemAutor(cl.list());
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true, true, true, true, true);
                atvBotoes(false, true, false, false);
            }
        });
//---------------------------------------------------------
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + autor.getIdAutor() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    cl.remover(autor);
                    zerarAtributos();
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                    atvBotoes(false, true, false, false);
                }
            }
        });
        textFieldId.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldId.setBackground(Color.GREEN);
                if (acao != "encontrou") {
                    labelAviso.setText("Digite uma Id e clic [Pesquisar]");
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                textFieldId.setBackground(Color.white);
            }
        });
        textFieldId.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldId.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldId.setBackground(Color.white);
            }
        });
        textFieldNome.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNome.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNome.setBackground(Color.white);
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
        new CRUDAutor();
    }
}
