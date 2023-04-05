import java.rmi.*;
import java.rmi.server.*;

public class VotingImplementation extends UnicastRemoteObject implements VotingInterface {
    public VotingImplementation () throws RemoteException {
        super();
    }

    public String sayHello (String phrase) throws RemoteException{
        return "Hello, " + phrase;
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
}