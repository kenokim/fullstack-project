package keno.blogProjectDb.support;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StackExchangeData {

    public void getStackExchange() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.stackexchange.com/2.2/users/1;5?site=stackoverflow&key=1";
        ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
        System.out.println(resp.toString());
    }
}
