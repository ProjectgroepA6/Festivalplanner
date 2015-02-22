package agenda;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;


public class Io {
	
	public static void main(String [] args) throws IOException{
		Io io = new Io();
	}
	
	public Io() throws IOException
	{
		writeIo();
	}
	
	public void writeIo() throws IOException
	{
		JFileChooser fileChooser = new JFileChooser();
		if(fileChooser.showSaveDialog(null)
				== fileChooser.APPROVE_OPTION) {
			java.io.File file = fileChooser.getSelectedFile();
		
		ObjectOutputStream output = null;
		try{
			output = new ObjectOutputStream(new FileOutputStream(file));
		}catch(IOException e){ 
			System.out.println("Could not open file." + e);
			System.exit(0);
		}
		try{
			output.writeObject(new Agenda());

			output.close();
		}catch(IOException e){
			System.out.println("Writing error.  " + e);
			System.exit(0); 
		}
		}

	}

	public void readIo() throws IOException
	{
		JFileChooser fileChooser = new JFileChooser();
		if(fileChooser.showOpenDialog(null)
				== fileChooser.APPROVE_OPTION) {
			java.io.File file = fileChooser.getSelectedFile();
			
			ObjectInputStream input = null;
			
			try{
				input = new ObjectInputStream(new FileInputStream(file));
			}catch(IOException e){
				System.out.println("There was a problem opening the file. " + e);
				System.exit(0);

			}
			Agenda agenda = null;
			try{
				agenda = (Agenda)input.readObject();
				input.close();
			}catch(Exception e){
				System.out.println("There was a issue reading this file: " + e);
				System.exit(0);
			}
			System.out.println("agenda's stuff is : " + agenda.getActs());
	}
			
	}
}







