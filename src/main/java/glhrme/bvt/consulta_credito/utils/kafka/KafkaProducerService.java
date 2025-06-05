package glhrme.bvt.consulta_credito.utils.kafka;

import com.google.gson.JsonObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, JsonObject> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, JsonObject> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, JsonObject mensagem) {
        validar(mensagem);
        kafkaTemplate.send(topic, mensagem);
    }

    public static void validar(JsonObject json) {
        if (Objects.isNull(json)) {
            throw new IllegalArgumentException("JsonObject não pode ser nulo");
        }

        if (!json.has("id")) {
            throw new IllegalArgumentException("Falta o campo obrigatório: id");
        } else {
            String id = json.get("id").getAsString();
            try {
                UUID.fromString(id);
            } catch (Exception e) {
                throw new IllegalArgumentException("Campo 'id' não é um UUID válido");
            }
        }

        if (!json.has("type")) {
            throw new IllegalArgumentException("Falta o campo obrigatório: type");
        } else {
            String type = json.get("type").getAsString();
            if (type.isBlank()) {
                throw new IllegalArgumentException("Campo 'type' não pode ser vazio");
            }
        }

        if (!json.has("timestamp")) {
            throw new IllegalArgumentException("Falta o campo obrigatório: timestamp");
        } else {
            String timestamp = json.get("timestamp").getAsString();
            try {
                ZonedDateTime.parse(timestamp);
            } catch (Exception e) {
                throw new IllegalArgumentException("Campo 'timestamp' não está no formato ZonedDateTime ISO-8601 válido");
            }
        }
    }
}