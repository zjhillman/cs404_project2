import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

/*
 * This class is the server to host the pull using java
 * rmi remote objects
 * Use VotingServer [port] [InetAddress] []
 */
public class VotingServer {
    private static boolean DEBUG = true;
    private BufferedReader stdIn = new BufferedReader(
        new InputStreamReader(System.in) );
    private static int port;
    private static String registry;
    
    private static void parseArgs (String args[]) { 
        // process flags
        int i = 0;
        while (i < args.length && args[i].startsWith("-")) {
            String arg = args[i];
            char flag = arg.charAt(1);
            
            switch (flag) {
                case 'p':
                    port = Integer.parseInt(args[i+1]);
                    i += 2;
                    break;
                case 'r':
                    registry = args[i + 1];
                    i += 2;
                    break;
                default:
                    i++;
                    break;
            }
        }
    }

    private static void startRegistry () throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            registry.list();
        } catch (RemoteException re) {
            System.out.println("Cannot find registry at port " + port);
            Registry registry = LocateRegistry.createRegistry(port);
            System.out.println("Registry created at " + port);
        }
        
    }

    public static void main (String args[]) {
        parseArgs(args);
        if (DEBUG) {
             System.out.println("Port: " + port);
            System.out.println("IP Address: " + registry);
            System.out.println("done");
        }

        try {
            VotingImplementation vi = new VotingImplementation();
        }
        catch (RemoteException re) {
            re.getCause();
            re.printStackTrace();
        }

    } // end main
}