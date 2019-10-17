/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdcollection;

import com.mycompany.dvdcollection.controller.DVDController;
import com.mycompany.dvdcollection.dao.DVDDao;
import com.mycompany.dvdcollection.dao.DVDDaoFileImpl;
import com.mycompany.dvdcollection.ui.DVDUserIO;
import com.mycompany.dvdcollection.ui.DVDUserIOConsoleImpl;
import com.mycompany.dvdcollection.ui.DVDView;

/**
 *
 * @author mac
 */
public class App {
    
        public static void main(String[] args) {
            
        DVDUserIO myIo = new DVDUserIOConsoleImpl();
        DVDView myView = new DVDView(myIo);
        DVDDao myDao = new DVDDaoFileImpl( DVDDaoFileImpl.DVD_FILE );
        DVDController controller = new DVDController(myDao, myView);
        controller.run();
        
    }
}
