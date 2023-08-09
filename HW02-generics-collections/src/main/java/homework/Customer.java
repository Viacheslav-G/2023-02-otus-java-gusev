package homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Customer {
    private static Map<Long, Customer> customersById = new HashMap<>();

    private final long id;
    private String name;
    private long scores;

    //todo: 1. в этом классе надо исправить ошибки

    public Customer(long id, String name, long scores) {

        Objects.requireNonNull(id);

    Customer existingCustomer = customersById.get(id);
        if (existingCustomer != null) {
             existingCustomer.setName(name);
            this.id = existingCustomer.getId();
            this.name = existingCustomer.getName();
            this.scores = scores;

        } else {

            this.id = id;
            this.name = name;
            this.scores = scores;
            customersById.put(id, this);
        }
}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
        propagateNameChange();
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        this.scores = scores;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Customer customer = (Customer) o;

            return id == customer.id;
        }

        @Override
        public int hashCode() {
            return (int) (id ^ (id >>> 32));
        }

    private void propagateNameChange() {
        for (Map.Entry<Long, Customer> entry : customersById.entrySet()) {
            if (entry.getValue() != this && entry.getValue().id == this.id) {
                entry.getValue().setName(this.name);
            }
        }
    }



}
