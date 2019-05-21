
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import java.util.ArrayList;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class DIFR {

	protected Shell shell;
	private Text INPUTGUY;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DIFR window = new DIFR();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	
	protected void createContents() {
		shell = new Shell();
		shell.setSize(495, 699);
		shell.setText("SWT Application");
		
		// List item created
		List listSignedOff = new List(shell, SWT.BORDER);
		listSignedOff.setBounds(241, 10, 222, 642);
		
		//Array List for containment of IDs created
		ArrayList<String> signedOffArrayList = new ArrayList<String>();
		
		//Status Label created
		Label lblStatus = new Label(shell, SWT.NONE);
		lblStatus.setText("Welcome!");
		lblStatus.setBounds(10, 94, 222, 112);
		
		//Text Field create
		Text txtRFIDInput = new Text(shell, SWT.BORDER);
		
		//Function for RFID press
		txtRFIDInput.addKeyListener(new KeyAdapter() {
			@Override
			
			public void keyPressed(KeyEvent e) {
				
				//Check if card is a school issue RFID card
				if (txtRFIDInput.getText().length() == 10) {
					
					//Checks to see if already marked off
					if(!signedOffArrayList.contains(txtRFIDInput.getText())) {
						
						// Insert code for collecting data from JSON file with additional details
						signedOffArrayList.add(txtRFIDInput.getText());
						listSignedOff.add(txtRFIDInput.getText());
						
						// Change to the name of the person once JSON file can be parsed
						lblStatus.setText(txtRFIDInput.getText() + " has signed in.");
						
						//Empty the text field
						txtRFIDInput.setText("");
						
					} else {
						
						//If already signed in, inform user and delete
						lblStatus.setText(txtRFIDInput.getText() + " has already signed in.");
						txtRFIDInput.setText("");
						
					}
				}
			}
		});
		
		txtRFIDInput.setBounds(27, 10, 136, 42);
		
		//Creates delete button
		Button btnDelete = new Button(shell, SWT.NONE);
		
		//On-Click event
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				//Removes selected from array
				for(int i = 0; i<signedOffArrayList.size(); i++) {
					if ( i == listSignedOff.getSelectionIndex()) {
						
						System.out.println(signedOffArrayList.get(i));
						signedOffArrayList.remove(i);
						
					}
				}
				
				//Removes from List
				signedOffArrayList.contains(listSignedOff.getSelectionIndex());
				listSignedOff.remove(listSignedOff.getSelectionIndex());
				
			}
		});
		
		btnDelete.setBounds(35, 179, 94, 27);
		btnDelete.setText("Delete");
		
		
	}
}
