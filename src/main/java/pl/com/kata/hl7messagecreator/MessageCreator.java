package pl.com.kata.hl7messagecreator;

public class MessageCreator {

	static DataSupplier ds = new DataSupplier();

	public static void createMessage() {
		ds.getNameList();
		ds.getLastNameList();
	}
}
