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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;

public class TelaChatServidor extends JFrame {
	
	private JPanel contentPane;
	private JTextArea painelTexto;
	private static String msg = "";
	
	public static final int PORT = 11000;
	
	public static void main(String[] args) {
		TelaChatServidor tela = new TelaChatServidor();
		tela.setVisible(true);
		try {
			
			ServerSocket server = new ServerSocket(PORT); //criando um servidor
			JOptionPane.showMessageDialog(null, "Porta 11000 aberta, aguardando conexao do cliente!");
			
			Socket clientSocket = server.accept(); //abrindo espaco de memória para um cliente
			JOptionPane.showMessageDialog(null, "Cliente "+clientSocket.getRemoteSocketAddress()+" CONECTOU!");
			
			Scanner s = new Scanner(clientSocket.getInputStream()); //retorna uma string de entrada de mensagens
			while(s.hasNextLine()) {
				tela.painelTexto.setText(msg += s.nextLine());
			}
			
			s.close(); //fechando a comunicacao
			clientSocket.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TelaChatServidor() {
		setTitle("SERVIDOR BlaBlaChat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 340, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mensagens recebidas:");
		lblNewLabel.setBounds(14, 23, 300, 16);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.add(lblNewLabel);
		
		painelTexto = new JTextArea();
		painelTexto.setBackground(SystemColor.text);
		painelTexto.setBounds(14, 50, 300, 146);
		contentPane.add(painelTexto);
	}

}
