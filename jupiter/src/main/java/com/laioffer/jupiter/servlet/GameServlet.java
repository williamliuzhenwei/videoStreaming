package com.laioffer.jupiter.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.jupiter.entity.Game;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;

@WebServlet(name = "GameServlet", value = "/game")
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//        JSONObject game = new JSONObject();
//        game.put("name", "World of Warcraft");
//        game.put("developer", "Blizzard Entertainment");
//        game.put("release_time", "Feb 11, 2005");
//        game.put("website", "https://www.worldofwarcraft.com");
//        game.put("price", 49.99);
//        response.getWriter().print(game);

        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        Game.Builder builder = new Game.Builder();
        builder.setName("World of Warcraft");
        builder.setDeveloper("Blizzard Entertainment");
        builder.setReleaseTime("Feb 11, 2005");
        builder.setWebsite("https://www.worldofwarcraft.com");
        builder.setPrice(49.99);


        Game game = builder.build();
        response.getWriter().print(mapper.writeValueAsString(game));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read game information from request body
        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
        String name = jsonRequest.getString("name");
        String developer = jsonRequest.getString("developer");
        String releaseTime = jsonRequest.getString("release_time");
        String website = jsonRequest.getString("website");
        float price = jsonRequest.getFloat("price");

        // Print game information to IDE console
        System.out.println("Name is: " + name);
        System.out.println("Developer is: " + developer);
        System.out.println("Release time is: " + releaseTime);
        System.out.println("Website is: " + website);
        System.out.println("Price is: " + price);
        // Return status = ok as response body to the client
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);

    }
}
