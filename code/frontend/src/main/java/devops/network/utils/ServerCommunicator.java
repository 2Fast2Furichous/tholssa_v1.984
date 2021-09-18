package devops.network.utils;

import org.zeromq.ZMQ.Context;
import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

/**
 * Communicates between back-end and front-end for data transfer.
 * 
 * @author Alexander Ayers
 * @version Fall 2021
 */
public class ServerCommunicator {
    private static final int SOCKET_PORT = 5555;

    /**
     * Sends a request while opening and closing a network port. Request should be
     * used with JSON and within specifed are the request type and the content of
     * the request.
     * 
     * @precondition none
     * @postcondition none
     * @param request the request that is being sent to the server.
     * @return the response from the server.
     */
    public static String sendRequest(String request) {
        Context context = ZMQ.context(10);
        Socket socket = context.socket(SocketType.REQ);
        socket.connect("tcp://127.0.0.1:" + ServerCommunicator.SOCKET_PORT);

        boolean isSent = socket.send(request.getBytes(ZMQ.CHARSET));

        String response;
        if (isSent) {
            response = socket.recvStr();
        } else {
            response = "";
        }
        
        socket.close();
        context.close();
        
        return response;

    }
}
