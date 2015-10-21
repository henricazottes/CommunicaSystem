import java.io.BufferedReader;
import java.io.IOException;


public class ListenSocket extends Thread {
	private String lastMessage;
	private BufferedReader reader;
	private Boolean stop;
	private Communica obs;

	public ListenSocket(BufferedReader myReader){
		this.reader = myReader;
		this.stop = false;
	}
	
	public void run(){
		while(!stop){
			try {
				setLastMessage(reader.readLine());
				if(getLastMessage() == null){
					setLastMessage("Your correspondant has left.");
					stopReader();
				}
				notifyObs();
				this.run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(String lastMessage) {
		this.lastMessage = lastMessage;
	}

	public void stopReader() {
		this.stop = true;
	}
	
	public void addObserver(Communica obs){
		this.obs = obs;
	}
	
	private void notifyObs(){
		this.obs.updateReceive();
	}
	
	
}
