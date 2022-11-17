package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

/**
 * Timestretch
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Javalin app = Javalin.create().start(7070);

        app._conf.addStaticFiles("/static", Location.CLASSPATH);

        app.get("/", ctx -> {
            ctx.render("tasks.html");
        });

        app.get("/taskinfo", ctx -> {
            ctx.render("taskInfo.html");
        });

        app.get("/newtask", ctx -> {
            ctx.render("newTask.html");
        });
    }
}
