package org.test.zk.menu;

import java.io.IOException;
import java.util.Map;

import org.zkoss.lang.Objects;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.out.AuInvoke;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.impl.LabelImageElement;

public class CustomMenu extends Menu {

	private static final long serialVersionUID = -3589168206331253219L;

	public Menupopup _popup;
	private String _content = "";
	private boolean _disabled = false;

	static {
		addClientEvent(CustomMenu.class, Events.ON_CLICK, CE_IMPORTANT | CE_DUPLICATE_IGNORE);
		addClientEvent(CustomMenu.class, Events.ON_CHANGE, CE_IMPORTANT | CE_DUPLICATE_IGNORE);
	}

	public CustomMenu() {
	}

	public CustomMenu(String label) {
		super(label);
	}

	public CustomMenu(String label, String src) {
		super(label, src);
	}

	/** Returns whether this is an top-level menu, i.e., not owning
	 * by another {@link Menupopup}.
	 */
	public boolean isTopmost() {
		return !(getParent() instanceof Menupopup);
	}

	/** Returns the {@link Menupopup} it owns, or null if not available.
	 */
	public Menupopup getMenupopup() {
		return _popup;
	}

	/** Returns the embedded content (i.e., HTML tags) that is
	 * shown as part of the description.
	 *
	 * <p>It is useful to show the description in more versatile way.
	 *
	 * <p>Default: empty ("").
	 *
	 * @since 5.0.0
	 */
	public String getContent() {
		return _content;
	}

	/** Sets the embedded content (i.e., HTML tags) that is
	 * shown as part of the description.
	 *
	 * <p>It is useful to show the description in more versatile way.
	 * 
	 * <p>There is a way to create Colorbox automatically by using
	 * #color=#RRGGBB, usage example <code>setContent("#color=#FFFFFF")</code>
	 *
	 * @since 5.0.0
	 */
	public void setContent(String content) {
		if (content == null)
			content = "";
		if (!Objects.equals(_content, content)) {
			_content = content;
			smartUpdate("content", content);
		}
	}

	/** Returns whether it is disabled.
	 * <p>Default: false.
	 * @since 8.0.3
	 */
	public boolean isDisabled() {
		return _disabled;
	}

	/** Sets whether it is disabled.
	 * @since 8.0.3
	 */
	public void setDisabled(boolean disabled) {
		if (disabled != _disabled) {
			_disabled = disabled;
			smartUpdate("disabled", _disabled);
		}
	}

	/**
	 * Opens the menupopup that belongs to the menu.
	 * <p>
	 * Note that this function is only applied when it is topmost menu, i.e. the parent of the menu is {@link Menubar}
	 * @since 6.0.0
	 */
	public void open() {
		if (this.getParent() instanceof Menubar)
			response("menu", new AuInvoke(this, "open", (Object) null));
	}

	//-- Component --//
	public String getZclass() {
		return _zclass == null ? "z-menu" : _zclass;
	}

	protected void renderProperties(ContentRenderer renderer) throws IOException {
		super.renderProperties(renderer);
		render(renderer, "content", _content);
		render(renderer, "disabled", _disabled);
	}

	public void beforeParentChanged(Component parent) {
		if (parent != null && !(parent instanceof Menubar) && !(parent instanceof Menupopup))
			throw new UiException("Unsupported parent for menu: " + parent);
		super.beforeParentChanged(parent);
	}

	public void beforeChildAdded(Component child, Component refChild) {
		/*if (child instanceof Menupopup) {
			if (_popup != null && _popup != child)
				throw new UiException("Only one menupopup is allowed: " + this);
		} else {
			throw new UiException("Unsupported child for menu: " + child);
		}
		super.beforeChildAdded(child, refChild);*/
	}

	public void onChildRemoved(Component child) {
		_popup = null;
		super.onChildRemoved(child);
	}

	public boolean insertBefore(Component child, Component refChild) {
		if (child instanceof Menupopup) {
			if (super.insertBefore(child, refChild)) {
				_popup = (Menupopup) child;
				return true;
			}
		} else {
			return super.insertBefore(child, refChild);
			//impossible but make it more extensible
		}
		return false;
	}

	//Cloneable//
	public Object clone() {
		final CustomMenu clone = (CustomMenu) super.clone();
		if (clone._popup != null)
			clone.afterUnmarshal();
		return clone;
	}

	private void afterUnmarshal() {
		_popup = (Menupopup) getFirstChild();
	}

	//Serializable//
	private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
		s.defaultReadObject();

		if (!getChildren().isEmpty())
			afterUnmarshal();
	}

	//--ComponentCtrl--//
	/** Processes an AU request.
	 *
	 * <p>Default: in addition to what are handled by {@link LabelImageElement#service},
	 * it also handles onClick.
	 * @since 5.0.0
	 */
	public void service(AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
		if (cmd.equals(Events.ON_CLICK)) {
			Events.postEvent(MouseEvent.getMouseEvent(request));
		} else if (cmd.equals(Events.ON_CHANGE)) {
			final Map<String, Object> data = request.getData();
			if (getContent().indexOf("#color") == 0) {
				disableClientUpdate(true);
				try {
					setContent("#color=" + (String) data.get("color"));
				} finally {
					disableClientUpdate(false);
				}
				Events.postEvent(InputEvent.getInputEvent(request, _content));
			}
		} else
			super.service(request, everError);
}	
	
}
