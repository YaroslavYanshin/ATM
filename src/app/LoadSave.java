package app;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * LoadSave
 */
public class LoadSave {
    private String name_file = "data.txt";
    File file;
    List<Card> cards;

    public LoadSave()
    {
        cards = new ArrayList<Card>();
        
        file = new File(name_file);
        cards.add(new Card("CFSE-FDSG-435F-G656", "2355", new Account("Иван", "Иванов", 15000)));
        cards.add(new Card("CFSE-JSND-9876-NJSF", "3696", new Account("Александр", "Габунов", 10000)));
        cards.add(new Card("CFSE-JSND-9876-NJSF", "3696", new Account("Петр", "Петров", 1000)));
        cards.add(new Card("CFSE-5346-9876-NJSF", "3696", new Account("Михаил", "Михайлов", 52346)));
        cards.add(new Card("CFSE-JSND-6855-5354", "3696", new Account("Иннокентий", "Лесов", 3454)));
        cards.add(new Card("3256-JSND-9876-NJSF", "3696", new Account("Леонид", "Пучковский", 3456)));
        try
        { 
            if (file.createNewFile())
            {
                System.out.println("Файл успешно создан!");
                
            }

        }
        catch (IOException  ex)
        {
            System.out.println(ex.getMessage()); 
        }
       
    }

    public void loadData() {
        //in future :C 
    }

    public void setData(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getData() {
        return this.cards;
    }

    public void saveData() {
        try
        { 
            FileWriter writer = new FileWriter(file,true);
            for (Card card : cards) {
                writer.write(card.getData() + " ");
            }
            writer.flush();

        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage()); 
        }
    }
}
