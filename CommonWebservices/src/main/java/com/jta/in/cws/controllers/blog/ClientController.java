package com.jta.in.cws.controllers.blog;

import com.jta.in.cws.constants.EnumConstants;
import com.jta.in.cws.utils.model.CustomResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/client")
public class ClientController {

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private LoadBalancerClient balancerClient;


    @GetMapping(value = "/blog", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse> addBlog(@RequestHeader(name = "Authorization") String token)
            throws Exception {
        CustomResponse response = new CustomResponse();
        System.out.println("token: " + token);
        ServiceInstance srvIns = balancerClient.choose("BLOG-SERVICES");

        String selectedHost = srvIns.getUri().toString();

        String url = selectedHost + "/blog/home";
        System.out.println("url "+url);


        RestTemplate rtemp = new RestTemplate();
        ResponseEntity<String> clientResp = rtemp.getForEntity(url, String.class, String.class);
        logger.info("coming from discovery:: {}" ,clientResp.getBody());
        response.setResponse(clientResp.getBody());
        response.setResponseStatus(EnumConstants.ResponseStatus.SUCCESS.getValue());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}





