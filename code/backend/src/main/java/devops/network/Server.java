package devops.network;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Server extends Thread {
	private Gson gson;
	/**
	 * Default constructor.
	 * 
	 * @precondition none
	 * @postcondition All default values have been initialized.
	 */
	public Server(){
		this.gson = new Gson();
	}

	@Override
	public void run() {
		Context context = ZMQ.context(10);
		Socket socket = context.socket(ZMQ.REP);
		socket.bind("tcp://127.0.0.1:5555");


        while (!Thread.currentThread().isInterrupted()) {
            String request = socket.recvStr();
			this.gson.fromJson(request, JsonObject.class);
            

        }

        socket.close();
        context.term();
		
	}
}

