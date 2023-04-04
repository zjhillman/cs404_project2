import java.rmi.*;
import java.rmi.server.*;

public class VotingImplementation extends UnicastRemoteObject implements VotingInterface {
    public VotingImplementation () throws RemoteException {
        super();
    }

    public String sayHello(String phrase) throws RemoteException{
        return "I heard" + phrase;
    }
}