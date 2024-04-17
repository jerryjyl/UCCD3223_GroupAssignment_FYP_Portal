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

public class MainAdapterOfferedTitles extends RecyclerView.Adapter<MainAdapterOfferedTitles.myViewHolder> {
    Context context;
    ArrayList<MainModel> list;

    public MainAdapterOfferedTitles(Context context, ArrayList<MainModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_item_offeredtitles,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  static class myViewHolder extends RecyclerView.ViewHolder{
        TextView noofstudent,skillrequired,expectedoutcome,
                description, projectTitle, supervisorName,
                areaOfStudy,equipment,suitablefor;
        public myViewHolder(@NonNull View itemView){
            super(itemView);
            supervisorName=(TextView) itemView.findViewById(R.id.SupervisorText);
            projectTitle=(TextView) itemView.findViewById(R.id.ProjectTitleText);
            description=(TextView) itemView.findViewById(R.id.DescriptionText);
            expectedoutcome=(TextView) itemView.findViewById(R.id.ExpectedOutcomeText);
            areaOfStudy=(TextView) itemView.findViewById(R.id.AreaOfStudyText);
            skillrequired=(TextView) itemView.findViewById(R.id.SkillRequiredText);
            noofstudent=(TextView) itemView.findViewById(R.id.NoOfStudentText);
            equipment=(TextView) itemView.findViewById(R.id.EquipmentText);
            suitablefor=(TextView) itemView.findViewById(R.id.SuitableForText);
        }
    }



    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        MainModel mainModel = list.get(position);
        if (mainModel != null) {

            holder.supervisorName.setText(mainModel.getSupervisor());
            holder.projectTitle.setText(mainModel.getProjectTitle());
            holder.description.setText(mainModel.getDescription());
            holder.expectedoutcome.setText(mainModel.getExpectedOutcome());
            holder.areaOfStudy.setText(mainModel.getAreaOfStudy());
            holder.skillrequired.setText(mainModel.getSkillsRequired());
            holder.noofstudent.setText(mainModel.getNoStudents());
            holder.equipment.setText(mainModel.getEquipment());
            holder.suitablefor.setText(mainModel.getSuitableFor());
        }
    }


}



