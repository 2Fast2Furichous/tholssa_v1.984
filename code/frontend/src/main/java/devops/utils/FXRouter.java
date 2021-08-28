package devops.utils;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.util.Duration;
import javafx.animation.FadeTransition;

/**
 * The login window controller.
 *
 * @author Marco Trombino (Modified by Furichous Jones IV)
 * @version Fall 2021
 */
public final class FXRouter {
	private static final String WINDOW_TITLE = "";
	private static final Double WINDOW_WIDTH = 800.0;
	private static final Double WINDOW_HEIGHT = 600.0;
	private static final Double FADE_ANIMATION_DURATION = 800.0;

	private static Object mainRef;
	private static Stage window;

	private static String windowTitle;
	private static Double windowWidth;
	private static Double windowHeight;

	private static String animationType;
	private static Double animationDuration;

	private static AbstractMap<String, RouteScene> routes = new HashMap<>();
	private static RouteScene currentRoute;

	private static class RouteScene {
		private String scenePath;
		private String windowTitle;
		private double sceneWidth;
		private double sceneHeight;
		private Object data;

		private RouteScene(String scenePath) {
			this(scenePath, getWindowTitle(), getWindowWidth(), getWindowHeight());
		}

		private RouteScene(String scenePath, String windowTitle) {
			this(scenePath, windowTitle, getWindowWidth(), getWindowHeight());
		}

		private RouteScene(String scenePath, double sceneWidth, double sceneHeight) {
			this(scenePath, getWindowTitle(), sceneWidth, sceneHeight);
		}

		/**
		 * Route scene constructor
		 * 
		 * @param scenePath:   .FXML scene file
		 * @param windowTitle: Scene (Stage) title
		 * @param sceneWidth:  Scene Width
		 * @param sceneHeight: Scene Height
		 */
		private RouteScene(String scenePath, String windowTitle, double sceneWidth, double sceneHeight) {
			this.scenePath = scenePath;
			this.windowTitle = windowTitle;
			this.sceneWidth = sceneWidth;
			this.sceneHeight = sceneHeight;
		}

		private static String getWindowTitle() {
			return FXRouter.windowTitle != null ? FXRouter.windowTitle : WINDOW_TITLE;
		}

		private static double getWindowWidth() {
			return FXRouter.windowWidth != null ? FXRouter.windowWidth : WINDOW_WIDTH;
		}

		private static double getWindowHeight() {
			return FXRouter.windowHeight != null ? FXRouter.windowHeight : WINDOW_HEIGHT;
		}
	}

	private FXRouter() {
	}

	/**
	 * FXRouter binder with Application Stage and main package
	 * 
	 * @param ref:       Main Class reference
	 * @param win:       Application Stage
	 */
	public static void initialize(Object ref, Stage win) {
		checkInstances(ref, win);
	}

	/**
	 * FXRouter binder with Application Stage, main package, and window title
	 * 
	 * @param ref:       Main Class reference
	 * @param win:       Application Stage
	 * @param winTitle:  Application Stage title
	 */
	public static void initialize(Object ref, Stage win, String winTitle) {
		checkInstances(ref, win);
		FXRouter.windowTitle = winTitle;
	}

	/**
	 * FXRouter binder with Application Stage, main package, and window dimensions
	 * 
	 * @param ref:       Main Class reference
	 * @param win:       Application Stage
	 * @param winWidth:  Application Stage width
	 * @param winHeight: Application Stage height
	 */
	public static void initialize(Object ref, Stage win, double winWidth, double winHeight) {
		checkInstances(ref, win);
		FXRouter.windowWidth = winWidth;
		FXRouter.windowHeight = winHeight;
	}

	/**
	 * FXRouter binder with Application Stage, main package, window title, and window
	 * dimensions
	 * 
	 * @param ref:       Main Class reference
	 * @param win:       Application Stage
	 * @param winTitle:  Application Stage title
	 * @param winWidth:  Application Stage width
	 * @param winHeight: Application Stage height
	 */
	public static void initialize(Object ref, Stage win, String winTitle, double winWidth, double winHeight) {
		checkInstances(ref, win);
		FXRouter.windowTitle = winTitle;
		FXRouter.windowWidth = winWidth;
		FXRouter.windowHeight = winHeight;
	}

	/**
	 * set FXRouter references only if they are not set yet
	 * 
	 * @param ref: Main Class reference
	 * @param win: Application Stage
	 */
	private static void checkInstances(Object ref, Stage win) {
		if (FXRouter.mainRef == null) {
			FXRouter.mainRef = ref;
		}
		if (FXRouter.window == null) {
			FXRouter.window = win;
		}
	}

	/**
	 * Define a FXRouter route
	 * 
	 * @param routeLabel:  Route label identifier
	 * @param scenePath:   .FXML scene file
	 */
	public static void register(String routeLabel, String scenePath) {
		RouteScene routeScene = new RouteScene(scenePath);
		routes.put(routeLabel, routeScene);
	}

	/**
	 * Define a FXRouter route
	 * 
	 * @param routeLabel: Route label identifier
	 * @param scenePath:  .FXML scene file
	 * @param winTitle:   Application Stage title
	 */
	public static void register(String routeLabel, String scenePath, String winTitle) {
		RouteScene routeScene = new RouteScene(scenePath, winTitle);
		routes.put(routeLabel, routeScene);
	}

	/**
	 * Define a FXRouter route
	 * 
	 * @param routeLabel:  Route label identifier
	 * @param scenePath:   .FXML scene file
	 * @param sceneWidth:  Scene Width
	 * @param sceneHeight: Scene Height
	 */
	public static void register(String routeLabel, String scenePath, double sceneWidth, double sceneHeight) {
		RouteScene routeScene = new RouteScene(scenePath, sceneWidth, sceneHeight);
		routes.put(routeLabel, routeScene);
	}

	/**
	 * Define a FXRouter route
	 * 
	 * @param routeLabel:  Route label identifier
	 * @param scenePath:   .FXML scene file
	 * @param winTitle:    Scene (Stage) title
	 * @param sceneWidth:  Scene Width
	 * @param sceneHeight: Scene Height
	 */
	public static void register(String routeLabel, String scenePath, String winTitle, double sceneWidth,
			double sceneHeight) {
		RouteScene routeScene = new RouteScene(scenePath, winTitle, sceneWidth, sceneHeight);
		routes.put(routeLabel, routeScene);
	}

	/**
	 * Switch between FXRouter route and show corresponding scenes
	 * 
	 * @param routeLabel: Route label identifier
	 * @throws Exception: throw FXMLLoader exception if file is not loaded correctly
	 */
	public static void show(String routeLabel) throws IOException {
		// get corresponding route
		RouteScene route = routes.get(routeLabel);
		loadRoute(route);
	}

	/**
	 * Switch between FXRouter route and show corresponding scenes
	 * 
	 * @param routeLabel: Route label identifier
	 * @param data:       Data passed to route
	 * @throws Exception: throw FXMLLoader exception if file is not loaded correctly
	 */
	public static void show(String routeLabel, Object data) throws IOException {
		// get corresponding route
		RouteScene route = routes.get(routeLabel);
		// set route data
		route.data = data;
		loadRoute(route);
	}

	private static void loadRoute(RouteScene route) throws IOException {
		FXRouter.currentRoute = route;

		Parent resource = FXMLLoader.load(FXRouter.mainRef.getClass().getResource(route.scenePath));

		FXRouter.window.setTitle(route.windowTitle);
		FXRouter.window.setScene(new Scene(resource, route.sceneWidth, route.sceneHeight));
		FXRouter.window.show();

		playTransition(resource);
	}

	/**
	 * set FXRouter switching animation
	 * 
	 * @param anType: Animation type
	 */
	public static void setAnimationType(String anType) {
		animationType = anType;
	}

	/**
	 * set FXRouter switching animation and duration
	 * 
	 * @param anType:     Animation type
	 * @param anDuration: Animation duration
	 */
	public static void setAnimationType(String anType, double anDuration) {
		animationType = anType;
		animationDuration = anDuration;
	}

	/**
	 * Animate routes switching based on animation type
	 * 
	 * @param resource: .FXML scene file to animate
	 */
	private static void playTransition(Parent node) {
		String anType = animationType != null ? animationType.toLowerCase() : "";
		switch (anType) {
			case "fade":
				Double fd = animationDuration != null ? animationDuration : FADE_ANIMATION_DURATION;
				FadeTransition ftCurrent = new FadeTransition(Duration.millis(fd), node);
				ftCurrent.setFromValue(0.0);
				ftCurrent.setToValue(1.0);
				ftCurrent.play();
				break;
			default:
				break;
		}
	}

	/**
	 * Get current route data
	 */
	public static Object getData() {
		return currentRoute.data;
	}
}