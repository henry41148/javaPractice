package org.example;

import org.example.dao.ManufacturerDAO;
import org.example.dao.PhoneDAO;
import org.example.domain.Manufacturer;
import org.example.domain.Phone;

public class Program
{
    private final static PhoneDAO phoneDAO = new PhoneDAO();

    private final static ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
    public static void main( String[] args )
    {
        PhoneDAO phoneDAO = new PhoneDAO();
        addPhones();
        System.out.print("All phones:" +phoneDAO.getAll());
        System.out.print("Phone ID 1:" +phoneDAO.get(1));
        updatePhone();
        System.out.print("Phone ID 1 after update manufacturer:" +phoneDAO.get(1));
        phoneDAO.remove(2);
        System.out.print("All phones after remove id 2:" +phoneDAO.getAll());
        System.out.println("List highest price phones:" + phoneDAO.getHighestPricePhone());
        System.out.println("Sort by country:" + phoneDAO.sortByCountry());
        int a=700;
        System.out.println("Phone higher than" +a +": "+phoneDAO.getPhonesHigher(a));

        addManufacturer();
        System.out.println("\nAll manufacturer:"+manufacturerDAO.getAll());
        System.out.println("All manufacturer more than 100 employee:"+manufacturerDAO.isMoreThan100());
        System.out.println("Manufacturer ID 1:" +manufacturerDAO.get(1));
        System.out.println("Total employee: "  +manufacturerDAO.getTotalEmployees());
        String location = "China";
        System.out.println("Manufacturer in" +location+": " +manufacturerDAO.getManuByLocation(location));
        manufacturerDAO.remove(4);
        System.out.println("\nAll manufacturer after remove id 4:"+manufacturerDAO.getAll());


    }
    public static void addPhones()
    {
        Phone phoneA = new Phone(1, "Ip", "gold", "US", 1000, 100);
        Phone phoneB = new Phone(2, "SS", "black", "Korea", 1000, 500);
        Phone phoneC = new Phone(3, "Huawei", "black", "China", 500, 1000);
        phoneDAO.add(phoneA);
        phoneDAO.add(phoneB);
        phoneDAO.add(phoneC);
    }
    public static void addManufacturer()
    {
        Manufacturer manufacturer1 = new Manufacturer(1, "Apple", "America", 300000);

        Manufacturer manufacturer4 = new Manufacturer(4, "Huawei", "China", 50);
        Manufacturer manufacturer2 = new Manufacturer(2, "Xiaomi", "China", 500000);
        Manufacturer manufacturer3 = new Manufacturer(3, "Samsung", "Korea", 400000);
        manufacturerDAO.add(manufacturer1);
        manufacturerDAO.add(manufacturer2);
        manufacturerDAO.add(manufacturer3);
        manufacturerDAO.add(manufacturer4);
    }
    public static void updatePhone()
    {
        Phone phone = phoneDAO.get(1);
        Manufacturer manufacturer = manufacturerDAO.get(1);
        phone.setManufacturer(manufacturer);
        phoneDAO.update(phone);
    }

}
