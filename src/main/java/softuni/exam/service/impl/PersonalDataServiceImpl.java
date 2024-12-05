package softuni.exam.service.impl;

import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PersonalDataImportDto;
import softuni.exam.models.dto.PersonalDataRootImportDto;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.repository.PersonalDataRepository;
import softuni.exam.service.PersonalDataService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;

//ToDo - Implement all the methods

@Service
public class PersonalDataServiceImpl implements PersonalDataService {
    private static final String PATH = "src/main/resources/files/xml/personal_data.xml";
    private final PersonalDataRepository personalDataRepository;
    private XmlParser xmlParser;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;

    @Autowired
    public PersonalDataServiceImpl(PersonalDataRepository personalDataRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.personalDataRepository = personalDataRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {

        return this.personalDataRepository.count() > 0;
    }

    @Override
    public String readPersonalDataFileContent() throws IOException {

        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importPersonalData() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        // Parse XML file into DTO
        PersonalDataRootImportDto personalDataRootDtos =
                this.xmlParser.fromFile(PATH, PersonalDataRootImportDto.class);
        if (personalDataRootDtos == null) {
            throw new IllegalArgumentException("Failed to parse XML file");
        }

        // Iterate through each personal data DTO
        for (PersonalDataImportDto dto : personalDataRootDtos.getPersonalDatas()) {
            Optional<PersonalData> personalDataOptional =
                    this.personalDataRepository.findByCardNumber(dto.getCardNumber());

            // Validate DTO and check for duplicates
            if (!this.validationUtil.isValid(dto) || personalDataOptional.isPresent()) {
                sb.append("Invalid personal data").append(System.lineSeparator());
                continue;
            }

            // Map DTO to Entity and save it
            PersonalData personalData = this.modelMapper.map(dto, PersonalData.class);
            personalData.setBirthDate(LocalDate.parse(dto.getBirthDate()));
            this.personalDataRepository.saveAndFlush(personalData);

            sb.append(String.format(
                            "Successfully imported personal data for visitor with card number %s",
                            dto.getCardNumber()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
