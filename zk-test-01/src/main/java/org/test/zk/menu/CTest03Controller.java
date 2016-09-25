package org.test.zk.menu;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class CTest03Controller extends SelectorComposer<Component> {

    private static final long serialVersionUID = -5377330487733845027L;

    @Listen( "onClick = tbody > tr > td" )
    public void onLickElement( Event event ) {
        
        System.out.println( event.getTarget().getId() );
        
    }
    
}
