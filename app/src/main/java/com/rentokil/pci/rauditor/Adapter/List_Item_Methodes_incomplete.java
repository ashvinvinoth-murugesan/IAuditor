package com.rentokil.pci.rauditor.Adapter;

/**
 * Created by Murugan Kuppusamy on 04-Oct-17.
 * */
public class List_Item_Methodes_incomplete {
    private String audi_name;
    private String audi_date;
    private String cus_name;
    private String doc_no;
    private String getcount_disp;
    private String audit_tech;
    private String audit_branch;
    private int id;


    public List_Item_Methodes_incomplete(int id, String audi_name, String cus_name, String audi_date, String doc_no,
                                         String getcount_disp,String audit_tech
            ,String audit_branch) {
        this.audi_name = audi_name;
        this.cus_name = cus_name;
        this.audi_date = audi_date;
        this.doc_no = doc_no;
        this.audit_branch = audit_branch;
        this.audit_tech = audit_tech;
        this.getcount_disp = getcount_disp;
        this.id = id;
    }

    public String getCus_name() {
        return this.cus_name;
    }

    public int getId() {
        return this.id;
    }

    public String getAudi_name() {
        return this.audi_name;
    }
    public String getDoc_no() {
        return this.doc_no;
    }
    public String getAudi_date() {
        return this.audi_date;
    }
    public String getcount_disp() {
        return this.getcount_disp;
    }
    public String getAudit_tech() {
        return this.audit_tech;
    } public String getAudit_branch() {
        return this.audit_branch;
    }
}
