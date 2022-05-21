import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HomeWorkCorporation {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        // старт программы
        System.out.println("Введите имя файла для загрузки списка");
        String path = reader.readLine();
        try (
                FileInputStream inputStream = new FileInputStream(path);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            List<Corporation> corporations = (List<Corporation>) objectInputStream.readObject();
            System.out.println(corporations);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Corporation> list = new ArrayList<>();

        while (true) {
            showMenu();
            int choice = 0;
            try {
                choice = Integer.parseInt(reader.readLine());
                if (choice > 0 && choice < 8) {
                    if (choice == 1) {
                        createList(list);
                        printList(list);
                        saveInfo(list);
                    }

                    if (choice == 2) {
                        changeInfo(list);
                    }
                    if (choice == 3) {
                        remove(list);
                    }
                    if (choice == 4) {
                        searchLastName(list);
                    }
                    if (choice == 5) {
                        searchByAge(list);
                    }
                    if (choice == 6) {
                        searchByLetter(list);
                    }
                    if (choice == 7) {
                        saveInfoAuto(list);
                    }
                } else {
                    System.err.println("Вы ввели неверное значение. Сделайте правильный выбор!");
                }


            } catch (NumberFormatException exception) {
                System.err.println("Вы ввели неверное значение. Сделайте правильный выбор!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    // меню
    public static void showMenu() {
        System.out.println("Сделайте выбор: " + "\n1-ввести данные сотрудника" + "\n2-редактирование данных" +
                "\n3-удаление сотрудника" + "\n4-поиск сотрудника по фамилии" + "\n5-вывести сотрудника по возрасту" +
                "\n6-вывести сотрудника по заданной букве фамилии" + "\n7-выйти");

    }

    // создание списка сотрудников корпорации
    public static List<Corporation> createList(List<Corporation> list) throws IOException {
        System.out.println("Сколько сотрудников будет добавлено?");
        try {
            int num = Integer.parseInt(reader.readLine());
            while (num > 0) {
                System.out.println("Введите имя");
                String name = null;
                String lastName = null;
                name = reader.readLine();
                System.out.println("Введите фамилию");
                lastName = reader.readLine();
                System.out.println("Введите возраст");
                int age = Integer.parseInt(reader.readLine());
                System.out.println("Введите должность");
                String position = reader.readLine();
                Corporation corporation = new Corporation(name, lastName, age, position);
                list.add(corporation);
                num--;
            }
        } catch (FileNotFoundException exception) {
            System.err.println("Введено неверное значение");
        }
        return list;
    }

    // изменении информации в соответствии с выбором пользователя: имя, фамилия, возраст, должность и предложение
    // сохранить информацию в файл
    public static void changeInfo(List<Corporation> list) throws IOException, ClassNotFoundException {
        System.out.println("Укажите номер сотрудника в списке");
        int num = Integer.parseInt(reader.readLine());
        if (num > 0 && num <= list.size()) {
            System.out.println("Какие данные необходимо изменить?" + "\n1-имя" + "\n2-фамилию" + "\n3-возраст" +
                    "\n4-должность");
            int info = Integer.parseInt(reader.readLine());
            if (info == 1) {
                System.out.println("Укажите верное имя");
                String name = reader.readLine();
                list.get(num - 1).setName(name);
                saveInfo(list);
            } else if (info == 2) {
                System.out.println("Укажите верную фамилию");
                String lastName = reader.readLine();
                list.get(num - 1).setLastName(lastName);
                saveInfo(list);
            } else if (info == 3) {
                System.out.println("Укажите верный возрас");
                int age = Integer.parseInt(reader.readLine());
                list.get(num - 1).setAge(age);
                saveInfo(list);
            } else if (info == 4) {
                System.out.println("Укажите верную должность");
                String position = reader.readLine();
                list.get(num - 1).setPosition(position);
            } else {
                System.err.println("Вы ввели неверное значение");
            }
            printList(list);

        } else {
            System.err.println("Вы ввели неверное значение");
        }

    }

    // удаление сотрудника из списка по фамилии, предложение записать новый список в файл
    public static void remove(List<Corporation> list) throws IOException, ClassNotFoundException {
        System.out.println("Укажите фамилию сотрудника для удаления");
        String lastName = reader.readLine();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLastName().equals(lastName)) {
                if (list.remove(list.get(i))) {
                    System.out.println("Сотрудник с фамилией " + lastName + " успешно удален");
                    printList(list);
                } else {
                    System.err.println("Сотрудник с фамилией " + lastName + " в списке отсутствует");
                }
            }

        }
        saveInfo(list);

    }

    // поиск сотрудника по фамилии и предложение сохранить найденную информацию в файл
    public static void searchLastName(List<Corporation> list) throws IOException, ClassNotFoundException {
        List<Corporation> newList = new ArrayList<>();
        System.out.println("Укажите фамилию сотрудника для поиска");
        String lastName = reader.readLine();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLastName().equals(lastName)) {
                newList.add(list.get(i));
            }

        }
        newList.forEach(System.out::println);
        saveInfo(newList);
    }

    // поиск сотрудника по возрасту и предложение сохранить найденную информацию в файл
    public static void searchByAge(List<Corporation> list) throws IOException, ClassNotFoundException {
        List<Corporation> newList = new ArrayList<>();
        System.out.println("Укажите возраст сотрудника для поиска");
        int age = Integer.parseInt(reader.readLine());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAge() == age) {
                newList.add(list.get(i));
            } else {
                System.err.println("Сотрудник с возрастом " + age + " не найден");
            }
        }

        newList.forEach(System.out::println);
        saveInfo(newList);
    }

    // поиск сотрудников по первой букве фамилии и предложение сохранить найденную информацию в файл
    public static void searchByLetter(List<Corporation> list) throws IOException, ClassNotFoundException {
        List<Corporation> newList = new ArrayList<>();

        System.out.println("Укажите букву фамилии сотрудника для поиска");
        String letter = reader.readLine();
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).getLastName().charAt(0)) == letter.charAt(0)) {
                newList.add(list.get(i));
            }
        }
        newList.forEach(System.out::println);
        saveInfo(newList);

    }

    // сохранение информации в файл
    public static void saveInfo(List<Corporation> list) throws IOException, ClassNotFoundException {
        System.out.println("Сохранить информацию в файл?" + "\n1- ДА" + "\n2-НЕТ");
        int answer = Integer.parseInt(reader.readLine());
        if (answer == 1) {
            System.out.println("Укажите имя файла");
            String path = reader.readLine();

            try (FileOutputStream fileOutputStream = new FileOutputStream(path);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(list);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (
                    FileInputStream inputStream = new FileInputStream(path);
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
                List<Corporation> corporations = (List<Corporation>) objectInputStream.readObject();
                System.out.println(corporations);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    // автоматическое сохранение инфомации в файле при выходе из программы - п. 7
    public static void saveInfoAuto(List<Corporation> list) throws IOException, ClassNotFoundException {
        String path = System.getProperty("user.dir") + File.separator + "File1.txt";


        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(list);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileInputStream inputStream = new FileInputStream(path);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            List<Corporation> corporations = (List<Corporation>) objectInputStream.readObject();
            System.out.println(corporations);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // печать списка сотрудников

    public static void printList(List<Corporation> list) throws IOException {
        list.forEach(System.out::println);
    }

    /**  Напишите информационную систему "Корпорация".
     Программа должна обеспечивать:
     ■■ ввод данных;
     ■■ редактирование данных сотрудника корпорации;
     ■■ удаление сотрудника корпорации;
     ■■ поиск сотрудника корпорации по фамилии;
     ■■ вывод информации обо всех сотрудниках, указан-
     ного возраста, или фамилия которых начинается на
     указанную букву.+


     Организуйте возможность сохранения найденной
     информации в файл.+

     Также весь список сотрудников корпорации сохраняется
     в файл (при выходе из программы автоматически, в про-
     цессе исполнения программы по команде пользователя).
     При старте программы происходит загрузка списка
     сотрудников корпорации из указанного пользователем
     файла.+

     */
}


