/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster;

import com.mycompany.flooringmaster.controller.FloorController;
import com.mycompany.flooringmaster.service.InvalidProductException;
import com.mycompany.flooringmaster.service.InvalidStateException;
import com.mycompany.flooringmaster.service.ServiceException;
import com.mycompany.flooringmaster.ui.FloorView;
import com.mycompany.flooringmaster.ui.FloorViewEnglish;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mac
 */
public class App {
    
    public static void main(String[] args) throws InvalidStateException, InvalidProductException, ServiceException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        FloorController controller = ctx.getBean("controller", FloorController.class);
        
        controller.run();
  
    }
    

    
    
}
