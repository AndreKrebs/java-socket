package exercicio02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	private static Socket socket;
    private static DataOutputStream saida;
    private static DataInputStream entrada;
    
    public static void main(String[] args) {
    	Scanner scn = new Scanner(System.in);
    	String mensagem = "";
    	
        try {
        	socket = new Socket("127.0.0.1", 50000);
        	
        	saida = new DataOutputStream(socket.getOutputStream());
        	entrada = new DataInputStream(socket.getInputStream());

        	do {
	            System.out.println("Mensagem para o servidor, digite `sair` para encerrar:");
	            mensagem = scn.nextLine();
	            
            	saida.writeUTF(mensagem);
            	System.out.println("Mensagem formatada: \n" + entrada.readUTF());
            } while (!mensagem.equals("sair"));
            
            socket.close();
            System.out.println("Encerrado aplicação cliente....");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
