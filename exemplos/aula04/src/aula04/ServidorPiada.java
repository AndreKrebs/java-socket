package aula04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorPiada {
	private static final String piada = "O que cai de pe e corre deitado? R: A chuva!";
	
	public static void main(String[] args) throws IOException {
		DatagramSocket dgSocket = new DatagramSocket(7777);
		byte[] mensagem = new byte[128];
		
		DatagramPacket dgPacket = new DatagramPacket(mensagem, mensagem.length);
		
		dgSocket.receive(dgPacket);
	
		String msg = new String(dgPacket.getData());
		
		System.out.println("A mensagem do datagram é: " + msg);
		
		InetAddress ia = dgPacket.getAddress();
		int porta = dgPacket.getPort();
		
		mensagem = piada.getBytes();
		
		dgPacket = new DatagramPacket(mensagem, mensagem.length, ia, porta);
		
		dgSocket.send(dgPacket);
	}
	
}
