package view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JDialog;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Cursor;

public class Relatorios extends JDialog {
	
	//objetos JDBC 
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	private static final long serialVersionUID = 1L;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorios dialog = new Relatorios();
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
	public Relatorios() {
		setModal(true);
		setTitle("Relatorios ");
		setBounds(100, 100, 615, 398);
		getContentPane().setLayout(null);
		
		JButton btnClientes = new JButton("");
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBorderPainted(false);
		btnClientes.setIcon(new ImageIcon(Relatorios.class.getResource("/img/clientesd.png")));
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("SERVIÇOS");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(330, 198, 99, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("CLIENTES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(178, 198, 87, 14);
		getContentPane().add(lblNewLabel);
		btnClientes.setBounds(161, 103, 115, 81);
		getContentPane().add(btnClientes);
		
		JButton btnServicos = new JButton("");
		btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos.setContentAreaFilled(false);
		btnServicos.setBorderPainted(false);
		btnServicos.setIcon(new ImageIcon(Relatorios.class.getResource("/img/services.png")));
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioServicoss();
				
			}
		});
		btnServicos.setBounds(297, 89, 144, 106);
		getContentPane().add(btnServicos);

	}//fim do codigo
	private void relatorioClientes() {
		//instanciar um objeto para construir a página pdf
		Document document = new Document();
		//configurar como A4 e modo paisagem
		//document.setPageSize(PageSize.A4.rotate());
		//gerar o documento pdf
		try {
			//criar um documento em branco (pdf) de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			//abrir o documento (formatar e inserir o conteúdo)
			document.open();
			//adicionar a data atual
			Date dataRelatorio = new Date();
			DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(formatador.format(dataRelatorio)));
			//adicionar um páragrafo
			document.add(new Paragraph("Clientes:"));
			document.add(new Paragraph(" ")); //pular uma linha
			//----------------------------------------------------------
			//query (instrução sql para gerar o relatório de clientes)
			String readClientes = "select nome,telefone,cpf from Clientesd order by nome";
			try {
				//abrir a conexão com o banco
				con = dao.conectar();
				//preparar a query (executar a instrução sql)
				pst = con.prepareStatement(readClientes);
				//obter o resultado (trazer do banco de dados)
				rs = pst.executeQuery();
				//atenção uso do while para trazer todos os clientes
				// Criar uma tabela de duas colunas usando o framework(itextPDF)
				PdfPTable tabela = new PdfPTable(3); //(2) número de colunas
				// Criar o cabeçalho da tabela
				PdfPCell col1 = new PdfPCell(new Paragraph("Cliente"));
				PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
				PdfPCell col3 = new PdfPCell(new Paragraph("Cpf"));
				tabela.addCell(col1);
				tabela.addCell(col2);
				tabela.addCell(col3);
				while (rs.next()) {
					//popular a tabela
					tabela.addCell(rs.getString(1));
					tabela.addCell(rs.getString(2));
					tabela.addCell(rs.getString(3));
				}				
				//adicionar a tabela ao documento pdf
				document.add(tabela);
				//fechar a conexão com o banco
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		//fechar o documento (pronto para "impressão" (exibir o pdf))
		document.close();
		//Abrir o desktop do sistema operacional e usar o leitor padrão
		//de pdf para exibir o documento
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}		
	}
	//IMPRESSÃO DE RELATÓRIOS

		private void relatorioServicoss() {
			//instanciar um objeto para construir a página pdf
			Document document = new Document();
			//configurar como A4 e modo paisagem
			document.setPageSize(PageSize.A4.rotate());
			//gerar o documento pdf
			try {
				//criar um documento em branco (pdf) de nome clientes.pdf
				PdfWriter.getInstance(document, new FileOutputStream("servicos.pdf"));
				//abrir o documento (formatar e inserir o conteúdo)
				document.open();
				//adicionar a data atual
				Date dataRelatorio = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				document.add(new Paragraph(formatador.format(dataRelatorio)));
				//adicionar um páragrafo
				document.add(new Paragraph("Serviços:"));
				document.add(new Paragraph(" ")); //pular uma linha
				//----------------------------------------------------------
				//query (instrução sql para gerar o relatório de clientes)
				String readServicos = "select os, dataOS, Modelo, Defeito, nome, valor from servicos inner join Clientesd\r\n" + "on servicos.id = Clientesd.idCli;";
				try {
					//abrir a conexão com o banco
					con = dao.conectar();
					//preparar a query (executar a instrução sql)
					pst = con.prepareStatement(readServicos);
					//obter o resultado (trazer do banco de dados)
					rs = pst.executeQuery();
					//atenção uso do while para trazer todos os clientes
					// Criar uma tabela de duas colunas usando o framework(itextPDF)
					PdfPTable tabela = new PdfPTable(6); //(6) número de colunas
					// Criar o cabeçalho da tabela
					PdfPCell col1 = new PdfPCell(new Paragraph("OS"));
					PdfPCell col2 = new PdfPCell(new Paragraph("Data"));
					PdfPCell col3 = new PdfPCell(new Paragraph("Modelo"));
					PdfPCell col4 = new PdfPCell(new Paragraph("Defeito"));
					PdfPCell col5 = new PdfPCell(new Paragraph("Cliente"));
					PdfPCell col6 = new PdfPCell(new Paragraph("Valor"));
					tabela.addCell(col1);
					tabela.addCell(col2);
					tabela.addCell(col3);
					tabela.addCell(col4);
					tabela.addCell(col5);
					tabela.addCell(col6);
					while (rs.next()) {
						//popular a tabela
						tabela.addCell(rs.getString(1));
						tabela.addCell(rs.getString(2));
						tabela.addCell(rs.getString(3));
						tabela.addCell(rs.getString(4));
						tabela.addCell(rs.getString(5));
						tabela.addCell(rs.getString(6));
					}				
					//adicionar a tabela ao documento pdf
					document.add(tabela);
					//fechar a conexão com o banco
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			//fechar o documento (pronto para "impressão" (exibir o pdf))
			document.close();
			//Abrir o desktop do sistema operacional e usar o leitor padrão
			//de pdf para exibir o documento
			try {
				Desktop.getDesktop().open(new File("servicos.pdf"));
			} catch (Exception e) {
				System.out.println(e);
			}		
		}// FIM DO RELATÓRIO CLIENTES
}//fim do construtor
