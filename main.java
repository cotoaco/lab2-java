import java.util.ArrayList;
import java.util.List;
class Name {
    private String surname;
    private String firstName;
    private String patronymic;
    public Name(String firstName) {
        this("", firstName, "");
    }
    public Name(String surname, String firstName) {
        this(surname, firstName, "");
    }
    public Name(String surname, String firstName, String patronymic) {
        this.surname = surname == null ? "": surname.trim();
        this.firstName = firstName == null ? "": firstName.trim();
        this.patronymic = patronymic == null ? "": patronymic.trim();
    }
    public String getSurname() {
        return surname;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getPatronymic() {return patronymic;}
    @Override
    public String toString() {
        String result = "";
        if (!surname.isEmpty()) {
            result += surname;
        }
        if (!firstName.isEmpty()) {
            if (!result.isEmpty()) result += " ";
            result += firstName;
        }
        if (!patronymic.isEmpty()) {
            if (!result.isEmpty()) result += " ";
            result += patronymic;
        }
        return result;
    }
}
class Person {
    private Name name;
    private int height;
    public  Person(Name name, int height) {
        if (name == null || height < 0) {
            throw new IllegalArgumentException("Неверные данные");
        }
        this.name = name;
        this.height = height;
    }
    public Name getName() {
        return name;
    }
    public int getHeight() {
        return height;
    }
    @Override
    public String toString() {
        return name + ", рост: " + height;
    }
}
class PersonWithFather {
    private Name name;
    private int height;
    private PersonWithFather father;
    public PersonWithFather(Name name, int height) {
        this(name, height, null);
    }
    public PersonWithFather(Name name, int height, PersonWithFather father) {
        if (name == null || height < 0) {
            throw new IllegalArgumentException("Неверные данные");
        }
        this.name = name;
        this.height = height;
        this.father = father;
    }
    public void setFather(PersonWithFather father) {
        this.father = father;
    }
    @Override
    public String toString() {
        String surname = name.getSurname();
        String firstname = name.getFirstName();
        String patronymic = name.getPatronymic();
        if (father != null) {
            Name fatherName = father.name;
            if (surname.isEmpty()) {
                surname = fatherName.getSurname();
            }
            if (patronymic.isEmpty() && !fatherName.getFirstName().isEmpty()) {
                patronymic = fatherName.getFirstName() + "ович";
            }
        }
        Name fullname = new Name(surname, firstname, patronymic);
        return fullname + ", рост: " + height + " см";
    }
}
class Point{
    private double x;
    private double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX(){
        return x;
    }
    public double getY() {
        return y;
    }
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }
    private String format(double value) {
        if (value == Math.rint(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
    @Override
    public String toString() {
        return "{" + format(x) + ";" + format(y) + "}";
    }
}
class Polyline {
    private List<Point> points;
    public Polyline() {
        points = new ArrayList<>();
    }
    public Polyline(Point... points) {
        this();
        addPoints(points);
    }
    public void addPoints(Point... newPoints) {
        if (newPoints == null) {
            throw new IllegalArgumentException("Массив точек не задан");
        }
        for (Point point : newPoints) {
            if (point == null) {
                throw new IllegalArgumentException("Точка не может быть null");
            }
            points.add(point);
        }
    }
public double getLenght(){
    double lenght = 0;
    for(int i = 1; i < points.size();i++) {
        Point p1 = points.get(i - 1);
        Point p2 = points.get(i);
        double dx = p2.getX() - p1.getX();
        double dy = p2.getY() - p1.getY();
        lenght += Math.sqrt(dx * dx + dy * dy);
    }
    return lenght;
}
@Override
public String toString() {
    return "Линия " + points;
    }
}
public class main {
    public static void main(String[] args) {
        System.out.println(" task 1.3 ");
        Name name1 = new Name(" Клеопатра");
        Name name2 = new Name("Пушкин","Александр","Сергеевич");
        Name name3 = new Name("Маяковский", "Владимир");
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(name3);
        System.out.println(" task 2.2 ");
        Person person1 = new Person(name1, 152);
        Person person2 = new Person(name2, 167);
        Person person3 = new Person(name3, 189);
        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person3);
        System.out.println(" task 2.3 ");
        PersonWithFather ivan = new PersonWithFather(new Name("Чудов", "Иван"), 175);
        PersonWithFather petr = new PersonWithFather(new Name("Чудов", "Петр"), 180);
        PersonWithFather boris = new PersonWithFather(new Name("Борис"), 170);
        petr.setFather(ivan);
        boris.setFather(petr);
        System.out.println(ivan);
        System.out.println(petr);
        System.out.println(boris);
        System.out.println(" task 4.9 ");
        Polyline emptyLine = new Polyline();
        System.out.println("Пустая ломаная " + emptyLine);
        Point start = new Point(1, 5);
        Point end = new Point(5, 3);
        Polyline line1 = new Polyline(start, new Point(2, 8), end);
        Polyline line2 = new Polyline(start, new Point(2, -5), new Point(4, -8), end);
        System.out.println("first " + line1);
        System.out.println("second " + line2);
        start.move(1, 1);
        System.out.println("После сдвига общей точки");
        System.out.println("first " + line1);
        System.out.println("second " + line2);
        System.out.println(" task 5.7 ");
        Polyline line = new Polyline(new Point(1,5), new Point(2,8), new Point(5,3));
        System.out.println("Исходная " + line);
        System.out.printf("Длина ломаной: %.2f%n", line.getLenght());
        line.addPoints(new Point(5, 15), new Point(8, 10));
        System.out.println("После добавления: " + line);
        System.out.printf("Новая длина ломаной: %.2f%n", line.getLenght());
    }
}
