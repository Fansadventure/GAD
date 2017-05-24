package com.bitbucket.mathiasj33.gad.blatt03;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws Exception {
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
				if (result.isPresent())
					System.out.println("Read response with value " + result.get() + ".");
				else
					System.out.println("Read response: Unknown key!");
			}, (storeResponse) -> {
				System.out.println("Store successful!");
			});
			response.accept(rv);
		} finally {
			master.close();
		}

	}
}
