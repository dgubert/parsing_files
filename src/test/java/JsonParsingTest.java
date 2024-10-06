import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Sample;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonParsingTest {
    private ClassLoader cl = JsonParsingTest.class.getClassLoader();
    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("sample.json"))){
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualData = mapper.readValue(reader, JsonNode.class);

            assertThat(actualData.get("course_name").asText()).isEqualTo("Intro to Data Structures");
            assertThat(actualData.get("students").get(0).get("name").asText()).isEqualTo("Alice");
            assertThat(actualData.get("students").get(0).get("major").asText()).isEqualTo("Computer Science");
            assertThat(actualData.get("students").get(1).get("name").asText()).isEqualTo("Bob");
            assertThat(actualData.get("students").get(1).get("major").asText()).isEqualTo("Computer Science");
            assertThat(actualData.get("students").get(2).get("name").asText()).isEqualTo("Eve");
            assertThat(actualData.get("students").get(2).get("major").asText()).isEqualTo("Computer Science");
        }
    }

    @Test
    void jsonFileParsingImprovedTest() throws Exception {
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("sample.json"))){
            ObjectMapper mapper = new ObjectMapper();
            Sample actualData = mapper.readValue(reader, Sample.class);

            assertThat(actualData.getCourse_name()).isEqualTo("Intro to Data Structures");
            assertThat(actualData.getStudents().get(0).getName()).isEqualTo("Alice");
            assertThat(actualData.getStudents().get(0).getMajor()).isEqualTo("Computer Science");
            assertThat(actualData.getStudents().get(1).getName()).isEqualTo("Bob");
            assertThat(actualData.getStudents().get(1).getMajor()).isEqualTo("Computer Science");
            assertThat(actualData.getStudents().get(2).getName()).isEqualTo("Eve");
            assertThat(actualData.getStudents().get(2).getMajor()).isEqualTo("Computer Science");
        }
    }
}