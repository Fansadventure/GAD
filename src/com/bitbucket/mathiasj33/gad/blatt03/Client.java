import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

  public static void main (String[] args) throws Exception {
    // Configuration
    String masterHost = "127.0.0.1";
    int masterClientPort = 5555;

    System.out.println("Examples: read key");
    System.out.println("          store key 42");
    System.out.println("Please type your request:");

    Scanner scanner = new Scanner(System.in);
    String line = scanner.nextLine();
    scanner.close();
    String[] lineParts = line.split(" ");
    IRequest request;



    // *****************************************
    // TODO: Werten Sie das Array lineParts aus und interpretieren die Eingabe entsprechend.
    // Eine passende Anfrage sollte danach in der Variable request gespeichert sein.
    // *****************************************



    System.out.println("Sending: " + request);
    Socket master = new Socket(masterHost, masterClientPort);
    try {
      ObjectOutputStream out = new ObjectOutputStream(master.getOutputStream());
      out.writeObject(request);
      out.flush();

      ObjectInputStream in = new ObjectInputStream(master.getInputStream());
      IResponse response = (IResponse) in.readObject();
      ResponseVisitor rv = new ResponseVisitor();
      rv.__( (readResponse) -> {
        SerializableOptional<Integer> result = readResponse.getValue();
        if(result.isPresent())
          System.out.println("Read response with value " + result.get() + ".");
        else
          System.out.println("Read response: Unknown key!");
      }, (storeResponse) -> {
        System.out.println("Store successful!");
      });
      response.accept(rv);
    }
    finally {
      master.close();
    }

  }
}
