package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import modelo.MaquinaTuringLongitudEnBinario;

public class Vista extends JFrame {

	private JPanel contentPane;
	private JTextField txtNoUno;
	private JLabel txtSalida;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
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
	public Vista() {
		//Panel interior
		UIManager.put("Panel.background", Color.decode("#2F575C"));

		//Panel exterior
		UIManager.put("OptionPane.background", Color.decode("#2F575C"));

		//Mensaje
		UIManager.put("OptionPane.messageForeground", Color.WHITE);
		UIManager.put("OptionPane.messageFont", new Font("Product Sans", Font.PLAIN, 15));

		//Botones
		UIManager.put("OptionPane.buttonFont", new Font("Product Sans", Font.BOLD, 15));
		UIManager.put("OptionPane.okButtonText", "ACEPTAR");
		UIManager.put("OptionPane.yesButtonText", "OK");
		UIManager.put("OptionPane.noButtonText", "OK");
		UIManager.put("OptionPane.cancelButtonText", "CANCELAR");

		UIManager.put("Button.background", Color.decode("#003A42"));
		UIManager.put("Button.foreground", Color.decode("#ACBFC2"));
		UIManager.put("Button.font", new Font("Product Sans", Font.BOLD, 15));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 417);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#2F575C"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{50, 0, 0, 0, 50, 0, 0};
		gbl_contentPane.rowHeights = new int[]{50, 100, 0, 100, 0, 50, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE, 0.0};
		contentPane.setLayout(gbl_contentPane);

		lblNewLabel = new JLabel("MAQUINA DE TURING");
		lblNewLabel.setIcon(new ImageIcon(Vista.class.getResource("/img/prueba-de-turing.png")));
		lblNewLabel.setFont(new Font("Product Sans", Font.BOLD, 80));
		lblNewLabel.setForeground(Color.decode("#A9ECF5"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		JButton btnNewButton = new JButton("Calcular");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setHorizontalTextPosition(SwingConstants.LEFT);
		btnNewButton.setIcon(new ImageIcon(Vista.class.getResource("/img/lupa.png")));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(Color.decode("#003A42"));
		btnNewButton.setForeground(Color.decode("#ACBFC2"));
		btnNewButton.setFont(new Font("Product Sans", Font.BOLD, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txtNoUno.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "CAMPOS VACIOS", "ERROR", JOptionPane.ERROR_MESSAGE);
					txtNoUno.requestFocus();
					return;
				}

				MaquinaTuringLongitudEnBinario analizador = new MaquinaTuringLongitudEnBinario(txtNoUno.getText());
				txtSalida.setText(analizador.analizar());
			}
		});

		txtNoUno = new JTextField();
		txtNoUno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println(e.getKeyChar());
				if (e.getKeyChar() != 'a' && e.getKeyChar() != 'b' && e.getKeyChar() != 'c') {
					e.consume();
				}

			}
		});
		txtNoUno.setFont(new Font("Product Sans", Font.BOLD, 30));
		txtNoUno.setBackground(Color.decode("#658A8F"));
		GridBagConstraints gbc_txtNoUno = new GridBagConstraints();
		gbc_txtNoUno.insets = new Insets(0, 0, 5, 5);
		gbc_txtNoUno.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNoUno.gridx = 2;
		gbc_txtNoUno.gridy = 4;
		contentPane.add(txtNoUno, gbc_txtNoUno);
		txtNoUno.setColumns(10);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 6;
		contentPane.add(btnNewButton, gbc_btnNewButton);

		txtSalida = new JLabel("");
		txtSalida.setFont(new Font("Product Sans", Font.BOLD, 50));
		txtSalida.setForeground(Color.decode("#ACBFC2"));
		GridBagConstraints gbc_txtSalida = new GridBagConstraints();
		gbc_txtSalida.gridwidth = 3;
		gbc_txtSalida.gridheight = 3;
		gbc_txtSalida.insets = new Insets(0, 0, 0, 5);
		gbc_txtSalida.gridx = 1;
		gbc_txtSalida.gridy = 8;
		contentPane.add(txtSalida, gbc_txtSalida);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
