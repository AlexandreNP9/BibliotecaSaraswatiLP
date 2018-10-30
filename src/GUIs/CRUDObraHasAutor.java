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

public class CRUDObraHasAutor extends JDialog {

    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnSave = new JButton("SELECIONAR");
    JButton btnCancel = new JButton(iconeCancel);
    
    JLabel labelId = new JLabel("Id");
    JTextField textFieldId = new JTextField(0);
    JLabel labelSobrenome = new JLabel("Sobrenome");
    JTextField textFieldSobrenome = new JTextField(40);
    JLabel labelNome = new JLabel("Nome");
    JTextField textFieldNome = new JTextField(40);
    JLabel labelNascimento = new JLabel("Ano de nascimento");
    JTextField textFieldNascimento = new JTextField(40);
    JLabel labelFalecimento = new JLabel("Ano de falecimento (se aplicável ou VIVO)");
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

    private void atvBotoes(boolean r) {
        btnRetrieve.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnRetrieve.setVisible(visivel);
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

    public CRUDObraHasAutor() {
        setTitle("AUTOR");
        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(true);
        habilitarAtributos(true, false, false, false, false, false);
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelId);
        Toolbar1.add(textFieldId);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
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
                        atvBotoes(true);
                        habilitarAtributos(true, false, false, false, false, false);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                        autorOriginal = autor;
                    } else {
                        atvBotoes(false);
                        zerarAtributos2();

                        labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                    }
                }
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
                        Logger.getLogger(CRUDObraHasAutor.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(CRUDObraHasAutor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    autor.setImagemAutor(textFieldImagem.getText());
                    cl.inserir(autor);
                    habilitarAtributos(true, false, false, false, false, false);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    autor.setIdAutor(Integer.valueOf(textFieldId.getText()));
                    autor.setSobrenomeAutor(textFieldSobrenome.getText());
                    autor.setNomeAutor(textFieldNome.getText());
                    try {
                        autor.setNascimentoAutor(sdf.parse(textFieldNascimento.getText()));
                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDObraHasAutor.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(CRUDObraHasAutor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    autor.setImagemAutor(textFieldImagem.getText());

                    cl.atualizar(autor);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false, false, false, false, false);
                    atvBotoes(false);
                    zerarAtributos();
                    labelAviso.setText("Registro atualizado...");
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false);
                habilitarAtributos(true, false, false, false, false, false);
                mostrarBotoes(true);
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
        new CRUDObraHasAutor();
    }
}
