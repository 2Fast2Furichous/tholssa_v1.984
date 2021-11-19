package devops.view.Elements;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class SceneGestures {

	private static final double MAX_SCALE = 10.0d;
	private static final double MIN_SCALE = .1d;

	private DragContext sceneDragContext = new DragContext();

	private PannableCanvas canvas;

	public SceneGestures(PannableCanvas canvas) {
		this.canvas = canvas;
	}

	public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
		return this.onMousePressedEventHandler;
	}

	public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
		return this.onMouseDraggedEventHandler;
	}

	public EventHandler<ScrollEvent> getOnScrollEventHandler() {
		return this.onScrollEventHandler;
	}

	private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

		public void handle(MouseEvent event) {

			// right mouse button => panning
			if (!event.isSecondaryButtonDown()) {
				return;
			}
				

			SceneGestures.this.sceneDragContext.mouseAnchorX = event.getSceneX();
			SceneGestures.this.sceneDragContext.mouseAnchorY = event.getSceneY();

			SceneGestures.this.sceneDragContext.translateAnchorX = SceneGestures.this.canvas.getTranslateX();
			SceneGestures.this.sceneDragContext.translateAnchorY = SceneGestures.this.canvas.getTranslateY();
		}

	};

	private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {

			// right mouse button => panning
			if (!event.isSecondaryButtonDown()) {
				return;
			}

			SceneGestures.this.canvas.setTranslateX(
					SceneGestures.this.sceneDragContext.translateAnchorX + event.getSceneX() - SceneGestures.this.sceneDragContext.mouseAnchorX);
			SceneGestures.this.canvas.setTranslateY(
					SceneGestures.this.sceneDragContext.translateAnchorY + event.getSceneY() - SceneGestures.this.sceneDragContext.mouseAnchorY);

			event.consume();
		}
	};

	/**
	 * Mouse wheel handler: zoom to pivot point
	 */
	private EventHandler<ScrollEvent> onScrollEventHandler = new EventHandler<ScrollEvent>() {

		@Override
		public void handle(ScrollEvent event) {

			double delta = 1.2;

			double scale = SceneGestures.this.canvas.getScale();
			double oldScale = scale;

			if (event.getDeltaY() < 0) {
				scale /= delta;
			} else {
				scale *= delta;
			}
				

			scale = clamp(scale, MIN_SCALE, MAX_SCALE);

			double focus = (scale / oldScale) - 1;

			double dx = (event.getSceneX()
					- (SceneGestures.this.canvas.getBoundsInParent().getWidth() / 2 + SceneGestures.this.canvas.getBoundsInParent().getMinX()));
			double dy = (event.getSceneY()
					- (SceneGestures.this.canvas.getBoundsInParent().getHeight() / 2 + SceneGestures.this.canvas.getBoundsInParent().getMinY()));

			SceneGestures.this.canvas.setScale(scale);

			// note: pivot value must be untransformed, i. e. without scaling
			SceneGestures.this.canvas.setPivot(focus * dx, focus * dy);
			event.consume();
		}

	};

	private static double clamp(double value, double min, double max) {

		if (Double.compare(value, min) < 0) {
			return min;
		}
		

		if (Double.compare(value, max) > 0) {
			return max;
		}
			

		return value;
	}
}