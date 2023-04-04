import java.io.*;

/*
 * This class is the server to host the pull using java
 * rmi remote objects
 * Use VotingServer [port] [InetAddress] []
 */
public class VotingServer {
    private BufferedReader stdIn = new BufferedReader(
        new InputStreamReader(System.in)
    );
    private static int port;
    private static String registry;

    public static void main (String args[]) {
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

        System.out.println("Port: " + port);
        System.out.println("IP Address: " + registry);
        System.out.println("done");
    }
}