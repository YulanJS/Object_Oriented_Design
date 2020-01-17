import java.lang.reflect.*;
import java.lang.Class;
import java.util.ArrayList;
public class ClassData
{	
	//cannot subclass final class Class
	//Class<T>
	private String name;
	private String superClass;
	private String[] interfaces;
	private String[] fields;
	private String[] methods;
	private ArrayList<String> providers;
	private ArrayList<String> clients;
//	private int providerCounter;
//	private int clientCounter;
	
	public ClassData() 
	{
		providers = new ArrayList<>();
		clients = new ArrayList<>();
	}
	
	public void setName(String name)
	{
		this.name = name; 
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setSuperClass(String superClassName)
	{
		this.superClass = superClassName;
	}
	
	public String getSuperClass()
	{
		return superClass;
	}
	
	public void setInterfaces(String[] interfaceNames)
	{
		this.interfaces = interfaceNames;
	}
	
	public String[] getInterfaces()
	{
		return interfaces;
	}
	
	public void setFields(String[] fieldsNames)
	{
		this.fields = fieldsNames;
	}
	
	public String[] getFields()
	{
		return fields;
	}
	
	public void setMethods(String[] methodsNames)
	{
		this.methods = methodsNames;
	}
	
	public String[] getMethods()
	{
		return methods;
	}
	
	public void addProvider(String newProvider)
	{
//		providers[providerCounter] = newProvider;
//		providerCounter++;
		providers.add(newProvider);
	}
	
	public void addClient(String newClient)
	{
//		clients[clientCounter] = newClient;
//		clientCounter++;
		clients.add(newClient);
	}
	
	public ArrayList<String> getProviders()
	{
		return providers;
	}
	
	public ArrayList<String> getClients()
	{
		return clients;
	}
	
	public void setProviders(ArrayList<String> providers)
	{
		this.providers = providers;
	}
	
	public void setClients(ArrayList<String> clients)
	{
		this.clients = clients;
	}
	
	public String toString()
	{
		String str = "";
		str += "\nClass Details: ";
		str += "\nName: " + name;
		str += "\nSuperclass: ";
		if(superClass == null)
		{
			str += "None";
		}
		else
		{
			str += superClass;
		}
	    
	    str += "\nInterfaces: ";
	    if(interfaces.length == 0)
	    {
	    	str += "\nNone";
	    }
	    else
	    {
	    	for(int i = 0; i < interfaces.length; i++)
	    	{
	    		str += "\n\t" + interfaces[i];
	    	}
	    }
	    
	    str += "\nFields: ";
	    if(fields.length == 0)
	    {
	    	str += "\nNone";
	    }
	    else
	    {
	    	for(int i = 0; i < fields.length; i++)
	    	{
	    		str += "\n\t" + fields[i];
	    	}
	    }
	    
	    str += "\nMethods: ";
	    if(methods.length == 0)
	    {
	    	str += "\nNone";
	    }
	    else
	    {
	    	for(int i = 0; i < methods.length; i++)
	    	{
	    		str += "\n\t" + methods[i];
	    	}
	    }
	    
	    str += "\nProviders: ";
	    if(providers.size() == 0)
	    {
	    	str += "\nNone";
	    }
	    else
	    {
	    	for(int i = 0; i < providers.size(); i++)
	    	{
	    		str += "\n\t" + providers.get(i);
	    	}
	    }
	    
	    str += "\nClients: ";
	    if(clients.size() == 0)
	    {
	    	str += "\nNone";
	    }
	    else
	    {
	    	for(int i = 0; i < clients.size(); i++)
	    	{
	    		str += "\n\t" + clients.get(i);
	    	}
	    }
		return str;
	}
}