package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class Sobre extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
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
	public Sobre() {
		setTitle("Eletro Gelado\r\n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/9990432_snowman_doll_people_holiday_ice_icon.png")));
		getContentPane().setForeground(new Color(255, 255, 255));
		setModal(true);
		setBounds(100, 100, 511, 388);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Autor: Lucas Silva \r\n\r\n\r\n ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 242, 147, 68);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Licença MIT");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(392, 226, 113, 14);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setIcon(new ImageIcon(Sobre.class.getResource("/img/mit-icon.png")));
		btnNewButton.setBounds(368, 251, 127, 87);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Eletro Gelado\r\n");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 34));
		lblNewLabel_2.setBounds(121, 22, 261, 31);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Gestão de serviços de Ar Condicionado \r\n");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(89, 192, 279, 15);
		getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setIcon(new ImageIcon(Sobre.class.getResource("/img/4527369_avatar_character_dragon_game_ice_icon.png")));
		btnNewButton_1.setBounds(162, 52, 132, 142);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Fone: 11 98514-8502");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(10, 296, 160, 14);
		getContentPane().add(lblNewLabel_3);

	}
}
