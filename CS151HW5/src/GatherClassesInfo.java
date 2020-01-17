import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.lang.Class;
import java.lang.reflect.*;
import java.util.ArrayList;
public class GatherClassesInfo {
	
	public HashMap<String, ClassData> getClassNameToClassDataMap(HashMap<String, ClassData> classesInfoMap)
	{
		//after scanning all classes' names in this directory, we can find providers and clients
		//because we only care about providers and clients in this directory
		//the argument is a map, all values are empty classDataObj...
		//after this method, it fills with all values needed.
		for(String className: classesInfoMap.keySet()) {
			try {
				//System.out.println(className);
				Class classObj = Class.forName(className);
				ClassData classDataObj = classesInfoMap.get(className);
				classDataObj.setName(classObj.getSimpleName());
				if(classObj.getSuperclass() != null)
				{
					classDataObj.setSuperClass(classObj.getSuperclass().getSimpleName());
				}
				Class[] interfaces = classObj.getInterfaces();
				String[] interfacesNames = new String[interfaces.length];
				for (int i = 0; i < interfaces.length; i++) {
					interfacesNames[i] = interfaces[i].getSimpleName();
				}
				classDataObj.setInterfaces(interfacesNames);
				
				
				Field[] fields = classObj.getDeclaredFields();
				String[] fieldsInfo = new String[fields.length];
				String tempFieldType;
				for (int i = 0; i < fields.length; i++) {
					fieldsInfo[i] = fields[i].getName(); // + " "
					// + fields[i].getType().getSimpleName() + " "
					// + fields[i].getModifiers();
					tempFieldType = fields[i].getType().getSimpleName();
					if(classesInfoMap.containsKey(tempFieldType))
					{
						classDataObj.addProvider(tempFieldType);
						classesInfoMap.get(tempFieldType).addClient(className);
					}
				}
				classDataObj.setFields(fieldsInfo);
				
				Method[] methods = classObj.getDeclaredMethods();
				String[] methodsNames = new String[methods.length];
				String methodModifier;
				for (int i = 0; i < methods.length; i++) {
					if(Modifier.isPublic(methods[i].getModifiers()))
					{
						methodModifier = "public";
					}
					else
					{
						methodModifier = "private";
					}
					methodsNames[i] = methods[i].getName() + ": " + methodModifier;
				}
				classDataObj.setMethods(methodsNames);
				
			} catch (ClassNotFoundException e) {
				System.err.println("Class " + className + " is not found");
			}
		}
		return classesInfoMap;
	}
}
