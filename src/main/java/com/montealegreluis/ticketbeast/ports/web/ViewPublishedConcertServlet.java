package com.montealegreluis.ticketbeast.ports.web;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.UnknownConcert;
import com.montealegreluis.ticketbeast.ports.hibernate.ConcertsRepository;
import com.montealegreluis.ticketbeast.ports.hibernate.DatabaseSession;
import com.montealegreluis.ticketbeast.store.ViewPublishedConcert;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewPublishedConcertServlet", urlPatterns = "/concerts/view")
public class ViewPublishedConcertServlet extends HttpServlet {
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        ViewPublishedConcert action = new ViewPublishedConcert(new ConcertsRepository(
            DatabaseSession.openSession()
        ));
        int concertId = Integer.parseInt(request.getParameter("id"));

        try {
            Concert concert = action.view(concertId);
            request.setAttribute("concert", concert);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (UnknownConcert unknownConcert) {
            response.setStatus(404);
        }
    }
}
