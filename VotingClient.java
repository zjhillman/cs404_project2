import java.io.*;
import java.rmi.*;

public class VotingClient {
    private static boolean DEBUG = true;
    private static int rmiPort;
    private static String hostName;
    private static String registryURL;
    private static BufferedReader stdIn = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static void parseArgs (String args[]) { 
        // process flags
        int i = 0;
        boolean pSet = false, hSet = false;
        while (i < args.length && args[i].startsWith("-")) {
            String arg = args[i];
            char flag = arg.charAt(1);
            
            switch (flag) {
                case 'p':
                    rmiPort = Integer.parseInt(args[i+1]);
                    i += 2;
                    pSet = true;
                    break;
                case 'h':
                    hostName = args[i + 1];
                    i += 2;
                    hSet = true;
                    break;
                default:
                    i++;
                    break;
            }
        }

        try {
            if (!pSet) {
                System.out.print("Please enter a port number: ");
                String in = stdIn.readLine();
                rmiPort = Integer.parseInt(in);
                pSet = true;
            }

            if (!hSet) {
                System.out.print("Please enter a host name: ");
                String in = stdIn.readLine();
                hostName = in;
                hSet = true;
            }
        } catch (IOException ioe) { 
            System.out.println("Invalid input");
        }   
    }

    public static void main (String args[]) {
        parseArgs(args);
        if (DEBUG) {
            System.out.println("Port: " + rmiPort);
            System.out.println("Host Name: " + hostName);
            System.out.println("done");
        }

        try {
            registryURL = "rmi://" + hostName + ":" + rmiPort + "/voting";
            VotingInterface vi = (VotingInterface) Naming.lookup(registryURL);
            System.out.println("Established connection to " + registryURL);

            String message = vi.sayHello("$uicideboy$");
            System.out.println("VotingServer: " + message);

            while (true) {
                System.out.println("Please choose one of the options below");
                System.out.println("[1] Get the number of yes votes");
                System.out.println("[2] Get the number of no votes");
                System.out.println("[3] Get the number of don't care votes");
                System.out.println("[4] Get the total number of votes");
                System.out.println("[.] Exit");

                String input = stdIn.readLine();
                int fromServer = -1;
                switch (input) {
                    case "1":
                        fromServer = vi.getYesCount();
                        System.out.println("\nNumber of yes votes: " + fromServer);
                        break;
                    case "2":
                        fromServer = vi.getNoCount();
                        System.out.println("\nNumber of no votes: " + fromServer);
                        break;
                    case "3":
                        fromServer = vi.getDontCareCount();
                        System.out.println("\nNumber of don't care votes: " + fromServer);
                        break;
                    case "4":
                        fromServer = vi.getTotalBallotsReceived();
                        System.out.println("\nTotal votes: " + fromServer);
                        break;
                    case ".":
                        return;
                    default:
                        System.out.println("\nInvalid input");
                        continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    } // end main
}