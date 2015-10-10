import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		List<String> fileNames = new LinkedList<String>();
		final File folder = new File("data/FourClassData"); //need to change folder for new data
		listFilesForFolder(folder,fileNames);
		System.out.println(fileNames.size()+" files read.");
		FileTransfer fileTransfer;
		for(int i=0;i<fileNames.size();i++){
			System.out.println("process file: "+fileNames.get(i));
			String inputFile = fileNames.get(i);
			File next = new File(inputFile);
			//String outputFile = "data/output/modified_"+next.getName(); // need to change folder for new data
			//String outputFile = "data/output/modified_geodesic_"+next.getName();
			String outputFile = "data/FourClassOutput/Geodesic/"+(i+1)+"_Geodesic_"+next.getName();
			fileTransfer = new GeodesicFileTransfer(inputFile);
			fileTransfer.ChangeFileFormat(outputFile);
		}
	}
	
	public static void listFilesForFolder(final File folder, List<String> fileNames) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry, fileNames);
	        } else {
	            fileNames.add(fileEntry.getPath());
	        }
	    }
	}

}
