package arquivos.br.listas;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MenuEscolherDiretorios extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtBuscarPdfs;
	private JTextField txtExcelArquivoElemax;
	private JTextField txtSalvarPdfs;
	private JButton btnIniciar;
	
	Arquivos arquivos = new Arquivos();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					visualFormulario();
					MenuEscolherDiretorios frame = new MenuEscolherDiretorios();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void verificaCamposPreenchidos() {
		// instancia nova thread já implementando o método run()
		new Thread() {
			// sobrescreve o método run()
			@Override
			public void run() {
				// while para fazer o loop infinito
				while (0 == 0) {

					try {
						
						if(txtBuscarPdfs.getText().isEmpty() || txtSalvarPdfs.getText().isEmpty() || txtExcelArquivoElemax.getText().isEmpty()) {
							btnIniciar.setEnabled(false);
						}else {
							btnIniciar.setEnabled(true);
						}
						// adormece thread por milisegundos
						sleep(500);
					} catch (InterruptedException ex) {
						Logger.getLogger(MenuEscolherDiretorios.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		}.start();// inicia a thread.
	}
	
	public static void visualFormulario() {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MenuEscolherDiretorios.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
	}


	/**
	 * Create the frame.
	 */
	public MenuEscolherDiretorios() {
		
		initComponents();
		verificaCamposPreenchidos();
		
		
	}
	
	public void initComponents() {
		setTitle("ESCOLHA DE DIRETORIOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 931, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(19))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel = new JLabel("BUSCAR - Selecione onde está os arquivos PDF para unificar:");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 15));
		
		txtBuscarPdfs = new JTextField();
		txtBuscarPdfs.setToolTipText("");
		txtBuscarPdfs.setColumns(10);
		
		JButton btnDiretorioBuscar = new JButton("Pesquisar");
		btnDiretorioBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtBuscarPdfs.setText(Arquivos.selecionarDiretorio("Selecionar diretório", "Escolha a pasta onde encontra-se os arquivos PDFs", true));
			}
		});
		btnDiretorioBuscar.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblNewLabel_1 = new JLabel("ELEMAX - Selecione o arquivo excel do sistema Elemax:");
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 15));
		
		txtExcelArquivoElemax = new JTextField();
		txtExcelArquivoElemax.setColumns(10);
		
		JButton btnDiretorioAquivoElemax = new JButton("Pesquisar");
		btnDiretorioAquivoElemax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtExcelArquivoElemax.setText(Arquivos.selecionarDiretorio("Selecionar arquivo", "Selecione o arquivo excel ELEMAX", false));
			}
		});
		btnDiretorioAquivoElemax.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 12));
		
		JLabel lblNewLabel_2 = new JLabel("SALVAR - Selecione onde deseja salvar os arquivos:");
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 15));
		
		txtSalvarPdfs = new JTextField();
		txtSalvarPdfs.setColumns(10);
		
		JButton btnDiretrioSalvar = new JButton("Pesquisar");
		btnDiretrioSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtSalvarPdfs.setText(Arquivos.selecionarDiretorio("Selecionar diretório", "Escolha a pasta para salvar os arquivos PDFs unificados", true));
			}
		});
		btnDiretrioSalvar.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 12));
		
		btnIniciar = new JButton("UNIFICAR PDFs");
		btnIniciar.setBackground(Color.RED);
		btnIniciar.setForeground(Color.RED);
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MergePDF.pdfs(txtBuscarPdfs.getText(), txtSalvarPdfs.getText(), txtExcelArquivoElemax.getText());
				JOptionPane.showMessageDialog(null, "O programa finalizou a unificação dos PDFs. Deu tudo certo.","FIM DO PROGRAMA",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnIniciar.setFont(new Font("Consolas", Font.BOLD, 12));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(txtBuscarPdfs, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnDiretorioBuscar, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(txtSalvarPdfs, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnDiretrioSalvar, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(txtExcelArquivoElemax, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnDiretorioAquivoElemax, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(309)
					.addComponent(btnIniciar, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
					.addGap(323))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(31)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDiretorioBuscar)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addComponent(txtBuscarPdfs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(34)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSalvarPdfs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(59)
							.addComponent(btnDiretrioSalvar)))
					.addGap(49)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(btnDiretorioAquivoElemax))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(txtExcelArquivoElemax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(33)
					.addComponent(btnIniciar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
