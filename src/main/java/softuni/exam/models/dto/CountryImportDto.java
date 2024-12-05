package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class CountryImportDto {
    @Expose
    @Length(min = 3, max = 40)
    private String name;
    @Expose
    @Positive
    private double area;

    public CountryImportDto() {
    }

    public @Length(min = 3, max = 40) String getName() {
        return name;
    }

    public void setName(@Length(min = 3, max = 40) String name) {
        this.name = name;
    }

    @Positive
    public double getArea() {
        return area;
    }

    public void setArea(@Positive double area) {
        this.area = area;
    }
}
