package softuni.exam.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;


import java.io.File;


@Component
public class XmlParserImpl implements XmlParser {
    @Override
    public <T> T fromFile(String filePath, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File(filePath);
            return (T) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T parse(String filePath, Class<T> tClass) throws JAXBException {
        return null;
    }
}


