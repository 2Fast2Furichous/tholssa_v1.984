package devops.view.Elements;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PannableCanvas extends Pane {

	private DoubleProperty scale = new SimpleDoubleProperty(1.0);

	public PannableCanvas() {
		setPrefSize(1000, 1000);
		scaleXProperty().bind(this.scale);
		scaleYProperty().bind(this.scale);

	}


	public void addGrid() {

		double width = getBoundsInLocal().getWidth();
		double height = getBoundsInLocal().getHeight();


		Canvas grid = new Canvas(width, height);

		grid.setMouseTransparent(true);

		GraphicsContext gc = grid.getGraphicsContext2D();

		gc.setStroke(Color.GRAY);
		gc.setLineWidth(1);


		double offset = 100;
		for (double i = offset; i < width; i += offset) {

			gc.strokeLine(i, 0, i, height);
			gc.strokeLine(0, i, width, i);
		}

		getChildren().add(grid);

		grid.toBack();
	}

	public double getScale() {
		return this.scale.get();
	}

	/**
	 * Set x/y scale
	 * 
	 * @param scale canvas zoom scale
	 */
	public void setScale(double scale) {
		this.scale.set(scale);
	}

	/**
	 * Set x/y pivot points
	 * 
	 * @param x x position
	 * @param y y position
	 */
	public void setPivot(double x, double y) {
		setTranslateX(getTranslateX() - x);
		setTranslateY(getTranslateY() - y);
	}
}