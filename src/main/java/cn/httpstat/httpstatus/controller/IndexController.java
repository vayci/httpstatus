package cn.httpstat.httpstatus.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author vayci
 */
@Controller
public class IndexController {

    private static final String INDEX = "index.html";

    @GetMapping("/")
    public String index(){
        return INDEX;
    }

    @GetMapping(value = "/{statusCode:\\d+}",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> status(
            @PathVariable Integer statusCode, @RequestParam(required = false) Integer sleep) throws InterruptedException {
        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        if(sleep!=null){
            Thread.sleep(sleep);
        }
        return ResponseEntity.status(httpStatus).body(httpStatus.getReasonPhrase());
    }

}
