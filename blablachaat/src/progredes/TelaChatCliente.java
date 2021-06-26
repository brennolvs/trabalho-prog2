package progredes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaChatCliente extends JFrame {
	private JPanel contentPane;
	private JTextArea mensagem;
	private static Socket client;
	
	public static final String SERVER_ADDRESS = "127.0.0.1"; //endereco de IP que o cliente vai se conectar
	
	public static void main(String[] args) {
		TelaChatCliente tela = new TelaChatCliente();
		tela.setVisible(true);
			try {
				client = new Socket(SERVER_ADDRESS, TelaChatServidor.PORT);
				JOptionPane.showMessageDialog(null, "Cliente conectado ao servidor");
			}  catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public static void enviarmensagem(String msg) {
		try {
			PrintStream out = new PrintStream(client.getOutputStream());
			out.println(msg);
			JOptionPane.showMessageDialog(null, "Mensagem enviada com sucesso ao servidor Blablachat!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TelaChatCliente() {
		setTitle("CLIENTE BlablaChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mensagens:");
		lblNewLabel.setBounds(14, 23, 410, 16);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblNewLabel);
		
		mensagem = new JTextArea();
		mensagem.setBounds(14, 50, 300, 112);
		contentPane.add(mensagem);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviarmensagem(mensagem.getText());
				mensagem.setText("");
			}
		});
		btnNewButton.setBounds(180, 185, 138, 23);
		contentPane.add(btnNewButton);
	}
}
