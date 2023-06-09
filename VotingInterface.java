import java.rmi.*;

public interface VotingInterface extends Remote {
    public String sayHello (String name) throws java.rmi.RemoteException;

    public String getTopic () throws RemoteException;

    public String getPollInstructions () throws RemoteException;

    public String getResultsInstructions () throws RemoteException;

    public int getYesCount () throws RemoteException;

    public int getNoCount () throws RemoteException;

    public int getDontCareCount () throws RemoteException;

    public int getTotalBallotsReceived () throws RemoteException;

    public void castYesVote () throws RemoteException;

    public void castNoVote () throws RemoteException;

    public void castDontCareVote () throws RemoteException;
}