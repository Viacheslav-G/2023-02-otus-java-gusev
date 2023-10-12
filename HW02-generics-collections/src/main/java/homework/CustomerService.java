package homework;


import java.util.*;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

   private TreeMap<Customer, String> customers;
    public CustomerService() {
        customers = new TreeMap<Customer, String>(Comparator.comparing(Customer::getScores));
    }

    public Map.Entry<Customer, String> getSmallest() {

        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        return customers.firstEntry();//customerWithSmallestScore
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        List<Map.Entry<Customer, String>> entryList = new ArrayList<>(customers.entrySet());
        customers.clear();
        for (Map.Entry<Customer, String> entry : entryList) {
            customers.put(entry.getKey(), entry.getValue());
        }
        return customers.higherEntry(customer)!= null? customers.higherEntry(customer): null;
    }

    public void add(Customer customer, String data) {
        customers.put(new Customer(customer.getId(), customer.getName(), customer.getScores()), data);
        }


}
