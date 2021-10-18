package devops.view.Elements;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class PannableCanvas extends Pane {

	DoubleProperty myScale = new SimpleDoubleProperty(1.0);

	public PannableCanvas() {
		setPrefSize(1000, 1000);
		scaleXProperty().bind(myScale);
		scaleYProperty().bind(myScale);

	}


	public void addGrid() {

		double w = getBoundsInLocal().getWidth();
		double h = getBoundsInLocal().getHeight();


		Canvas grid = new Canvas(w, h);

		grid.setMouseTransparent(true);

		GraphicsContext gc = grid.getGraphicsContext2D();

		gc.setStroke(Color.GRAY);
		gc.setLineWidth(1);


		double offset = 100;
		for (double i = offset; i < w; i += offset) {

			gc.strokeLine(i, 0, i, h);
			gc.strokeLine(0, i, w, i);
		}

		getChildren().add(grid);

		grid.toBack();
	}

	public double getScale() {
		return myScale.get();
	}

	/**
	 * Set x/y scale
	 * 
	 * @param myScale
	 */
	public void setScale(double scale) {
		myScale.set(scale);
	}

	/**
	 * Set x/y pivot points
	 * 
	 * @param x
	 * @param y
	 */
	public void setPivot(double x, double y) {
		setTranslateX(getTranslateX() - x);
		setTranslateY(getTranslateY() - y);
	}
}