package com.rentokil.pci.rauditor.MSOT;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_A;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_B;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_C;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_D;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_E;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_F;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_G;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_H;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_I;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_J;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_K;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_L;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_M;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_N;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_O;
import com.rentokil.pci.rauditor.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    Context mContext;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, genre;
        public  LinearLayout lay_size;
        ImageView imageView;




        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);

            db = new DatabaseHelper(mContext);
            sd = db.getReadableDatabase();
            cv1 = new ContentValues();
            cv2 = new ContentValues();

            lay_size = (LinearLayout) view.findViewById(R.id.lay_size);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);


        }
    }




    public MoviesAdapter(Context mContext,List<Movie> moviesList) {
        this.mContext = mContext;
        this.moviesList = moviesList;
    }

    @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.msot_size, parent, false);

        final MyViewHolder vh = new MyViewHolder(itemView);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("YYYT","pos = "+vh.getAdapterPosition());

                if(vh.getAdapterPosition()==0){
                    Intent intent = new Intent(v.getContext(), Page_A.class);
                    v.getContext().startActivity(intent);
                }

            }
        });

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Movie movie = moviesList.get(position);

        Log.e("GGGG","movie ="+movie);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());

        holder.imageView.setImageDrawable(mContext.getResources().getDrawable(movie.getImage()));


        Log.e("NNNNN","p1"+position);

//        Cursor c5;
//        c5 = sd.rawQuery("Select * from " + db.MSOT_TABLE, null);
//        c5.moveToFirst();
//
//        Log.e("WWWF","msot status"+c5.getString(c5.getColumnIndex(db.STATUS)));
//
//        String get_msot_status = c5.getString(c5.getColumnIndex(db.STATUS));
//
//        if(get_msot_status.equalsIgnoreCase("99")) {

//            Cursor c6;
//            c6 = sd.rawQuery("Select * from " + db.ACTIVITIES_PAGE_1, null);
//            c6.moveToFirst();
//
//
//
//            String get_page_1 = c6.getString(c6.getColumnIndex(db.STATUS_1));
//
//        Log.e("RRRRQ", "status = "+c6.getString(c6.getColumnIndex(db.STATUS_1)));
//
//            if(get_page_1.equalsIgnoreCase("cb1")){
//
//                Log.e("AAAAAQ", "Entry1");
//                         if(position==5){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==6){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==7){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==8){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==9){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==10){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==11){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==12){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==13){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==14){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }
//
//
//            }
//
//            else if(get_page_1.equalsIgnoreCase("undocb1")){
//
//                Log.e("AAAAAQ", "ELSE");
//                holder.lay_size.setVisibility(View.VISIBLE);
//            }








//        if(get_msot_status.equalsIgnoreCase("99")) {
//
//            for (int i = 0; i < List_Adapter.modelArrayList.size(); i++) {
//                if (List_Adapter.modelArrayList.get(i).getSelected()) {
//
//
//                    Log.e("LLLLL", "TT = " + List_Adapter.modelArrayList.get(i));
//                    Log.e("UUUUU", "TT = " + i);
//
//                    if (i == 0) {
//
//                        Log.e("KKKT", "0");
//
//                        Toast.makeText(mContext, "Welcome 0", Toast.LENGTH_SHORT).show();
//
//                        if(position==5){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==6){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==7){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==8){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==9){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==10){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==11){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==12){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==13){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==14){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }
//
//
//
//
//
//                    } else if (i == 1) {
//
//
//                        Log.e("KKKT", "1");
//                        if(position==6){
//
//                            holder.lay_size.setVisibility(View.VISIBLE);
//                            holder.lay_size.setBackgroundResource(R.color.thickgreen);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//                        }
//                        if(position==7){
//
//                            holder.lay_size.setVisibility(View.VISIBLE);
//                            holder.lay_size.setBackgroundResource(R.color.thickgreen);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//                        }
//
//                        if(position==8){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==9){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==10){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==11){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==12){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==13){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }   if(position==14){
//
//                            holder.lay_size.setVisibility(View.GONE);
//                            holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//
//                        }
//
//                        Toast.makeText(mContext, "Welcome 1", Toast.LENGTH_SHORT).show();
//
//                    } else if (i == 2) {
//
//                        Toast.makeText(mContext, "Welcome 2", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//            }
//
//        }


/////////////////////////INCOMPLETE///////////////////////////////
//        if(get_msot_status.equalsIgnoreCase("1")) {
//
//
//            if(position == 0)
//            {
//                holder.lay_size.setVisibility(View.GONE);
//                Log.e("HHHH","e1");
//                holder.lay_size.setBackgroundResource(R.color.thickgreen);
//                holder.lay_size.setEnabled(false);
//
//
//            }
//
//            else
//            {
//                holder.lay_size.setEnabled(true);
//
//                Log.e("HHHH","e3");
//                holder.lay_size.setBackgroundResource(R.color.white);
////                holder.lay_size.setBackgroundResource(R.color.title_background);
//            }
//
//        }
//        else if(get_msot_status.equalsIgnoreCase("99")) {
//
//
//
//            holder.lay_size.setEnabled(true);
//        }
//
//
//        if(get_msot_status.equalsIgnoreCase("2")) {
//
//
//            if(position == 0)
//            {
//                holder.lay_size.setVisibility(View.GONE);
//                Log.e("HHHH","e1");
//                holder.lay_size.setBackgroundResource(R.color.thickgreen);
//                holder.lay_size.setEnabled(false);
//            }
//            else if(position == 1)
//            {
//
//                Log.e("HHHH","e2");
//                holder.lay_size.setBackgroundResource(R.color.thickgreen);
//                holder.lay_size.setEnabled(false);
//
//            }
//            else
//            {
//                holder.lay_size.setEnabled(true);
//                Log.e("HHHH","e3");
//                holder.lay_size.setBackgroundResource(R.color.white);
//                //holder.rootView.setBackgroundColor(Color.WHITE);
////                holder.lay_size.setBackgroundResource(R.color.title_background);
//            }
//
//
//
//        }
//        else if(get_msot_status.equalsIgnoreCase("99")) {
//
//            holder.lay_size.setEnabled(true);
//        }
//
//
//
//        if(get_msot_status.equalsIgnoreCase("3")) {
//
//
//            if(position == 0)
//            {
//                holder.lay_size.setVisibility(View.GONE);
//                holder.lay_size.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
//                Log.e("HHHH","e1");
//                holder.lay_size.setBackgroundResource(R.color.thickgreen);
//                holder.lay_size.setEnabled(false);
//            }
//            else if(position == 1)
//            {
//
//
//                Log.e("HHHH","e2");
//                holder.lay_size.setBackgroundResource(R.color.thickgreen);
//                holder.lay_size.setEnabled(false);
//            }
//            else if(position == 2)
//            {
//
//
//                Log.e("HHHH","e2");
//                holder.lay_size.setBackgroundResource(R.color.thickgreen);
//                holder.lay_size.setEnabled(false);
//            }
//            else
//            {
//                holder.lay_size.setEnabled(true);
//                Log.e("HHHH","e3");
//                holder.lay_size.setBackgroundResource(R.color.white);
//                //holder.rootView.setBackgroundColor(Color.WHITE);
////                holder.lay_size.setBackgroundResource(R.color.title_background);
//            }
//
//
//
//        }
//        else if(get_msot_status.equalsIgnoreCase("99")) {
//
//            holder.lay_size.setEnabled(true);
//        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        holder.lay_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                holder.title.setText(moviesList.get(position).getTitle());


                String get_audit_name=moviesList.get(position).getTitle();





                Log.e("YYYT","HolderPOS = "+(moviesList.get(position).getTitle()));

                if(get_audit_name.equalsIgnoreCase("INCIDENT REPORTING")){

                    Intent intent = new Intent(v.getContext(), Page_A.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }

                if(get_audit_name.equalsIgnoreCase("SINA CARD")){

                    Intent intent = new Intent(v.getContext(), Page_B.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }
                if(get_audit_name.equalsIgnoreCase("ZERO TOLERANCE POLICY")){

                    Intent intent = new Intent(v.getContext(), Page_C.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);


                }

                if(get_audit_name.equalsIgnoreCase("SHE GOLDEN RULES")){

                    Intent intent = new Intent(v.getContext(), Page_D.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);


                }

                if(get_audit_name.equalsIgnoreCase("COMPLIANCE TO BASIC SAFETY RULES")){

                    Intent intent = new Intent(v.getContext(), Page_K.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);


                }
                if(get_audit_name.equalsIgnoreCase("SHE GOLDEN RULES TRAINING AT THE BRANCH")){

                    Intent intent = new Intent(v.getContext(), Page_L.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);


                }
                if(get_audit_name.equalsIgnoreCase("SRA IMPLEMENTATION AT THE BRANCH")){

                    Intent intent = new Intent(v.getContext(), Page_M.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);


                }
                if(get_audit_name.equalsIgnoreCase("PERSONAL PROTECTIVE EQUIPMENT")){

                    Intent intent = new Intent(v.getContext(), Page_N.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }

                if(get_audit_name.equalsIgnoreCase("DRIVING & MOTORBIKE RIDING SAFETY")){

                    Intent intent = new Intent(v.getContext(), Page_O.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);


                }

                if(get_audit_name.equalsIgnoreCase("5.WORKING AT HEIGHT")){

                    Intent intent = new Intent(v.getContext(), Page_E.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }
                if(get_audit_name.equalsIgnoreCase("6.SLIP, TRIP & FALL, MOVING VEHICLE & OTHER RISKS")){

                    Intent intent = new Intent(v.getContext(), Page_F.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }
                if(get_audit_name.equalsIgnoreCase("7.DRIVING & PEDESTRIAN SAFETY")){

                    Intent intent = new Intent(v.getContext(), Page_G.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }
                if(get_audit_name.equalsIgnoreCase("8.ELECTRICAL SAFETY")){

                    Intent intent = new Intent(v.getContext(), Page_H.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }
                if(get_audit_name.equalsIgnoreCase("9.FUMIGATION SAFETY")){

                    Intent intent = new Intent(v.getContext(), Page_I.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }
                if(get_audit_name.equalsIgnoreCase("10.EMPLOYEE INDUCTION & CONSULTATION")){

                    Intent intent = new Intent(v.getContext(), Page_J.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }
                if(get_audit_name.equalsIgnoreCase("11.COMPLIANCE TO BASIC SAFETY RULES")){

                    Intent intent = new Intent(v.getContext(), Page_K.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }
                if(get_audit_name.equalsIgnoreCase("12.SHE GOLDEN RULES TRAINING AT THE BRANCH")){

                    Intent intent = new Intent(v.getContext(), Page_L.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }
                if(get_audit_name.equalsIgnoreCase("13.SRA IMPLEMENTATION AT THE BRANCH")){

                    Intent intent = new Intent(v.getContext(), Page_M.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }
                if(get_audit_name.equalsIgnoreCase("14.PERSONAL PROTECTIVE EQUIPMENT")){

                    Intent intent = new Intent(v.getContext(), Page_N.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }
                if(get_audit_name.equalsIgnoreCase("15.DRIVING & MOTORBIKE RIDING SAFETY")){

                    Intent intent = new Intent(v.getContext(), Page_O.class);
                    intent.putExtra("page_name",""+position);
                    v.getContext().startActivity(intent);

                }





            }
        });

    }

//        private void HolderPos(final MoviesAdapter.MyViewHolder holder,final int position)
//        {
//            holder.title.setText(moviesList.get(position).getTitle());
//
//            String get_audit_name=moviesList.get(position).getTitle();
//
//
//            Log.e("YYYT","HolderPOS = "+(moviesList.get(position).getTitle()));
//
//            if(get_audit_name.equalsIgnoreCase("1.INCIDENT REPORTING")){
//
//
//
//            }
//
//
//        }

        @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
