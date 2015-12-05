import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class FileFunctions {
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		String fileFolder = "data/Zhao_trees/All_Cells";
		String NNCfile = "data/Zhao_trees/output/dBSum_deltaLinfty_combSum_Euclidean_euclideanNNC_NN.txt";
		String resultFile = "data/Zhao_trees/output/classifyResult.txt";
		FileFunctions.validateSameDirectory(fileFolder, NNCfile, resultFile);
	}
	
	public static void validateSameDirectory(String fileFolder, String input, String output) throws FileNotFoundException, UnsupportedEncodingException{
		List<String> fileNames = new LinkedList<String>();
		final File folder = new File(fileFolder);
		FileFunctions.listFilesForFolder(folder,fileNames);
		int[] sameClass = new int[fileNames.size()];
		List<List<Integer>> nearestNeighborIndex = FileFunctions.readKNNList(input); // index start from 1
		for(int i=0;i<nearestNeighborIndex.size();i++){
			for(int j = 0;j< nearestNeighborIndex.get(i).size();j++){
				int NNIndex = nearestNeighborIndex.get(i).get(j)-1; 
				String queryName = fileNames.get(i);
				String NNName = fileNames.get(NNIndex);
				//System.out.println(queryName+"\n"+NNName+"\n");
				if(queryName.substring(0,queryName.lastIndexOf("\\")).equals(NNName.substring(0, NNName.lastIndexOf("\\")))){
					sameClass[i] += 1;
				}
			}
		}
		int sum = 0;
		for(int i=0;i<sameClass.length;i++){
			sum+=sameClass[i]>0?1:0;
		}
		System.out.println(sum+"/"+sameClass.length);
		printList(sameClass, output);
	}
	
	public static void printList(int[] sameClass, String output) throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writer = new PrintWriter(output, "UTF-8");
		for(int i=0;i<sameClass.length;i++){
			writer.println(sameClass[i]);
		}
		writer.close();
	}
	
	public static List<List<Integer>> readKNNList(String input) throws FileNotFoundException{
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		Scanner scanner = new Scanner(new FileReader(input));
		while(scanner.hasNext()){
			String[] nextLine = scanner.nextLine().split("\\s+");
			List<Integer> tmp = new ArrayList<Integer>();
			for(int i=0;i<nextLine.length;i++){
				tmp.add(Integer.parseInt(nextLine[i]));
			}
			list.add(tmp);
		}
		System.out.println(list.size());
		scanner.close();
		return list;
	}
	
	public static List<Integer> readList(String input) throws FileNotFoundException{
		List<Integer> list = new ArrayList<Integer>();
		Scanner scanner = new Scanner(new FileReader(input));
		while(scanner.hasNext()){
			list.add(scanner.nextInt());
		}
		scanner.close();
		return list;
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
	
	public static void deleteComment(List<String> fileNames, String outputFolder) throws FileNotFoundException{
		for(int i=0;i<fileNames.size();i++){
			Scanner input = new Scanner(new FileReader(fileNames.get(i)));
			String line = "";
			while(!input.hasNextInt()){
				input.nextLine();
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
	
	private static void extractNeuronFile(String fileName, String outputFolder, int structId,String prefix) throws FileNotFoundException{
		int rootStructID = 1;
		int index, structID, parentIndex, curIndex = 1;
		float x, y, z, radius;
		Map<Integer,Integer> indexMap = new HashMap<Integer,Integer>();
		File next = new File(fileName);
		Scanner input = new Scanner(next);
		String path = next.getPath();
		String name = next.getName();
		path = path.substring(0, path.length()-name.length());
		String outputFile = outputFolder+path+prefix+name;
		File  p = new File(outputFolder+path);
		System.out.println(path+"\n"+outputFile);
		p.mkdirs();
		PrintWriter writer = new PrintWriter(outputFile);
		while(!input.hasNextInt()){
			input.nextLine();
		}
		while(input.hasNext()){
			index = input.nextInt();
			structID = input.nextInt();
			x = input.nextFloat();
			y = input.nextFloat();
			z = input.nextFloat();
			radius = input.nextFloat();
			parentIndex = input.nextInt();
			if(structID==rootStructID||structID==structId){
				indexMap.put(index, curIndex);
				if(indexMap.containsKey(parentIndex)){
					//node
					parentIndex = indexMap.get(parentIndex);
					writer.println(" "+curIndex+" "+structID+" "+x+" "+y+" "+z+" "+radius+" "+parentIndex);
				}else{
					//root
					writer.println(" "+curIndex+" "+structID+" "+x+" "+y+" "+z+" "+radius+" "+"-1");
				}
				curIndex++;
			}
		}
		writer.close();
		input.close();
	}
	
	public static void copyAndBreak(List<String> fileNames, String outputFolder) throws FileNotFoundException{

		for(int i=0;i<fileNames.size();i++){
			extractNeuronFile(fileNames.get(i),outputFolder,2,"axon_");
			extractNeuronFile(fileNames.get(i),outputFolder,3,"dentrite_");
		}
	}
	

}
