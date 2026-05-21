import java.util.*;
import java.util.stream.Collectors;

public class EmployeeUtill {
    List<Employee> employeeList = new ArrayList<Employee>();

    EmployeeUtill() {
        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(190, "Manu Barma", 35, "Male", "Account And Finance", 2010, 47000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
    }

    public static void main(String[] args) {
        EmployeeUtill employeeUtill = new EmployeeUtill();
        List<Employee> list = employeeUtill.employeeList;

 //How many male and female employees are there in the organization?
        Map<String, Long> countOfMaleAndFemale = list.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(countOfMaleAndFemale);

//        Print the name of all departments in the organization?
        List<String> departementName = list.stream()
                .map(emp -> emp.getDepartment())
                .distinct()
                .toList();
        System.out.println(departementName);


//        What is the average age of male and female employees?
        Map<String, Double> averageAgeOfGender = list.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(averageAgeOfGender);
//        Get the details of highest paid employee in the organization?
//        Optional<Employee> maxsal = list.stream().max(Comparator.comparing(Employee::getSalary));
//        System.out.println(maxsal);
        Optional<Employee> maxsalUsingSort = list.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .skip(1)
                .findFirst();
        System.out.println(maxsalUsingSort);


//        Get the names of all employees who have joined after 2015?
        List<Employee> empListJoinedAfter2025 = list.stream()
                .filter(emp -> emp.getYearOfJoining() > 2015)
                .map(emp -> emp)
                .toList();
        System.out.println(empListJoinedAfter2025);


//        Count the number of employees in each department?
        Map<String, Long> depermentWiseEmpCount = list.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(depermentWiseEmpCount);

//        What is the average salary of each department?
        Map<String, Double> deptWiseAvgSal = list.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(deptWiseAvgSal);

//        Get the details of youngest male employee in the product development department?
        Optional<Employee> youngestEmp = list.stream()
                .filter(emp -> emp.getGender().equalsIgnoreCase("male"))
                .filter(emp -> emp.getDepartment().equalsIgnoreCase("Product Development"))
                .min(Comparator.comparing(Employee::getAge));
        System.out.println(youngestEmp);


//        Who has the most working experience in the organization?
        Integer year = list.stream()
                .sorted(Comparator.comparingInt(Employee::getYearOfJoining))
                .map(emp -> emp.getYearOfJoining())
                .findFirst()
                .get();

        List<Employee> mostWorkingExp = list.stream()
                .filter(employee -> employee.getYearOfJoining() == year)
                .toList();

        System.out.println(mostWorkingExp);

//        How many male and female employees are there in the sales and marketing team?
        Map<String, Long> countOfGenderInSalesDept = list.stream()
                .filter(employee -> employee.getDepartment().equalsIgnoreCase("Sales And Marketing"))
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(countOfGenderInSalesDept);


//        List down the names of all employees in each department?
        Map<String, List<Employee>> deptWiseEmpList = list.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println(deptWiseEmpList);

//        Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        Map<Boolean, List<Employee>> partEmp = list.stream().collect(Collectors.partitioningBy(emp -> emp.getAge() <= 25));
        System.out.println(partEmp);


    }
}
