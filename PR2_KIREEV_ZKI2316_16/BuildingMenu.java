import java.util.Scanner;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public class BuildingMenu {
    public static void main(String[] args) throws UnsupportedEncodingException{

        //Создание списка для хранения объектов здания
        ArrayList<Building> buildings = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

         // Цикл для отображения меню и обработки выбора пользователя
        while (choice != 6) {
            System.out.println("Меню:");
            System.out.println("1. Добавить пустой объект");
            System.out.println("2. Добавить объект с данными");
            System.out.println("3. Редактировать поле объекта");
            System.out.println("4. Вывести информацию обо всех объектах");
            System.out.println("5. Отсортировать объекты");
            System.out.println("6. Завершить работу программы");
            System.out.print("Выберите действие: ");

            choice = scanner.nextInt();

            // Обработка выбора пользователя с помощью оператора switch
            switch (choice) {
                case 1:
                    clearConsole();
                    buildings.add(new Building());
                    break;
                case 2:
                    clearConsole();
                    System.out.print("Введите тип здания (жилое, не жилое и т.д): ");
                    String type = scanner.next();
                    System.out.print("Введите количество этажей: ");
                    int floors = scanner.nextInt();
                    System.out.print("Введите статус здания (новое, старое, реконструированное и т.д): ");
                    String status = scanner.next();
                    System.out.print("Введите площадь: ");
                    double area = scanner.nextDouble();
                    System.out.print("Является ли коммерческим (true/false): ");
                    boolean isCommercial = scanner.nextBoolean();
                    buildings.add(new Building(type, floors, status, area, isCommercial));
                    break;
                case 3:
                    clearConsole();
                    System.out.print("Введите индекс объекта для редактирования: ");
                    int index = scanner.nextInt();
                    System.out.print("Введите поле для редактирования (type/floors/status/area/isCommercial): ");
                    String fieldName = scanner.next();
                    System.out.print("Введите новое значение: ");
                    Object value;
                    if (fieldName.equals("isCommercial")) {
                        value = scanner.nextBoolean();
                    } else if (fieldName.equals("floors")) {
                        value = scanner.nextInt();
                    } else if (fieldName.equals("area")) {
                        value = scanner.nextDouble();
                    } else {
                        scanner.nextLine();
                        value = scanner.nextLine();
                    }
                    buildings.get(index - 1).editBuildingField(fieldName, value);
                    break;
                case 4:
                    clearConsole();
                    Building.displayAllBuildings(buildings);
                    break;
                case 5:
                    clearConsole();
                    System.out.print("Выберите поле для сортировки (type/floors/status/area/isCommercial): ");
                    String sortField = scanner.next();
                    Building.sortBuildings(buildings, sortField);
                    break;
                case 6:
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Некорректный выбор. Повторите попытку.");
            }
        }
        scanner.close();
    }

    // Метод отчистки консоли
    public static void clearConsole() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}

// Класс, представляющий объект здания
class Building {
    private String type;
    private int floors;
    private String status;
    private double area;
    private boolean isCommercial;

    // Конструктор по умолчанию
    public Building() {
        this.type = "Жилое здание";
        this.floors = 1;
        this.status = "Новое";
        this.area = 0.0;
        this.isCommercial = false;
    }

    // Конструктор с параметрами
    public Building(String type, int floors, String status, double area, boolean isCommercial) {
        this.type = type;
        this.floors = floors;
        this.status = status;
        this.area = area;
        this.isCommercial = isCommercial;
    }

    // Геттеры и сеттеры для атрибутов
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean isCommercial() {
        return isCommercial;
    }

    public void setCommercial(boolean commercial) {
        isCommercial = commercial;
    }

    public void editBuildingField(String fieldName, Object value) {
        switch (fieldName) {
            case "type":
                setType((String) value);
                break;
            case "floors":
                setFloors((int) value);
                break;
            case "status":
                setStatus((String) value);
                break;
            case "area":
                setArea((double) value);
                break;
            case "isCommercial":
                setCommercial((boolean) value);
                break;
            default:
                System.out.println("Некорректное поле для редактирования.");
        }
    }

    // Метод для вывода информации обо всех объектах
    public static void displayAllBuildings(ArrayList<Building> buildings) {
        for (Building building : buildings) {
            System.out.println(building.toString());
        }
    }

    // Метод для сортировки объектов по выбранному полю
    public static void sortBuildings(ArrayList<Building> buildings, String field) {
        switch (field) {
            case "type":
                Collections.sort(buildings, Comparator.comparing(Building::getType));
                break;
            case "floors":
                Collections.sort(buildings, Comparator.comparingInt(Building::getFloors));
                break;
            case "status":
                Collections.sort(buildings, Comparator.comparing(Building::getStatus));
                break;
            case "area":
                Collections.sort(buildings, Comparator.comparingDouble(Building::getArea));
                break;
            case "isCommercial":
                Collections.sort(buildings, Comparator.comparing(Building::isCommercial));
                break;
            default:
                System.out.println("Некорректное поле для сортировки.");
        }
    }

    @Override
    public String toString() {
        return "Building{" +
                "type='" + type + '\'' +
                ", floors=" + floors +
                ", status='" + status + '\'' +
                ", area=" + area +
                ", isCommercial=" + isCommercial +
                '}';
    }
}




