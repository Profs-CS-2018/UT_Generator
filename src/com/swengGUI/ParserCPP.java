package com.swengGUI;

import java.io.*;
import java.util.ArrayList;

public class ParserCPP implements ParserI
{
	
	protected BufferedReader readerBuff;
	protected FileReader fileReader;
	protected ArrayList<String> lineList;
	protected File file;
	private String line;
	private int num;
	private String[] list;
	
	public ParserCPP()
	{
		parse();
		
	}
	
	@Override
	public void parse ()
	{
		file = getFile("src/ExampleTest.cpp");
		System.out.println("Input File: " + file.toString() + "\n");
		lineList = new ArrayList();
		line = "Default";
		num = 0;
		try
		{
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e)
		{

			e.printStackTrace();
		}
		readerBuff = new BufferedReader(fileReader);
		
		try
		{
			
			while((line = readerBuff.readLine()) != null)
			{
				if(line.contains("#include"))
				{
					
					line = line.replace("#include <", "");
					line = line.replace(">", "");
					lineList.add("Line: "+ (num + 1) + ".) " + line);
					System.out.println("Name of Header File: " + line);
				}
				else
				{
					
				}
				num++;
			}
		} catch (IOException e)
		{

			e.printStackTrace();
		}
	}
	
	public void setFile()
	{
		
	}

	public File getFile(String filename)
	{
		file = new File(filename);
		return file;
	}

	public ArrayList<String> getLineList ()
	{
	
		return lineList;
	}

	public void setLineList ( ArrayList<String> lineList )
	{
	
		this.lineList = lineList;
	}
	
	
}
