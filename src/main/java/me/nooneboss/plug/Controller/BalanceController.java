package me.nooneboss.plug.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.nooneboss.plug.Model.BalanceRequest;
import me.nooneboss.plug.Model.BalanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class BalanceController {
    private final Logger log = LoggerFactory.getLogger(BalanceController.class);

    private final DecimalFormat df = new DecimalFormat("#.00");

    @PostMapping(value = "/balance", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public Object getBalanceInfo(@RequestBody BalanceRequest request) {
        try {
            String clientId = request.getClientId();
            char firstChar = clientId.charAt(0);

            String currency;
            Double maxLimit;

            switch (firstChar) {
                case '8':
                    currency = "US";
                    maxLimit = 2000.0;
                    break;
                case '9':
                    currency = "EU";
                    maxLimit = 1000.0;
                    break;
                default:
                    currency = "RUB";
                    maxLimit = 10000.0;
                    break;
            }

            Double balance = ThreadLocalRandom.current().nextDouble(0, maxLimit);

            BalanceResponse response = new BalanceResponse();
            response.setRqUID(request.getRqUID());
            response.setClientId(request.getClientId());
            response.setAccount(request.getAccount());
            response.setCurrency(currency);
            response.setBalance(df.format(balance).replace(",", "."));
            response.setMaxLimit(df.format(maxLimit).replace(",", "."));

            log.info("\nBalance request: {}\nBalance response: {}", request, response);
            return response;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
