package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList <String> list = new ArrayList<>();
        int size = 10;
        int choose = 1;
        while (choose != 0) {
            System.out.println("1. Добавить элемент");
            System.out.println("2. Вывести элементы на экран");
            System.out.println("3. Удаление элемента");
            System.out.println("4. Поиск одинаковых элементов");
            System.out.println("5. Реверс всех строк");
            System.out.println("6. Статистика по символам");
            System.out.println("7. Поиск подстроки в строках коллекции");
            System.out.println("8. Подсчитать длины строк");
            System.out.println("9. Изменить размер коллекции");
            System.out.println("10. Инициализация коллекции по текстовому документу");
            System.out.println("11. Выгрузка в xml файл");
            System.out.println("Выберите операцию (введите 0 для выхода): ");
            choose = Integer.parseInt(scanner.nextLine());

            switch (choose) {
                case 1: {
                    System.out.println("Введите строку для добавления");
                    String temp = scanner.nextLine();
                    if (list.size() >= size) {
                        list.remove(0);
                    }

                    list.add(temp);
                    break;
                }

                case 2: {
                    for(int i = 0; i < list.size(); i++) {
                        System.out.println(i + ". " + list.get(i));
                    }
                    System.out.println("----------");
                    break;
                }

                case 3: {
                    System.out.println("Введите номер элмеента, который хотите удалить");
                    int temp = Integer.parseInt(scanner.nextLine());
                    list.remove(temp);
                    break;
                }

                case 4: {
                    System.out.println("Введите элемент для поиска");
                    String tempStr = scanner.nextLine();
                    int tempInt = 0;
                    for (String str : list) {
                        if (str.equals(tempStr)) {
                            tempInt++;
                        }
                    }
                    System.out.println("Количество совпадений - " + tempInt);
                    break;
                }

                case 5: {
                    for (int i = 0; i < list.size(); i++) {
                        list.set(i, reverseString(list.get(i)));
                    }
                    break;
                }

                case 6: {
                    ArrayList<Statistic> statistics = new ArrayList<>();
                    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
                    for (char ch : alphabet) {
                        statistics.add(new Statistic(ch, 0));
                    }

                    for (String str : list) {
                        char[] temp = str.toCharArray();
                        for (char ch : temp) {
                            for (Statistic stat : statistics) {
                                if (stat.symbol == ch) {
                                    stat.count++;
                                }
                            }
                        }
                    }

                    for (Statistic stat : statistics) {
                        System.out.print(stat.symbol + ":"  + stat.count + ", ");
                    }
                    System.out.println();
                    break;
                }

                case 7: {
                    System.out.println("Введите подстроку для поиска");
                    String tempStr = scanner.nextLine();
                    ArrayList<String> tempList = new ArrayList<>();
                    for (String str : list) {
                        if (str.contains(tempStr)) {
                            tempList.add(str);
                        }
                    }
                    System.out.println("Найденные строки по подстроке:");
                    for (String str : tempList) {
                        System.out.println(str);
                    }
                    break;
                }

                case 8: {
                    ArrayList<Integer> tempList = new ArrayList<>();

                    for (String str : list) {
                        tempList.add(str.length());
                    }
                    Collections.sort(tempList);

                    for (Integer integer : tempList) {
                        System.out.print(integer + ", ");
                    }

                    System.out.println();

                    break;
                }

                case 9: {
                    System.out.println("Введите новый размер коллекции (старый -" + size + "):");
                    size = Integer.parseInt(scanner.nextLine());
                    break;
                }

                case 10: {
                    list.clear();
                    try {
                        System.out.println("Введите название файла");
                        String fileName = scanner.nextLine();
                        File file = new File(fileName);
                        Scanner reader = new Scanner(file);
                        while (reader.hasNextLine()) {
                            if (list.size() >= size) {
                                list.remove(0);
                            }
                            String temp = reader.nextLine();
                            list.add(temp);
                        }
                        reader.close();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }

                case 11: {
                    System.out.println("Введите название файла");
                    String fileName = scanner.nextLine();
                    ArrayList<String> tempList = new ArrayList<>();
                    tempList.add("<?xml version=\"1.0\"  encoding = \"utf-8\" ?>");
                    tempList.add("<listOfStrings>");
                    for(String str : list) {
                        tempList.add("<string>" + str + "</string>");
                    }
                    tempList.add("</listOfStrings>");
                    try {
                        File file = new File(fileName);
                        if (!file.createNewFile()) {
                            System.out.println("Файл уже существует");
                        }

                        FileWriter fileWriter = new FileWriter(fileName);

                        for (String str : tempList) {
                            fileWriter.write(str + "\n");
                        }

                        fileWriter.close();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }

                case 0: {
                    break;
                }

                default: {
                    System.out.println("Введите корректное значение");
                    break;
                }
            }
        }
    }
}
