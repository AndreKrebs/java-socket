package exercicio04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {

	private static int piadaVez = 0;
	
	public static void main(String[] args) throws IOException {

		DatagramSocket dgSocket = new DatagramSocket(7777);
		byte[] mensagem = new byte[128];
		
		DatagramPacket dgPacket = new DatagramPacket(mensagem, mensagem.length);
		while (true) {
			dgSocket.receive(dgPacket);
			
			InetAddress ia = dgPacket.getAddress();
			int porta = dgPacket.getPort();
			
			mensagem = buscaPiada().getBytes();
			
			dgPacket = new DatagramPacket(mensagem, mensagem.length, ia, porta);
			
			dgSocket.send(dgPacket);
		}
	}

	
	private static String buscaPiada() throws IOException {
		File file = new File("piadas.txt").getAbsoluteFile();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		int cont = 0;
		
		while ((line = br.readLine()) != null) {
			if (piadaVez == cont++) {
				piadaVez++;
				return line;
			}
		}
		
		return "Sem mais piadas para enviar";
	}
	
}
