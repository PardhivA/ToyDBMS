import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class cs21b003_dbengine{
    public void create_table(String tableName){
      // File file = new File(tableName+".txt");
    try {
      // Create the File object
      File file = new File(tableName);

      // Check if the file already exists
      if (file.exists()) {
        System.out.println("File already exists. Deleting...");
        if (file.delete()) {
          System.out.println("File deleted successfully.");
        } else {
          System.err.println("Failed to delete existing file!");
          return; // Exit if deletion fails
        }
      }

      // Create a new file
      boolean created = file.createNewFile();
      if (created) {
        System.out.println("File " + tableName + " created successfully!");
      } else {
        System.err.println("Failed to create new file!");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }  
    }

    public List<String> load_table(String tableName){
        List<String> stringList = new ArrayList<>();

       // Add the new string to the ArrayList
         try {
      // Replace "filename.txt" with your actual file path
      Scanner scanner = new Scanner(new FileReader(tableName+".txt"));

      while (scanner.hasNextLine()) {

        String line = scanner.nextLine();
            stringList.add(line);

        // // String[] splitted = line.split(' ');
        // if(splitted[0] == 'create'){

        // }
        // else if(splitted[1] == "insert"){

        // }

        // Process each line here
        // System.out.println(line);
    }
    scanner.close();
  }
  catch (IOException e) {
      e.printStackTrace();
    }

    return stringList;

}


public List<String> addAttribute(List<String> table, String dtype, String name){
  if(table.size() == 0){
    table.add(0, name);
  }
  else{
  String temp = table.get(0) + " "+name; 

  table.set(0, temp); 
  }
  return table;
}

public void saveTable(List<String> table, String tableName){
   try {
      // Replace "filename.txt" with your desired filename and path
      File file = new File(tableName + ".txt");

      // Delete the existing file (if it exists)
      if (file.exists()) {
        file.delete();
      }

      // Create a new FileWriter (overwrites existing content)
      FileWriter writer = new FileWriter(file);

      // Write your desired data to the file
      // String data = "This is the new content for the file.";
      for(int i = 0;i<table.size();i+=1){
        writer.write(table.get(i)+'\n');
      }

      writer.close(); // Important to close the writer

      System.out.println("File rewritten successfully!");
    } catch (IOException e) {
      e.printStackTrace();
    }
}


  public List<String> insertInto(List<String> table, String data){
    String [] temp = data.split(",");
    String ans=  "";
    for(int i  =0;i< temp.length;i++){
      if(i==0 && i == temp.length - 1){
        String []_temp = temp[0].split("\\(");
        String []_temp1 = _temp[1].split("\\)");
        ans = ans + _temp1[0];
      }
      else if(i==0){
        String []_temp = temp[0].split("\\(");
        ans = ans + _temp[1];
      }
      else if(i == temp.length - 1){
            String []_temp = temp[i].split("\\)");
        ans = ans +" "+ _temp[0];
      }
      else{
        ans = ans +" " + temp[i];
      }
    }
    table.add(ans);
    return table;
  }

  }