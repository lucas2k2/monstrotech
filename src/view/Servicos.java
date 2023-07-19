package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.DAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;

public class Servicos extends JDialog {

	/**
	 * 
	 */
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private static final long serialVersionUID = 1L;
	private JTextField txtOS;
	private JTextField txtData;
	private JTextField txtModelo;
	private JTextField txtDefeito;
	private JTextField txtValor;
	private JTextField txtID;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnBuscar;
	private JButton btnExcluir;
	private JTextField txtCliente;
	private JList listaClientes;
	private JScrollPane scrollPane;
	private JButton btnImprimir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servicos dialog = new Servicos();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Servicos() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane.setVisible(false);
			}
		});
		setModal(true);
		setBounds(100, 100, 553, 362);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("OS");
		lblNewLabel.setBounds(74, 43, 46, 14);
		getContentPane().add(lblNewLabel);

		txtOS = new JTextField();
		txtOS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
					String caracteres = "0123456789";
					if (!caracteres.contains(e.getKeyChar()+ "")) {
						e.consume();
					}
			}
		});
		txtOS.setEditable(false);
		txtOS.setBounds(118, 40, 103, 20);
		getContentPane().add(txtOS);
		txtOS.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setBounds(74, 82, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setBounds(118, 79, 204, 20);
		getContentPane().add(txtData);
		txtData.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Modelo");
		lblNewLabel_2.setBounds(59, 125, 78, 14);
		getContentPane().add(lblNewLabel_2);

		txtModelo = new JTextField();
		txtModelo.setBounds(118, 122, 204, 20);
		getContentPane().add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Defeito");
		lblNewLabel_3.setBounds(60, 167, 89, 14);
		getContentPane().add(lblNewLabel_3);

		txtDefeito = new JTextField();
		txtDefeito.setBounds(118, 164, 204, 20);
		getContentPane().add(txtDefeito);
		txtDefeito.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Valor");
		lblNewLabel_4.setBounds(236, 215, 32, 14);
		getContentPane().add(lblNewLabel_4);

		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789";
				if (!caracteres.contains(e.getKeyChar()+ "")) {
					e.consume();
				}
			}
		});
		txtValor.setBounds(201, 230, 103, 20);
		getContentPane().add(txtValor);
		txtValor.setColumns(10);
		

		btnAdicionar = new JButton("");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setIcon(new ImageIcon(Servicos.class.getResource("/img/user add.png")));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBounds(119, 249, 46, 57);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setIcon(new ImageIcon(Servicos.class.getResource("/img/edit nv.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(224, 249, 53, 57);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setIcon(new ImageIcon(Servicos.class.getResource("/img/deletar.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirServico();
			}
		});
		btnExcluir.setBounds(323, 249, 53, 57);
		getContentPane().add(btnExcluir);

		btnBuscar = new JButton("");
		btnBuscar.setBorderPainted(false);
		btnBuscar.setBorder(null);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setIcon(new ImageIcon(Servicos.class.getResource("/img/pesquisar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(231, 11, 64, 57);
		getContentPane().add(btnBuscar);

		JButton btnNewButton = new JButton("");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setToolTipText("Limpar Campos");
		btnNewButton.setIcon(new ImageIcon(Servicos.class.getResource("/img/borracha.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}

		});
		btnNewButton.setBounds(356, 145, 64, 57);
		getContentPane().add(btnNewButton);
		
		// substituir o click pela tecla <ENTER> em um botão  enter
				getRootPane().setDefaultButton(btnBuscar);
				
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(null, "Cliente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel.setBounds(356, 43, 159, 73);
				getContentPane().add(panel);
				panel.setLayout(null);
				
				scrollPane = new JScrollPane();
				scrollPane.setVisible(false);
				scrollPane.setBounds(9, 33, 140, 37);
				panel.add(scrollPane);
				
				listaClientes = new JList();
				listaClientes.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						buscarClienteLista();
						listaClientes.setEnabled(true);
					}
				});
				scrollPane.setViewportView(listaClientes);
				
				txtCliente = new JTextField();
				txtCliente.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						listarClientes();
					}
					@Override
					public void keyTyped(KeyEvent e) {
						
					}
				});
				txtCliente.setColumns(10);
				txtCliente.setBounds(10, 15, 139, 20);
				panel.add(txtCliente);
				
						JLabel lblNewLabel_5 = new JLabel("ID do Cliente");
						lblNewLabel_5.setBounds(12, 43, 74, 14);
						panel.add(lblNewLabel_5);
						
								txtID = new JTextField();
								txtID.setEditable(false);
								txtID.setBounds(85, 40, 63, 20);
								panel.add(txtID);
								txtID.setColumns(10);
								
								btnImprimir = new JButton("");
								btnImprimir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
								btnImprimir.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										imprimir();
									}
								});
								btnImprimir.setIcon(new ImageIcon(Servicos.class.getResource("/img/4243338_basic_app_print_ux_icon.png")));
								btnImprimir.setContentAreaFilled(false);
								btnImprimir.setBounds(432, 249, 71, 57);
								getContentPane().add(btnImprimir);

	} // fim do construtor

	private void limparCampos() {
		txtCliente.setText(null);
		txtID.setText(null);
		txtModelo.setText(null);
		txtDefeito.setText(null);
		txtValor.setText(null);
		txtData.setText(null);
		txtOS.setText(null);
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnBuscar.setEnabled(true);
		
	}

	private void adicionar() {

		String comando = "insert into servicos (id,Modelo,defeito,valor) value (?,?,?,?)";
		String numOs = "SELECT OS FROM servicos WHERE OS = (SELECT MAX(OS) FROM servicos)";
		String info = "";
		if (txtID.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo ID não pode estar vazio.");
			txtID.requestFocus();
		} else if (txtID.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo OS não pode estar vazio.");
			txtModelo.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo Modelo não pode estar vazio.");
			txtDefeito.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "O campo Defeito não pode estar vazio.");
			txtDefeito.requestFocus();
		} else {
			try {
				con = dao.conectar();
				
				pst = con.prepareStatement(comando);
				pst.setString(1, txtID.getText());
				pst.setString(2, txtModelo.getText());
				pst.setString(3, txtDefeito.getText());
				pst.setString(4, txtValor.getText());

				pst.executeUpdate();
				con.close();
				
				con = dao.conectar();
				pst = con.prepareStatement(numOs);
				rs = pst.executeQuery();
				
				if (rs.next()) {
					info = rs.getString(1);
				}
				con.close();
				limparCampos();
				JOptionPane.showInternalMessageDialog(null, "Adicionado com sucesso");
				JOptionPane.showInternalMessageDialog(null,"Sua OS é: "+ info);
				btnEditar.setEnabled(true);
			} catch (SQLException se) {
				se.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	} // fim do método adicionar

	private void editar() {
		// System.out.println("teste do botão editar");
		// validação dos campos obrigatórios
		 if (txtModelo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Modelo do Ar Condicionado");
			txtModelo.requestFocus();

		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Defeito do Ar Condicionado");
			txtDefeito.requestFocus();

		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Valor desse serviço");
			txtValor.requestFocus();

		} else {
			// lógica principal
			// CRUD - Update
			String update = "update servicos set os=?, dataOS=?, Modelo=?, Defeito=?, Valor=? where id=?";
			try {
				// abrir a conexão
				con = dao.conectar();
				// preparar a query (instrução sql)
				pst = con.prepareStatement(update);

				pst.setString(1, txtOS.getText());
				pst.setString(2, txtData.getText());
				pst.setString(3, txtModelo.getText());
				pst.setString(4, txtDefeito.getText());
				pst.setString(5, txtValor.getText());
				pst.setString(6, txtID.getText());

				// executar a query
				pst.executeUpdate();
				// confirmar para o usuário
				JOptionPane.showMessageDialog(null, "Dados do cliente editados com sucesso.");
				// limpar os campos
				limparCampos();
				// fechar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	} // fim do método editar contato

	private void buscar() {
		
		// captura do numero da OS (sem usar a caixa de texto)
		String numOS = JOptionPane.showInputDialog("Número da OS");
		
		// dica - testar o evento primeiro
		// System.out.println("teste do botão buscar");
		// Criar uma variável com a query (instrução do banco)
		String read = "select * from servicos where os = ?";
		// tratamento de exceções
		try {
			// abrir a conexão
			con = dao.conectar();
			// preparar a execução da query (instrução sql - CRUD Read)
			// O parâmetro 1 substitui a ? pelo conteúdo da caixa de texto
			pst = con.prepareStatement(read);
			pst.setString(1, numOS);
			// executar a query e buscar o resultado
			rs = pst.executeQuery();
			// uso da estrutura if else para verificar se existe o contato
			// rs.next() -> se existir um contato no banco
			if (rs.next()) {
				// preencher as caixas de texto com as informações
				txtOS.setText(rs.getString(1)); // 1º Campo da tabela ID
				txtData.setText(rs.getString(2)); // 3º Campo da tabela (Fone)
				txtModelo.setText(rs.getString(3)); // 3º Campo da tabela (Fone)
				txtDefeito.setText(rs.getString(4)); // 4° Campo da tabela (Email)
				txtValor.setText(rs.getString(5));
				txtID.setText(rs.getString(6));
				// Valição (liberação dos botões alterar e excluir)
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
				btnBuscar.setEnabled(false);

			} else {
				// se não existir um contato no banco
				JOptionPane.showMessageDialog(null, "OS inexistente");
				// Validação (liberação do botão adicionar)
				btnAdicionar.setEnabled(true);
				btnBuscar.setEnabled(false);
			}
			// fechar conexão (IMPORTANTE)
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// FIM DO METODO BUSCAR
	
	private void excluirServico() {
		// System.out.println("teste do botão deletar");
		// validação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste serviço?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// CRUD - Delete
			String delete = "delete from servicos where id=?";
			// tratamento de exceções
			try {
				// abrir a conexão
				con = dao.conectar();
				// preparar a query (instrução sql)
				pst = con.prepareStatement(delete);
				// substituir a ? pelo id do contato
				pst.setString(1, txtID.getText());
				// executar a query
				pst.executeUpdate();
				// limpar campos
				limparCampos();
				// exibir uma mensagem ao usuário
				JOptionPane.showMessageDialog(null, "Serviço excluído");
				// fechar a conexão
				con.close();

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	
	/**
	 * Método usado para listar o nome dos usuários na lista
	 */
	private void listarClientes() {
		// System.out.println("teste");
		// A linha abaixo cria um objeto usando como referência um vetor dinâmico, este
		// objeto irá temporariamente armazenar os nomes
		DefaultListModel<String> modelo = new DefaultListModel<>();
		// Setar o modelo (vetor na lista)
		listaClientes.setModel(modelo);
		// Query (instrução sql)
		String readLista = "select * from Clientesd where nome like '" + txtCliente.getText() + "%'" + "order by nome";
		try {
			// Abrir a conexão
			con = dao.conectar();
			// Preparar a query (instrução sql)
			pst = con.prepareStatement(readLista);
			// Executar a query e trazer o resultado para lista
			rs = pst.executeQuery();
			// Uso do while para trzer os usuários enquanto existir
			while (rs.next()) {
				// Mostrar a barra de rolagem (scrollpane)
				scrollPane.setVisible(true);
				// Mostrar a lista
				// listaUsers.setVisible(true);
				// Adicionar os usuários no vetor -> lista
				modelo.addElement(rs.getString(2));
				// Esconder a lista se nenhuma letra for digitada
				if (txtCliente.getText().isEmpty()) {
					scrollPane.setVisible(false);
				}
			}
			// Fechar Conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método que busca o usuário selecionado na lista
	 */
	private void buscarClienteLista() {
		// System.out.println("teste");
		// variavel que captura o indice da linha da lista
		int linha = listaClientes.getSelectedIndex();
		if (linha >= 0) {
			// query (instrução slq)
			// limit(0,1) - seleciona o indice 0 e 1 usuario da lista
			String readBuscaLista = "select * from Clientesd where nome like '" + txtCliente.getText() + "%'"
					+ "order by nome limit " + (linha) + ", 1";
			try {
				// abrir a conexao
				con = dao.conectar();
				// preparar a query para execução
				pst = con.prepareStatement(readBuscaLista);
				// executar e obter ao resultado
				rs = pst.executeQuery();
				if (rs.next()) {
					// esconder a lista
					scrollPane.setVisible(false);
					// setar os campos
					txtID.setText(rs.getString(1));
					txtCliente.setText(rs.getString(2));
					
					// validação (liberação dos botoes)
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
					listaClientes.setEnabled(false);
					btnAdicionar.setEnabled(false);
				} else {
					JOptionPane.showMessageDialog(null, "Cliente Inexistente");
				}
				// fechar conexao
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			// se nao existir no banco um usuario
			scrollPane.setVisible(false);

		}
	}
	/**
	 * Impressão da OS
	 */
	private void imprimir() {
		// instanciar objeto para usar os métodos da biblioteca
		Document document = new Document();
		// documento pdf
		try {
			// criar um documento em branco (pdf) de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("os.pdf"));
			// abrir o documento (formatar e inserir o conteúdo)
			document.open();
			String readOS = "select * from servicos where os = ?";
			// conexão com o banco
			try {
				// abrir a conexão
				con = dao.conectar();
				// preparar a execução da query (instrução sql)
				pst = con.prepareStatement(readOS);
				pst.setString(1, txtOS.getText());
				// executar a query
				rs = pst.executeQuery();
				// se existir a OS
				if (rs.next()) {					
					//document.add(new Paragraph("OS: " + rs.getString(1)));
					Paragraph os = new Paragraph ("OS: " + rs.getString(1));
					os.setAlignment(Element.ALIGN_RIGHT);
					document.add(os);
						
					Paragraph Modelo = new Paragraph ("Modelo: " + rs.getString(3));
					Modelo.setAlignment(Element.ALIGN_LEFT);
					document.add(Modelo);
					
					Paragraph Defeito = new Paragraph ("Defeito: " + rs.getString(4));
					Defeito.setAlignment(Element.ALIGN_LEFT);
					document.add(Defeito);
					
					Paragraph Data = new Paragraph ("Data: " + rs.getString(2));
					Data.setAlignment(Element.ALIGN_LEFT);
					document.add(Data);
					
					Paragraph Valor = new Paragraph ("Valor: " + rs.getString(5));
					Valor.setAlignment(Element.ALIGN_LEFT);
					document.add(Valor);
				
					//imprimir imagens
					Image imagem = Image.getInstance(Servicos.class.getResource("/img/airgelado.png"));
					//resolução da imagem
					imagem.scaleToFit(200,200);
					imagem.setAbsolutePosition(200,530 );
					document.add(imagem);					
				}
				// fechar a conexão com o banco
				con.close();
				} catch (Exception e) {
					System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		// fechar o documento (pronto para "impressão" (exibir o pdf))
		document.close();
		// Abrir o desktop do sistema operacional e usar o leitor padrão
		// de pdf para exibir o documento
		try {
			Desktop.getDesktop().open(new File("os.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	
} // fim do codigo
