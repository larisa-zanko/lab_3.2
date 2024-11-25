//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = null; // Объект для чтения из файла
        try {
            File file = new File("input.txt"); // Путь к файлу
            // Проверяем, существует ли файл, и создаем его, если он не найден
            if (!file.exists()) {
                file.createNewFile();
            }
            br = new BufferedReader(new FileReader(file)); // Создаем BufferedReader для чтения файла
            String line;
            // Читаем файл построчно и выводим на консоль
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            // Получаем строки с максимальным количеством слов
            List<String> maxLines = findMaxWordLines(file);
            // Выводим результат на консоль

            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Строки с максимальным количеством слов:");
            for (String maxLine : maxLines) {
                System.out.println(maxLine);
            }
        } catch (IOException e) {
            System.out.print("Ошибка: " + e.getMessage()); // Обработка исключений при чтении файла
        } finally {
            // Закрываем BufferedReader, если он был открыт
            try {
                if (br != null) {
                    br.close(); // Закрываем BufferedReader
                }
            } catch (IOException e) {
                System.out.println("Ошибка при закрытии BufferedReader: " + e.getMessage());
            }
        }
    }

    // Метод для поиска строк с максимальным количеством слов
    public static List<String> findMaxWordLines(File file) {
        List<String> maxLines = new ArrayList<>(); // Список для хранения строк с максимальным количеством слов
        int maxWordCount = 0; // Переменная для хранения максимального количества слов

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            // Читаем файл построчно
            while ((line = br.readLine()) != null) {
                int wordCount = countWords(line); // Считаем количество слов в текущей строке
                // Если текущая строка имеет больше слов, чем раньше найденные
                if (wordCount > maxWordCount) {
                    maxWordCount = wordCount; // Обновляем максимальное количество слов
                    maxLines.clear(); // Очищаем список, так как найдено новое максимальное количество
                    maxLines.add(line); // Добавляем текущую строку в список
                } else if (wordCount == maxWordCount) {
                    // Если количество слов такое же, добавляем строку в список
                    if (maxLines.size() < 10) {
                        maxLines.add(line); // Добавляем строку, если в списке меньше 10 строк
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Обработка исключений при чтении файла
        }
        return maxLines; // Возвращаем список строк с максимальным количеством слов
    }
    // Метод для подсчета слов в строке
    public static int countWords(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line); // Разбиваем строку на слова
        return tokenizer.countTokens(); // Возвращаем количество слов
    }
}