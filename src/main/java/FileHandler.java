package main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler
{
    //File reader
    BufferedReader reader = null;

    //Method for reading list from file
    public List<String> readFile(File file)
    {
        List<String> list = new ArrayList<String>();
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            while ((text = reader.readLine()) != null)
            {
                list.add(text);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        finally
        {
            try
            {
                if(reader != null)
                {
                    reader.close();
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        return list;
    }

}
