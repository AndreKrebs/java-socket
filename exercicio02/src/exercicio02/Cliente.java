package exercicio02;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	private static Socket socket;
    private static DataOutputStream saida;
    
    public static void main(String[] args) {
    	Scanner scn = new Scanner(System.in);
    	String mensagem = "";
    	
        try {
        	socket = new Socket("127.0.0.1", 50000);
        	
        	saida = new DataOutputStream(socket.getOutputStream());

        	do {
	            System.out.println("Mensagem para o servidor, digite `sair` para encerrar:");
	            mensagem = scn.nextLine();
	            
            	saida.writeUTF(mensagem);
            } while (!mensagem.equals("sair"));
            
            socket.close();
            System.out.println("Encerrado aplicação cliente....");
        } catch(Exception e) {
            
        }
    }
}
