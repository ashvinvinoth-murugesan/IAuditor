package com.rentokil.pci.rauditor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ashvin vinoth on 28-09-2018.
 */

public class ButtonAdapter extends AppCompatDialogFragment{

    protected static String[] Product_Line_Str;

    protected ArrayList<CharSequence> selectedProducts2 = new ArrayList<CharSequence>();
    protected static String[] Product_Line_Str_1 = new String[]{
            "GSS",
            "IAM",
            "PPS",
            "IFM",
            "IMM",
            "ILM",
            "ISM",
            "Pro-guard",
            "SIM",
            "Specify"};

    protected static String[] Product_Line_Str_2 = new String[]{
            "Cockroach",
            "Ants",
            "Rodents",
            "Flies",
            "House Gecko",
            "Spider",
            "Mosquitoes",
            "Termites",
            "Stored Product Pests",
            "Pest Birds",
            "Others Specify",};

    protected static String[] Product_Line_Str_3 = new String[]{
            "Cockroach",
            "Ants",
            "Rodents",
            "Flies",
            "House Gecko",
            "Spider",
            "Mosquitoes",
            "Termites",
            "Stored Product Pests",
            "Pest Birds",
            "Others Specify",};

    protected static String[] Product_Line_Str_4 = new String[]{
            "Monthly",
            "Bi-Monthly",
            "Six-Monthly",
            "Yearly",
            "As per Requirement",};

    protected static String[] Product_Line_Str_5 = new String[]{
            "FHE",
            "Hospitality",
            "Healthcare",
            "Pharma",
            "Corporate & ITES",
            "Warehouse",
            "Retailer",
            "Educational Institute",
            "Others",};

    protected static String[] Product_Line_Str_6 = new String[]{
            "Manufacturing",
            "Processing",
            "Warehouse",
            "ITES",
            "Corporate",
            "Hospitality",
            "Healthcare",
            "Retailer",
            "Educational Institute",
            "Others",};

    protected static String[] Product_Line_Str_7= new String[]{
            "Dish Washer",
            "Guest Reception Aread",
            "Serivce Counter",
            "Billing Counter",
            "Dry Store",
            "Ice Machine",
            "Manager Desk",
            "Vending Machine",
            "Freezer",
            "Refrigerator",
            "Delivery Bag Rack",
            "Proofer & Dough Making Machine",
            "Dough Roller",
            "Hand Washing",
            "Retarder Machine",
            "Make Line",
            "Oven",
            "Pizza packing Table",
            "Gas Stove",
            "Gas Cylinder Stores",
            "Bar Counter",
            "Guest Dinning area",
            "External Area",
            "Mezzanine Floor",
            "False Ceiling",
            "Any Other Specify",
            "Operation Area",
            "Main Kitchen",
            "Double Door between Kitchen & Dinning",
            "Area in-front of Staff Door",
            "Area in-front of Main Door",};






    protected static String[] Product_Line_Str_11 = new String[]{
            "Live / Dead Rodent",
            "Urine smell",
            "Foot print",
            "Gnawing mark",
            "Damaged Products",
            "Fresh Droppings",
            "Old Droppings",
            "Grease Marks",
            "Not Applicable",
            "Others",};



    String status_check;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        ContextThemeWrapper ctw = new ContextThemeWrapper(getActivity(), R.style.AppDialog);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_LIGHT);

   //    Toast.makeText(getContext(),""+getArguments().getString("status"),Toast.LENGTH_SHORT).show();
        status_check=getArguments().getString("status");

        if(status_check.equals("111")){
            Product_Line_Str=Product_Line_Str_1;
        }
        if(status_check.equals("222")){

            Product_Line_Str=Product_Line_Str_2;
        }
        if(status_check.equals("666")){

            Product_Line_Str=Product_Line_Str_3;
        }
        if(status_check.equals("777")){

            Product_Line_Str=Product_Line_Str_1;
        }
        if(status_check.equals("888")){

            Product_Line_Str=Product_Line_Str_3;
        }

        if(status_check.equals("999")){

            Product_Line_Str=Product_Line_Str_3;
        }


        if(status_check.equals("102")){

            Product_Line_Str=Product_Line_Str_5;
        }

        if(status_check.equals("103")){

            Product_Line_Str=Product_Line_Str_1;
        }
        if(status_check.equals("104")){

            Product_Line_Str=Product_Line_Str_2;
        }
        if(status_check.equals("108")){

            Product_Line_Str=Product_Line_Str_6;
        }
        if(status_check.equals("110")){

            Product_Line_Str=Product_Line_Str_3;
        }
        if(status_check.equals("113")){

            Product_Line_Str=Product_Line_Str_5;
        }
        if(status_check.equals("114")){

            Product_Line_Str=Product_Line_Str_1;
        }
        if(status_check.equals("115")){

            Product_Line_Str=Product_Line_Str_2;
        }

        if(status_check.equals("117")){

            Product_Line_Str=Product_Line_Str_11;
        }

        if(status_check.equals("1616")){

            Product_Line_Str=Product_Line_Str_3;
        }

        if(status_check.equals("1717")){

            Product_Line_Str=Product_Line_Str_7;
        }

        if(status_check.equals("1818")){

            Product_Line_Str=Product_Line_Str_3;
        }
        if(status_check.equals("1919")){

            Product_Line_Str=Product_Line_Str_1;
        }
        if(status_check.equals("2020")){

            Product_Line_Str=Product_Line_Str_3;
        }
        if(status_check.equals("2121")){

            Product_Line_Str=Product_Line_Str_1;
        }
  if(status_check.equals("2222")){

      Log.e("RRRR","e5");


            Product_Line_Str=Product_Line_Str_3;
        }
  if(status_check.equals("2223")){

            Product_Line_Str=Product_Line_Str_3;
        }
  if(status_check.equals("2224")){

            Product_Line_Str=Product_Line_Str_3;
        }
  if(status_check.equals("2225")){

      Log.e("RRRR","q5");

            Product_Line_Str=Product_Line_Str_3;
        }
  if(status_check.equals("2226")){

            Product_Line_Str=Product_Line_Str_3;
        }
  if(status_check.equals("2227")){

            Product_Line_Str=Product_Line_Str_3;
        }
  if(status_check.equals("2228")){

            Product_Line_Str=Product_Line_Str_3;
        }
       if(status_check.equals("2229")){

            Product_Line_Str=Product_Line_Str_3;
        }
        if(status_check.equals("2230")){

            Product_Line_Str=Product_Line_Str_3;
        }
        if(status_check.equals("2231")){

            Product_Line_Str=Product_Line_Str_3;
        }
        if(status_check.equals("2232")){

            Product_Line_Str=Product_Line_Str_3;
        }
        if(status_check.equals("2233")){

            Product_Line_Str=Product_Line_Str_3;
        }
        if(status_check.equals("2235")){

            Product_Line_Str=Product_Line_Str_3;
        }








        boolean[] checkedProducts_Lines2 = new boolean[Product_Line_Str.length];

        int count = Product_Line_Str.length;

        for (int i = 0; i < count; i++)

            checkedProducts_Lines2[i] = selectedProducts2.contains(Product_Line_Str[i]);
        final DialogInterface.OnMultiChoiceClickListener coloursDialogListener2 = new DialogInterface.OnMultiChoiceClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if (isChecked) {

                    selectedProducts2.add(Product_Line_Str[which]);



                } else

                    selectedProducts2.remove(Product_Line_Str[which]);

                onChangeselectedProducts2();

            }

        };
//        ContextThemeWrapper ctw = new ContextThemeWrapper(getContext(), AlertDialog.THEME_HOLO_LIGHT);
//        Dialog alertDialog = new Dialog(getActivity());
//        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        alertDialog.show();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_LIGHT);

        builder.setMultiChoiceItems(Product_Line_Str, checkedProducts_Lines2, coloursDialogListener2);

//        AlertDialog dialog = builder.create();
//
//
//        dialog.show();
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // ok button
                        dialog.dismiss();
                    }
                });
        builder .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // cancel button
                        dialog.dismiss();
                    }
                });

        return builder.create();


    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    protected void onChangeselectedProducts2() {
        StringBuilder stringBuilder = new StringBuilder();

        for (CharSequence product : selectedProducts2)

            if (selectedProducts2.size() == 1) {
                stringBuilder.append(product);

            } else {
                stringBuilder.append(product + ",");
            }

        String line_quoted = stringBuilder.toString();
        if (line_quoted.endsWith(",")) {
            int a = line_quoted.length();

            Log.e("EEE", "a count = " + a);
            String Lion = removeCharAt(line_quoted.substring(0, a - 1), 0);

                if(line_quoted.substring(0,a-1).startsWith(",")) {
                    if (status_check.equalsIgnoreCase("111")) {
                        SIR_Cus_2.sircustxt1.setText(Lion);

                }else if(status_check.equalsIgnoreCase("222")){
                        SIR_Cus_2.sircustxt2.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("666")){
                        SIR_Obser_3.sirobsdisplay.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("777")){
                        PMCA_Cus_Info_2.pestcusdisp2.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("888")){
                        PMCA_Cus_Info_2.pestcusdisp3.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("999")){
                        PMCA_Obser_3.pestobsdisp.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("102")){
                        IPM_Title_1.ipm_title_txt_disply2.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("103")){
                        IPM_Cus_Info_2.ipm_cusinfo_disp1.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("104")){
                        IPM_Cus_Info_2.ipm_cusinfo_disp2.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("108")){
                        IPM_Facility_4.disp1.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("110")){
                        IPM_Obser_5.ipm_obser_txt_disp1.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("113")){
                        QSR_Title_1.qsr_title_txt_disply2.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("113")){
                        QSR_Title_1.qsr_title_txt_disply2.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("114")){
                        QSR_Cus_Info_2.qsr_cusinfo_disp1.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("115")){
                        QSR_Cus_Info_2.qsr_cusinfo_disp2.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("117")){
                        QSR_Facility_4.dis2.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("1616")){
                        QSR_Obser_5.qsrobsdisp.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("1717")){
                        QSR_Obser_5.qsr_obser_disp1.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("1818")){
                        QSR_Obser_5.qsr_obser_disp2.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("1919")){
                        AIB_Cus_Info_2.disp1.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2020")){
                        AIB_Cus_Info_2.disp2.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2121")){
                        AIB_Cus_Pest_3.disp1.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2222")){
                        Log.e("RRRR","e2");
                        AIB_Ext_Obser_5_1.aib_ex1_disp1.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2223")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp2.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2224")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp3.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2225")){
                        Log.e("RRRR","q2"+Lion);
                        AIB_Ext_Obser_5_1.aib_ex1_disp4.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2226")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp5.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2227")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp6.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2228")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp7.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2229")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp8.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2230")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp9.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2231")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp10.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2232")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp11.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2233")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp12.setText(Lion);
                    }
                    else if(status_check.equalsIgnoreCase("2235")){
                        AIB_Interl_Obser_6.sirobsdisplay.setText(Lion);
                    }





            } else {

                Log.e("AAEE", "enter2");
                    String Lionvalue = line_quoted.substring(0, a - 1);
                    if (status_check.equalsIgnoreCase("111")) {

                        SIR_Cus_2.sircustxt1.setText(Lionvalue);
                    }else if(status_check.equalsIgnoreCase("222")){
                        SIR_Cus_2.sircustxt2.setText(Lionvalue);
                    }

                    else if(status_check.equalsIgnoreCase("666")){
                        SIR_Obser_3.sirobsdisplay.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("777")){
                        PMCA_Cus_Info_2.pestcusdisp2.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("888")){
                        PMCA_Cus_Info_2.pestcusdisp3.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("999")){
                        PMCA_Obser_3.pestobsdisp.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("102")){
                        IPM_Title_1.ipm_title_txt_disply2.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("103")){
                        IPM_Cus_Info_2.ipm_cusinfo_disp1.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("104")){
                        IPM_Cus_Info_2.ipm_cusinfo_disp2.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("108")){
                        IPM_Facility_4.disp1.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("110")){
                        IPM_Obser_5.ipm_obser_txt_disp1.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("113")){
                        QSR_Title_1.qsr_title_txt_disply2.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("113")){
                        QSR_Title_1.qsr_title_txt_disply2.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("114")){
                        QSR_Cus_Info_2.qsr_cusinfo_disp1.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("115")){
                        QSR_Cus_Info_2.qsr_cusinfo_disp2.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("117")){
                        QSR_Facility_4.dis2.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("1616")){
                        QSR_Obser_5.qsrobsdisp.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("1717")){
                        QSR_Obser_5.qsr_obser_disp1.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("1818")){
                        QSR_Obser_5.qsr_obser_disp2.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("1919")){
                        AIB_Cus_Info_2.disp1.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2020")){
                        AIB_Cus_Info_2.disp2.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2121")){
                        AIB_Cus_Pest_3.disp1.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2222")){
                        Log.e("RRRR","e1");
                        AIB_Ext_Obser_5_1.aib_ex1_disp1.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2223")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp2.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2224")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp3.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2225")){

                        Log.e("RRRR","q1");
                        AIB_Ext_Obser_5_1.aib_ex1_disp4.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2226")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp5.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2227")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp6.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2228")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp7.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2229")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp8.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2230")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp9.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2231")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp10.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2232")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp11.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2233")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp12.setText(Lionvalue);
                    }
                    else if(status_check.equalsIgnoreCase("2235")){
                        AIB_Interl_Obser_6.sirobsdisplay.setText(Lionvalue);
                    }

                }

        } else {

                Log.e("AAEE","enter3");

                if(line_quoted.startsWith(",")){
                    line_quoted=removeCharAt(line_quoted,0);
                    if (status_check.equalsIgnoreCase("111")) {
                        SIR_Cus_2.sircustxt1.setText(line_quoted);
                    }else if(status_check.equalsIgnoreCase("222")){
                        SIR_Cus_2.sircustxt2.setText(line_quoted);
                    }

                    else if(status_check.equalsIgnoreCase("666")){
                        SIR_Obser_3.sirobsdisplay.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("777")){
                        PMCA_Cus_Info_2.pestcusdisp2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("888")){
                        PMCA_Cus_Info_2.pestcusdisp3.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("999")){
                        PMCA_Obser_3.pestobsdisp.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("102")){
                        IPM_Title_1.ipm_title_txt_disply2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("103")){
                        IPM_Cus_Info_2.ipm_cusinfo_disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("104")){
                        IPM_Cus_Info_2.ipm_cusinfo_disp2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("108")){
                        IPM_Facility_4.disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("110")){
                        IPM_Obser_5.ipm_obser_txt_disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("113")){
                        QSR_Title_1.qsr_title_txt_disply2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("113")){
                        QSR_Title_1.qsr_title_txt_disply2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("114")){
                        QSR_Cus_Info_2.qsr_cusinfo_disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("115")){
                        QSR_Cus_Info_2.qsr_cusinfo_disp2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("117")){
                        QSR_Facility_4.dis2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("1616")){
                        QSR_Obser_5.qsrobsdisp.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("1717")){
                        QSR_Obser_5.qsr_obser_disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("1818")){
                        QSR_Obser_5.qsr_obser_disp2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("1919")){
                        AIB_Cus_Info_2.disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2020")){
                        AIB_Cus_Info_2.disp2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2121")){
                        AIB_Cus_Pest_3.disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2222")){
                        Log.e("RRRR","e3");
                        AIB_Ext_Obser_5_1.aib_ex1_disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2223")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2224")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp3.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2225")){

                        Log.e("RRRR","q3");
                        AIB_Ext_Obser_5_1.aib_ex1_disp4.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2226")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp5.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2227")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp6.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2228")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp7.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2229")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp8.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2230")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp9.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2231")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp10.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2232")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp11.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2233")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp12.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2235")){
                        AIB_Interl_Obser_6.sirobsdisplay.setText(line_quoted);
                    }

                }else {

                    Log.e("AAEE","enter3");

                    if (status_check.equalsIgnoreCase("111")) {
                        SIR_Cus_2.sircustxt1.setText(line_quoted);
                    }else if(status_check.equalsIgnoreCase("222")){
                        SIR_Cus_2.sircustxt2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("666")){
                        SIR_Obser_3.sirobsdisplay.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("777")){
                        PMCA_Cus_Info_2.pestcusdisp2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("888")){
                        PMCA_Cus_Info_2.pestcusdisp3.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("999")){
                        PMCA_Obser_3.pestobsdisp.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("102")){
                        IPM_Title_1.ipm_title_txt_disply2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("103")){
                        IPM_Cus_Info_2.ipm_cusinfo_disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("104")){
                        IPM_Cus_Info_2.ipm_cusinfo_disp2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("108")){
                        IPM_Facility_4.disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("110")){
                        IPM_Obser_5.ipm_obser_txt_disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("113")){
                        QSR_Title_1.qsr_title_txt_disply2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("113")){
                        QSR_Title_1.qsr_title_txt_disply2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("114")){
                        QSR_Cus_Info_2.qsr_cusinfo_disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("115")){
                        QSR_Cus_Info_2.qsr_cusinfo_disp2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("117")){
                        QSR_Facility_4.dis2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("1616")){
                        QSR_Obser_5.qsrobsdisp.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("1717")){
                        QSR_Obser_5.qsr_obser_disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("1818")){
                        QSR_Obser_5.qsr_obser_disp2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("1919")){
                        AIB_Cus_Info_2.disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2020")){
                        AIB_Cus_Info_2.disp2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2121")){
                        AIB_Cus_Pest_3.disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2222")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp1.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2223")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp2.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2224")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp3.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2225")){
                        Log.e("RRRR","q10");
                        AIB_Ext_Obser_5_1.aib_ex1_disp4.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2226")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp5.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2227")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp6.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2228")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp7.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2229")){
                        AIB_Ext_Obser_5_1.aib_ex1_disp8.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2230")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp9.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2231")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp10.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2232")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp11.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2233")){
                        AIB_Ext_Obser_5_2_1.aib_ex2_disp12.setText(line_quoted);
                    }
                    else if(status_check.equalsIgnoreCase("2235")){
                        AIB_Interl_Obser_6.sirobsdisplay.setText(line_quoted);
                    }

                }


            }



//            if(status_check.equalsIgnoreCase("222")) {
//                SIR_Cus_2.sircustxt2.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("666")) {
//                SIR_Obser_3.sirobsdisplay.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("777")) {
//                PMCA_Cus_Info_2.pestcusdisp2.setText(line_quoted.substring(0, a - 1));
//            } if(status_check.equalsIgnoreCase("888")) {
//                PMCA_Cus_Info_2.pestcusdisp3.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("999")) {
//                PMCA_Obser_3.pestobsdisp.setText(line_quoted.substring(0, a - 1));
//            }
//
//            if(status_check.equalsIgnoreCase("102")) {
//                IPM_Title_1.ipm_title_txt_disply2.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("103")) {
//                IPM_Cus_Info_2.ipm_cusinfo_disp1.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("104")) {
//                IPM_Cus_Info_2.ipm_cusinfo_disp2.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("108")) {
//                IPM_Facility_4.disp1.setText(line_quoted.substring(0, a - 1));
//            }
//
//            if(status_check.equalsIgnoreCase("110")) {
//                IPM_Obser_5.ipm_obser_txt_disp1.setText(line_quoted.substring(0, a - 1));
//            }
//
//            if(status_check.equalsIgnoreCase("113")) {
//                QSR_Title_1.qsr_title_txt_disply2.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("114")) {
//                QSR_Cus_Info_2.qsr_cusinfo_disp1.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("115")) {
//                QSR_Cus_Info_2.qsr_cusinfo_disp2.setText(line_quoted.substring(0, a - 1));
//            }
//
//            if(status_check.equalsIgnoreCase("117")) {
//                QSR_Facility_4.dis2.setText(line_quoted.substring(0, a - 1));
//            }
//
//            if(status_check.equalsIgnoreCase("1616")) {
//                QSR_Obser_5.qsrobsdisp.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("1717")) {
//                QSR_Obser_5.qsr_obser_disp1.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("1818")) {
//                QSR_Obser_5.qsr_obser_disp2.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("1919")) {
//                AIB_Cus_Info_2.disp1.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("2020")) {
//                AIB_Cus_Info_2.disp2.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("2121")) {
//                AIB_Cus_Pest_3.disp1.setText(line_quoted.substring(0, a - 1));
//            }
//      if(status_check.equalsIgnoreCase("2222")) {
//                AIB_Ext_Obser_5_1.aib_ex1_disp1.setText(line_quoted.substring(0, a - 1));
//            }
// if(status_check.equalsIgnoreCase("2223")) {
//     AIB_Ext_Obser_5_1.aib_ex1_disp2.setText(line_quoted.substring(0, a - 1));
//            }
// if(status_check.equalsIgnoreCase("2224")) {
//     AIB_Ext_Obser_5_1.aib_ex1_disp3.setText(line_quoted.substring(0, a - 1));
//            }
// if(status_check.equalsIgnoreCase("2225")) {
//     AIB_Ext_Obser_5_1.aib_ex1_disp4.setText(line_quoted.substring(0, a - 1));
//            }
// if(status_check.equalsIgnoreCase("2226")) {
//     AIB_Ext_Obser_5_1.aib_ex1_disp5.setText(line_quoted.substring(0, a - 1));
//            }
// if(status_check.equalsIgnoreCase("2227")) {
//     AIB_Ext_Obser_5_1.aib_ex1_disp6.setText(line_quoted.substring(0, a - 1));
//            }
// if(status_check.equalsIgnoreCase("2228")) {
//     AIB_Ext_Obser_5_1.aib_ex1_disp7.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("2229")) {
//                AIB_Ext_Obser_5_1.aib_ex1_disp8.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("2230")) {
//                AIB_Ext_Obser_5_2_1.aib_ex2_disp9.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("2231")) {
//                AIB_Ext_Obser_5_2_1.aib_ex2_disp10.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("2232")) {
//                AIB_Ext_Obser_5_2_1.aib_ex2_disp11.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("2233")) {
//                AIB_Ext_Obser_5_2_1.aib_ex2_disp12.setText(line_quoted.substring(0, a - 1));
//            }
//            if(status_check.equalsIgnoreCase("2235")) {
//                AIB_Interl_Obser_6.sirobsdisplay.setText(line_quoted.substring(0, a - 1));
//            }
//




    }






}
