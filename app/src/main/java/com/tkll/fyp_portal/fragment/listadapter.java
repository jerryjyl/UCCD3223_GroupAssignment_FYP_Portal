package com.tkll.fyp_portal.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.tkll.fyp_portal.R;

public class listadapter extends FirebaseRecyclerAdapter<listmodel, listadapter.ViewHolder> {

    public listadapter(@NonNull FirebaseRecyclerOptions<listmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull listmodel model) {
        holder.areaOfStudyText.setText(model.getAreaOfStudy());
        holder.moderatorNameText.setText(model.getModeratorName());
        holder.programmeText.setText(model.getProgramme());
        holder.projectTitleText.setText(model.getProjectTitle());
        holder.projectTypeText.setText(model.getProjectType());
        holder.studentNameText.setText(model.getStudentName());
        holder.studentNoText.setText(model.getStudentNo());
        holder.supervisorNameText.setText(model.getSupervisorName());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView areaOfStudyText, moderatorNameText, programmeText, projectTitleText, projectTypeText, studentNameText, studentNoText, supervisorNameText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            areaOfStudyText = itemView.findViewById(R.id.AreaOfStudyText);
            moderatorNameText = itemView.findViewById(R.id.ModeratorNameText);
            programmeText = itemView.findViewById(R.id.ProgrammeText);
            projectTitleText = itemView.findViewById(R.id.ProjectTitleText);
            projectTypeText = itemView.findViewById(R.id.ProjectTypeText);
            studentNameText = itemView.findViewById(R.id.StudentNameText);
            studentNoText = itemView.findViewById(R.id.StudentNoText);
            supervisorNameText = itemView.findViewById(R.id.SupervisorNameText);
        }
    }

}