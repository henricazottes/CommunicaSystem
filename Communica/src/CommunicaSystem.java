import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;


public class CommunicaSystem {
	public static void main(String args[]){
		
		OutputStreamWriter myWriter;
		InputStreamReader  myReader;
		CommunicaTCP networkInterface = null;
		
			
		 if (args[0].equals("S")){
			 networkInterface = new CommunicaTCPServer();
			 			 
		 }else if (args[0].equals("C")){
			 
			 networkInterface = new CommunicaTCPClient();
			 
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
