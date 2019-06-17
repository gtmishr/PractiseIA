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

public class commandLineRFID {

	public static void main(String[] args){

    commandLineRFID program = new commandLineRFID();

		try {
			URL url = new URL("https://ibcsamazonec2.tk/students");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			Map<String, String> parameters = new HashMap<>();
			parameters.put("param1", "val");

			con.setDoInput(true);

			con.setRequestProperty("Content-Type", "application/json");
			String contentType = con.getHeaderField("Content-Type");

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

		System.out.println("Welcome!");
    System.out.print("Enter a Function: ");
    Scanner reader = new Scanner(System.in);
    String nextLine = reader.nextLine();

		if (nextLine.equals("Reader") || nextLine.equals("r")) {
        program.Reader();
    } else if (nextLine.equals("Delete") || nextLine.equals("d")) {
        program.Delete();
    } else if (nextLine.equals("Exit") || nextLine.equals("e")){
			System.out.println("Leaving program...");
		}

	}

    private ArrayList <String> signedOffArrayListNames = new ArrayList <String> ();
		private ArrayList <String> signedOffArrayListRFID = new ArrayList <String> ();


    public void Reader() {
				StringBuilder name = new StringBuilder();
				int numberofthingos = 0;
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter a number: ");
        String nextLine = reader.nextLine();

        if (nextLine.length() <= 10) {

            // Check to see if already marked off
            if (!signedOffArrayListRFID.contains(nextLine)) {

								for(int i = 0; i < namedArrayList.size();i++){
										if (namedArrayList.get(i).contains(nextLine)){

												StringBuilder first = new StringBuilder(namedArrayList.get(i - 2));
												StringBuilder last = new StringBuilder(namedArrayList.get(i - 1));

												for(int j = 0; j<first.length() - 14; j++){
													name.append(Character.toString(first.charAt(13 + j)));
												};

												name.append(" ");
												for(int j=0; j<first.length() - 14; j++){
													name.append(Character.toString(last.charAt(12 + j)));
												};

										}
								}
                // Insert code for collecting data from JSON file with additional details
                signedOffArrayListRFID.add(nextLine);
								signedOffArrayListNames.add(name.toString());
                // Change to the name of the person once JSON file can be parsed
                System.out.println(name + " has signed in.");
            } else {
            	// If already signed in, inform user and delete
                System.out.println(name + " has already signed in.");
            }

            System.out.print("Enter a Function: ");

            String postNextLine = reader.nextLine();
            if (postNextLine.equals("Reader") || postNextLine.equals("r") ) {
                Reader();
            } else if(postNextLine.equals("Delete") || postNextLine.equals("d")){
                Delete();
            } else if(nextLine.equals("Exit") || nextLine.equals("e")){
							System.out.println("Leaving program...");
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
            }
        }

        System.out.print("Enter a Function: ");
        String a = reader.nextLine();

        if (a.equals("Reader")|| a.equals("r")) {
            Reader();
        } else if(a.equals("Delete") || a.equals("d")){
						Delete();
				} else if (a.equals("Exit") || a.equals("e")){
					System.out.println("Leaving program...");
				}
    }

		private ArrayList <String> namedArrayList = new ArrayList <String> ();

		// Parse the JSON input (this is pretty dodgy so if my prospect employer sees this in like 10 years time please note that this was a stepping stone on our ways to learn how to code :) )
		public void ParseJSON(StringBuffer apiInput) {
			apiInput = apiInput.deleteCharAt(0);
			apiInput = apiInput.deleteCharAt(apiInput.length() - 1);
			String parsedString = apiInput.toString();
			String[] parsedArray = parsedString.split(",");

			for (int i = 0; i <= parsedArray.length - 1; i++) {
				namedArrayList.add(parsedArray[i]);
			}


		}

}
