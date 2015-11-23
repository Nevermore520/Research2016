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
		final File folder = new File("data/Zhao_trees/All_Cells");//new File("data/FourClassDataHippoBreak"); //need to change folder for new data
		FileFunctions.listFilesForFolder(folder,fileNames);
		String output = "data/indexFile.txt";
		//printIndexFile(fileNames,output);
		String outputFolder = "data/SWC/";
		//FileFunctions.deleteComment(fileNames,outputFolder);
		String outputAbsPath = "data/Zhao_trees/output/ZhaoIndexFile";
		printIndexFileAbsolutePath(fileNames,outputAbsPath);
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

}
