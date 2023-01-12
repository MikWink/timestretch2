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
            List<Time>times = new ArrayList<>();
            while (res.next()) {
                times.add(new Time(res.getInt(1), res.getTimestamp(2), res.getTimestamp(3)));
            }
            res = stmt.executeQuery("SELECT * FROM timestretch.task WHERE Task_ID =" + id );
            res.next();
            Task task = new Task(res.getInt(1), res.getString("Titel"), res.getString("Beschreibung"));
            res.close();
            stmt.close();
            con.close();

            ctx.render("taskInfo.html", Map.of("time", times, "task", task));
        });

        app.get("/taskinfo/2", ctx -> {

            ctx.render("taskInfo.html");
        });

        app.post("/createTask", ctx -> {
            String titel = ctx.formParam("titel");
            String beschreibung = ctx.formParam("beschreibung");
            Connection con = DriverManager.getConnection("jdbc:postgresql://pgdb.wannaco.de:4711/","g1", "k&eiQ_duwfyaPl");
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT nextval('idCounter')");
            res.next();
            int counter = (res.getInt(1))+14;
            stmt.execute("INSERT INTO timestretch.task VALUES ("+ counter + ", '" + titel + "', '"+ beschreibung + "');");
            ctx.redirect("/");
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
