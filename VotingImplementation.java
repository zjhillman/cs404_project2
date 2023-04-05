import java.rmi.*;
import java.rmi.server.*;

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
        String option4 = "[.] Exit\n";
        String options = option1 + option2 + option3 + option4;
        return options;
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