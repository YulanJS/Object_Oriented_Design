import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;

public class FileLoader {
	public HashMap<String, ClassData> loadPackage(String path) throws IOException {
		HashMap<String, ClassData> emptyClassDataMap = new HashMap<>();
		String className;
//		System.out.println("Gathering class files in " + path);
		FilenameFilter classFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".class") && !name.toLowerCase().contains("$");
			}
		};
		File f = new File(path); // the directory, really!
		for (File file : f.listFiles(classFilter)) {
			className = file.getName().replace(".class", "");
//			System.out.println(className);
			emptyClassDataMap.put(className, new ClassData());
		}
		return emptyClassDataMap;
	}
	
}