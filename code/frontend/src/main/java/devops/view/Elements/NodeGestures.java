package devops.view.Elements;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * Listeners for making the nodes draggable via left mouse button. Considers if
 * parent is zoomed.
 */
public class NodeGestures {

	private DragContext nodeDragContext = new DragContext();

	private PannableCanvas canvas;

	public NodeGestures(PannableCanvas canvas) {
		this.canvas = canvas;
	}

	public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
		return this.onMousePressedEventHandler;
	}

	public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
		return this.onMouseDraggedEventHandler;
	}

	private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

		public void handle(MouseEvent event) {

			// left mouse button => dragging
			if (!event.isPrimaryButtonDown()) {
				return;
			}
			
			NodeGestures.this.nodeDragContext.mouseAnchorX = event.getSceneX();
			NodeGestures.this.nodeDragContext.mouseAnchorY = event.getSceneY();

			Node node = (Node) event.getSource();

			NodeGestures.this.nodeDragContext.translateAnchorX = node.getTranslateX();
			NodeGestures.this.nodeDragContext.translateAnchorY = node.getTranslateY();

		}

	};

	private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {

			// left mouse button => dragging
			if (!event.isPrimaryButtonDown()) {
				return;
			}

			double scale = NodeGestures.this.canvas.getScale();

			Node node = (Node) event.getSource();

			node.setTranslateX(NodeGestures.this.nodeDragContext.translateAnchorX
					+ ((event.getSceneX() - NodeGestures.this.nodeDragContext.mouseAnchorX) / scale));
			node.setTranslateY(NodeGestures.this.nodeDragContext.translateAnchorY
					+ ((event.getSceneY() - NodeGestures.this.nodeDragContext.mouseAnchorY) / scale));

			event.consume();

		}
	};
}
