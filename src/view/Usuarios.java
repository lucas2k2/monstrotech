package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import model.DAO;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class Usuarios extends JDialog {

	// Instanciar objetos JDBC
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JTextField txtNome;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JList listUsers;
	private JScrollPane scrollPane;
	private JComboBox cboUS;
	private JButton btnExcluir;
	private JCheckBox chckSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
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
	public Usuarios() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// clicar no painel do JDialog
				scrollPane.setVisible(false);
				txtNome.setText(null);
			}
		});
		setTitle("Usuários");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 511, 378);
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBorder(null);
		scrollPane.setBounds(82, 136, 263, 94);
		getContentPane().add(scrollPane);

		listUsers = new JList();
		listUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarUsuarioLista();
			}
		});
		listUsers.setBorder(null);
		scrollPane.setViewportView(listUsers);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(26, 26, 46, 14);
		getContentPane().add(lblNewLabel);

		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setBounds(64, 23, 86, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);
		txtID.setDocument(new Validador(5));

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(26, 116, 46, 14);
		getContentPane().add(lblNewLabel_1);

		txtNome = new JTextField();
		txtNome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// preesionar uma tecla

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// soltar uma tecla
				listarUsuarios();
			}
		});
		txtNome.setBounds(82, 116, 263, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador(50));

		JButton btnBuscar = new JButton("");
		btnBuscar.setBorderPainted(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/pesquisar.png")));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarUsuario();
			}
		});
		btnBuscar.setBounds(293, 55, 68, 50);
		getContentPane().add(btnBuscar);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(26, 77, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtLogin = new JTextField();
		txtLogin.setBounds(82, 74, 201, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		txtLogin.setDocument(new Validador(30));

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(26, 162, 46, 14);
		getContentPane().add(lblNewLabel_3);

		JButton btnLimpar = new JButton("");
		btnLimpar.setBorderPainted(false);
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/vasoura.png")));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setBounds(354, 253, 89, 71);
		getContentPane().add(btnLimpar);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(82, 159, 263, 20);
		getContentPane().add(txtSenha);
		txtSenha.setColumns(10);
		txtSenha.setDocument(new Validador(50));

		JButton btnAdicionar = new JButton("");
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/user add.png")));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setBounds(18, 253, 89, 71);
		getContentPane().add(btnAdicionar);

		cboUS = new JComboBox();
		cboUS.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboUS.setBounds(315, 22, 103, 22);
		getContentPane().add(cboUS);

		JLabel lblPerfil = new JLabel("Perfil:");
		lblPerfil.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPerfil.setBounds(274, 25, 46, 14);
		getContentPane().add(lblPerfil);

		JButton btnEditar = new JButton("");
		btnEditar.setContentAreaFilled(false);
		btnEditar.setBorderPainted(false);
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/edit nv.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// evento que vai verificar se o checkbox foi selecionado
				if (chckSenha.isSelected()) {
					editarUsuario();
				} else {
					EditarUsuarioExcetoSenha();

				}
			}
		});
		btnEditar.setBounds(133, 253, 89, 71);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/deletar.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Excluir();
			}
		});
		btnExcluir.setBounds(244, 253, 89, 71);
		getContentPane().add(btnExcluir);

		chckSenha = new JCheckBox("alterar senha ");
		chckSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckSenha.isSelected()) {
					txtSenha.setText(null);
					txtSenha.requestFocus();
					txtSenha.setBackground(Color.yellow);
				}else {
					txtSenha.setBackground(Color.white);
					
				}
			}
		});
		chckSenha.setBounds(18, 223, 113, 23);
		getContentPane().add(chckSenha);

	}// fim do construtor

	/**
	 * Método usado para listar o nome dos usuários na lista
	 */
	private void listarUsuarios() {
		// System.out.println("teste");
		// a linha abaixo cria um objeto usando como referência um vetor dinâmico, este
		// objeto irá temporariamente armazenar os nomes
		DefaultListModel<String> modelo = new DefaultListModel<>();
		// setar o modelo (vetor na lista)
		listUsers.setModel(modelo);
		// Query (instrução sql)
		String readLista = "select * from usuarios where nome like '" + txtNome.getText() + "%'" + "order by nome";
		try {
			// abrir a conexão
			con = dao.conectar();
			// preparar a query (instrução sql)
			pst = con.prepareStatement(readLista);
			// executar a query e trazer o resultado para lista
			rs = pst.executeQuery();
			// uso do while para trazer os usuários enquanto existir
			while (rs.next()) {
				// mostrar a barra de rolagem (scrollpane)
				scrollPane.setVisible(true);
				// adicionar os usuarios no vetor -> lista
				modelo.addElement(rs.getString(2));
				// esconder o scrollpane se nenhuma letra for digitada
				if (txtNome.getText().isEmpty()) {
					scrollPane.setVisible(false);
				}
			}
			// fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método que busca o usuário selecionado da lista
	 */
	private void buscarUsuarioLista() {
		// System.out.println("teste");
		// variável que captura o índice da linha da lista
		int linha = listUsers.getSelectedIndex();
		if (linha >= 0) {
			// Query (instrução sql)
			// limit (0,1) -> seleciona o índice 0 e 1 usuário da lista
			String readListaUsuario = "select * from usuarios where nome like '" + txtNome.getText() + "%'"
					+ "order by nome limit " + (linha) + " , 1";
			try {
				// abrir a conexão
				con = dao.conectar();
				// preparar a query para execução
				pst = con.prepareStatement(readListaUsuario);
				// executar e obter o resultado
				rs = pst.executeQuery();
				if (rs.next()) {
					// esconder a lista
					scrollPane.setVisible(false);
					// setar os campos
					txtID.setText(rs.getString(1));
					txtNome.setText(rs.getString(2));
					txtLogin.setText(rs.getString(3));
					txtSenha.setText(rs.getString(4));
					cboUS.setSelectedItem(rs.getString(5));

				} else {
					JOptionPane.showMessageDialog(null, "Usuário inexistente");
				}
				// fechar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			// se não existir no banco um usuário da lista
			scrollPane.setVisible(false);
		}
	}

	/**
	 * Método usado para buscar um usuário no banco pelo login
	 */
	private void buscarUsuario() {
		// System.out.println("teste do botão buscar");
		String read = "select * from usuarios where login = ?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, txtLogin.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtID.setText(rs.getString(1));
				txtNome.setText(rs.getString(2));
				txtLogin.setText(rs.getString(3));
				txtSenha.setText(rs.getString(4));
				cboUS.setSelectedItem(rs.getString(5));
			} else {
				JOptionPane.showMessageDialog(null, "Usuário inexistente");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método para adicionar um novo usuário
	 */
	private void adicionarUsuario() {
		// Criar uma variável/objeto para capturar a senha
		String capturaSenha = new String(txtSenha.getPassword());
		// System.out.println("teste");
		// validação de campos obrigatórios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do usuário");
			txtNome.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login do usuário");
			txtLogin.requestFocus();
		} else if (capturaSenha.length() == 0) {
			JOptionPane.showMessageDialog(null, "Preencha a senha do usuário");
			txtLogin.requestFocus();
		} else {
			// lógica principal
			// CRUD Create
			String create = "insert into usuarios (nome,login,senha,perfil) values (?,?,md5(?),?)";
			// tratamento de exceções
			try {
				// abrir a conexão
				con = dao.conectar();
				// preparar a execução da query(instrução sql - CRUD Create)
				pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, capturaSenha);
				pst.setString(4, cboUS.getSelectedItem().toString());
				// executa a query(instrução sql (CRUD - Create))
				pst.executeUpdate();
				// confirmar
				JOptionPane.showMessageDialog(null, "Usuário adicionado");
				// limpar os campos
				limparCampos();
				// fechar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do método adicionar

	/**
	 * Limpar campos
	 */
	private void limparCampos() {
		txtID.setText(null);
		txtNome.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		scrollPane.setVisible(false);
		cboUS.setSelectedItem("");
		txtSenha.setBackground(Color.white);
		chckSenha.setSelected(false);
	}

	private void editarUsuario() {
		// criar uma variavel para capturar a senha
		String capturasenha = new String(txtSenha.getPassword());
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome DO Usuario");
			txtNome.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Login Do Usuario");
		} else if (capturasenha.length()==0) {
			JOptionPane.showMessageDialog(null, "Digite o Senha Do Usuario");
			txtSenha.requestFocus();
		} else {
			// logica principal
			// CRUD update
			String update = "Update usuarios set nome=?, login=?,senha=md5(?),perfil=?,where id=?";
			// tratamento de exceçoes
			try {
				// abrir a conexao
				con = dao.conectar();
				// preparar a query (instrucao sql)
				pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, capturasenha);
				pst.setString(4, txtID.getText());
				pst.setString(5, cboUS.getSelectedItem().toString());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados Do Usuario Editados Com Sucesso");
				limparCampos();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private void Excluir() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desse usuario?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		{
			if (confirma == JOptionPane.YES_OPTION) {
				String delete = "delete from usuarios where id=?";
				try {
					con = dao.conectar();
					pst = con.prepareStatement(delete);
					pst.setString(1, txtID.getText());
					pst.executeUpdate();
					limparCampos();
					JOptionPane.showMessageDialog(null, "Usuario excluido");
					con.close();

				} catch (Exception e) {
					System.out.println(e);

				}
			}

		}
	}

	private void EditarUsuarioExcetoSenha() {
		// criar uma variavel para capturar a senha
		String capturasenha = new String(txtSenha.getPassword());
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome DO Usuario");
			txtNome.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Login Do Usuario");
			txtLogin.requestFocus();
		} else {
			// logica principal
			// CRUD update
			String update2 = "Update usuarios set nome=?, login=?,perfil=?, where id=?";
			// tratamento de exceçoes
			try {
				// abrir a conexao
				con = dao.conectar();
				// preparar a query (instrucao sql)
				pst = con.prepareStatement(update2);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, cboUS.getSelectedItem().toString());
				pst.setString(4, txtID.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados Do Usuario Editados Com Sucesso");
				limparCampos();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}// fim do código
