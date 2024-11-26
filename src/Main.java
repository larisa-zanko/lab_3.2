//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
port java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;



//Первоначальное условие 3.	Строки текстового файла input.txt состоят из слов, разделенных одним или несколькими пробелами. Перед первым, а также после последнего слова строки пробелы могут отсутствовать. 
//Требуется определить строки, содержащие максимальное количество слов. Если таких строк несколько, найти первые 10. Результат вывести на консоль в форме, удобной для чтения. 


//Обновлённое условие 
//В текстовом файле input.txt содержатся строки, каждая из которых состоит из слов, разделённых одним или несколькими пробелами. 
//Возможно, что пробелы могут отсутствовать перед первым словом и после последнего слова в строке.

//Требуется:

//Определить строки, которые содержат максимальное количество уникальных слов.
//Если таких строк несколько, вывести первые 10 из них.
//Для более удобного восприятия, результат следует представить в виде таблицы, где каждая строка будет содержать:
//Номер строки (индекс)
//Саму строку
//Количество уникальных слов в ней


public class Main {
    public static void main(String[] args) {
        BufferedReader br = null; // Объект для чтения из файла
        try {
            File file = new File("input.txt"); 
            if (!file.exists()) {
                file.createNewFile();
            }
            br = new BufferedReader(new FileReader(file)); 
            
            // Получаем строки с максимальным количеством уникальных слов
            List<LineInfo> maxLines = findMaxUniqueWordLines(file);
            
            // Выводим результат на консоль
            System.out.printf("%-5s | %-50s | %-20s%n", "№", "Строка", "Уникальных слов");
            System.out.println("---------------------------------------------------------------");
            for (LineInfo lineInfo : maxLines) {
                System.out.printf("%-5d | %-50s | %-20d%n", lineInfo.index, lineInfo.line, lineInfo.uniqueWordCount);
            }
        } catch (IOException e) {
            System.out.print("Ошибка: " + e.getMessage()); 
        } finally {
           
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии BufferedReader: " + e.getMessage());
            }
        }
    }

    public static List<LineInfo> findMaxUniqueWordLines(File file) {
        List<LineInfo> maxLines = new ArrayList<>(); // Список для хранения информации о строках
        int maxUniqueCount = 0; 

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            int index = 0; 
            while ((line = br.readLine()) != null) {
                index++;
                Set<String> uniqueWords = new HashSet<>(); 
                StringTokenizer tokenizer = new StringTokenizer(line.trim()); // Используем StringTokenizer для разбиения строки
                while (tokenizer.hasMoreTokens()) {
                    uniqueWords.add(tokenizer.nextToken());
                }
                int uniqueWordCount = uniqueWords.size(); 

                if (uniqueWordCount > maxUniqueCount) {
                    maxUniqueCount = uniqueWordCount; 
                    maxLines.clear(); 
                    maxLines.add(new LineInfo(index, line, uniqueWordCount)); 
                } else if (uniqueWordCount == maxUniqueCount) {
                    if (maxLines.size() < 10) {
                        maxLines.add(new LineInfo(index, line, uniqueWordCount));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Обработка исключений при чтении файла
        }
        return maxLines; // Возвращаем список строк с максимальным количеством уникальных слов
    }
    static class LineInfo {
        int index; 
        String line; 
        int uniqueWordCount; 

        LineInfo(int index, String line, int uniqueWordCount) {
            this.index = index;
            this.line = line;
            this.uniqueWordCount = uniqueWordCount;
        }
    }
}
