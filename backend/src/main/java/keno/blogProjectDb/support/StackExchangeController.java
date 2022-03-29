package keno.blogProjectDb.support;


import keno.blogProjectDb.support.stackdatadto.ItemStackDto;
import keno.blogProjectDb.support.stackdatadto.QuestionListStackDto;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StackExchangeController {
    @Autowired StackExchangeDataService stackExchangeDataService;
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
            HttpClientBuilder.create().build());
    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

    @GetMapping("/stackexchangecache/getall")
    public void getall() throws InterruptedException {
        String url = "http://localhost:8090/givemeitem";
        QuestionListStackDto dto = restTemplate.getForObject(url, QuestionListStackDto.class);
        for (ItemStackDto item : dto.getItems()) {
            try {
                stackExchangeDataService.store(item);
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
