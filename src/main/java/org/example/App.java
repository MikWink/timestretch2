package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.sql.*;
import java.util.*;

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


        app.get("/taskinfo/{id}", ctx -> {
            String id = ctx.pathParam("id");
            Connection con = DriverManager.getConnection("jdbc:postgresql://pgdb.wannaco.de:4711/","g1", "k&eiQ_duwfyaPl");
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM timestretch.time WHERE Task_ID =" + id);
            res.next();
            Time time = new Time(res.getInt("Task_ID"), res.getTimestamp("Timestamp_start"), res.getTimestamp("Timestamp_end"));
            res = stmt.executeQuery("SELECT * FROM timestretch.task WHERE Task_ID =" + id );
            res.next();
            Task task = new Task(res.getInt(1), res.getString("Titel"), res.getString("Beschreibung"));
            res.close();
            stmt.close();
            con.close();

            ctx.render("taskInfo.html", Map.of("time", time, "task", task));
        });

        app.get("/taskinfo/2", ctx -> {

            ctx.render("taskInfo.html");
        });

        app.get("/newtask", ctx -> {
            ctx.render("newTask.html");
        });

        app.get("/", ctx -> {
            Connection con = DriverManager.getConnection("jdbc:postgresql://pgdb.wannaco.de:4711/","g1", "k&eiQ_duwfyaPl");
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("select * from timestretch.task");
            List<Task>tasks = new ArrayList<>();
            while (res.next()) {
                tasks.add(new Task(res.getInt(1), res.getString("Titel"), res.getString("Beschreibung")));
            }
            res.close();
            stmt.close();
            con.close();
            ctx.render("tasks.html", Map.of("Task", tasks));
        });
    }
}
