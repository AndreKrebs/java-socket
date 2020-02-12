package exercicio02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(50000);
		
		while (true) {
			Socket socket = server.accept();
			try {
            	
            	System.out.println("Novo cliente: " + socket);
	            
            	DataInputStream entrada = new DataInputStream(socket.getInputStream());
	            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            	
	            Thread t = new EntradaServidor(entrada, saida, socket);
	            
	            t.start();
	            
			} catch(Exception e) {
				socket.close();
				e.printStackTrace();
		    }
		}
	}
}
