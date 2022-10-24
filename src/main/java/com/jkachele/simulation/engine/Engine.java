/******************************************
 *Project-------OpenGL-Compute_Shaders
 *File----------Engine.java
 *Author--------Justin Kachele
 *Date----------10/18/2022
 *License-------MIT License
 ******************************************/
package com.jkachele.simulation.engine;

import com.jkachele.simulation.util.Color;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;

public class Engine implements Runnable{

    private Color backgroundColor;

    private final Thread GAME_LOOP_THREAD;

    public Engine(int width, int height, String title, Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        GAME_LOOP_THREAD = new Thread(this, "GAME_LOOP_THREAD");
        Window.init(width, height, title, backgroundColor);
    }

    public void start() {
        GAME_LOOP_THREAD.start();
    }

    @Override
    public void run() {
        try {
            Window.start();
            gameLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void gameLoop() {
        // Set the clear color
        glClearColor(backgroundColor.getRed(), backgroundColor.getGreen(),
                backgroundColor.getBlue(), backgroundColor.getAlpha());

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(Window.glfwWindow) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(Window.glfwWindow); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
}
