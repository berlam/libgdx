
package com.badlogic.gdx.controllers.teavm.plugin;

import org.teavm.vm.spi.TeaVMHost;
import org.teavm.vm.spi.TeaVMPlugin;

public class LibGDXControllersTeaVMPlugin implements TeaVMPlugin {
	@Override
	public void install (TeaVMHost host) {
		host.add(new OverlayTransformer());
	}
}
