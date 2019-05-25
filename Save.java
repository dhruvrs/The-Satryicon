import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
public class Save
{
   //sets the conditionals to load the game
   //@return HashMap<String, String> containing the save data
   public static HashMap<String, String> loadSave(String saveFile)
   { 
      HashMap<String, String> saveData = new HashMap<String, String>();
      try{
         Scanner file = new Scanner(new File(saveFile));
         while(file.hasNext())
            saveData.put(file.next(), file.next());
      }catch(IOException e){}
      return saveData;
   }
   //rewrites the savedata with any updates
   public static void overwrite(HashMap<String, String> saveData, String saveFile) throws IOException
   {
      FileWriter file = new FileWriter(saveFile);
      for(Map.Entry<String, String> pair: saveData.entrySet())
         file.write(pair.getKey()+" "+pair.getValue());
   }
}