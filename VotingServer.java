import java.io.*;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.registry.LocateRegistry;
import java.rmi.*;
import java.text.SimpleDateFormat;
import java.text.Format;
import java.util.Date;

/*
 * This class is the server to host the pull using java
 * rmi remote objects
 * Use VotingServer [port] [] []
 */
public class VotingServer {
    private static boolean DEBUG = false;
    private static int port;
    private static String registryURL;
    private static int yesCount = 0, 
                        noCount = 0, 
                        dontCareCount = 0, 
                        totalBallotsReceived = 0;
    private static VotingImplementation vi;
    private static String topic = "Do you believe A.I. chatbots are dangerous?";
    private static BufferedReader stdIn = new BufferedReader(
        new InputStreamReader(System.in) 
    );
    
    private static void parseArgs (String args[]) {
        boolean pSet = false;

        // process flags
        int i = 0;
        while (i < args.length && args[i].startsWith("-")) {
            String arg = args[i];
            char flag = arg.charAt(1);
            
            switch (flag) {
                case 'p':
                    port = Integer.parseInt(args[i+1]);
                    i += 2;
                    pSet = true;
                    break;
                default:
                    i++;
                    break;
            }
        }

        // if an arguement was not already entered
        try {
            if (!pSet) {
                System.out.print("Please enter a port number: ");
                String in = stdIn.readLine();
                port = Integer.parseInt(in);
                pSet = true;
            }
        } catch (IOException ioe) { 
            System.out.println("Invalid input");
        }
        if (DEBUG)
            System.out.println("Port: " + port);
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

    public static int getYesCount () {
        return yesCount;
    }

    public static int getNoCount () {
        return noCount;
    }

    public static int getDontCareCount () {
        return dontCareCount;
    }

    public static void castYesBallot () {
        try {
            Format format = new SimpleDateFormat("HH:mm:ss");
            String clientHost = RemoteServer.getClientHost();
            String dateReceived = format.format(new Date());
            yesCount++;
            totalBallotsReceived++;
            System.out.println("[" + dateReceived + "] " + clientHost + " has cast a yes ballot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void castNoBallot () {                         
        try {
            Format format = new SimpleDateFormat("HH:mm:ss");
            String clientHost = RemoteServer.getClientHost();
            String dateReceived = format.format(new Date());
            noCount++;
            totalBallotsReceived++;
            System.out.println("[" + dateReceived + "] " + clientHost + " has cast a no ballot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void castDontCareBallot () {
        try {
            Format format = new SimpleDateFormat("HH:mm:ss");
            String clientHost = RemoteServer.getClientHost();
            String dateReceived = format.format(new Date());
            dontCareCount++;
            totalBallotsReceived++;
            System.out.println("[" + dateReceived + "] " + clientHost + " has cast a dont care ballot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getTotalBallotsReceived () {
        return totalBallotsReceived;
    }

    public static String getTopic () {
        return topic;
    }

    public static void main (String args[]) {
        parseArgs(args);

        try {
            vi = new VotingImplementation();
            startRegistry();
            registryURL = "rmi://localhost:" + port + "/voting";

            Naming.rebind(registryURL, vi);
            listRegistry();
            System.out.println("Server ready.");
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
    } // end main
}