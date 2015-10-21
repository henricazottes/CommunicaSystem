import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicaTCPServer extends CommunicaTCP {
	private ServerSocket serverSk;
	private Socket mySocket;
	
	public CommunicaTCPServer(int port){
		mySocket = new Socket();
		try {
			serverSk = new ServerSocket(port);
			System.out.println("Waiting Client...");
			mySocket = serverSk.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public InputStream getInputStream() throws IOException{
		return this.mySocket.getInputStream();
	}
	
	@Override
	public OutputStream getOutputStream() throws IOException{
		return this.mySocket.getOutputStream();
	}
	
	@Override
	public void close(){
		try {
			this.mySocket.close();
			this.serverSk.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}