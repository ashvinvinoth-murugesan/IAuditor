package com.rentokil.pci.rauditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ashvin vinoth on 10-09-2018.
 */

public class ExpandableListDataPump  {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> sitereport = new ArrayList<>();
        sitereport.add("1.Title Page");
        sitereport.add("2.Customer Service Information");
        sitereport.add("3.Observation");
        sitereport.add("4.Summary");


        List<String> pestcomplaint = new ArrayList<String>();
        pestcomplaint.add("1.Title Page");
        pestcomplaint.add("2.Customer Service Information");
        pestcomplaint.add("3.Observation");
        pestcomplaint.add("4.Summary");


        List<String> aibaudit = new ArrayList<String>();
        aibaudit.add("1.Title Page");
        aibaudit.add("2.Customer Service Information");
        aibaudit.add("3.Customer Pest Management Program(CPMP)File");
        aibaudit.add("4.Facility Information");
        aibaudit.add("5.External Observations");
        aibaudit.add("6.Internal Observations");
        aibaudit.add("7.Summary");

        List<String> qsr_pest = new ArrayList<String>();
        qsr_pest.add("1.Title Page");
        qsr_pest.add("2.Customer Service Information");
        qsr_pest.add("3.Customer Pest Management Program(CPMP)File");
        qsr_pest.add("4.Facility Information");
        qsr_pest.add("5.Observations");
        qsr_pest.add("6.Summary");

        List<String> ipm_audit = new ArrayList<String>();
        ipm_audit.add("1.Title Page");
        ipm_audit.add("2.Customer Service Information");
        ipm_audit.add("3.Customer Pest Management Program(CPMP)File");
        ipm_audit.add("4.Facility Information");
        ipm_audit.add("5.Observations");
        ipm_audit.add("6.Summary");

        expandableListDetail.put("Commercial Rentokil PCI - Site Inspection Report", sitereport);
        expandableListDetail.put("Commercial Rentokil PCI - Pest Management Complaint Audit Report", pestcomplaint);
        expandableListDetail.put("Commercial Rentokil PCI - AIB Audit", aibaudit);
        expandableListDetail.put("Commercial Rentokil PCI - QSR-Pest Management Audit", qsr_pest);
        expandableListDetail.put("Commercial Rentokil PCI - IPM Audit", ipm_audit);
        return expandableListDetail;
    }
}
