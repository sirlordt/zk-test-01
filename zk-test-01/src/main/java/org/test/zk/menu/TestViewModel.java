package org.test.zk.menu;

import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;

public class TestViewModel  {

  @AfterCompose
  public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view){

      Selectors.wireEventListeners( view, this );
      
  }
   
  @Listen( "onTest=#btnOpen" )
  public void onTest(Event evt){
  
      Messagebox.show( "1234" );
  
  }
     
}
