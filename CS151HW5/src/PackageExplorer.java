import java.util.HashMap;
import java.util.ArrayList;
import java.lang.Class;
import java.lang.reflect.*;
import java.io.*;
import java.util.*;
import java.beans.*;

public class PackageExplorer {
	private static HashMap<String, ClassData> classesInfoMap;
	private static ArrayList<String> classNamesArrList;
	
	public static void main(String[] args) throws IOException, IllegalArgumentException {
		// classesInfoMap = new HashMap<>();
		String directoryPath;
		// = "C:\\Users\\Lucy\\eclipse-workspace\\CS146Program0.5\\bin";
		
		if (args.length == 0) {
			directoryPath = System.getProperty("user.dir");
			// System.out.println("Current directory: " + directoryPath);
		} else if (args.length == 1) {
			directoryPath = args[0];
			// System.out.println("Directory is: " + directoryPath);
		} else {
			throw new IllegalArgumentException("There should be one argument.");
		}
		
		FileLoader fl = new FileLoader();
		classesInfoMap = fl.loadPackage(directoryPath);
		
		GatherClassesInfo gatherInfo = new GatherClassesInfo();
		classesInfoMap = gatherInfo.getClassNameToClassDataMap(classesInfoMap);
		// Then, it goes to display part - menu method
		packageExplorerMenu();
	}
	
	public static void packageExplorerMenu() throws IOException {
		boolean quit = false;
		String numRegex = "\\d+";
		InputStreamReader inStreamReader = new InputStreamReader(System.in);
		BufferedReader bfReader = new BufferedReader(inStreamReader);
		while (!quit) {
			// start building menu
			String prompt = "Welcome to PackageExplorer - Main Menu";
			System.out.println(prompt);
			System.out.println(buildSeparator("-", prompt.length()));
			System.out.println("\t 1. List all classes");
			System.out.println("\t 2. View a class");
			System.out.println("\t 3. Save all classes");
			System.out.println("\t 4. Load class info from xml");
			
			String mainMenuChoice;
			System.out.printf("Enter your choice(1-4) or q to quit: ");
			mainMenuChoice = bfReader.readLine();
			
			if (mainMenuChoice.equalsIgnoreCase("q")) {
				System.out.println("Goodbye!");
				quit = true;
			} else if (!mainMenuChoice.matches(numRegex)) {
				// Input is not a number
				System.out.println("Invalid choice for main menu");
			} else if (mainMenuChoice.equals("1")) {
				prompt = "List of classes";
				System.out.println(prompt);
				System.out.println(buildSeparator("-", prompt.length()));
				int keyCounter = 0;
				classNamesArrList = new ArrayList<>();
				for (String className : classesInfoMap.keySet()) {
					System.out.printf("%d. %s\n", keyCounter + 1, classesInfoMap.get(className).getName());
					classNamesArrList.add(className);
					keyCounter++;
				}
				
				// View class details
				String choiceOfClassNum;
				boolean backToMainMenu = false;
				while (!backToMainMenu) {
					System.out.printf("Enter (1 - %d) to view details or m for main menu: ", keyCounter);
					choiceOfClassNum = bfReader.readLine();
					if (choiceOfClassNum.equalsIgnoreCase("m")) {
						backToMainMenu = true;
					} else if (!choiceOfClassNum.matches(numRegex)) {
						System.out.println("Invalid choice of class number");
					} else if (Integer.parseInt(mainMenuChoice) >= 1
							&& Integer.parseInt(mainMenuChoice) <= keyCounter) {
						// show class info
						int classNum = Integer.parseInt(choiceOfClassNum);
						String chosenClassName = classNamesArrList.get(classNum - 1);
						System.out.println(classesInfoMap.get(chosenClassName).toString());
						// save as xml
						System.out.println("Enter s to save or m for Main Menu: ");
						String saveOrBackToMain = bfReader.readLine();
						if (saveOrBackToMain.equalsIgnoreCase("m")) {
							backToMainMenu = true;
						} else if (saveOrBackToMain.equalsIgnoreCase("s")) {
							
							// save as xml
							try {
								XMLEncoder xmlOut = new XMLEncoder(
										new BufferedOutputStream(new FileOutputStream(chosenClassName + ".xml")));
								xmlOut.writeObject(classesInfoMap.get(chosenClassName));
								xmlOut.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
							System.out.printf("\nSaved class information as %s.xml\n", chosenClassName);
							backToMainMenu = true;
						} else {
							System.out.println("Invalid choice for save or not");
						}
					} else {
						System.out.println("Invalid choice of class number");
					}
				}
			}
			
			else if (mainMenuChoice.equals("2")) {
				System.out.println("Please type in a class name to view its details or type m back to main menu");
				String aClassName = bfReader.readLine();
				aClassName = aClassName.substring(0, 1).toUpperCase() + aClassName.substring(1);
				if (classesInfoMap.keySet().contains(aClassName)) {
					System.out.println(classesInfoMap.get(aClassName).toString());
				} else if (!aClassName.equalsIgnoreCase("m")) {
					System.out.println("Cannot find this class in the directory");
				}
			} else if (mainMenuChoice.equals("3")) {
				// save into XML
				try {
					System.out.println("Enter a filename(end with '.xml' please) to save information in: ");
					String fileName = bfReader.readLine();
					XMLEncoder xmlOut = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
					// ClassData[] classDataArray =
					// (ClassData[])(classesInfoMap.values().toArray());
					// cannot cast Object to ClassData...JVM doesn't allow
					// that...(ClassData[])Object[]
					ClassData[] classDataArray = classesInfoMap.values().toArray(new ClassData[0]);
					// parameter in toArray() determines array type only.(cannot do type casting as
					// said above)
					// the size of the parameter or the content it contains doesn't matter
					
					xmlOut.writeObject(classDataArray);
					xmlOut.close();
					System.out.printf("\nSaved class information as %s\n", fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (mainMenuChoice.equals("4")) {
				// load class info from xml
				ClassData[] classDataArray = null;
				try {
					System.out.println("Enter a filename(end with '.xml') to load information from : ");
					// Create an XMLDecoder around the file
					String fileName = bfReader.readLine();
					XMLDecoder xmlIn = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
					// Read in the whole array of DotModels
					classDataArray = (ClassData[]) xmlIn.readObject();
					xmlIn.close();
					
					classesInfoMap = new HashMap<String, ClassData>();
					classNamesArrList = new ArrayList<>();
					for (ClassData classData : classDataArray) {
						classNamesArrList.add(classData.getName());
						classesInfoMap.put(classData.getName(), classData);
					}
					System.out.printf("\nLoaded all class information in %s\n", fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else {
				System.out.println("Invalid choice for main menu");
			}
		}
	}
	
	public static String buildSeparator(String symbol, int numOfRepeat) {
		String repeatedString = "";
		for (int i = 0; i < numOfRepeat; i++) {
			repeatedString += symbol;
		}
		return repeatedString;
	}
}