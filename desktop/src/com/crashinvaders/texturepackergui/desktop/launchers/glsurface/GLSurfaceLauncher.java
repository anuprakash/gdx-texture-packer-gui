package com.crashinvaders.texturepackergui.desktop.launchers.glsurface;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.crashinvaders.texturepackergui.App;
import com.crashinvaders.texturepackergui.AppConstants;
import com.crashinvaders.texturepackergui.AppParams;
import com.crashinvaders.texturepackergui.desktop.Arguments;
import com.github.czyzby.autumn.fcs.scanner.DesktopClassScanner;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class GLSurfaceLauncher {
	public static void main(final String[] args) {
		Arguments arguments = new Arguments();
		try {
			CmdLineParser parser = new CmdLineParser(arguments);
			parser.parseArgument(args);
		} catch (CmdLineException e) {
			System.out.println("Error: " + e.getLocalizedMessage());
			return;
		}
		start(arguments);
	}

	public static void start(Arguments arguments) {
		final LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
		configuration.title = "LibGDX Texture Packer GUI";
		configuration.addIcon("icon128.png", Files.FileType.Classpath);
		configuration.addIcon("icon32.png", Files.FileType.Classpath);
		configuration.addIcon("icon16.png", Files.FileType.Classpath);
		configuration.preferencesDirectory = AppConstants.EXTERNAL_DIR;
		configuration.width = 1024;
		configuration.height = 600;
		configuration.allowSoftwareMode = arguments.softOpenGL;

		AppParams appParams = new AppParams();
		appParams.startupProject = arguments.project;

		App app = new App(new DesktopClassScanner(), appParams);
		new LwjglApplication(new WindowParamsPersistingApplicationWrapper(app, configuration), configuration);
	}
}