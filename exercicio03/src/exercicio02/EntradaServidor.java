package exercicio02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class EntradaServidor extends Thread {

	final DataInputStream entrada; 
	final DataOutputStream saida; 
    final Socket socket;

	public EntradaServidor(DataInputStream entrada, DataOutputStream saida, Socket socket) {
		super();
		this.entrada = entrada;
		this.saida = saida;
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
		        	
	        		saida.writeUTF(converteStringParaMaiusculo(stringRecebida));
		        }
		        
		        
			} catch (EOFException e) {
				e.printStackTrace();
				break;
			} catch (IOException e) {
				System.out.println("Falha: A conexão foi encerrada de forma inesperada");
				e.printStackTrace();
			}
		}
		
		try {
			entrada.close();
			saida.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }

	private String converteStringParaMaiusculo(String stringRecebida) {
		return stringRecebida.toUpperCase();
	}
}
