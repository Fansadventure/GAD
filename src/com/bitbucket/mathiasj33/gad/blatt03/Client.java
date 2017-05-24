package com.bitbucket.mathiasj33.gad.blatt03;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Consumer;

public class Client {

	private static String line;
	private static Consumer<Integer> callback;
	
	public static void startClient(String line, Consumer<Integer> callback) throws Exception {
		Client.line = line;
		Client.callback = callback;
		main(new String[]{});
	}
	
	public static void main(String[] args) throws Exception {
		// Configuration
		String masterHost = "127.0.0.1";
		int masterClientPort = 5555;

		//TODO: just for testing purposes
		String line;
		
		if(Client.line != null) {
			line = Client.line;
		} else {
			System.out.println("Examples: read key");
			System.out.println("          store key 42");
			System.out.println("Please type your request:");

			Scanner scanner = new Scanner(System.in);
			line = scanner.nextLine();
			scanner.close();
		}
		String[] lineParts = line.split(" ");
		System.out.println(lineParts);
		IRequest request;

		if(lineParts[0].equals("read")) {
			request = new ReadRequest(lineParts[1]);
		} else if(lineParts[0].equals("store")) {
			request = new StoreRequest(lineParts[1], Integer.parseInt(lineParts[2]));
		} else {
			throw new IllegalArgumentException("Illegal input");
		}
		
		System.out.println("Sending: " + request);
		Socket master = new Socket(masterHost, masterClientPort);
		try {
			ObjectOutputStream out = new ObjectOutputStream(master.getOutputStream());
			out.writeObject(request);
			out.flush();

			ObjectInputStream in = new ObjectInputStream(master.getInputStream());
			IResponse response = (IResponse) in.readObject();
			ResponseVisitor rv = new ResponseVisitor();
			rv.__((readResponse) -> {
				SerializableOptional<Integer> result = readResponse.getValue();
				if (result.isPresent()) {
					System.out.println("Read response with value " + result.get() + ".");
					if(callback != null) callback.accept(result.get());
				}
				else {
					System.out.println("Read response: Unknown key!");
					if(callback != null) callback.accept(null);
				}
			}, (storeResponse) -> {
				System.out.println("Store successful!");
			});
			response.accept(rv);
		} finally {
			master.close();
		}

	}
}
