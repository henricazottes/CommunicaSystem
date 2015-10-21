import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class CommunicaTCPClient extends CommunicaTCP{
	private Socket mySocket;
	
	public CommunicaTCPClient(){
		try {
			mySocket = new Socket(InetAddress.getByName("localhost"), 43000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
