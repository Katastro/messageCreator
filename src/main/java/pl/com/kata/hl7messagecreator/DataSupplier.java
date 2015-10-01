package pl.com.kata.hl7messagecreator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataSupplier {
	String namesFile = "src/main/resources/names.json";
	String messageTemplateFolder = "src/main/resources/templates";

	ArrayList<String> nameList = null;
	ArrayList<String> lastNameList = null;

	Random rand = new Random();

	public List<String> getNameList() {

		if (nameList == null) {
			File names = new File(namesFile);
			InputStream ioStream = null;
			try {
				ioStream = new FileInputStream(names);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			JSONObject json = new JSONObject(new JSONTokener(ioStream));

			Type listType = new TypeToken<List<String>>() {
			}.getType();
			nameList = new Gson().fromJson(json.get("names").toString(), listType);

		}

		return nameList;
	}

	public List<String> getLastNameList() {

		if (lastNameList == null) {
			File names = new File(namesFile);
			InputStream ioStream = null;
			try {
				ioStream = new FileInputStream(names);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			JSONObject json = new JSONObject(new JSONTokener(ioStream));

			Type listType = new TypeToken<List<String>>() {
			}.getType();
			lastNameList = new Gson().fromJson(json.get("lastNames").toString(), listType);

		}

		return lastNameList;
	}

	private Map<String, String> getFileList() {
		File folder = new File(messageTemplateFolder);
		File[] listOfFiler = folder.listFiles();

		HashMap<String, String> fileList = new HashMap<String, String>();

		for (File f : listOfFiler) {
			try {
				fileList.put(f.getName(), new String(Files.readAllBytes(Paths.get(f.toURI()))));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return fileList;
	}
}
