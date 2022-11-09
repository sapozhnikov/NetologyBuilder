import java.util.Optional;
import java.util.OptionalInt;

public class PersonBuilder {
    private Optional<String> name;
    private Optional<String> surname;
    private OptionalInt age;
    private Optional<String> address;

    public PersonBuilder() {
        name = Optional.empty();
        surname = Optional.empty();
        age = OptionalInt.empty();
        address = Optional.empty();
    }

    public PersonBuilder setName(String name) {
        this.name = Optional.ofNullable(name);
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = Optional.ofNullable(surname);
        return this;
    }

    public PersonBuilder setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        this.age = OptionalInt.of(age);
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = Optional.ofNullable(address);
        return this;
    }

    public Person build() {
        if (name.isEmpty() || surname.isEmpty()) {
            throw new IllegalStateException("Небходимы имя и фамилия");
        }
        Person person;
        if (age.isEmpty()) {
            person = new Person(name.get(), surname.get());
        } else {
            person = new Person(name.get(), surname.get(), age.getAsInt());
        }
        if (address.isPresent()) {
            person.setCity(address.get());
        }

        return person;
    }
}
