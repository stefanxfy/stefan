package com.stefan.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
   // @RequestMapping(value="/hello",method=RequestMethod.GET)
    @GetMapping("/hello")
    //@PostMapping("/hello")
    public String say(@RequestParam(value ="id",required = false,defaultValue = "100") Integer myId){
        return "id="+myId;
    }
}
