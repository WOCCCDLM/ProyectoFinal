package edu.eci.arsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import edu.eci.arsw.model.ClientMessage;
import edu.eci.arsw.model.ServerMessage;

@RestController
@RequestMapping("/rest")
public class MyRestController {
    
    
    @Autowired 
    private SimpMessagingTemplate template;  
    
    @RequestMapping(value = "/msg",method = RequestMethod.POST)        
    public ResponseEntity<?> addMessage(@RequestBody ClientMessage p) {  
        System.out.println(p.getMessage()+" "+"metodo post rest");
        template.convertAndSend("/topic/messages",new ServerMessage(p.getMessage()));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/check",method = RequestMethod.GET)        
    public String check() {
        return "REST API OK";                
    }
    
    
}