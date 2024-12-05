package softuni.exam.models.dto;

import jakarta.xml.bind.annotation.*;
import org.hibernate.validator.constraints.Length;

@XmlRootElement(name = "visitor")
@XmlAccessorType(XmlAccessType.FIELD)
public class VisitorImportDto {

    // <visitor>
    //        <first_name>John</first_name>
    //        <last_name>Smith</last_name>
    //        <attraction_id>12</attraction_id>
    //        <country_id>73</country_id>
    //        <personal_data_id>61</personal_data_id>
    //    </visitor>
    @XmlElement(name = "first_name")
    @Length(min = 2, max = 20)
    private String firstName;
    @XmlElement(name = "last_name")
    @Length(min = 2, max = 20)
    private String lastName;
    @XmlElement(name = "attraction_id")
    private long attractionId;
    @XmlElement(name = "country_id")
    private long countryId;
    @XmlElement(name = "personal_data_id")
    private long personalDataId;


    public VisitorImportDto() {

    }

    public @Length(min = 2, max = 20) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Length(min = 2, max = 20) String firstName) {
        this.firstName = firstName;
    }

    public @Length(min = 2, max = 20) String getLastName() {
        return lastName;
    }

    public void setLastName(@Length(min = 2, max = 20) String lastName) {
        this.lastName = lastName;
    }

    public long getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(int attractionId) {
        this.attractionId = attractionId;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public long getPersonalDataId() {
        return personalDataId;
    }

    public void setPersonalDataId(int personalDataId) {
        this.personalDataId = personalDataId;
    }


}
