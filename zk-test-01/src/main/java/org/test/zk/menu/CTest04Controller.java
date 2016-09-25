package org.test.zk.menu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Messagebox;

public class CTest04Controller extends SelectorComposer<Component> {

    private static final long serialVersionUID = 1693927652775743536L;

    @Override
    public void doAfterCompose( Component comp ) {

        try {
            
            super.doAfterCompose( comp );
            
        } 
        catch ( Exception ex ) {

            ex.printStackTrace();
            
        }

    }

    @Listen( "onTest=#btnOpen" )
    public void onTest( Event event ){

        Messagebox.show( "1234" );

    }    

    @Listen( "onValidate=#winClient" )
    public void onValidate( Event event ){

        Messagebox.show( "1234" );

    }
    
    @Listen( "onClickDiv=#divTest02" )
    public void onClickdivTest02( Event event ){

        Messagebox.show( "divTest02" );

    }    
    
}
