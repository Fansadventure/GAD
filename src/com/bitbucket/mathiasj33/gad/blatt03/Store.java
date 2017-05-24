import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Store {

  public static void main (String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
    // Configuration
    String masterHost = "127.0.0.1";
    int masterStorePort = 5000;

    // *****************************************
    // TODO: Bereiten Sie eine Struktur vor, um die übergebenen und angefragten Werte zu speichern.
    // *****************************************

    Socket master = new Socket(masterHost, masterStorePort);
    try {
      while (true) {
        ObjectInputStream masterIn = new ObjectInputStream(master.getInputStream());
        ObjectOutputStream masterOut = new ObjectOutputStream(master.getOutputStream());
        IRequest request = (IRequest) masterIn.readObject();
        System.out.println(request);
        RequestVisitor rv = new RequestVisitor();
        Mutable<IResponse> response = new Mutable<IResponse>();
        rv.__( (ReadRequest readRequest) -> {

          // *****************************************
          // TODO: Lesen Sie den angefragten Wert aus (sofern vorhanden!),
          // bereiten Sie eine passende ReadResponse zur Anfrage vor,
          // und übergeben Sie sie an das response Objekt.
          // *****************************************

          // ReadResponse readResponse = ???
          // response.set(readResponse);

        }, (StoreRequest storeRequest) -> {

          // *****************************************
          // TODO: Speichern Sie den übergebenen Wert.
          // *****************************************

          response.set(new StoreResponse());
        });
        request.accept(rv);
        System.out.println(response.get());
        masterOut.writeObject(response.get());
        masterOut.flush();
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    finally {
      master.close();
    }
  }
}
