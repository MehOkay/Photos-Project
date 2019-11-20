package model;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Helper function class to serialize data
 * @author bluel
 *
 */
public class SerializeData {
	
	/**
	 * Serializes user data to preserve state of the system
	 * @param users is the arraylist of users in the system
	 */
	public static void writeData(ArrayList<User> users) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("data/data.dat");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			objectOutputStream.writeObject(users);

			objectOutputStream.close();
			fileOutputStream.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
