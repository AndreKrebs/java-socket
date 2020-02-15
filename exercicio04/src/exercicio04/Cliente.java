package exercicio04;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {

	public static void main(String[] args) throws IOException {

		DatagramSocket dgSocket = new DatagramSocket();
		byte[] mensagem = new byte[128];
		
		// envio >
		String msg = "Quero uma piada!";
		mensagem = msg.getBytes();
		
		InetAddress endereco = InetAddress.getByName("localhost");
		
		DatagramPacket dgPacket = new DatagramPacket(mensagem, 
				mensagem.length, endereco, 7777);
		
		dgSocket.send(dgPacket);
		
		// retorno <
		mensagem = new byte[128];
		dgPacket = new DatagramPacket(mensagem, mensagem.length);
		
		dgSocket.receive(dgPacket);
		
		String piada = new String(dgPacket.getData());
		System.out.println("Piada: "+piada);
		
	}

}
