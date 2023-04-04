import java.io.*;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.*;

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
    private static String registryURL;
    
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
                    registryURL = args[i + 1];
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
            LocateRegistry.createRegistry(port);
            System.out.println("Registry created at " + port);
        }
    }

    private static void listRegistry () throws RemoteException, MalformedURLException {
        System.out.println("Registry at " + registryURL + " contains:");
        String names[] = Naming.list(registryURL);
        for (int i = 0; i < names.length; i++)
            System.out.println(names[i]);
    }

    public static void main (String args[]) {
        parseArgs(args);
        if (DEBUG) {
             System.out.println("Port: " + port);
            System.out.println("IP Address: " + registryURL);
            System.out.println("done");
        }

        try {
            VotingImplementation vi = new VotingImplementation();
            startRegistry();
            registryURL = "rmi://localhost:" + port + "/voting";

            Naming.rebind(registryURL, vi);
            listRegistry();
            System.out.println("Server ready.");
        }
        catch (Exception re) {
            re.getCause();
            re.printStackTrace();
            return;
        }
    } // end main
}