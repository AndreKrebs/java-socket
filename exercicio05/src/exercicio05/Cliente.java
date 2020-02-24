package exercicio05;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {

	public static void main(String[] args) {
		try {
			Registry registro = LocateRegistry.getRegistry("127.0.0.1", 1099);
			
			Validadora stub = (Validadora) registro.lookup("metodosValidadores");
			
			System.out.println("O CPF 855.191.930-02 é " + (stub.validarCpf("85519193002")==true ? "valido":"invalido"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
