package org.example.zk;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;

public class CController extends SelectorComposer<Component>  {

    private static final long serialVersionUID = 8939455466026130684L;
    
    /*
    @Wire
    Popup popupPanel;
    
    @Wire
    Button buttonTask01;
    
    @Wire
    Window windowNotification;

    @Wire
    Popup popupNotification;
 
    /*
    @Wire
    Window winClient;
    
    @Listen( "onClick=#btnClick" )
    public void onClick01( Event event ) {
        
        if ( winClient.inHighlighted() == false )
            winClient.doHighlighted();
        else
            winClient.doEmbedded();
        
    }

    @Listen( "onClick=#btnClick02" )
    public void onClick02( Event event ) {
        
        winClient.setMaximizable(true);  //doHighlighted();
        winClient.setSizable( true );
        
    }
    */
    
    public void doAfterCompose(Component comp) throws Exception {
        
        super.doAfterCompose( comp );
        
        //windowNotification.setWidgetOverride( "_doMouseMove" , "function (evt) { return false; }" );
        
        //popupNotification.setWidgetOverride( "_doFloatUp", "function (ctl) { if (!this.isVisible()) return; var wgt = ctl.origin; for (var floatFound; wgt; wgt = wgt.parent) { if (wgt == this) { if (!floatFound) this.setTopmost(); return; } if (wgt == this.parent && wgt.ignoreDescendantFloatUp_(this)) return; floatFound = floatFound || wgt.isFloating_(); } /*this.close({sendOnOpen: true});*/ }" );
        
    };
    
    @Listen( "onClick=#buttonTask01" )
    public void buttonTask01onClick( Event event ) {
        
        //popupPanel.open( buttonTask01, "before_end" );
        
        //panelNotification.setVisible(true);
        
        //windowNotification.doOverlapped();

        //popupNotification.open( buttonTask01, "before_end" );
        //pop
        
    }
    
}
