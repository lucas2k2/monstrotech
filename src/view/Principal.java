	package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

public class Principal extends JFrame {

	// Instanciar objetos JDBC
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton lblStatus;
	private JLabel lblDate;
	//estes componentes serao alterados pela tela de login (public)
	public JLabel lblUsuario1;
	public JLabel lblUsuario;
	public JButton btnUsuarios;
	public JButton btnRelatorios1;
	public JPanel panelRodape;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
				setarData();
				
			}
		});
		setTitle("EletroGelado");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/9990432_snowman_doll_people_holiday_ice_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 559);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnUsuarios = new JButton("");
		btnUsuarios.setEnabled(false);
		btnUsuarios.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnUsuarios.setToolTipText("Usuários");
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/img/309037_remove_user_users_icon (1).png")));
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// abrir a tela de usuarios
				Usuarios Usuarios = new Usuarios();
				Usuarios.setVisible(true);

			}
		});
		btnUsuarios.setBounds(84, 86, 147, 125);
		contentPane.add(btnUsuarios);

		JButton btnSobre = new JButton("");
		btnSobre.setContentAreaFilled(false);
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre Sobre = new Sobre();
				Sobre.setVisible(true);
			}
		});
		btnSobre.setBorderPainted(false);
		btnSobre.setIcon(new ImageIcon(Principal.class.getResource("/img/infooo.png")));
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBounds(490, 11, 64, 64);
		contentPane.add(btnSobre);
		
		panelRodape = new JPanel();
		panelRodape.setBackground(new Color(0, 0, 255));
		panelRodape.setBounds(0, 460, 564, 60);
		contentPane.add(panelRodape);
		panelRodape.setLayout(null);
				
				lblUsuario1 = new JLabel("");
				lblUsuario1.setForeground(new Color(255, 255, 255));
				lblUsuario1.setBounds(336, 22, 85, 14);
				panelRodape.add(lblUsuario1);
		
				lblStatus = new JButton("");
				lblStatus.setBounds(490, 11, 64, 49);
				panelRodape.add(lblStatus);
				lblStatus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				lblStatus.setContentAreaFilled(false);
				lblStatus.setBorder(null);
				lblStatus.setBorderPainted(false);
				lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/img/off.png")));
				lblStatus.setToolTipText("Status off\r\n");
				
				lblDate = new JLabel("New label");
				lblDate.setBounds(15, 10, 221, 35);
				panelRodape.add(lblDate);
				lblDate.setForeground(new Color(255, 255, 255));
				lblDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
				JLabel asdasd = new JLabel("Usuario:");
				asdasd.setForeground(new Color(255, 255, 255));
				asdasd.setFont(new Font("Tahoma", Font.BOLD, 12));
				asdasd.setBounds(279, 11, 75, 34);
				panelRodape.add(asdasd);
		
		JLabel lblNewLabel = new JLabel("ELETRO");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblNewLabel.setBounds(118, 11, 174, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("GELADO\r\n");
		lblNewLabel_1.setForeground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(261, 11, 141, 31);
		contentPane.add(lblNewLabel_1);
		
		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientesd clientesd = new Clientesd();
				clientesd.setVisible(true);
			}
		});
		btnClientes.setToolTipText("Clientes");
		btnClientes.setIcon(new ImageIcon(Principal.class.getResource("/img/61816_business_buyers_clients_users_icon.png")));
		btnClientes.setBounds(316, 257, 133, 130);
		contentPane.add(btnClientes);
		
		JButton btnServiços = new JButton("");
		btnServiços.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servicos servicos = new Servicos();
				servicos.setVisible(true);
			}
		});
		btnServiços.setIcon(new ImageIcon(Principal.class.getResource("/img/9070393_air_conditioning_icon.png")));
		btnServiços.setBounds(84, 257, 147, 130);
		contentPane.add(btnServiços);
		
		btnRelatorios1 = new JButton("");
		btnRelatorios1.setEnabled(false);
		btnRelatorios1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorios relatorios = new Relatorios();
				relatorios.setVisible(true);
			}
		});
		btnRelatorios1.setIcon(new ImageIcon(Principal.class.getResource("/img/1622837_analytics_docs_documents_graph_pdf_icon.png")));
		btnRelatorios1.setBounds(316, 86, 133, 125);
		contentPane.add(btnRelatorios1);
	}

	private void status() {
		try {
			// abrir a conexão
			con = dao.conectar();
			if (con == null) {
				System.out.println("Erro de conexão");
				lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/img/off/png")));
			} else {
				System.out.println("Banco conectado");
				lblStatus.setIcon(new ImageIcon(Principal.class.getResource("/img/on.png")));
			}
			// NUNCA esquecer de fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);}
		}//fim do metodo status()
		/**
		 * Método responsavel por setar a data no rodapé
		 */
	    private void setarData() {
		Date data = new Date();
		// criar objeto para formatar a data
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		//alterar o texto do label pela data atual formatada
		lblDate.setText(formatador.format(data));
	    
	}
}// fim do metodo status da conexao()
