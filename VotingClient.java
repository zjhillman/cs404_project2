import java.io.*;
import java.rmi.*;

public class VotingClient {
    private static boolean DEBUG = true;
    private static int rmiPort;
    private static String hostName;
    private static String registryURL;
    private static String usersName;
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
            
            try {
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
                    case 'n':
                        usersName = stdIn.readLine();
                    default:
                        i++;
                        break;
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
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

    private static void poll (VotingInterface vi) {
        try {
            String topic = vi.getTopic() + "\n";
            String instructions = vi.getPollInstructions();
            System.out.println("\n" + topic + instructions);
            String input = stdIn.readLine();

            switch (input) {
                case "1":
                    vi.castYesVote();
                    break;
                case "2":
                    vi.castNoVote();
                    break;
                case "3":
                    vi.castDontCareVote();
                    break;
                case ".":
                    return;
                default:
                    break;
            }
            System.out.println("Vote submitted\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String args[]) {
        parseArgs(args);
        if (DEBUG) {
            System.out.println("Port: " + rmiPort);
            System.out.println("Host Name: " + hostName);
            System.out.println("cla parsed");
        }

        try {
            registryURL = "rmi://" + hostName + ":" + rmiPort + "/voting";
            VotingInterface vi = (VotingInterface) Naming.lookup(registryURL);
            System.out.println("Established connection to " + registryURL);

            // greet
            System.out.println("What is your name?");
            usersName = stdIn.readLine();
            String message = vi.sayHello(name);
            System.out.println("\n" + message + ",");

            // state topic, ask to cast ballot
            message = vi.getTopic();
            System.out.println("Today's topic is: '" + message +"'");
            System.out.println("Do you wish to participate in the poll?");
            String response = stdIn.readLine();
            if (response.toLowerCase().equals("yes"))
                poll(vi);


            // loop to view results until client disconnects
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