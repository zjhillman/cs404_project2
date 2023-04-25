import java.io.*;
import java.rmi.*;
import java.util.ArrayList;

/*
 * VotingClient
 * v0.4.6
 */
public class VotingClient {
    private static boolean DEBUG = false;
    private static int rmiPort;
    private static String hostName;
    private static String usersName;
    private static String registryURL;
    private static VotingInterface vi;
    private static boolean running;
    private static BufferedReader stdIn = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static void parseArgs (String args[]) { 
        // process flags
        int i = 0;
        boolean pSet = false, hSet = false, nSet = false;
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
                case 'n':
                    usersName = args[i + 1];
                    i += 2;
                    nSet = true;
                    break;
                default:
                    i++;
                    break;
            }
        }

        try {
            if (!nSet) {
                System.out.println("What is your name?");
                String in = stdIn.readLine();
                usersName = in;
                nSet = true;
            }
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

        if (DEBUG) {
            System.out.println("Name: " + usersName);
            System.out.println("Host Name: " + hostName);
            System.out.println("Port Number: " + rmiPort);
            System.out.println("--CLA Parsed--");
        }
    }

    private static void connect () {
        try {
            registryURL = "rmi://" + hostName + ":" + rmiPort + "/voting";
            vi = (VotingInterface) Naming.lookup(registryURL);
            System.out.println("Established connection to " + registryURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static void joinPoll () {
        ArrayList <String> yesCommands = new ArrayList<String>();
        yesCommands.add("yes");
        yesCommands.add("yeah");
        yesCommands.add("y");
        yesCommands.add("yuh");
        ArrayList <String> noCommands = new ArrayList<String>();
        noCommands.add("no");
        noCommands.add("n");
        noCommands.add("nah");
        noCommands.add("no thanks");

        try {
            // greet
            String message = vi.sayHello(usersName);
            System.out.println("\n" + message + ",");
    
            // state topic, ask to cast ballot
            message = vi.getTopic();
            System.out.println("Today's topic is '" + message +"'");
    
            boolean properResponse = true;
            do {
                System.out.println("Do you wish to participate in the poll?\n(You may see the results regardless)");
                String response = stdIn.readLine();
                if (yesCommands.contains(response)) {
                    poll();
                    properResponse = true;
                }
                else if (noCommands.contains(response)) {
                    System.out.println("\nYou chose not to particpate in the poll");
                    vi.castDontCareVote();
                    properResponse = true;
                }
                else {
                    System.out.println("\nCommand not understood, please try again");
                    properResponse = false;
                }
            } while (!properResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void poll () {
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
                case ".":
                    running = false;
                    return;
                default:
                    return;
            }
            System.out.println("\nVote submitted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getResult () {
        try {
            String message = vi.getResultsInstructions();
            System.out.println(message);

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
                    System.out.println("\nNumber of people who didn't vote: " + fromServer);
                    break;
                case ".":
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid input");
                    break;
            }
        } catch (Exception e) {
            running = false;
            e.printStackTrace();
        }
    }

    public static void main (String args[]) {
        parseArgs(args);

        try {
            running = true;
            connect();
            joinPoll();

            // loop to view results until client disconnects
            while (running) {
                getResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    } // end main
}