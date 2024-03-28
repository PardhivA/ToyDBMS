import java.util.List;
import java.util.Scanner;
import java.util.*;
// import java.io.File;
// import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

  class ParserForIC{
  cs21b003_dbengine obj = new cs21b003_dbengine();
  
   private  List<String> Lines = null;
       HashMap<String, List<String>> IC = new HashMap<>();
       public void StringListHolder(List<String> stringList) {
         // Create a defensive copy to prevent external modifications
         this.Lines = new ArrayList<>(stringList);
        //  System.out.println(Lines);
        }
        
        // Getter method to access the internal list (read-only)
        public List<String> getStringList() {
          // Return an unmodifiable view to prevent external modifications
          return Collections.unmodifiableList(this.Lines);
        }
        


     


    public void ParseList(){
          
      for (int i = 0;i< this.Lines.size();i++){
        String[] splitted = this.Lines.get(i).split(" ");
        // System.out.println(splitted[0]);
        if(splitted[0].equals("create")){
          // System.out.println("Fsfs");
            List<String> _IC = new ArrayList<>();
            String temp = new String("create_table "+splitted[1]);
            _IC.add(temp);
            System.out.println(_IC.get(0));
            for(int j =0 ;j < splitted[3].charAt(0) - 48;j++){
              i++;
              String[] ele = this.Lines.get(i).split(" ");
              _IC.add("add_attribute " + splitted[2]+" " + ele[0] + " " + ele[1]);
            }
            IC.put(splitted[2], _IC);
         }
         else if(splitted[0].equals("insert")){
                      String temp = new String("insert_into " + splitted[2]  + " " + splitted[3]);

                       List<String> _IC = IC.get(splitted[2]);
            _IC.add(temp);
            IC.put(splitted[2], _IC);
         }
      } 
      System.out.println(IC);
          Set<String> keySet = IC.keySet();
        for(String table : keySet){
      obj.saveTable(IC.get(table), "IC_"+table);
        }
      //     String[] splitted = this.Lines.toArray(new String[0]).split();
        
    //     if(splitted[0] == "create"){
    //          try {
    //   // Replace "filename.txt" with your desired filename and path
    //   File file = new File("filename.txt");

    //   // Check if the file already exists
    //   if (file.exists()) {
    //     System.out.println("File already exists. Replacing the existing file.");
    //     // Delete the existing file
    //     file.delete();
    //   }

    //   // Create the new file
    //   boolean created = file.createNewFile();

    //   if (created) {
    //     System.out.println("File created successfully: " + file.getAbsolutePath());
    //   } else {
    //     System.out.println("Unexpected error creating the file.");
    //   }
    // } catch (IOException e) {
    //   e.printStackTrace();
    // }
    //     }
    //     else if(splitted[1] == "insert"){

    //     }
     }

     public void Tableop(){
      Set<String> keySet = IC.keySet();
        for(String table : keySet){
          List<String> commands = IC.get(table);
          for (int i =0 ;i < commands.size();i++){
            String command = commands.get(i);
            String []temp = command.split(" ");
            if(temp[0].equals("create_table")){
                obj.create_table(table);
            }
            else if(temp[0].equals("add_attribute")){
              List<String> table_entries = obj.load_table(table);

             table_entries =  obj.addAttribute(table_entries, temp[2], temp[3]);
             obj.saveTable(table_entries, table);
            }
            else if(temp[0].equals("insert_into")){
               List<String> table_entries = obj.load_table(table);

             table_entries =  obj.insertInto(table_entries, temp[2]);
             obj.saveTable(table_entries, table);
            }
          }          
        }
     }
}



public class cs21b003_parser {
    public static void main(String args[]){
        ParserForIC obj=  new ParserForIC();
        List<String> stringList = new ArrayList<>();

    // Add the new string to the ArrayList
         try {
      // Replace "filename.txt" with your actual file path
      Scanner scanner = new Scanner(new FileReader("Input.txt"));

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
            stringList.add(line);

      }
      obj.StringListHolder(stringList);
      obj.ParseList();
      obj.Tableop();
      scanner.close(); // Close the Scanner to release resources
    } catch (Exception e) {
      e.printStackTrace();
    }
    }
}





        // // String[] splitted = line.split(' ');
        // if(splitted[0] == 'create'){

        // }
        // else if(splitted[1] == "insert"){

        // }

        // Process each line here
        // System.out.println(line);