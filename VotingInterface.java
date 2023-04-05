import java.rmi.*;

public interface VotingInterface extends Remote {
    /*
     * TODO: Add methods for implementation
     */
    public String sayHello (String phrase) throws java.rmi.RemoteException;

    public int getYesCount () throws RemoteException;

    public int getNoCount () throws RemoteException;

    public int getDontCareCount () throws RemoteException;

    public int getTotalBallotsReceived () throws RemoteException;
}