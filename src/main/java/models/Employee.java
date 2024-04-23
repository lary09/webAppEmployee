package models;

public class Employee {
    private int id;
    private String name;
    private String lastName;
    private double salary;
    private String departmentName;
    private Integer department_id;

    public Employee() {}

    public Employee( String name, String lastName, double salary, Integer department_id) {
        this.name = name;
        this.lastName = lastName;
        this.salary = salary;
        this.department_id = department_id;
    }
    public Employee(int id, String name, String lastName, double salary, Integer department_id, String departmentName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.salary = salary;
        this.department_id = department_id;
        this.departmentName = departmentName;
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
    public int getDepartment_id() {
        return department_id;
    }
    public void setDepartment_id(int department_id){
        this.department_id = department_id;
    }
    public String getDepartmentName(){
        return departmentName;
    }
    public void setDepartmnetName(String departmenttName){
        this.departmentName = departmenttName;
    }
}

