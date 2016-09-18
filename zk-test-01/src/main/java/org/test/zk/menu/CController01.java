package org.test.zk.menu;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

public class CController01 extends SelectorComposer<Component> {

	private static final long serialVersionUID = 438981069561208609L;
	
	@Wire
	Window windowMenuTest01;
	
	//Menubar menubarTest01;

	boolean bMenuOpen = false;
	
	
	
	@Wire
	Menupopup menupopupNew;

	@Wire
	Menupopup menupopupUndo;

	@Wire
	Menupopup menupopupCut;

	@Wire
	Menupopup menupopupCopy;

	@Wire
	Menupopup menupopupPaste;
	
	@Wire
	Popup popupTest01;
	
	@Wire
	Button btnDoModal01; 

	@Wire
	Button btnDoModal02; 
	
	@Wire( "#windowMenuTest01 #menuNew" )
	Menu menuNew;

	@Wire( "#windowMenuTest01 #menuUndo" )
	Menu menuUndo;

	@Wire( "#windowMenuTest01 #menuCut" )
	Menu menuCut;

	@Wire( "#windowMenuTest01 #menuCopy" )
	Menu menuCopy;

	@Wire( "#windowMenuTest01 #menuPaste" )
	Menu menuPaste;

	@Wire( "#windowMenuTest01 #buttonNew" )
	Toolbarbutton buttonNew;

	@Wire( "#windowMenuTest01 #buttonUndo" )
	Toolbarbutton buttonUndo;
	
	@Wire( "#windowMenuTest01 #buttonCut" )
	Toolbarbutton buttonCut;

	@Wire( "#windowMenuTest01 #buttonCopy" )
	Toolbarbutton buttonCopy;

	@Wire( "#windowMenuTest01 #buttonPaste" )
	Toolbarbutton buttonPaste;
	
	@Wire( "#windowMenuTest01 #divTest01" ) 
	Div divTest01;
	
	@Override
	public void doAfterCompose( Component comp ) throws Exception {

		super.doAfterCompose(comp);

		CustomMenupopup customMenuPopup = new CustomMenupopup();
		
		customMenuPopup.setWidgetOverride( "doKeyDown_" , "function (evt) { this.$supers( 'doKeyDown_', arguments); }" );
		
		customMenuPopup.setSclass( "customMenuPopup" );
		
		divTest01.setParent( customMenuPopup );
		
		//popupTest01.setParent( customMenuPopup );
		
		//btnDoModal02.addForward( orgEvent, target, targetEvent );
		
		customMenuPopup.setParent( menuNew );

		menuNew.setWidgetOverride( "doKeyDown_" , "function (evt) { this.$supers( 'doKeyDown_', arguments); }" );
		
		//menupopupNew.setParent( menuNew );

		menupopupUndo.setParent( menuUndo );

		menupopupCut.setParent( menuCut );

		menupopupCopy.setParent( menuCopy );

		menupopupPaste.setParent( menuPaste );

		//zk.log('hello!'); 
		//menuNew.setWidgetOverride( "doClick_" , "function (evt) { if (this.menupopup) { if (this.isTopmost()) this.getMenubar()._lastTarget = this; _doClick(this, evt); } else { this._showContentHandler(); } evt.stop(); }" );
		
		/*menubarTest01 = new Menubar();
		
		menubarTest01.setParent( windowMenuTest01 );
		
		Menu menuRoot01 = new Menu();
		
		menuRoot01.setId( "menuRoot01" );

		menuRoot01.setLabel("01");
		
		menuRoot01.setParent( menubarTest01 );
		
		menupopupTest01.setParent( menuRoot01 );
		
		CustomMenu menuRoot02 = new CustomMenu();
		
		//menuRoot02._popup = (Menupopup) popupTest01;
		
		menuRoot02.setId( "menuRoot02" );
		
		menuRoot02.setLabel( "04" );
			
		menuRoot02.setParent( menubarTest01 );

		menupopupTest02.setParent( menuRoot02 );*/
		
		

		//popupTest01.setParent( menuRoot02 );
		
		//popupTest01.setParent( customMenu );
		
		/*customMenu.setParent( menubarTest01 );*/
				
	}
	
    // 
	//@Listen( "onClick = #windowMenuTest01 #menuNew; onClick = #windowMenuTest01 #menuUndo; onClick = #windowMenuTest01 #menuCut; onClick = #windowMenuTest01 #menuCopy; onClick = #windowMenuTest01 #menuPaste" )
	public void onClickMenu(  Event event ) {
		
		System.out.println( "onClick " + event.getTarget().getId() );

	}	
		
	@Listen( "onMouseOver = #windowMenuTest01 #divTest01; onMouseOver = #windowMenuTest01 #btnDoModal01; onMouseOver = #windowMenuTest01 #buttonNew; onMouseOver=#windowMenuTest01 #buttonUndo; onMouseOver=#windowMenuTest01 #buttonCut; onMouseOver=#windowMenuTest01 #buttonCopy; onMouseOver=#windowMenuTest01 #buttonPaste" )
	public void onMouseOverButtonToolbar(  Event event ) {
		
		System.out.println( "onMouseOver " + event.getTarget().getId() );
		
		if ( bMenuOpen ) {

			Events.postEvent( "onClick", event.getTarget(), null );

		}
		
	}
	
	@Listen( "onMouseOut = #windowMenuTest01 #divTest01; onMouseOut = #windowMenuTest01 #btnDoModal01; onMouseOut = #windowMenuTest01 #buttonNew; onMouseOut=#windowMenuTest01 #buttonUndo; onMouseOut=#windowMenuTest01 #buttonCut; onMouseOut=#windowMenuTest01 #buttonCopy; onMouseOut=#windowMenuTest01 #buttonPaste" )
	public void onMouseOutButtonToolbar( Event event ) {
		
		System.out.println( "onMouseOut " + event.getTarget().getId() );
		
	}

	@Listen( "onClick = #windowMenuTest01 #divTest01; onClick = #windowMenuTest01 #btnDoModal01; onClick = #windowMenuTest01 #buttonNew; onClick=#windowMenuTest01 #buttonUndo; onClick=#windowMenuTest01 #buttonCut; onClick=#windowMenuTest01 #buttonCopy; onClick=#windowMenuTest01 #buttonPaste" )
	public void onClickButtonToolbar(  Event event ) {
		
		System.out.println( "onClick " + event.getTarget().getId() );
		
		menupopupNew.close();
		menupopupUndo.close();
		menupopupCut.close();
		menupopupCopy.close();
		menupopupPaste.close();
				
		if ( event.getTarget().getId().equalsIgnoreCase( "btnDoModal01" ) ) {
			
			windowMenuTest01.doModal();
			
		}
		else if ( event.getTarget().getId().equalsIgnoreCase( "buttonNew" ) ) {

			menupopupNew.open( buttonNew, "after_end" );
			
		}
		else if ( event.getTarget().getId().equalsIgnoreCase( "buttonUndo" ) ) {

			menupopupUndo.open( buttonUndo, "after_end" );
			
		}
		else if ( event.getTarget().getId().equalsIgnoreCase( "buttonCut" ) ) {
			
			menupopupCut.open( buttonCut, "after_end" );
			
		}
		else if ( event.getTarget().getId().equalsIgnoreCase( "buttonCopy" ) ) {
			
			menupopupCopy.open( buttonCopy, "after_end" );
			
		}
		else if ( event.getTarget().getId().equalsIgnoreCase( "buttonPaste" ) ) {
			
			menupopupPaste.open( buttonPaste, "after_end" );
			
		}
		
		bMenuOpen = true;
		
	}
	
	@Listen( "onOpen=#menupopupNew; onOpen=#menupopupUndo; onOpen=#menupopupCut; onOpen=#menupopupCopy; onOpen=#menupopupPaste" )
	public void onOpenMenuPopup( Event event ) {
		
		System.out.println( "onOpen " + event.getTarget().getId() );
		
		//popupTest01.open( btnDoModal, "after_end" );
		
	}
	
	
	@Listen( "onClick = div > ul > #divTest01 > #btnDoModal02" )
	public void onClickbtnDoModal01( Event event ) {
		
		windowMenuTest01.doModal();
		
	}
	
	
	
}
