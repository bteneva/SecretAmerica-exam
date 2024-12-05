package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AttractionImportDto;
import softuni.exam.models.entity.Attraction;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.AttractionService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service

public class AttractionServiceImpl implements AttractionService {

    private static final String PATH_INPUT = "src/main/resources/files/json/attractions.json";

    private final AttractionRepository attractionRepository;
    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    @Autowired
    public AttractionServiceImpl(AttractionRepository attractionRepository, CountryRepository countryRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.attractionRepository = attractionRepository;
        this.countryRepository = countryRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.attractionRepository.count() > 0;
    }

    @Override
    public String readAttractionsFileContent() throws IOException {
        return Files.readString(Path.of(PATH_INPUT));
    }

    @Override
    public String importAttractions() throws IOException {
        StringBuilder sb = new StringBuilder();

        // Deserialize JSON into DTO array
        AttractionImportDto[] dtos = this.gson.fromJson(readAttractionsFileContent(), AttractionImportDto[].class);

        for (AttractionImportDto dto : dtos) {
            // Check if attraction already exists in the repository
            Optional<Attraction> attractionOptional = this.attractionRepository.findByName(dto.getName());

            // Validate DTO and check if the attraction already exists
            if (!this.validationUtil.isValid(dto) || attractionOptional.isPresent()) {
                sb.append("Invalid attraction").append(System.lineSeparator());
                continue;
            }

            // Find country by ID
            Optional<Country> countryOptional = this.countryRepository.findById(dto.getCountry());


            if (countryOptional.isEmpty()) {
                sb.append("Invalid attraction").append(System.lineSeparator());
                continue;
            }

            // Map DTO to entity
            Attraction attraction = this.modelMapper.map(dto, Attraction.class);
            attraction.setCountry(countryOptional.get());

            // Save the attraction
            this.attractionRepository.saveAndFlush(attraction);
            sb.append(String.format("Successfully imported attraction %s", dto.getName()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }



    @Override
    public String exportAttractions() {
        StringBuilder sb = new StringBuilder();

        List<Attraction> attractions = this.attractionRepository.export(300);

        attractions.forEach(a ->
                sb.append(String.format("Attraction with ID%d:\n" +
                                "***%s - %s at an altitude of %sm. somewhere in %s.",
                        a.getId(), a.getName(), a.getDescription(), a.getElevation(), a.getCountry().getName())).append(System.lineSeparator()));

        return sb.toString();

        //"Attraction with ID{attraction id}:
        //***{attraction name} - {attraction description} at an altitude of {attraction elevation}m. somewhere in {attraction country}.
        //. . ."

    }
}