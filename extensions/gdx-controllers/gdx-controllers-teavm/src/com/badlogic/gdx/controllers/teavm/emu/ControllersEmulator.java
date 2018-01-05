
package com.badlogic.gdx.controllers.teavm.emu;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.ControllerManager;
import com.badlogic.gdx.controllers.teavm.TeaVMControllers;
import com.badlogic.gdx.utils.ObjectMap;

/** @author Alexey Andreev */
public class ControllersEmulator {
	private static final ObjectMap<Application, ControllerManager> managers = new ObjectMap<Application, ControllerManager>();

	@SuppressWarnings("unused")
	static private void initialize () {
		managers.put(Gdx.app, new TeaVMControllers());
	}
}
