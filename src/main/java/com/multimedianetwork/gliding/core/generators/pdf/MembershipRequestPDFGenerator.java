package com.multimedianetwork.gliding.core.generators.pdf;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.multimedianetwork.gliding.model.MembershipRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Cami
 */
public class MembershipRequestPDFGenerator {

    private static final String FILES_FOLDER = "/template/request/";
    private static final String LOCAL_TEMPLATE_PATH_FULL = "cererefull.pdf";
    private static final String LOCAL_TEMPLATE_PATH_TEMP = "cereretemp.pdf";

    private static final MembershipRequestPDFGenerator instance
            = new MembershipRequestPDFGenerator();

    private MembershipRequestPDFGenerator() {
    }

    public static MembershipRequestPDFGenerator getInstance() {
        return instance;
    }

    public synchronized void generateMembershipRequestPDF(MembershipRequest membershipRequest) {
        try {

            HttpServletRequest request
                    = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

            String templateFilename = request.getRealPath(FILES_FOLDER + LOCAL_TEMPLATE_PATH_FULL);
            if (membershipRequest.isTemporaryMembership()) {
                templateFilename = request.getRealPath(FILES_FOLDER + LOCAL_TEMPLATE_PATH_TEMP);
            }

            String newfilename = request.getRealPath(FILES_FOLDER + "req" + membershipRequest.getId() + ".pdf");

            //Reads in the pdf Template
            PdfReader reader = new PdfReader(templateFilename);

            PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(newfilename));
            AcroFields form = stamp.getAcroFields();

            //set the field values in the pdf form
            form.setField("lastName", membershipRequest.getLastName());
            form.setField("firstName", membershipRequest.getFirstName());
            form.setField("citizenship", membershipRequest.getCitizenship());
            form.setField("icSerie", membershipRequest.getIcSerie());
            form.setField("icNumber", membershipRequest.getIcNumber());
            form.setField("pasportNumber", membershipRequest.getPasportNumber());
            form.setField("cnp", membershipRequest.getCnp());
            form.setField("addressNumber", membershipRequest.getAddressNumber());
            form.setField("addressCity", membershipRequest.getAddressCity());
            form.setField("addressStreet", membershipRequest.getAddressStreet());
            form.setField("addressFlat", membershipRequest.getAddressFlat());
            form.setField("addressSc", membershipRequest.getAddressSc());
            form.setField("addressFloor", membershipRequest.getAddressFloor());
            form.setField("addressApartment", membershipRequest.getAddressApartment());
            form.setField("addressCode", membershipRequest.getAddressCode());
            form.setField("addressCounty", membershipRequest.getAddressCounty());
            form.setField("addressCountry", membershipRequest.getAddressCountry());
            form.setField("telephone", membershipRequest.getTelephone());
            form.setField("email", membershipRequest.getEmail());
            form.setField("birthDateDay", String.valueOf(membershipRequest.getBirthDateDay()));
            form.setField("birthDateMonth", String.valueOf(membershipRequest.getBirthDateMonth()));
            form.setField("birthDateYear", String.valueOf(membershipRequest.getBirthDateYear()));
            form.setField("birthPlaceCity", membershipRequest.getBirthPlaceCity());
            form.setField("birthPlaceCounty", membershipRequest.getBirthPlaceCounty());
            form.setField("birthPlaceCountry", membershipRequest.getBirthPlaceCountry());

            form.setField("licenseNumber", membershipRequest.getLicenseNumber());
            if (membershipRequest.getExperienceNrHours() != null) {
                form.setField("experienceNrHours", String.valueOf(membershipRequest.getExperienceNrHours()));
            }
            if (membershipRequest.getExperienceNrTakeoffs() != null) {
                form.setField("experienceNrTakeoffs", String.valueOf(membershipRequest.getExperienceNrTakeoffs()));
            }
            if (membershipRequest.getExperienceNrKm() != null) {
                form.setField("experienceNrKm", String.valueOf(membershipRequest.getExperienceNrKm()));
            }

            if (membershipRequest.isBAcrobaticFlight()) {
                form.setField("BAcrobaticFlight", "On");
            }
            if (membershipRequest.isBControlFlight()) {
                form.setField("BControlFlight", "On");
            }
            if (membershipRequest.isBGliderLicense()) {
                form.setField("BGliderLicense", "On");
            }
            if (membershipRequest.isBInCloudsFlight()) {
                form.setField("BInCloudsFlight", "On");
            }
            if (membershipRequest.isBInstructor()) {
                form.setField("BInstructor", "On");
            }
            if (membershipRequest.isBMotorgliderLicense()) {
                form.setField("BMotorgliderLicense", "On");
            }
            if (membershipRequest.isBNightFlight()) {
                form.setField("BNightFlight", "On");
            }
            if (membershipRequest.isBTestPilot()) {
                form.setField("BTestPilot", "On");
            }
            if (membershipRequest.isBTowingPlane()) {
                form.setField("BTowingPlane", "On");
            }
            if (membershipRequest.isBTowingSelf()) {
                form.setField("BTowingSelf", "On");
            }
            if (membershipRequest.isBTowingWinch()) {
                form.setField("BTowingWinch", "On");
            }

            stamp.setFormFlattening(true);
            stamp.close();
            reader.close();

        } catch (IOException ex) {
            Logger.getLogger(MembershipRequestPDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(MembershipRequestPDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
