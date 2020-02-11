package exercicio02;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class EntradaServidor extends Thread {

	final DataInputStream entrada; 
    final Socket socket;

	public EntradaServidor(DataInputStream entrada, Socket socket) {
		super();
		this.entrada = entrada;
		this.socket = socket;
	}
    
	@Override
    public void run(){
		while (true) {
			try {
				
		        String stringRecebida = entrada.readUTF();
		        
		        if (stringRecebida != null) {
		        	if (stringRecebida.equals("sair")) {
		        		System.out.println("Conexão fechada com cliente " + socket);
		        		this.socket.close();
		        		break;
		        	}
		        	System.out.println("MENSAGEM DO CLIENTE: " + stringRecebida);
		        }
		        
		        
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			entrada.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
