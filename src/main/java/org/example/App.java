package org.example;

import io.javalin.Javalin;

/**
 * Timestretch
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        var app = Javalin.create().start(7070);

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
