package stringPlagarism.aglorthim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class MClass {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String ptrnLine, textLine,inpLine,sFilePath,srcLine;
		String input;
		int srcLineIndex=1, inpLineIndex=1; 
		KMP kmpComponent;
		BMSubStr bmComponent;
		RabinKarp rkComponent;
		int inputLen,srcLen,patterntextLength;
		double lcssSimRatio = 0, kmpSimRatio = 0, boyreMooreSimRatio = 0, stringNaSimRatio = 0;
		int rkNumberOfMatches;
		int fullTextLength=0, fullPatternLength=0;
		int mainTimeCount= 0, lcssComponentCount=0,kmpComponentCount=0, rkComponentCount=0, bmComponentCount=0, naiveComponentCount=0;
		boolean rkPlagarismStatus = false;
		//Source folder for the genuine files (pool) 
		final File folder = new File("C:\\AI_Project");
		File fileKmp = new File("kmp.txt"); // kmp result to be written in kmp.txt
		File fileBM = new File("boyreMoore.txt"); // Boyes Moore result to be written in boyreMoore.txt
		File fileRK = new File("rk.txt"); //Rabin Karp result of the plagarism written in rk.txt
		int coun = 0;
		// old result files deleted for the new result files
		fileKmp.delete();
		fileBM.delete();
		fileRK.delete();
		
		FileWriter outKmpFile = new FileWriter("kmp.txt", true);
		FileWriter outBoyreMooreFile = new FileWriter("boyreMoore.txt", true);
		FileWriter outRkFile = new FileWriter("rk.txt", true);
		
		// reading the files from the pool for the plagiarism 
		for (final File fileEntry : folder.listFiles()) {
			sFilePath = fileEntry.getPath();
			srcLineIndex=1;
			File sourceFile = new File(sFilePath);
			File inputFile = new File( "input.txt"); // the source file to be examined for plagiarism
			
			@SuppressWarnings("resource")
			BufferedReader sReader = new BufferedReader( new FileReader(sourceFile));
			while((srcLine = sReader.readLine())!=null){

				BufferedReader reader = new BufferedReader( new FileReader(inputFile));
				inpLineIndex=1;
				fullTextLength = fullTextLength+srcLine.length();
				while((inpLine = reader.readLine())!=null){
					inputLen = inpLine.length();
					srcLen = srcLine.length();
				
					if(inputLen>0 && srcLen>0) // making sure that the strings to be compared have non-zero length
					{
						if(srcLen>inputLen)
						{   textLine = srcLine;
						ptrnLine = inpLine;
						}
						else
						{  textLine = inpLine;
						ptrnLine = srcLine;
						}

						patterntextLength = ptrnLine.length();
						
						//fullTextLength = fullTextLength+textLine.length();
						if(coun<1)
						{
						fullPatternLength = fullPatternLength+ ptrnLine.length();
						}

					    //Running KMP component
						kmpComponent = new KMP();
						 if(patterntextLength!=0)
						{ kmpSimRatio= (kmpComponent.searchSubString(textLine, ptrnLine)/(double)(patterntextLength));
						}
						System.out.println("KMP Result");
						System.out.println("Similarity ratio = "+kmpSimRatio*100.000+" Line Number of the input file= "+inpLineIndex+
								" Line Number of the source file "+fileEntry.getName()+ " = "+srcLineIndex);
						System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
						PrintWriter outPKmpFile = new PrintWriter(outKmpFile);
   				    	if(kmpSimRatio>0.60)
   				    	{ outPKmpFile.append("Line "+inpLineIndex + " of the input file has plagarised " +kmpSimRatio*100.000+
   				    			"% from line "+srcLineIndex +" of the source file "+fileEntry.getName()+"\n");
   				    	}

   				        //Computing KMP Time Count
   				    	kmpComponentCount = kmpComponentCount+kmpComponent.getTimeCount();
                        System.out.println("The amount of time KMP took for a text lenghth "+textLine.length()+" and pattern length " +ptrnLine.length()+" is "+
                        		          kmpComponentCount);
                        mainTimeCount = kmpComponentCount+mainTimeCount;

						//Running boyer moore algorithm
						bmComponent = new BMSubStr(ptrnLine,textLine);
						if(patterntextLength!=0)
						{
							boyreMooreSimRatio = (bmComponent.BMSimilarity()/(double)(patterntextLength));
						}
						System.out.println("boyer moore Result");
						System.out.println("Similarity ratio = "+boyreMooreSimRatio*100.000+" Line Number of the input file= "+inpLineIndex+
								" Line Number of the source file "+fileEntry.getName()+ " = "+srcLineIndex);
						System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
						
						PrintWriter outPBoyreMooreFile = new PrintWriter(outBoyreMooreFile);
   				    	if(boyreMooreSimRatio>0.60)
   				    	{ outPBoyreMooreFile.append("Line "+inpLineIndex + " of the input file has plagarised " +boyreMooreSimRatio*100.000+
   				    			"% from line "+srcLineIndex +" of the source file "+fileEntry.getName()+"\n");
   				    	}
						
   				        //Computing boyer moore Time Count
   				    	bmComponentCount = bmComponentCount+bmComponent.getTimeCount();
                        
                        mainTimeCount = bmComponentCount+mainTimeCount;  
                        System.out.println("The amount of time boyer moore took for a text lenghth "+textLine.length()+" and pattern length " +ptrnLine.length()+" is "+
                				bmComponentCount);
                        
   				    	//Running Rabin Karp Algorithm
						rkComponent = new RabinKarp();
						if(patterntextLength!=0)
						{
							rkNumberOfMatches = rkComponent.search(ptrnLine,textLine);
							if(rkNumberOfMatches>0)
							{  
								rkPlagarismStatus = true;
							}
							else
							{
								rkPlagarismStatus =false;
							}
							if(rkPlagarismStatus)
							{   System.out.println("Rabin Karp Algorithm Result");
								System.out.println(" Line Number of the input file= "+inpLineIndex+ " is plagarised from" +
									" Line Number of the source file "+fileEntry.getName()+ " = "+srcLineIndex+" Number of times string matched was "+rkNumberOfMatches);
								System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
							 PrintWriter outPRkFile = new PrintWriter(outRkFile);
	   				    	 outPRkFile.append("Line "+inpLineIndex + " of the input file has plagarised from line "+srcLineIndex +" of the source file "+fileEntry.getName()+
	   				    			 " "+rkNumberOfMatches+" time string matching found\n");
							}
							rkComponentCount = rkComponentCount+rkComponent.getTimeCount();
	                        System.out.println("The amount of time Rabin Karp took for a text lenghth "+textLine.length()+" and pattern length " +ptrnLine.length()+" is "+
	                        		rkComponentCount);
	                        mainTimeCount = rkComponentCount+mainTimeCount;
	                        
						}
						inpLineIndex++;
					}
					
				}
				coun++;
				srcLineIndex++;
			}
			
		}
		outKmpFile.close();
		outBoyreMooreFile.close();
		outRkFile.close();
		
       	 System.out.println("The amount of time KMP took for a text lenghth "+fullTextLength+" and pattern length " +fullPatternLength+" is "+
		          kmpComponentCount);
		System.out.println("The amount of time boyer moore took for a text lenghth "+fullTextLength+" and pattern length " +fullPatternLength+" is "+
				bmComponentCount);
		System.out.println("The amount of time Rabin Karp took for a text lenghth "+fullTextLength+" and pattern length " +fullPatternLength+" is "+
        		rkComponentCount);
		System.out.println("The amount of total time  took for full Text length "+fullTextLength+" and full pattern length " +fullPatternLength+" is "+
         		mainTimeCount);
		
	}

}

