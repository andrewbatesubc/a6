import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class recurrence {
	private static int[][] soln;

	public recurrence(){	
	}

	public static int STSHelper(String[] words, int length, int i, int j){
		String tempString = "";
		for(int k = i; k <= j; k++){
			tempString = tempString.concat(words[k]);
		}
		
		int toReturn = tempString.length() + j - i;
		if((length - toReturn) >= 0)
			return  (int) Math.pow((length - toReturn), 2);
		else
			return (int)Double.POSITIVE_INFINITY;
	}

	private static int minSTS(String[] words, int lineLength){

		soln = new int[words.length][words.length];
		for(int i = 0; i < words.length; i++)
			Arrays.fill(soln[i], (int) Double.POSITIVE_INFINITY);

		for(int i = 0; i < words.length; i++){
			for(int j = 0; j <= i; j++){
				minSquaredHelper(words, lineLength, i, j);
			}
		}

		int minToReturn = (int)Double.POSITIVE_INFINITY;

		for(int i = 0; i < words.length; i++){
			int temp = soln[words.length - 1][i];
			if(temp < minToReturn)
				minToReturn = temp;
		}

		return minToReturn;

	}


	public static void minSquaredHelper(String[] words, int lineLength, int i, int j){
		if (j == 0){
			soln [i][0] = STSHelper(words, lineLength, 0, i);
		}
		else{
				int minInRow = (int)Double.POSITIVE_INFINITY;
				
				for(int k = 0; k < soln[j-1].length; k++){
					if(soln[j-1][k] < minInRow)
						minInRow = soln[j-1][k];
				}

				if(STSHelper(words, lineLength, j, i) != (int)Double.POSITIVE_INFINITY){
					soln[i][j] = minInRow + STSHelper(words, lineLength, j, i);	

				}
				else
					soln[i][j] = (int)Double.POSITIVE_INFINITY;
			}
			
		}
	

	public static void main(String[] args){
		String test0 = "";
		int test0LineLength = 0;

		String test1 = "Hello";
		int test1LineLength = 6;

		String test2 = "Hi there"; // violates constraint - word too long for line length
		int test2LineLength = 4;

		String test3 = "Hi there";
		int test3LineLength = 6;

		String test4 = "Banana bread is best with chocolate chips";
		int test4LineLength = 15;

		String test5 = "Banana bread is best with chocolate chips";
		int test5LineLength = 10;

		String test6 = "explain why this theta bound";
		int test6LineLength = 12;

		String[] testArray0 = test0.split(" ");
		String[] testArray1 = test1.split(" ");
		String[] testArray2 = test2.split(" ");
		String[] testArray3 = test3.split(" ");
		String[] testArray4 = test4.split(" ");
		String[] testArray5 = test5.split(" ");
		String[] testArray6 = test6.split(" ");

		wordGenerator(25);
		
		
	}

	private static void wordGenerator(int n) {
		ArrayList<String> originalList = new ArrayList<String>();
		ArrayList<String> newList = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		BufferedReader br = null;
		String line;
		
		System.out.println("Please enter file name to be read");
		
		try{
			br = new BufferedReader(new FileReader("C:\\Files\\" + scanner.next()));
		} catch (FileNotFoundException fnfex){
			System.out.println("File was not found");
			System.exit(0);
		}
		
		try {
			while((line = br.readLine()) != null){
				//System.out.println(line);
				originalList.add(line);
			}
		} catch (IOException ioex) {
			System.out.println(ioex.getMessage() + "Error readin file");
		}
		
		int selectionIndex = 0;
		for(int i = 0; i <= n; i++){
			 Random rand = new Random();
			 selectionIndex += rand.nextInt(10000);
			 if(selectionIndex <= originalList.size())
				 newList.add(originalList.get(selectionIndex));
			 else{
				 selectionIndex /= 10;
			 	 newList.add(originalList.get(selectionIndex));
			 }
		}
		for(String s : newList)
			System.out.println(s);
	}




}
