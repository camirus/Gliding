/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multimedianetwork.gliding.controller.member;

import com.multimedianetwork.gliding.controller.NewDayAndFlightController;
import com.multimedianetwork.gliding.managers.FlightManager;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Cami
 */
@ViewScoped
@ManagedBean
public class NewDayAndFlightMemberController extends NewDayAndFlightController {

    @Override
    protected void retrieveFlights(Date date) {
        flights = new FlightManager().getListInBooklet(date);
    }

}
