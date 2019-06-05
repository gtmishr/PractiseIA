//package com.mkyong;

import java.util.Scanner;
import java.util.ArrayList;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.lang.Object;
import java.lang.StringBuilder;

//import javax.net.ssl.HttpsURLConnection;

public class commandline {

	public static void main(String[] args){

    commandline program = new commandline();
    System.out.println("Welcome!");
    System.out.print("Enter a Function: ");
    Scanner reader = new Scanner(System.in);
    String a = reader.nextLine();

		try {
			URL url = new URL("https://ibcsamazonec2.tk/students");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			Map<String, String> parameters = new HashMap<>();
			parameters.put("param1", "val");

			con.setDoInput(true);

			con.setRequestProperty("Content-Type", "application/json");
			String contentType = con.getHeaderField("Content-Type");
			//String contentTypeTwo = con.getInputStream("Content-Type");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
	    	content.append(inputLine);
			}
			program.ParseJSON(content);
			in.close();

			con.disconnect();
		} catch (Exception e){
			System.out.print("Connection is not working");
			System.out.print(e);
		}
		if (a.equals("Reader") || a.equals("r")) {
        program.Reader();
    } else if (a.equals("Delete") || a.equals("d")) {
        program.Delete();
    }
	}

    private ArrayList < String > signedOffArrayList = new ArrayList < String > ();


    public void Reader() {

				int numberofthingos = 0;
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter a number: ");
        String n = reader.nextLine();
				System.out.println(namedArrayList);

        if (n.length() <= 10) {

            //Checks to see if already marked off
            if (!signedOffArrayList.contains(n)) {

								for(int z = 0; z < namedArrayList.size();z++){
										if (namedArrayList.get(z).contains(n)){
											System.out.println(z);
											for(int h = 0; h<namedArrayList.get(z-2).length();h++){
												StringBuilder g = new StringBuilder(namedArrayList.get(z-2));
												String[] sas = namedArrayList.get(z-2).split("");
												System.out.println(g);
												while(numberofthingos<3){
													if(sas[z].equals("\"")){
														numberofthingos = numberofthingos + 1;
													}
													numberofthingos = numberofthingos + 1;
													sas.remove(0);
													System.out.println(sas);
												}
											}
											System.out.println(namedArrayList.get(z+2));
										} else {
											System.out.println("Not"+z);
										}
								}
                // Insert code for collecting data from JSON file with additional details
                signedOffArrayList.add(n);
                System.out.println(n);
                System.out.println(signedOffArrayList);
                // Change to the name of the person once JSON file can be parsed
                System.out.println(n + " has signed in.");
            } else {
                // If already signed in, inform user and delete
                System.out.println(n + " has already signed in.");
            }
            System.out.print("Enter a Function: ");

            String a = reader.nextLine();
            if (a.equals("Reader")) {
                Reader();
            } else {
                Delete();
            }
        }
    }

    public void Delete() {

        Scanner reader = new Scanner(System.in);
        System.out.print("Enter a number: ");
        String Deleted = reader.nextLine();

        for (int i = 0; i < signedOffArrayList.size(); i++) {
            if (signedOffArrayList.get(i).equals(Deleted)) {

                System.out.println(Deleted + "Has been Removed");
                signedOffArrayList.remove(i);
                System.out.println(signedOffArrayList);

            }
        }
        System.out.print("Enter a Function: ");
        String a = reader.nextLine();

        if (a.equals("Reader")) {
            Reader();
        } else {
            Delete();
        }
    }

		private ArrayList < String > namedArrayList = new ArrayList < String > ();

		public void ParseJSON(StringBuffer apiInput) {
			// Delete first and last characters, and then convert the StringBuffer into a String, and split into array
			apiInput = apiInput.deleteCharAt(0);
			apiInput = apiInput.deleteCharAt(apiInput.length() - 1);
			String parsedString = apiInput.toString();
			String[] parsedArray = parsedString.split(",");

			for (int i = 0; i <= parsedArray.length - 1; i++) {
				namedArrayList.add(parsedArray[i]);
			}


		}

}
