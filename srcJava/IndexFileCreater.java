import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class IndexFileCreater {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		List<String> fileNames = new LinkedList<String>();
		final File folder = new File("data/FourClassData"); //need to change folder for new data
		listFilesForFolder(folder,fileNames);
		String output = "data/indexFile.txt";
		//printIndexFile(fileNames,output);
		String outputFolder = "data/SWC/";
		//deleteComment(fileNames,outputFolder);
		String outputAbsPath = "data/indexFileAbsPath.txt";
		printIndexFileAbsolutePath(fileNames,outputAbsPath);
	}
	
	private static void deleteComment(List<String> fileNames, String outputFolder) throws FileNotFoundException{
		for(int i=0;i<fileNames.size();i++){
			Scanner input = new Scanner(new FileReader(fileNames.get(i)));
			String line = "";
			while(input.hasNext()){
				line = input.nextLine();
				if(line.charAt(0)!='#'){
					break;
				}
			}
			File next = new File(fileNames.get(i));
			System.out.println(next.getName());
			String outputFile = outputFolder+next.getName();
			PrintWriter writer = new PrintWriter(outputFile);
			writer.println(line);
			while(input.hasNext()){
				writer.println(input.nextLine());
			}
			input.close();
			writer.close();
		}
	}
	
	private static void printIndexFile(List<String> fileNames,String output) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(output, "UTF-8");
		for(int i=0;i<fileNames.size();i++){
			File next = new File(fileNames.get(i));
			writer.println((i+1)+" "+next.getName());
		}
		writer.close();
	}
	
	private static void printIndexFileAbsolutePath(List<String> fileNames,String output) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(output, "UTF-8");
		for(int i=0;i<fileNames.size();i++){
			writer.println((i+1)+" "+fileNames.get(i));
		}
		writer.close();
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
