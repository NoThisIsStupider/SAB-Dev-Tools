package main;

import java.util.ArrayList;


public class Main 
{
/*<<<<<<< Old stuff
	public static String objFile;
	public static String mtlFile;
	
	public static void main(String[] args)
	{
		if (args.length == 2)
		{
			objFile = args[0];
			mtlFile = args[1];
		}
		else
		{
			System.out.println("Wrong arg count, must be the .obj followed by the .mtl.");
			return;
		}	
    	if (objFile.toLowerCase().endsWith(".obj") &&
    		mtlFile.toLowerCase().endsWith(".mtl"))
    	{
    		ArrayList<String> OBJFile = Input.openFile(objFile);
    		ArrayList<String> MTLFile = Input.openFile(mtlFile);
    		System.out.println("Getting rid of textures with identical .png's");
    		Fixer.fixMaterials(OBJFile, MTLFile);
    		System.out.println("Grouping usemtl groups in obj file");
    		Fixer.organizeOBJ(OBJFile);
    		System.out.println("Done. Writing out...");
    		Output.writeFile(OBJFile, objFile);
    		Output.writeFile(MTLFile, mtlFile);
    		System.out.println("Done");
    	}
=======*/
	//public static String objFile = "E:\\CWorkspace\\NewSonicThing\\NewSonicThing\\res\\Models\\Levels\\CityEscape\\CityEscape.obj";
	//public static String mtlFile = "E:\\CWorkspace\\NewSonicThing\\NewSonicThing\\res\\Models\\Levels\\CityEscape\\CityEscape.mtl";
	
	//public static String objFile = "E:\\CWorkspace\\SA2LevelEditor\\SA2LevelEditor\\res\\Models\\GlobalObjects\\Shrine\\Shrine.obj";
	//public static String mtlFile = "E:\\CWorkspace\\SA2LevelEditor\\SA2LevelEditor\\res\\Models\\GlobalObjects\\Shrine\\Shrine.mtl";
	
	//public static String objFile = "C:\\Users\\Anders\\Desktop\\shadow\\Shadow.obj";
	//public static String mtlFile = "C:\\Users\\Anders\\Desktop\\shadow\\Shadow.mtl";
	
	public static void main(String[] args)
	{
		//cleanObjWithMtl(objFile, mtlFile);
		
		new GuiDrop();
		
		System.out.println("Input args:");
		for (String s: args)
		{
			System.out.println(s);
		}

		for (String arg: args)
		{
			if (arg.toLowerCase().endsWith(".obj"))
			{
				startCleanObj(arg);
			}
			else
			{
				System.out.println("Ignoring "+arg+" because it's not an .obj file.");
			}
		}
	}
	
	public static void startCleanObj(String objFile)
	{
		String objFileName = objFile;
		if (!objFileName.toLowerCase().endsWith(".obj"))
		{
			System.out.println("Ignoring "+objFile+" because it's not an .obj file");
			return;
		}
		
		String mtlFileName = null;
		ArrayList<String> OBJFile = Input.openFile(objFileName);
		String mtlName = Input.findMtlNameFromObj(OBJFile);
		if (mtlName == null)
		{
			System.out.println("Ignoring "+objFile+" because it doesn't have a mtllib");
			return;
		}
		
		if (objFileName.contains("/"))
		{
			String objFolder = objFileName.substring(0, objFileName.lastIndexOf("/"));
			mtlFileName = objFolder + "/" + mtlName;
		}
		else if (objFileName.contains("\\"))
		{
			String objFolder = objFileName.substring(0, objFileName.lastIndexOf("\\"));
			mtlFileName = objFolder + "\\" + mtlName;
		}
		else
		{
			mtlFileName = mtlName;
		}

		cleanObjWithMtl(objFileName, mtlFileName);
	}
	
	public static void cleanObjWithMtl(String objFile, String mtlFile)
	{
		ArrayList<String> OBJFile = Input.openFile(objFile);
		ArrayList<String> MTLFile = Input.openFile(mtlFile);
		Fixer.fixMaterials(OBJFile, MTLFile);
		Fixer.organizeOBJ(OBJFile);
		Fixer.optimizePositions(OBJFile);
		Fixer.optimizeUVs(OBJFile);
		
		System.out.print("Exporting... ");
		Output.writeFile(OBJFile, objFile);
		Output.writeFile(MTLFile, mtlFile);
		System.out.println("done.");
	}
}
