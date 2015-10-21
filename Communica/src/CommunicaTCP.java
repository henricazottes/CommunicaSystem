import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public abstract class CommunicaTCP {
	public abstract OutputStream getOutputStream() throws IOException;
	public abstract InputStream getInputStream() throws IOException;
	public abstract void close();
}
