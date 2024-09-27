package pkgSlUtils;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class slWindowManager {
    private static slWindowManager my_window = null;  // Singleton instance
    private long glfw_win;  // Window handle

    private slWindowManager() {}  // Private constructor for Singleton

    // Singleton access method
    public static slWindowManager get() {
        if (my_window == null) {
            my_window = new slWindowManager();
        }
        return my_window;
    }

    // Initializes the window with the specified dimensions and title
    public long initGLFWWindow(int width, int height, String title) {
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        glfw_win = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glfw_win == NULL) {
            throw new RuntimeException("Failed to create GLFW window");
        }
        glfwMakeContextCurrent(glfw_win);
        return glfw_win;
    }

    // Returns the current window size
    public int[] getCurrentWindowSize() {
        int[] size = new int[2];
        glfwGetWindowSize(glfw_win, size, size);
        return size;
    }

    // Destroys the current GLFW window
    public void destroyGlfwWindow() {
        glfwDestroyWindow(glfw_win);
    }

    // Checks if the window should close
    public boolean isGlfwWindowClosed() {
        return glfwWindowShouldClose(glfw_win);
    }

    // Swaps the OpenGL buffers for the window
    public void swapBuffers() {
        glfwSwapBuffers(glfw_win);
    }

    // Updates the OpenGL context to this window
    public void updateContextToThis() {
        glfwMakeContextCurrent(glfw_win);
    }
}
