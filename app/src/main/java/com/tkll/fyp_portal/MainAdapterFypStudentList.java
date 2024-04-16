package com.tkll.fyp_portal;

import android.content.Context;
import android.view.ContentInfo;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class MainAdapterFypStudentList extends RecyclerView.Adapter<MainAdapterFypStudentList.myViewHolder> {
    Context context;
    ArrayList<MainModel3> list;

    public MainAdapterFypStudentList(Context context, ArrayList<MainModel3> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_item_fypstudentlist,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
   public  static class myViewHolder extends RecyclerView.ViewHolder{
        TextView studentName, projectTitle, supervisorName,projectType, areaOfStudy,programme,moderatorName;
       public myViewHolder(@NonNull View itemView){
           super(itemView);
           studentName = (TextView) itemView.findViewById(R.id.StudentText);
           projectTitle=(TextView) itemView.findViewById(R.id.ProjectTitleText);
           supervisorName=(TextView) itemView.findViewById(R.id.SupervisorText);
           projectType=(TextView) itemView.findViewById(R.id.ProjectTypeText);
           areaOfStudy=(TextView) itemView.findViewById(R.id.AreaOfStudyText);
           programme=(TextView) itemView.findViewById(R.id.ProgrammeText);
           moderatorName=(TextView) itemView.findViewById(R.id.ModeratorNameText);
       }
   }



    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        MainModel3 mainModel3 = list.get(position);
        if (mainModel3 != null) {
            holder.studentName.setText(mainModel3.getStudentName());
            holder.projectTitle.setText(mainModel3.getProjectTitle());
            holder.supervisorName.setText(mainModel3.getSupervisorName());
            holder.projectType.setText(mainModel3.getProjectType());
            holder.areaOfStudy.setText(mainModel3.getAreaOfStudy());
            holder.programme.setText(mainModel3.getProgramme());
            holder.moderatorName.setText(mainModel3.getModeratorName());
        }
    }


}



