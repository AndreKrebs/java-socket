package exercicio05;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor implements Validadora {

	
	@Override
	public boolean validarCpf(String cpf) throws RemoteException {
		return validaCpf(cpf);
	}

	public static void main(String[] args) {
		try {
			Servidor s = new Servidor();
			
			Validadora stub = (Validadora)UnicastRemoteObject.exportObject(s, 0);
			
			Registry registro = LocateRegistry.getRegistry("127.0.0.1", 1099);
			
			registro.bind("metodosValidadores", stub);
			
			System.out.println("Servidor RMI esta pronto!!!");
			
		} catch (Exception e) {
			
		}
	}
	
	public static boolean validaCpf(String cpf) {
    	if (cpf.length() != 11) {
    		return false;
    	}
    	
    	return calculoDigito(cpf);
    }

	private static boolean calculoDigito(String cpf) {
		int soma, resto;
		
		for (int dg = 0; dg < 2; dg++) {
			soma = 0;
			resto = 0;
			
			for (int cont = 0; cont < cpf.length()-(2-dg); cont++) {
				soma += ((10+dg) - cont) * (Character.getNumericValue(cpf.charAt(cont)));
			}
			
			resto = soma%11;
			
			if (resto < 2) {
				// digito menor que dois é igual a zero
				if (Character.getNumericValue(cpf.charAt(cpf.length()-(2-dg))) == 0) {
					continue;
				} else {
					return false;
				}
			} else {
				int digito = Character.getNumericValue(cpf.charAt(cpf.length()-(2-dg)));
				
				int sub = 11 - resto;
				
				if (sub == digito) {
					continue;
				} else {
					return false;
				}
			}
		}
		
		return true;
	}
	
}
