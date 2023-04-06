import java.rmi.*;
import java.rmi.server.*;
import java.text.SimpleDateFormat;
import java.text.Format;
import java.util.*;

public class VotingImplementation extends UnicastRemoteObject implements VotingInterface {
    public VotingImplementation () throws RemoteException {
        super();
    }

    public String sayHello (String name) throws RemoteException {
        return "Hello, " + name;
    }

    public String getTopic () throws RemoteException {
        return VotingServer.getTopic();
    }

    public String getPollInstructions () throws RemoteException {
        String option1 = "[1] Yes\n";
        String option2 = "[2] No\n";
        String option3 = "[3] Don't Care (See Results)\n";
        String option4 = "[.] Exit";
        return option1 + option2 + option3 + option4;
    }

    public String getResultsInstructions () throws RemoteException{
        Format format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(new Date());

        String option1 = "As of " + time + " the total number of votes is: " + getTotalBallotsReceived() + "\n";
        String option2 = "Please choose one of the options below\n";
        String option3 = "[1] Get the number of yes votes\n";
        String option4 = "[2] Get the number of no votes\n";
        String option5 = "[3] Get the number of don't care votes\n";
        String option6 = "[.] Exit";
        return option1 +option2 + option3 + option4 + option5 + option6;
    }

    public int getYesCount () throws RemoteException {
        return VotingServer.getYesCount();
    }

    public int getNoCount () throws RemoteException {
        return VotingServer.getNoCount();
    }

    public int getDontCareCount () throws RemoteException {
        return VotingServer.getDontCareCount();
    }

    public int getTotalBallotsReceived () throws RemoteException {
        return VotingServer.getTotalBallotsReceived();
    }

    public void castYesVote () throws RemoteException {
        VotingServer.castYesBallot();
    }

    public void castNoVote () throws RemoteException {
        VotingServer.castNoBallot();
    }

    public void castDontCareVote () throws RemoteException {
        VotingServer.castDontCareBallot();
    }
}