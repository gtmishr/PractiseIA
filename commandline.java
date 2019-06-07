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

    private ArrayList < String > signedOffArrayListNames = new ArrayList < String > ();
		private ArrayList < String > signedOffArrayListRFID = new ArrayList < String > ();


    public void Reader() {
				StringBuilder name = new StringBuilder();
				int numberofthingos = 0;
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter a number: ");
        String n = reader.nextLine();

        if (n.length() <= 10) {

            //Checks to see if already marked off
            if (!signedOffArrayListRFID.contains(n)) {

								for(int z = 0; z < namedArrayList.size();z++){
										if (namedArrayList.get(z).contains(n)){

												StringBuilder first = new StringBuilder(namedArrayList.get(z-2));
												StringBuilder last = new StringBuilder(namedArrayList.get(z-1));

												for(int Z=0;Z<first.length()-14;Z++){
													name.append(Character.toString(first.charAt(13+Z)));
												};
												name.append(" ");
												for(int Z=0;Z<first.length()-14;Z++){
													name.append(Character.toString(last.charAt(12+Z)));
												};

										} else {
											//System.out.println("Not"+z);
										}
								}
                // Insert code for collecting data from JSON file with additional details
                signedOffArrayListRFID.add(n);
								signedOffArrayListNames.add(name.toString());
                //System.out.println("Ziling Ouyang");
                // System.out.println(signedOffArrayList);
                // Change to the name of the person once JSON file can be parsed
                System.out.println(name + " has signed in.");
            } else {
                // If already signed in, inform user and delete
                System.out.println(name + " has already signed in.");
            }
            System.out.print("Enter a Function: ");

            String a = reader.nextLine();
            if (a.equals("Reader") || a.equals("r") ) {
                Reader();
            } else if(a.equals("Delete") || a.equals("d")){
                Delete();
            }
        }
    }

    public void Delete() {

        Scanner reader = new Scanner(System.in);
        System.out.print("Enter a number: ");
        String Deleted = reader.nextLine();

        for (int i = 0; i < signedOffArrayListRFID.size(); i++) {
            if (signedOffArrayListRFID.get(i).equals(Deleted)) {

                System.out.println(signedOffArrayListNames.get(i) + " has signed out");
                signedOffArrayListRFID.remove(i);
								signedOffArrayListNames.remove(i);
                //System.out.println(signedOffArrayList);

            }
        }
        System.out.print("Enter a Function: ");
        String a = reader.nextLine();

        if (a.equals("Reader")|| a.equals("r")) {
            Reader();
        } else if(a.equals("Delete") || a.equals("d")){
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
