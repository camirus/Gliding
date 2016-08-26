/*
 * 
 * 
 */
package com.multimedianetwork.gliding.servlets;

import com.multimedianetwork.gliding.managers.FlightTrackManager;
import com.multimedianetwork.gliding.model.FlightTrack;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "DrawMapServlet", urlPatterns = {"/DrawMap"})
public class DrawMapServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String fName = "/resources/map.html";

            ServletContext cntxt = this.getServletContext();
            InputStream ins = cntxt.getResourceAsStream(fName);
            if (ins != null) {
                InputStreamReader isr = new InputStreamReader(ins);
                BufferedReader reader = new BufferedReader(isr);
                int n = 0;
                String line = "";
                while ((line = reader.readLine()) != null) {
                    if (line.contains("'%points%'")) {
                        line = line.replace("'%points%'", "");

                        String gliderName = request.getParameter("glider");
                        String start = request.getParameter("start");
                        String end = request.getParameter("end");

                        if (gliderName != null && start != null & end != null) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                Date startDate = sdf.parse(start);
                                Date endDate = sdf.parse(end);

                                List<FlightTrack> flightTracks = new FlightTrackManager().getListBetweenTimeStamps(
                                        gliderName, startDate, endDate);

                                int index = 0;
                                while (index < flightTracks.size()) {
                                    FlightTrack flightTrack = flightTracks.get(index);
                                    StringBuilder pointsSB = new StringBuilder();
                                    pointsSB.append("{lat: ").append(new BigDecimal(flightTrack.getLatitude()).setScale(3, RoundingMode.HALF_UP))
                                            .append(", lng: ").append(new BigDecimal(flightTrack.getLongitude()).setScale(3, RoundingMode.HALF_UP))
                                            .append("}");
//                                        pointsSB.append("[ ").append(new BigDecimal(flightTrack.getLongitude()).setScale(3, RoundingMode.HALF_UP))
//                                            .append(", ").append(new BigDecimal(flightTrack.getLatitude()).setScale(3, RoundingMode.HALF_UP))
//                                            .append("]");
                                    if (index != (flightTracks.size() - 1)) {
                                        pointsSB.append(",");
                                    }
                                    out.println(pointsSB.toString());
                                    index++;
                                }

                            } catch (ParseException ex) {
                                Logger.getLogger(DrawMapServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }
                    out.println(line);
                }
            }

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
