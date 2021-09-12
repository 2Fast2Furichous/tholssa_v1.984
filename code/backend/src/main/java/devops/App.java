package devops;

import devops.network.Server;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Server server = new Server();
        server.run();
    }
}
