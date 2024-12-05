package softuni.exam.config;

//ToDo Create configurations
import com.google.gson.Gson;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.xml.bind.JAXBException;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.util.XmlParser;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    ;

    @Bean
    public Gson gson() {
        return new Gson().newBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

//    @Bean
//    public XmlParser xmlParser() {
//        return new XmlParser() {
//            @Override
//            public <T> T parse(String filePath, Class<T> tClass) throws JAXBException {
//                return (T) xmlParser();
//            }
//        };
//    }
}

