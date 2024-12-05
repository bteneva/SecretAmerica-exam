package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

public class AttractionImportDto {

    //"name": "Machu Picchu",
    //    "description": "An ancient Incan city located in the Andes mountains",
    //    "type": "historical site",
    //    "elevation": 2430,
    //    "country": 134
    @Expose
    @Length(min = 5, max = 40)
    private String name;
    @Expose
    @Length(min = 10, max = 100)
    private String description;
    @Expose
    @Size(min = 3, max = 30)
    private String type;
    @Expose
    @Min(0)
    private int elevation;
    @Expose
    private long country;

    public AttractionImportDto() {
    }

    public @Length(min = 5, max = 40) String getName() {
        return name;
    }

    public void setName(@Length(min = 5, max = 40) String name) {
        this.name = name;
    }

    public @Length(min = 10, max = 100) String getDescription() {
        return description;
    }

    public void setDescription(@Length(min = 10, max = 100) String description) {
        this.description = description;
    }

    public @Size(min = 3, max = 30) String getType() {
        return type;
    }

    public void setType(@Size(min = 3, max = 30) String type) {
        this.type = type;
    }

    @Min(0)
    public int getElevation() {
        return elevation;
    }

    public void setElevation( @Min(0) int elevation) {
        this.elevation = elevation;
    }

    public long getCountry() {
        return country;
    }

    public void setCountry(long country) {
        this.country = country;
    }
}
