package devops;

import devops.network.Gateway;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Server is Running" );
        Gateway server = new Gateway();
        server.run();
    }
}
