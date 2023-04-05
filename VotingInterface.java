import java.rmi.*;

public interface VotingInterface extends Remote {
    /*
     * TODO: Add methods for implementation
     */
    public String sayHello (String phrase) throws java.rmi.RemoteException;

    public String getTopic () throws RemoteException;

    public String getPollInstructions () throws RemoteException;

    public int getYesCount () throws RemoteException;

    public int getNoCount () throws RemoteException;

    public int getDontCareCount () throws RemoteException;

    public int getTotalBallotsReceived () throws RemoteException;

    public void castYesVote () throws RemoteException;

    public void castNoVote () throws RemoteException;

    public void castDontCareVote () throws RemoteException;
}