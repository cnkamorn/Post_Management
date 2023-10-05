package application.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import application.Exception.InputHeaderException;
import application.Exception.InvalidFileTypeException;

/**
 * This class is a an import class It contains related methods about import the
 * post
 * 
 * @author Chanakan Amornpatchara
 * @version 1.0.0
 */
public class ImportFile {

	private static ImportFile Instance;

	private ImportFile() {
	};

	public static ImportFile getInstance() {
		if (Instance == null) {
			Instance = new ImportFile();
		}
		return Instance;
	}

	public ArrayList<Post> bulkImport(File file)
			throws InvalidFileTypeException, InputHeaderException, FileNotFoundException {
		ArrayList<Post> postList = new ArrayList();
		boolean headerCheck = false;
		if ((file.getName().contains(".csv")) && (file != null)) {
			Scanner reader = new Scanner(file);
			while (reader.hasNext()) {
				String line = reader.nextLine();
				if (line.contains("ID,content,author,likes,shares,date-time")) {
					headerCheck = true;
				} else {
					String[] tokens = line.split(",");
					Post post = new Post(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Integer.parseInt(tokens[3]),
							Integer.parseInt(tokens[4]), tokens[5]);
					postList.add(post);
				}
			}
			reader.close();
			if (!headerCheck) {
				throw new InputHeaderException("No header found");
			}
		} else {
			throw new InvalidFileTypeException("invalid file");
		}
		return postList;
	}
}
