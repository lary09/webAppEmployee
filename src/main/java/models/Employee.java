package models;

public class Employee {
    private int id;
    private String name;
    private String lastName;
    private double salary;

    public Employee() {}

    public Employee(int id, String name, String lastName, double salary) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.salary = salary;
    }

    public int getId(){
        return id;
    }
    public  void setId(){this.id = id;}

    public String getNombre() {
        return name;
    }

    public void  setNombre(String name) {
        this.name = name;
    }
    public String getApellido(){
        return lastName;
    }
    public void setApellido(String lastName){
        this.lastName = lastName;
    }
    public double getSalario(){
        return  salary;
    }
    public void setSalario(double salary){
        this.salary =salary;
    }
}

