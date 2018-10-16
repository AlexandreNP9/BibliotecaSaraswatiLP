package GUIs;

import DAOs.DAOObra;
import DAOs.DAOStatus;
import DAOs.DAOTipoObra;
import Entidades.Obra;
import Entidades.Status;
import Entidades.TipoObra;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;

public class CRUDObra extends JDialog {

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
    JLabel labelNome = new JLabel("Título");
    JTextField textFieldNome = new JTextField(45);
    JLabel labelAno = new JLabel("Ano de publicação");
    JTextField textFieldAno = new JTextField(4);
    JLabel labelQuantidade = new JLabel("Quantidade");
    JTextField textFieldQuantidade = new JTextField(0);
    JLabel labelObservacoes = new JLabel("Observações");
    JTextField textFieldObservacoes = new JTextField(45);
    JLabel labelTipoObra = new JLabel("Tipo de Obra");
    JTextField textFieldTipoObra = new JTextField(0);
    JLabel labelStatus = new JLabel("Status");
    JTextField textFieldStatus = new JTextField(0);

    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update
    DAOObra cl = new DAOObra();
    Obra obra;
    Obra obraOriginal;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    DAOTipoObra daoTipoObra = new DAOTipoObra();
    DAOStatus daoStatus = new DAOStatus();

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

    private void habilitarAtributos(boolean id, boolean nome, boolean ano, boolean quantidade, boolean observacoes, boolean tipo, boolean status) {
        if (id) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEnabled(id);
        textFieldId.setEditable(id);
        textFieldNome.setEditable(nome);
        textFieldAno.setEditable(ano);
        textFieldQuantidade.setEditable(quantidade);
        textFieldObservacoes.setEditable(observacoes);
        textFieldTipoObra.setEditable(tipo);
        textFieldStatus.setEditable(status);
    }

    public void zerarAtributos() {
        textFieldId.setText("");
        textFieldNome.setText("");
        textFieldAno.setText("");
        textFieldQuantidade.setText("");
        textFieldObservacoes.setText("");
        textFieldTipoObra.setText("");
        textFieldStatus.setText("");

    }

    public void zerarAtributos2() {
        textFieldNome.setText("");
        textFieldAno.setText("");
        textFieldQuantidade.setText("");
        textFieldObservacoes.setText("");
        textFieldTipoObra.setText("");
        textFieldStatus.setText("");

    }

    public CRUDObra() {
        setTitle("OBRA");
        setSize(600, 400);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false, false, false, false);
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
        centro.setLayout(new GridLayout(8, 2));
        centro.add(labelId);
        centro.add(textFieldId);
        centro.add(labelNome);
        centro.add(textFieldNome);
        centro.add(labelAno);
        centro.add(textFieldAno);
        centro.add(labelQuantidade);
        centro.add(textFieldQuantidade);
        centro.add(labelObservacoes);
        centro.add(textFieldObservacoes);
        centro.add(labelTipoObra);
        centro.add(textFieldTipoObra);
        centro.add(labelStatus);
        centro.add(textFieldStatus);

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
        textFieldTipoObra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldTipoObra.getText().equals("")) {
                    List<String> listaAuxiliar = daoTipoObra.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = textFieldTipoObra.getLocationOnScreen();
                        lc.x = lc.x + textFieldTipoObra.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldTipoObra.setText(aux[0]);
                        }
                    }
                }
            }
        });
        
        textFieldStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldStatus.getText().equals("")) {
                    List<String> listaAuxiliar = daoStatus.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = textFieldStatus.getLocationOnScreen();
                        lc.x = lc.x + textFieldStatus.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldStatus.setText(aux[0]);
                        }
                    }
                }
            }
        });

        btnRetrieve.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                obra = new Obra();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços

                if (textFieldId.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Deve ser informado um valor para esse campo");
                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                } else {
                    obra.setIdObra(textFieldId.getText());
                    obra = cl.obter(obra.getIdObra());
                    if (obra != null) { //se encontrou na lista
                        textFieldNome.setText(obra.getTituloObra());
                        textFieldAno.setText(sdf.format(obra.getAnoObra()));
                        textFieldQuantidade.setText(String.valueOf(obra.getQuantidadeObra()));
                        textFieldObservacoes.setText(obra.getObservacoesObra());

                        TipoObra tipoObra = daoTipoObra.obter(obra.getTipoobraidtipoObra().getIdtipoObra());
                        textFieldTipoObra.setText(tipoObra.getIdtipoObra() + " - " + tipoObra.getNometipoObra());

                        Status status = daoStatus.obter(obra.getStatusIdStatus().getIdStatus());
                        textFieldStatus.setText(status.getIdStatus() + " - " + status.getNomeStatus());

                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false, false, false, false, false, false);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                        obraOriginal = obra;
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

                habilitarAtributos(false, true, true, true, true, true, true);
                textFieldNome.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        }
        );
        btnSave.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                if (acao.equals("insert")) {
                    obra = new Obra();
                    obra.setIdObra(textFieldId.getText());
                    obra.setTituloObra(textFieldNome.getText());
                    try {
                        obra.setAnoObra(sdf.parse(textFieldAno.getText()));

                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDObra.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                    obra.setQuantidadeObra(Integer.parseInt(textFieldQuantidade.getText()));
                    obra.setObservacoesObra(textFieldObservacoes.getText());

                    TipoObra tipoObra = new DAOTipoObra().obter(Integer.valueOf(textFieldTipoObra.getText()));
                    obra.setTipoobraidtipoObra(tipoObra);
                    Status status = new DAOStatus().obter(Integer.valueOf(textFieldStatus.getText()));
                    obra.setStatusIdStatus(status);

                    cl.inserir(obra);
                    habilitarAtributos(true, false, false, false, false, false, false);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    obra.setIdObra(textFieldId.getText());
                    obra.setTituloObra(textFieldNome.getText());
                    try {
                        obra.setAnoObra(sdf.parse(textFieldAno.getText()));

                    } catch (ParseException ex) {
                        Logger.getLogger(CRUDObra.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                    obra.setQuantidadeObra(Integer.parseInt(textFieldQuantidade.getText()));
                    obra.setObservacoesObra(textFieldObservacoes.getText());

                    TipoObra tipoObra = new DAOTipoObra().obter(Integer.valueOf(textFieldTipoObra.getText()));
                    obra.setTipoobraidtipoObra(tipoObra);
                    Status status = new DAOStatus().obter(Integer.valueOf(textFieldStatus.getText()));
                    obra.setStatusIdStatus(status);

                    cl.atualizar(obra);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false, false, false, false, false, false);
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
                habilitarAtributos(true, false, false, false, false, false, false);
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
                ListagemObra guiListagem = new ListagemObra(cl.list());
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
                habilitarAtributos(false, true, true, true, true, true, true);
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
                        "Confirma a exclusão do registro <ID = " + obra.getIdObra() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    cl.remover(obra);
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
        textFieldNome.addFocusListener(
                new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe
            ) {
                textFieldNome.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe
            ) { //ao perder o foco, fica branco
                textFieldNome.setBackground(Color.white);
            }
        }
        );
        textFieldAno.addFocusListener(
                new FocusListener() {
            @Override
            public void focusGained(FocusEvent e
            ) {
                textFieldAno.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e
            ) {
                textFieldAno.setBackground(Color.white);
            }
        }
        );
        textFieldQuantidade.addFocusListener(
                new FocusListener() {
            @Override
            public void focusGained(FocusEvent e
            ) {
                textFieldQuantidade.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e
            ) {
                textFieldQuantidade.setBackground(Color.white);
            }
        }
        );
        textFieldObservacoes.addFocusListener(
                new FocusListener() {
            @Override
            public void focusGained(FocusEvent e
            ) {
                textFieldObservacoes.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e
            ) {
                textFieldObservacoes.setBackground(Color.white);
            }
        }
        );
        textFieldTipoObra.addFocusListener(
                new FocusListener() {
            @Override
            public void focusGained(FocusEvent e
            ) {
                textFieldTipoObra.setBackground(Color.ORANGE);
            }

            @Override
            public void focusLost(FocusEvent e
            ) {
                textFieldTipoObra.setBackground(Color.white);
            }
        }
        );
        textFieldStatus.addFocusListener(
                new FocusListener() {
            @Override
            public void focusGained(FocusEvent e
            ) {
                textFieldStatus.setBackground(Color.ORANGE);
            }

            @Override
            public void focusLost(FocusEvent e
            ) {
                textFieldStatus.setBackground(Color.white);
            }
        }
        );

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco

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
        new CRUDObra();
    }
}
