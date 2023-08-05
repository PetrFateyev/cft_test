package org.example.utils;

public class Menu {

    public static void show() {
        System.out.println("Программа реализует алгоритм слияния предварительно отсортированных файлов");
        System.out.println("Параметры запуска:");
        System.out.println("режим сортировки (необязательный параметр) " +
                "тип данных (обязательный параметр) " +
                "имя выходного файла (обязательный параметр) " +
                "имена входных файлов (не менее одного)");
        System.out.println("Аргументы запуска: ");
        System.out.println("-s: тип входных данных для строк");
        System.out.println("-i: тип входных данных для чисел");
        System.out.println("-a: сортировка по возрастанию (по умолчанию)");
        System.out.println("-d: сортировка по убыванию");
    }
}
