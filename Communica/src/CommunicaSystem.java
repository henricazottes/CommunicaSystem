import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;


public class CommunicaSystem {
	public static void main(String args[]){
		
		OutputStreamWriter myWriter;
		InputStreamReader  myReader;
		CommunicaTCP networkInterface = null;
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Mode ? (S)erver / (C)lient: ");
		String mode = scanner.next();
		System.out.print("Port: ");
		int port = Integer.parseInt(scanner.next());
		
		 if (mode.equals("S")){
			 
			 networkInterface = new CommunicaTCPServer(port);
			 			 
		 }else if (mode.equals("C")){
			 try {
				 
				 System.out.print("Server IP: ");
				 InetAddress ip = InetAddress.getByName(scanner.next());
				 networkInterface = new CommunicaTCPClient(ip, port);
				 
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 
			 
		 }		 
		 
		 try {
			 
			myReader = new InputStreamReader(networkInterface.getInputStream());
			myWriter = new OutputStreamWriter(networkInterface.getOutputStream());
			Communica communica = new Communica(myWriter, myReader, networkInterface);
			
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
}
