
package com.badlogic.gdx.controllers.teavm.support;

/** @author Alexey Andreev */
public interface GamepadSupportListener {
	public void onGamepadConnected (int index);

	public void onGamepadDisconnected (int index);

	public void onGamepadUpdated (int index);
}
