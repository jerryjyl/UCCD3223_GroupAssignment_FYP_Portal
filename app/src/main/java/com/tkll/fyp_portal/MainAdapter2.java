package com.tkll.fyp_portal;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter2 extends FirebaseRecyclerAdapter<MainModel2,MainAdapter2.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter2(@NonNull FirebaseRecyclerOptions<MainModel2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, final int position, @NonNull MainModel2 model) {
        holder.studentName.setText(model.getStudentName());
        holder.projectTitle.setText(model.getProjectTitle());
        holder.supervisorName.setText(model.getSupervisorName());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.studentName.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup2))
                        .setExpanded(true,1200)
                        .create();
                View view=dialogPlus.getHolderView();

                EditText supervisorname=view.findViewById(R.id.txtSupervisorName);
                EditText studentname=view.findViewById(R.id.txtStudentName);
                EditText projecttitle=view.findViewById(R.id.txtProjectTitle);
                EditText programme=view.findViewById(R.id.txtProgramme);
                EditText areaofstudy=view.findViewById(R.id.txtAreaOfStudy);
                EditText moderatorname=view.findViewById(R.id.txtModeratorName);
                EditText projecttype=view.findViewById(R.id.txtProjectType);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                supervisorname.setText(model.getSupervisorName());
                projecttitle.setText(model.getProjectTitle());
                studentname.setText(model.getStudentName());
                programme.setText(model.getProgramme());
                areaofstudy.setText(model.getAreaOfStudy());
                moderatorname.setText(model.getModeratorName());
                projecttype.setText(model.getProjectType());

                dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("SupervisorName",supervisorname.getText().toString());
                        map.put("ProjectTitle",projecttitle.getText().toString());
                        map.put("Programme",programme.getText().toString());
                        map.put("StudentName",studentname.getText().toString());
                        map.put("AreaOfStudy",areaofstudy.getText().toString());
                        map.put("ProjectType",projecttype.getText().toString());
                        map.put("ModeratorName",moderatorname.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("IIPSPW Student List")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.studentName.getContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @org.jetbrains.annotations.NotNull Exception e) {
                                        Toast.makeText(holder.studentName.getContext(),"Error While Updating",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }


                });


            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.studentName.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Delete data cannot undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("IIPSPW Student List")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.studentName.getContext(),"Cancelled.",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item2,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView studentName,projectTitle,supervisorName;

        Button btnEdit,btnDelete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = (TextView) itemView.findViewById(R.id.StudentText);
            projectTitle=(TextView) itemView.findViewById(R.id.ProjectTitleText);
            supervisorName=(TextView) itemView.findViewById(R.id.SupervisorText);

            btnEdit =(Button) itemView.findViewById(R.id.btnEdit);
            btnDelete=(Button) itemView.findViewById(R.id.btnDelete);

        }
    }
}

