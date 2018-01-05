
package com.badlogic.gdx.backends.teavm;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.Clipboard;
import org.teavm.jso.JSBody;
import org.teavm.jso.browser.TimerHandler;
import org.teavm.jso.browser.Window;
import org.teavm.jso.dom.html.HTMLCanvasElement;

import java.util.ArrayList;
import java.util.List;

public class TeaVMApplication implements Application {
	private Window window = Window.current();
	private ApplicationListener listener;
	private TeaVMApplicationConfig config;
	private HTMLCanvasElement canvas;
	private TeaVMGraphics graphics;
	private TeaVMFiles files;
	private TeaVMAudio audio;
	private TeaVMInput input;
	private int logLevel = LOG_ERROR;
	private List<LifecycleListener> lifecycleListeners = new ArrayList<LifecycleListener>();

	public TeaVMApplication (ApplicationListener listener, TeaVMApplicationConfig config) {
		this.listener = listener;
		this.config = config;
	}

	@JSBody(params = "message", script = "console.log(\"TeaVM: \" + message);")
	native static public void consoleLog (String message);

	public void start () {
		TeaVMFileLoader.loadFiles(new TeaVMFilePreloadListener() {
			@Override
			public void error () {
			}

			@Override
			public void complete () {
				startGdx();
			}
		});
	}

	private void startGdx () {
		canvas = config.getCanvas();
		graphics = new TeaVMGraphics(canvas, config);
		files = new TeaVMFiles();
		audio = new TeaVMAudio();
		input = new TeaVMInput(canvas);
		Gdx.app = this;
		Gdx.graphics = graphics;
		Gdx.gl = graphics.getGL20();
		Gdx.gl20 = graphics.getGL20();
		Gdx.files = files;
		Gdx.audio = audio;
		Gdx.input = input;
		listener.create();
		listener.resize(canvas.getWidth(), canvas.getHeight());
		delayedStep();
	}

	private void delayedStep () {
		window.setTimeout(new TimerHandler() {
			@Override
			public void onTimer () {
				TeaVMApplication.this.step();
			}
		}, 10);
	}

	private void step () {
		graphics.update();
		graphics.frameId++;
		listener.resize(canvas.getWidth(), canvas.getHeight());
		listener.render();
		input.reset();
		delayedStep();
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return listener;
	}

	@Override
	public Graphics getGraphics () {
		return graphics;
	}

	@Override
	public Audio getAudio () {
		return audio;
	}

	@Override
	public Input getInput () {
		return input;
	}

	@Override
	public Files getFiles () {
		return files;
	}

	@Override
	public Net getNet () {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void log (String tag, String message) {
		if (logLevel > LOG_INFO) {
			consoleLog("Info " + tag + ": " + message);
		}
	}

	@Override
	public void log (String tag, String message, Throwable exception) {
		if (logLevel > LOG_INFO) {
			consoleLog("Info " + tag + ": " + message);
		}
	}

	@Override
	public void error (String tag, String message) {
		if (logLevel > LOG_ERROR) {
			consoleLog("Error " + tag + ": " + message);
		}
	}

	@Override
	public void error (String tag, String message, Throwable exception) {
		if (logLevel > LOG_ERROR) {
			consoleLog("Error " + tag + ": " + message);
		}
	}

	@Override
	public void debug (String tag, String message) {
		if (logLevel >= LOG_DEBUG) {
			consoleLog("Debug " + tag + ": " + message);
		}
	}

	@Override
	public void debug (String tag, String message, Throwable exception) {
		if (logLevel > LOG_DEBUG) {
			consoleLog("Debug " + tag + ": " + message);
		}
	}

	@Override
	public int getLogLevel () {
		return logLevel;
	}

	@Override
	public void setLogLevel (int logLevel) {
		this.logLevel = logLevel;
	}

	@Override
	public ApplicationLogger getApplicationLogger () {
		// TODO mro Matthias Rosenstock
		throw new UnsupportedOperationException();
	}

	@Override
	public void setApplicationLogger (ApplicationLogger applicationLogger) {

	}

	@Override
	public ApplicationType getType () {
		return ApplicationType.WebGL;
	}

	@Override
	public int getVersion () {
		return 0;
	}

	@Override
	public long getJavaHeap () {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNativeHeap () {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Preferences getPreferences (String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clipboard getClipboard () {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postRunnable (Runnable runnable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit () {
	}

	@Override
	public void addLifecycleListener (LifecycleListener listener) {
		lifecycleListeners.add(listener);
	}

	@Override
	public void removeLifecycleListener (LifecycleListener listener) {
		lifecycleListeners.remove(listener);
	}
}
