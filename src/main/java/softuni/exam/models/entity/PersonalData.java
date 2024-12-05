package softuni.exam.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.exam.models.enums.Gender;

import java.time.LocalDate;

@Entity
@Table(name = "personal_datas")
public class PersonalData extends BaseEntity{
    @Column(name = "age")
    private int age;
    @Column(name = "birth_date")
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Column(name = "card_number", unique = true, nullable = false)
    private String cardNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "CHAR(1)")
    private Gender gender;

    @OneToOne(mappedBy = "personalData", cascade = CascadeType.ALL)
    private Visitor visitor;

    public PersonalData() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public @Past LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(@Past LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
