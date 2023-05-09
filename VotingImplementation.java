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
        String time = format.format(new Date()),
               result1, result2, result3, result4;
        int totalVotes = getTotalBallotsReceived(),
            yesVotes = getYesCount(), 
            noVotes = getNoCount(), 
            dontCareVotes = getDontCareCount();

        if (totalVotes == 1)
            result1 = "As of " + time + ", " + totalVotes + " vote has been cast\n";
        else
            result1 = "As of " + time + ", " + totalVotes + " votes have been cast\n";
        if (yesVotes == 1)
            result2 = "with " + yesVotes + " yes vote, ";
        else
            result2 = "with " + yesVotes + " yes votes, ";
        if (noVotes == 1)
            result3 = noVotes + " no vote, and ";
        else
            result3 = noVotes + " no votes, and ";
        if (dontCareVotes == 1)
            result4 = dontCareVotes + " voter who was indifferent.\n";
        else
            result4 = dontCareVotes + " voters who were indifferent.\n";
            
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