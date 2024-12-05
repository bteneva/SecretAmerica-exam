package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.VisitorImportDto;
import softuni.exam.models.dto.VisitorsRootImportDto;
import softuni.exam.models.entity.Attraction;
import softuni.exam.models.entity.Country;
import softuni.exam.models.entity.PersonalData;
import softuni.exam.models.entity.Visitor;
import softuni.exam.repository.AttractionRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.repository.PersonalDataRepository;
import softuni.exam.repository.VisitorRepository;
import softuni.exam.service.VisitorService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

//ToDo - Implement all the methods

@Service
public class VisitorServiceImpl implements VisitorService {
    private static final String PATH = "src/main/resources/files/xml/visitors.xml";
    private final VisitorRepository visitorRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final CountryRepository countryRepository;
    private final PersonalDataRepository personalDataRepository;
    private final AttractionRepository attractionRepository;

    @Autowired
    public VisitorServiceImpl(VisitorRepository visitorRepository, XmlParser xmlParser,ModelMapper modelMapper, ValidationUtil validationUtil, CountryRepository countryRepository, PersonalDataRepository personalDataRepository, AttractionRepository attractionRepository) {
        this.visitorRepository = visitorRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.countryRepository = countryRepository;
        this.personalDataRepository = personalDataRepository;
        this.attractionRepository = attractionRepository;
    }

    @Override
    public boolean areImported() {

        return this.visitorRepository.count() > 0;
    }

    @Override
    public String readVisitorsFileContent() throws IOException {

        return Files.readString(Path.of(PATH));
    }

//    @Override
//    public String importVisitors() {
//        StringBuilder stringBuilder = new StringBuilder();
//
//        // Parse the XML file into a DTO object
//        VisitorsRootImportDto visitorsRootImportDto = this.xmlParser.fromFile(PATH, VisitorsRootImportDto.class);
//
//
//
//        for (VisitorImportDto dto : visitorsRootImportDto.getVisitors()) {
////            System.out.println(dto.toString());
////            System.out.println(dto.getFirstName());
////            System.out.println(dto.getLastName());
////            System.out.println(dto.getPersonalDataId());
////            System.out.println(dto.getAttractionId());
////            System.out.println(dto.getCountryId());
//
//            // Check if a visitor with the same firstName and lastName exists
//            Optional <Visitor> visitorOptionalFirstNameLastName =
//                    this.visitorRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());
//            Optional <Visitor> visitorOptionalId = this.visitorRepository.findByPersonalDataId((long)dto.getPersonalDataId());
//            // Fetch related entities
//            Optional<Attraction> attractionOpt = this.attractionRepository.findById(dto.getAttractionId());
//            Optional<Country> countryOpt = this.countryRepository.findById(dto.getCountryId());
//            Optional<PersonalData> personalDataOpt = this.personalDataRepository.findById(dto.getPersonalDataId());
//
//
//
//            Visitor visitor = new Visitor();
//            if (visitorOptionalId.isPresent() || !validationUtil.isValid(dto) || visitorOptionalFirstNameLastName.isPresent()) {
//                stringBuilder.append("Invalid visitor").append(System.lineSeparator());
//                continue;
//            }
//            else {
//                visitor.setFirstName(dto.getFirstName());
//                visitor.setLastName(dto.getLastName());
//
//
//                visitor.setAttraction(attractionOpt.get());
//                visitor.setCountry(countryOpt.get());
//                visitor.setPersonalData(personalDataOpt.get());
//
//                visitorRepository.save(visitor);
//                stringBuilder.append(String.format("Successfully imported visitor %s %s", visitor.getFirstName(), visitor.getLastName()))
//                        .append(System.lineSeparator());
//            }
//
//            //Visitor visitor = modelMapper.map(dto, Visitor.class);
////
////            System.out.println(visitor.getFirstName());
////            System.out.println(visitor.getLastName());
////            //visitor.setAttraction(dto.getAttractionId().get());
////            System.out.println(visitor.getAttraction());
////            System.out.println(visitor.getPersonalData().getId());
////            System.out.println(visitor.getCountry().getId());
////
////            System.out.println(visitor.getCountry());
////            break;
//
//            //Optional<Country> countryOptional = this.countryRepository.findById(dto.getCountryId());
//            //System.out.println(countryOptional.get().getName());
//            //Optional<PersonalData> personalDataOptional = this.personalDataRepository.findById(dto.getPersonalDataId());
//            //System.out.println(personalDataOptional.get().getId());
//           // Optional<Attraction> attractionOptional = this.attractionRepository.findById(dto.getAttractionId());
////            System.out.println(attractionOptional.get().getName());
////            System.out.printf("---------------");
////            System.out.println();
//
////            visitor.setPersonalData(personalDataOptional.get());
////            visitor.setAttraction(attractionOptional.get());
////            visitor.setCountry(countryOptional.get());
//
//            //visitorRepository.saveAndFlush(visitor);
//
////           stringBuilder.append(String.format("Successfully imported visitor %s %s", dto.getFirstName(), dto.getLastName()))
////                    .append(System.lineSeparator());
//
//
//            //System.out.println(visitorByFirstNameLastName.get().getFirstName());
//
//            // You can check the unique fields like personal_number_id if that's part of the import
//
//
//            //System.out.println(visitorByPersonalNumberId);
//
//            // If either of these exist or if the DTO is invalid, skip the import
//
//
//
//            // Map the DTO to the Visitor entity
////            Visitor visitor = modelMapper.map(dto, Visitor.class);
////            System.out.println("visitor.firstName: " + visitor.getFirstName()
////                    + " visitor.lastName: " + visitor.getLastName()
////                    + " visitor.getCountry: " + visitor.getCountry()
////                    + " visitor.getAttraction: " + visitor.getAttraction()
////                    + " visitor.getPersonalData: " + visitor.getPersonalDataId()
////                    + " dto.getPersonalData: " + dto.getPersonalDataId()
////                    + " dtoGetAttractionId: " + dto.getAttractionId()
////                    + " dto.getCountryId: " + dto.getCountryId());
//
//
//
//
////            Optional<Attraction> attraction = this.attractionRepository.findById(dto.getAttractionId());
////            Optional<Country> country = this.countryRepository.findById(dto.getCountryId());
////            Optional<PersonalData> personalData = this.personalDataRepository.findById(dto.getPersonalDataId());
////
////            visitor.setCountry(country.get());
////            visitor.setAttraction(attraction.get());
////            visitor.setPersonalDataId(personalData.get());
//
//
//
////            Optional<Country> country = this.countryRepository.findById(dto.getCountryId());
////            if (country.isEmpty()) {
////                stringBuilder.append("Invalid visitor: Country not found").append(System.lineSeparator());
////                continue;
////            }
////
////            Optional<PersonalData> personalData = this.personalDataRepository.findById(dto.getPersonalData());
////            if (personalData.isEmpty()) {
////                stringBuilder.append("Invalid visitor: Personal data not found").append(System.lineSeparator());
////                continue;
////            }
////            visitor.setAttraction(attraction.get());
////             visitor.setCountry(country.get());
////             visitor.setPersonalData(personalData.get());
////
////
//            // this.visitorRepository.saveAndFlush(visitor);
////
////            // Add success message
//           //stringBuilder.append(String.format("Successfully imported visitor %s %s", dto.getFirstName(), dto.getLastName()))
//                   //.append(System.lineSeparator());
////
//        }
//
//        return stringBuilder.toString();
//        //return null;
//    }
@Override
public String importVisitors() {
    StringBuilder stringBuilder = new StringBuilder();
    VisitorsRootImportDto visitorsRootImportDto = this.xmlParser.fromFile(PATH, VisitorsRootImportDto.class);

    for (VisitorImportDto dto : visitorsRootImportDto.getVisitors()) {
        if (!validationUtil.isValid(dto)) {
            stringBuilder.append("Invalid visitor").append(System.lineSeparator());
            continue;
        }

        Optional<PersonalData> personalDataOptional = personalDataRepository.findById(dto.getPersonalDataId());
        Optional<Attraction> attractionOptional = attractionRepository.findById(dto.getAttractionId());
        Optional<Country> countryOptional = countryRepository.findById(dto.getCountryId());

        if (personalDataOptional.isEmpty() || attractionOptional.isEmpty() || countryOptional.isEmpty()) {
            stringBuilder.append("Invalid visitor").append(System.lineSeparator());
            continue;
        }

        PersonalData personalData = personalDataOptional.get();
        if (visitorRepository.findByPersonalData(personalData).isPresent()) {
            stringBuilder.append("Invalid visitor").append(System.lineSeparator());
            continue;
        }
        Optional<Visitor> optionalVisitorById = this.visitorRepository.findById(dto.getPersonalDataId());
        if (optionalVisitorById.isPresent()) {
            stringBuilder.append("Invalid visitor").append(System.lineSeparator());
            continue;
        }
        Optional<Visitor> optionalVisitorByFirstNameLastName = this.visitorRepository
                .findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());
        if (optionalVisitorByFirstNameLastName.isPresent()) {
            stringBuilder.append("Invalid visitor").append(System.lineSeparator());
            continue;
        }

        Visitor visitor = modelMapper.map(dto, Visitor.class);
        visitor.setPersonalData(personalData);
        visitor.setAttraction(attractionOptional.get());
        visitor.setCountry(countryOptional.get());

        visitorRepository.save(visitor);
        stringBuilder.append(String.format("Successfully imported visitor %s %s", dto.getFirstName(), dto.getLastName()))
                .append(System.lineSeparator());
    }

    return stringBuilder.toString();
}

}

