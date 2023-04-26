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
        String option3 = "[3] Indifferent/Other\n";
        String option4 = "[.] Exit";
        return option1 + option2 + option3 + option4;
    }

    public String getResultsInstructions () throws RemoteException{
        Format format = new SimpleDateFormat("HH:mm:ss");
        String time = format.format(new Date());

        String result1 = "As of " + time + ", " + getTotalBallotsReceived() + " votes have been cast\n";
        String result2 = "with " + getYesCount() + " yes vote(s), ";
        String result3 = getNoCount() + " no vote(s), and ";
        String result4 = getDontCareCount() + " voters who were indifferent.\n";
        String option1 = "[ENTER] Refresh the results\n";
        String option2 = "[.] Exit";
        return result1 + result2 + result3 + result4 + option1 + option2;
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