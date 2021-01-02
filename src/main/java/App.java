
import dao.AddressDao;
import dao.EmployeeDao;
import dao.PhoneNumberDao;

import entity.Address;
import entity.Employee;
import entity.FullName;
import entity.PhoneNumber;

import util.JPAUtil;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {



    private static PhoneNumber[] phoneNumber = new PhoneNumber[10];
    private static Address[] addresses = new Address[10];
    private static Employee[] employees = new Employee[10];
    private static FullName[] fullNames = new FullName[10];
    private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static PhoneNumberDao phoneNumberDao;
    private static AddressDao addressDao;
    private static EmployeeDao employeeDao;
    private static final Integer CHECK_PHON_ID = 1;//problem
    private static final Integer CHECK_EMP_ID = 1;//problem
    private static final Integer CHECK_ADR_ID = 1;//problem


    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        initializeDao(entityManager);
        entityManager.getTransaction().begin();

        if (employeeDao.load(CHECK_EMP_ID) == null)//problem
        {
           //   initializeData();
        }
   //addressDao.loadCity();
//        System.out.println("MAX SALARY:="+employeeDao.loadSalary());
//        System.out.println("NAME OF EMPLOYEE  TAKE MAX SALARY:="+employeeDao.loadFirstSalary()[0]+" "+employeeDao.loadFirstSalary()[1]);
       // deleteData();
       // updateData();
        System.out.println("--------------------------------------------------------------------------------------------------------");

        List<Employee> employees2 =employeeDao.findEmployeesBySupervisor2();
        for (Employee e:employees2)
        {

            System.out.println(e.getId());
        }
        System.out.println(employees2);
        System.out.println("--------------------------------------------------------------------------------------------------------");
      //   showData();

        entityManager.getTransaction().commit();
        entityManager.close();
        JPAUtil.shutdown();
    }

    public static void initializeData() {


        /*-------------input first step of  data--------------------*/
        //------------------Address
        addresses[0]=createAddress( 54545004,  "    Mr John Smith  132, My Street, Bigtown BG23 4YZ England","london");
        addresses[1]=createAddress( 123324324,  "    Mr John Smith 132, My Street, Kingston, New York 12401 United States","New York");
        addresses[2]=createAddress( 123324334,  "    Mr John Smith 132, My Street, Kingston, New York 12401 United States","New York");
        addresses[3]=createAddress(1233243004, "    Mr John Smith  132, My Street, Bigtown BG23 4YZ England", "london");
        addresses[4]=createAddress( 54545004,  "    Mr John Smith  132, My Street, Bigtown BG23 4YZ England","london");
        addresses[5]=createAddress( 123324324,  "    Mr John Smith 132, My Street, Kingston, New York 12401 United States","New York");
        addresses[6]=createAddress( 123324334,  "    Mr John Smith 132, My Street, Kingston, New York 12401 United States","New York");
        addresses[7]=createAddress(1233243004, "    Mr John Smith  132, My Street, Bigtown BG23 4YZ England", "london");


        //------------------phoneNumber
        phoneNumber[0]=createPhoneNumber(02101022233,02133234432);
        phoneNumber[1]=createPhoneNumber(010002777777,02133255552);
        phoneNumber[2]=createPhoneNumber(02101033233,02133233332);
        phoneNumber[3]=createPhoneNumber(021010244233,021332344432);
        phoneNumber[4]=createPhoneNumber(010002777777,02133255552);
        phoneNumber[5]=createPhoneNumber(02101022233,02133234432);
        phoneNumber[6]=createPhoneNumber(02101033233,02133233332);
        phoneNumber[7]=createPhoneNumber(021010244233,021332344432);


        //------------------fullName

        fullNames[0]=createFullName("ali","samadi");
        fullNames[1]=createFullName("masood ","javadi");
        fullNames[2]=createFullName("rahim ","elhami");
        fullNames[3]=createFullName("sadegh ","samadiyan");
        //------------------employee





        employees[0]=createEmployee( 123324334, 12.1);
        employees[1]=createEmployee( 123325554, 14.1);
        employees[2]=createEmployee( 123325564, 16.1);
        employees[3]=createEmployee( 123355334, 16.1);

        /*-------------input data about relation--------------------*/
        //------------------Address






        Set<PhoneNumber> phoneNumber1=new HashSet<>();
        phoneNumber1.add(phoneNumber[0]);
        phoneNumber1.add(phoneNumber[1]);
        addresses[0].setPhoneNumber(phoneNumber1);
        Set<PhoneNumber> phoneNumber2=new HashSet<>();
        phoneNumber2.add(phoneNumber[0]);
        phoneNumber2.add(phoneNumber[1]);
        addresses[1].setPhoneNumber(phoneNumber2);
        Set<PhoneNumber> phoneNumber3=new HashSet<>();
        phoneNumber3.add(phoneNumber[0]);
        phoneNumber3.add(phoneNumber[1]);
        addresses[2].setPhoneNumber(phoneNumber3);
        Set<PhoneNumber> phoneNumber4=new HashSet<>();
        phoneNumber4.add(phoneNumber[0]);
        phoneNumber4.add(phoneNumber[1]);
        addresses[3].setPhoneNumber(phoneNumber4);
        Set<PhoneNumber> phoneNumber5=new HashSet<>();
        phoneNumber5.add(phoneNumber[0]);
        phoneNumber5.add(phoneNumber[1]);
        addresses[4].setPhoneNumber(phoneNumber5);
        Set<PhoneNumber> phoneNumber6=new HashSet<>();
        phoneNumber6.add(phoneNumber[0]);
        phoneNumber6.add(phoneNumber[1]);
        addresses[5].setPhoneNumber(phoneNumber6);
        Set<PhoneNumber> phoneNumber7=new HashSet<>();
        phoneNumber7.add(phoneNumber[0]);
        phoneNumber7.add(phoneNumber[1]);
        addresses[6].setPhoneNumber(phoneNumber7);
        Set<PhoneNumber> phoneNumber8=new HashSet<>();
        phoneNumber8.add(phoneNumber[0]);
        phoneNumber8.add(phoneNumber[1]);
        addresses[7].setPhoneNumber(phoneNumber8);

        addressDao.save(addresses[0]);
        addressDao.save(addresses[1]);
        addressDao.save(addresses[2]);
        addressDao.save(addresses[3]);
        addressDao.save(addresses[4]);
        addressDao.save(addresses[5]);
        addressDao.save(addresses[6]);
        addressDao.save(addresses[7]);


        //------------------employee
        Set<Address> addressSet1=new HashSet<>();
        addressSet1.add(addresses[0]);
        addressSet1.add(addresses[1]);
        employees[0].setAddressSet(addressSet1);
        employees[0].setFullName(fullNames[0]);

        Set<Address> addressSet2=new HashSet<>();
        addressSet2.add(addresses[2]);
        addressSet2.add(addresses[3]);
        employees[1].setAddressSet(addressSet2);
        employees[1].setFullName(fullNames[1]);

        Set<Address> addressSet3=new HashSet<>();
        addressSet3.add(addresses[4]);
        addressSet3.add(addresses[5]);
        employees[2].setAddressSet(addressSet3);
        employees[2].setFullName(fullNames[2]);

        Set<Address> addressSet4=new HashSet<>();
        addressSet4.add(addresses[6]);
        addressSet4.add(addresses[7]);
        employees[3].setAddressSet(addressSet4);
        employees[3].setFullName(fullNames[3]);

        employeeDao.save(employees[0]);
        employeeDao.save(employees[1]);
        employeeDao.save(employees[2]);
        employeeDao.save(employees[3]);

        phoneNumberDao.save(phoneNumber[0]);
        phoneNumberDao.save(phoneNumber[1]);
        phoneNumberDao.save(phoneNumber[2]);
        phoneNumberDao.save(phoneNumber[3]);
        phoneNumberDao.save(phoneNumber[4]);
        phoneNumberDao.save(phoneNumber[5]);
        phoneNumberDao.save(phoneNumber[6]);
        phoneNumberDao.save(phoneNumber[7]);

    }

    public static FullName createFullName( String  firstName, String  lastName)
    {
        FullName fullName = new FullName();
        fullName.setFirstName(firstName);
        fullName.setLastName(lastName);
        return fullName;
    }
    public static Employee createEmployee(  long    empCode,Double  salary)
    {
        Employee employee = new Employee();

        employee.setEmpCode(empCode);
        employee.setSalary(salary);
        return employee;
    }

    public static PhoneNumber createPhoneNumber( long   telNumber,long   mobNumber) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setMobNumber(mobNumber);
        phoneNumber.setTelNumber(telNumber);
        return phoneNumber;
    }

    public static Address createAddress(  long     postalCode, String   postalAddress,String   city)
    {
        Address address = new Address();
        address.setPostalAddress(postalAddress);
        address.setPostalCode(postalCode);
        address.setCity(city);
        return address;

    }

    public static void initializeDao(EntityManager entityManager) {
        phoneNumberDao = new PhoneNumberDao(entityManager);
        employeeDao = new EmployeeDao(entityManager);
        addressDao = new AddressDao(entityManager);
    }

    public static void deleteData()
    {
        Address newAddress = addressDao.load(4);
        try {
            addressDao.delete(newAddress);
        } catch (Exception e) {
        }

    }

    public static void updateData()
    {
        Address newAddress = addressDao.load(2);
        Employee newEmployee  = employeeDao.load(1);
        newAddress.setEmployee (newEmployee );
        addressDao.update(newAddress);
    }

    public static <T> void showData() {
        Address newAddress = addressDao.load(4);
        Address newAddress2 = addressDao.load(3);
        Address newAddress3 = addressDao.load(2);
        Address newAddress4 = addressDao.load(1);
        PhoneNumber newPhoneNumber =phoneNumberDao.load(1);
        Employee newEmployee = employeeDao.load(1);

        try {
            System.out.println("************************************\n");
            System.out.println(" Id= " + newAddress.getId() + "  Number= " + newAddress.getPhoneNumber() + "  City= " + newAddress.getCity());
        } catch (Exception e) {
        }
        System.out.println("************************************\n");
        System.out.println("************************************\n");
        System.out.println(" Id= " + newAddress2.getId() + "  Number= " + newAddress2.getPhoneNumber() + "  City= " + newAddress2.getCity());


        System.out.println("************************************\n");
        System.out.println("************************************\n");
        System.out.println(" Id= " + newAddress3.getId() + "  Number= " + newAddress3.getPhoneNumber() + "  City= " + newAddress3.getCity());
        System.out.println("************************************\n");
        System.out.println("************************************\n");
        System.out.println(" Id= " + newAddress4.getId() + "  Number= " + newAddress4.getPhoneNumber()+ "  City= " + newAddress4.getCity());
        System.out.println("************************************\n");
        System.out.println("************************************\n");



        System.out.println(" Id= "+newEmployee.getId()+"   FirstName= "+newEmployee.getFullName().getFirstName()+"   LastName= "+newEmployee.getFullName().getLastName()+"  Salary= "+newEmployee.getSalary());
        System.out.println("************************************\n"  );

        boolean flage=false;
        for(Address object:newEmployee.getAddressSet())
        {
            if(flage==false) {
                flage=true;
                System.out.println("---------------information from students have relation by teacher----------------\n");
            }
            System.out.println("        FirstName:  "+object.getCity()+  "   LastName:   " + object.getPostalAddress()+ "\n");
            System.out.println("          _______________________________\n"  );
        }



        System.out.println("************************************\n");
        System.out.println(" Id= "+newPhoneNumber.getId()+"  MobNumber= "+newPhoneNumber.getMobNumber()+"  TelNumber= "+newPhoneNumber.getTelNumber());
        System.out.println("************************************\n"  );





    }


}
