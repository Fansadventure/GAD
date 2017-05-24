package com.bitbucket.mathiasj33.gad.blatt03;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;

public class Store {

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		// Configuration
		String masterHost = "127.0.0.1";
		int masterStorePort = 5000;

		Hashtable<String, Integer> table = new Hashtable<>();
		System.out.println("Start store");
		Socket master = new Socket(masterHost, masterStorePort);
		System.out.println("Connected.");
		try {
			while (true) {
				ObjectInputStream masterIn = new ObjectInputStream(master.getInputStream());
				ObjectOutputStream masterOut = new ObjectOutputStream(master.getOutputStream());
				IRequest request = (IRequest) masterIn.readObject();
				System.out.println(request);
				RequestVisitor rv = new RequestVisitor();
				Mutable<IResponse> response = new Mutable<IResponse>();
				rv.__((ReadRequest readRequest) -> {
					 String key = readRequest.getKey();
					 ReadResponse readResponse;
					 if(table.containsKey(key)) readResponse = new ReadResponse(table.get(key));
					 else readResponse = new ReadResponse();
					 response.set(readResponse);

				}, (StoreRequest storeRequest) -> {
					table.put(storeRequest.getKey(), storeRequest.getValue());
					response.set(new StoreResponse());
				});
				request.accept(rv);
				System.out.println(response.get());
				masterOut.writeObject(response.get());
				masterOut.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			master.close();
		}
	}
}
