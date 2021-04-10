package site.ncov.www.ncov.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王思源
 * @version 0.0.0
 */

@RestController
@RequestMapping("/ping")
public class PingPangController {

    @RequestMapping
    public String PingPang() {
        return "pang";
    }
}
