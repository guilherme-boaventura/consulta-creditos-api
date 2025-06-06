package glhrme.bvt.consulta_credito.utils.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class KafkaProducerService {

    @Autowired
    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, KafkaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String type, Object payload) throws JsonProcessingException {
        if(Objects.isNull(payload) || !StringUtils.hasText(type)) {
            throw new IllegalArgumentException("O corpo e o tipo da mensagem devem ser informados para o seu envio.");
        }

        kafkaTemplate.send(topic, new KafkaMessage(UUID.randomUUID().toString(), type, ZonedDateTime.now().toString(), payload));
    }
}